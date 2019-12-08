package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.RoleService;
import uz.skladapp.model.pure_models.Role;

import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService dao;

    @GetMapping("")
    public Iterable<Role> getAllRoles() {
        return dao.getRolesList();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRole(@PathVariable("id") String id) {
        return dao.getRoleByID(Long.valueOf(id));
    }

    @PostMapping("")
    public void createRole(@RequestBody String newRoleText) throws Exception {
        dao.saveRole(newRoleText);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Role replace(@RequestBody String role, @PathVariable Long id) throws Exception {
        return dao.update(role, id);
    }

}
