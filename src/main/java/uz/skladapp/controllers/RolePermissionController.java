package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.RolePermissionService;
import uz.skladapp.DTO.PermissionDTO;

import java.util.List;

@RestController
@RequestMapping("/role_permissions")
public class RolePermissionController {
    @Autowired
    private RolePermissionService dao;


    @GetMapping("/{role_id}")
    public List<PermissionDTO> getPersmissionsOfRole(@PathVariable("role_id") String id) {
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
