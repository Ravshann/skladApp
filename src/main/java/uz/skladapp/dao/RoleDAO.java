package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.skladapp.model.Attribute;
import uz.skladapp.model.Role;
import uz.skladapp.model.repositories.RoleRepository;

import java.util.Optional;

@Component
public class RoleDAO {
    @Autowired
    private RoleRepository repository;



    public Iterable<Role> getRolesList() {
        return repository.findAll();
    }

    public Optional<Role> getRoleByID(Long id){
        Optional<Role> role = repository.findById(id);
        return role;
    }

    public void saveRole(String newRoleText) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newRoleJson = mapper.readTree(newRoleText);
        Role newRole = new Role();
        newRole.setRole_name(newRoleJson.get("role_name").asText());
        repository.save(newRole);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Role update(String string, Long id) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(role -> {
                    role.setRole_name(json.get("role_name").asText());
                    return repository.save(role);
                })
                .get();
    }
}
