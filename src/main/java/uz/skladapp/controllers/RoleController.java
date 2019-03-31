package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.RoleDAO;
import uz.skladapp.model.Role;
import uz.skladapp.model.repositories.RoleRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleDAO roleDAO;

    @RequestMapping("")
    public @ResponseBody
    Iterable<Role> getAllRoles() {
        return roleDAO.getRolesList();
    }

    @GetMapping("/")
    public Optional<Role> getRole(@RequestParam("id") String id) {

        return roleDAO.getRoleByID(Long.valueOf(id));
    }

    @PostMapping("/create")
    public void createRole(@RequestBody String newRoleText) throws Exception {
        roleDAO.saveRole(newRoleText);
    }

}
