package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.RolePermissionsDAO;
import uz.skladapp.model.Permission;

import java.util.List;

@RestController
@RequestMapping("/role_permissions")
public class RolePermissionController {
    @Autowired
    private RolePermissionsDAO dao;


    @RequestMapping("/{role}")
    public List<Permission> getPersmissionsOfRole(@PathVariable("role") String id) {
        return dao.getList(Long.valueOf(id));
    }

    @PostMapping("/add")
    public void addPermission(@RequestBody String ids) throws Exception {
        dao.addPermission(ids);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }
}
