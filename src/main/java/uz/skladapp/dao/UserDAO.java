package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Company;
import uz.skladapp.model.Role;
import uz.skladapp.model.User;
import uz.skladapp.model.repositories.CompanyRepository;
import uz.skladapp.model.repositories.RoleRepository;
import uz.skladapp.model.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserDAO {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RoleRepository roleRepository;


    public Iterable<User> getUserList() {
        return userRepository.findAll();
    }


    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void saveUser(String string) throws Exception {
        boolean isSupervisor = false;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(string);
        Optional<User> supervisor = null;

        if (!jsonNode.get("supervisor").asText().equals("null")) {
            Long u_id = Long.valueOf(jsonNode.get("supervisor").toString());
            supervisor = userRepository.findById(u_id);
            isSupervisor = true;
        }
        Long c_id = Long.valueOf(jsonNode.get("company_ID").toString());
        Optional<Company> company = companyRepository.findById(c_id);
        Long r_id = Long.valueOf(jsonNode.get("role_ID").toString());
        Optional<Role> role = roleRepository.findById(r_id);

        User newUser = new User();
        if (isSupervisor) {
            newUser.setSupervisor(supervisor.get());
        }

        newUser.setFirst_name(jsonNode.get("first_name").asText());
        newUser.setLast_name(jsonNode.get("last_name").asText());
        newUser.setEmail(jsonNode.get("email").asText());
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
        boolean isSupervisor = false;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Optional<User> supervisor = null;

        if (!json.get("supervisor").asText().equals("null")) {
            Long u_id = Long.valueOf(json.get("supervisor").toString());
            supervisor = userRepository.findById(u_id);
            isSupervisor = true;
        }
        Long c_id = Long.valueOf(json.get("company_ID").toString());
        Optional<Company> company = companyRepository.findById(c_id);
        Long r_id = Long.valueOf(json.get("role_ID").toString());
        Optional<Role> role = roleRepository.findById(r_id);
        boolean finalIsSupervisor = isSupervisor;
        Optional<User> finalSupervisor = supervisor;
        return userRepository.findById(id)
                .map(user -> {
                    if (finalIsSupervisor) {
                        user.setSupervisor(finalSupervisor.get());
                    }
                    user.setFirst_name(json.get("first_name").asText());
                    user.setLast_name(json.get("last_name").asText());
                    user.setEmail(json.get("email").asText());
                    user.setPassword(json.get("password").asText());
                    user.setCompany_ID(company.get());
                    user.setRole_ID(role.get());
                    user.setUser_phone(json.get("user_phone").asText());
                    user.setUsername(json.get("username").asText());
                    return userRepository.save(user);
                })
                .get();
    }
}
