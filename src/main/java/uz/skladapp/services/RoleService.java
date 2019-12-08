package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Permission;
import uz.skladapp.model.pure_models.Role;
import uz.skladapp.model.pure_models.RolePermission;
import uz.skladapp.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;


    public Iterable<Role> getRolesList() {
        return repository.findAllButAdmin();
    }

    public Optional<Role> getRoleByID(Long id) {
        Optional<Role> role = repository.findById(id);
        return role;
    }

    public void saveRole(String newRoleText) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newRoleJson = mapper.readTree(newRoleText);
        Role newRole = new Role();
        newRole.setRole_name(newRoleJson.get("role_name").asText());
        repository.save(newRole);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Role update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(role -> {
                    role.setRole_name(json.get("role_name").asText());
                    return repository.save(role);
                })
                .get();
    }


    // Create an association object for the relationship and set its data.
    public void addPermission(Permission permission, Role role) {
        RolePermission association = new RolePermission();
        association.setPermission(permission);
        association.setRole(role);
        association.setPermission_ID(permission.getPermission_ID());
        association.setRole_ID(role.getRole_ID());


        if (role.getPermissions() == null)
            role.setPermissions(new ArrayList<>());

        role.getPermissions().add(association);
        // Also add the association object to the other class
        permission.getRoles().add(association);

    }

    public void removePermission(Permission permission, Role role) {
        for (Iterator<RolePermission> iterator = role.getPermissions().iterator(); iterator.hasNext(); ) {
            RolePermission rolePermission = iterator.next();

            if (rolePermission.getRole().equals(role) && rolePermission.getPermission().equals(permission)) {
                iterator.remove();
                rolePermission.getPermission().getRoles().remove(rolePermission);
                rolePermission.setRole(null);
                rolePermission.setPermission(null);
            }
        }
    }
}
