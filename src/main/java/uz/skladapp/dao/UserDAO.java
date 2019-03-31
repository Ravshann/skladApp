package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    public void saveUser(String newUserText) throws Exception {
        boolean isSupervisor = false;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(newUserText);
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
}
