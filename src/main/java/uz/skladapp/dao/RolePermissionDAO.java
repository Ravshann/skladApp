package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Permission;
import uz.skladapp.model.pure_models.Role;
import uz.skladapp.model.pure_models.RolePermission;
import uz.skladapp.model.repositories.PermissionRepository;
import uz.skladapp.model.repositories.RoleRepository;
import uz.skladapp.model.special_models.PermissionRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RolePermissionDAO {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleDAO roleDAO;


    public List<PermissionRaw> getList(Long id) {
        Optional<Role> role = roleRepository.findById(Long.valueOf(id));
        List<PermissionRaw> permissions = new ArrayList<>();
        for (RolePermission rolePermission : role.get().getPermissions()) {
            permissions.add(new PermissionRaw(
                    rolePermission.getPermission().getPermission_ID(),
                    rolePermission.getPermission().getPermission_name(),
                    rolePermission.getPermission().getPermission_description()));

        }
        return permissions;
    }

    public void addPermission(String ids) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newJson = mapper.readTree(ids);
        Long id_r = Long.valueOf(newJson.get("role_ID").toString());
        Long id_p = Long.valueOf(newJson.get("permission_ID").toString());
        Role role = roleRepository.findById(id_r).get();
        Permission permission = permissionRepository.findById(id_p).get();
        roleDAO.addPermission(permission,role);
        permissionRepository.save(permission);
    }

    public void delete(String object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newJson = mapper.readTree(object);
        Long id_r = Long.valueOf(newJson.get("role_ID").toString());
        Long id_p = Long.valueOf(newJson.get("permission_ID").toString());
        Role role = roleRepository.findById(id_r).get();
        Permission permission = permissionRepository.findById(id_p).get();
        roleDAO.removePermission(permission,role);
        permissionRepository.save(permission);
    }
}
