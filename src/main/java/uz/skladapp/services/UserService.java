package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.*;
import uz.skladapp.DTO.PermissionDTO;
import uz.skladapp.DTO.UserRolePermissions;
import uz.skladapp.repositories.CompanyRepository;
import uz.skladapp.repositories.PermissionRepository;
import uz.skladapp.repositories.RoleRepository;
import uz.skladapp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    public Iterable<User> getUserList() {
        return userRepository.findAllButAdmin();
    }

    public Iterable<User> getAllDepartmentManagers() {
        return userRepository.findAllDepartmentManagers();
    }

    public Iterable<User> getAllStorageManagers() {
        return userRepository.findAllStorageManagers();
    }


    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public UserRolePermissions getUserPermissions(Long id) {
        Role userRole = userRepository.findById(id).get().getRole_ID();
        UserRolePermissions userRolePermissions = new UserRolePermissions();
        userRolePermissions.setUser_ID(id);
        userRolePermissions.setRole_ID(userRole.getRole_ID());
        userRolePermissions.setRole_name(userRole.getRole_name());
        userRolePermissions.setPermissions(new ArrayList<>());
        for (RolePermission rolePermission : userRole.getPermissions()) {
            PermissionDTO permissionDTO = new PermissionDTO();
            Permission permission = permissionRepository.findById(rolePermission.getPermission_ID()).get();
            permissionDTO.setPermission_name(permission.getPermission_name());
            permissionDTO.setPermission_ID(permission.getPermission_ID());
            permissionDTO.setPermission_description(permission.getPermission_description());
            userRolePermissions.getPermissions().add(permissionDTO);
        }
        return userRolePermissions;
    }

    public void saveUser(String string) throws Exception {
        boolean hasSupervisor = false;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(string);
        Optional<User> supervisor = null;

        if (!jsonNode.get("supervisor").asText().isEmpty()) {
            Long u_id = jsonNode.get("supervisor").asLong();
            supervisor = userRepository.findById(u_id);
            hasSupervisor = true;
        }
        Long c_id = jsonNode.get("company_ID").asLong();
        Optional<Company> company = companyRepository.findById(c_id);
        Long r_id = jsonNode.get("role_ID").asLong();
        Optional<Role> role = roleRepository.findById(r_id);

        User newUser = new User();

        if (hasSupervisor) {
            newUser.setSupervisor(supervisor.get());
        }

        newUser.setFirst_name(jsonNode.get("first_name").asText());
        newUser.setLast_name(jsonNode.get("last_name").asText());

        newUser.setPassword(jsonNode.get("password").asText());
        newUser.setCompany_ID(company.get());
        newUser.setRole_ID(role.get());
        newUser.setUser_phone(jsonNode.get("user_phone").asText());
        newUser.setUsername(jsonNode.get("username").asText());
        userRepository.save(newUser);

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User update(String string, Long id) throws Exception {
        boolean hasSupervisor = false;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Optional<User> supervisor = null;

        if (!json.get("supervisor").asText().isEmpty()) {
            Long u_id = json.get("supervisor").asLong();
            supervisor = userRepository.findById(u_id);
            hasSupervisor = true;
        }
        Long c_id = json.get("company_ID").asLong();
        Optional<Company> company = companyRepository.findById(c_id);
        Long r_id = json.get("role_ID").asLong();
        Optional<Role> role = roleRepository.findById(r_id);
        boolean finalIsSupervisor = hasSupervisor;
        Optional<User> finalSupervisor = supervisor;
        return userRepository.findById(id)
                .map(user -> {
                    if (finalIsSupervisor) {
                        user.setSupervisor(finalSupervisor.get());
                    }
                    user.setFirst_name(json.get("first_name").asText());
                    user.setLast_name(json.get("last_name").asText());

                    user.setPassword(json.get("password").asText());
                    user.setCompany_ID(company.get());
                    user.setRole_ID(role.get());
                    user.setUser_phone(json.get("user_phone").asText());
                    user.setUsername(json.get("username").asText());
                    return userRepository.save(user);
                })
                .get();
    }


    public void addCompany(Company company, User user) {
        UserCompany association = new UserCompany();
        association.setCompany(company);
        association.setUser(user);
        association.setCompany_ID(company.getCompany_ID());
        association.setUser_ID(user.getUser_ID());


        if (user.getCompanies() == null)
            user.setCompanies(new ArrayList<>());

        user.getCompanies().add(association);

        company.getUsers().add(association);
    }

    public void removeCompany(Company company, User user) {
        for (Iterator<UserCompany> iterator = user.getCompanies().iterator(); iterator.hasNext(); ) {
            UserCompany userCompany = iterator.next();

            if (userCompany.getCompany().equals(user) && userCompany.getCompany().equals(company)) {
                iterator.remove();
                userCompany.getCompany().getUsers().remove(userCompany);
                userCompany.setUser(null);
                userCompany.setCompany(null);
            }
        }
    }
}
