package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Permission;
import uz.skladapp.model.Role;
import uz.skladapp.model.RolePermission;
import uz.skladapp.model.repositories.PermissionRepository;
import uz.skladapp.model.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RolePermissionsDAO {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    public List<Permission> getList(Long id) {
        Optional<Role> role = roleRepository.findById(Long.valueOf(id));
        List<Permission> permissions = new ArrayList<>();
        for (RolePermission rolePermission : role.get().getPermissions()) {
            permissions.add(rolePermission.getPermission());

        }
        return permissions;
    }

    public void addPermission(String ids) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newJson = mapper.readTree(ids);
        Long id_r = Long.valueOf(newJson.get("role_ID").toString());
        Long id_p = Long.valueOf(newJson.get("permission_ID").toString());
        Optional<Role> role = roleRepository.findById(id_r);
        Optional<Permission> permission = permissionRepository.findById(id_p);
        role.get().addPermission(permission.get());
        permissionRepository.save(permission.get());
        System.out.println(permission.get().getPermission_ID().toString());

    }
}
