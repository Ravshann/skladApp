package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.RolePermissionDAO;
import uz.skladapp.model.special_models.PermissionRaw;

import java.util.List;

@RestController
@RequestMapping("/role_permissions")
public class RolePermissionController {
    @Autowired
    private RolePermissionDAO dao;


    @RequestMapping("/{role_id}")
    public List<PermissionRaw> getPersmissionsOfRole(@PathVariable("role_id") String id) {
        return dao.getList(Long.valueOf(id));
    }

    @PostMapping("/save")
    public void addPermission(@RequestBody String data) throws Exception {
        dao.addPermission(data);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }
}
