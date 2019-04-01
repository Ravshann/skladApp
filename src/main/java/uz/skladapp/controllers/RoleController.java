package uz.skladapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.RoleDAO;
import uz.skladapp.model.Role;

import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleDAO dao;

    @RequestMapping("")
    public @ResponseBody
    Iterable<Role> getAllRoles() {
        return dao.getRolesList();
    }

    @GetMapping("/")
    public Optional<Role> getRole(@RequestParam("id") String id) {

        return dao.getRoleByID(Long.valueOf(id));
    }

    @PostMapping("/save")
    public void createRole(@RequestBody String newRoleText) throws Exception {
        dao.saveRole(newRoleText);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PutMapping("/update/{id}")
    Role replace(@RequestBody String role, @PathVariable Long id) throws Exception {
        return dao.update(role, id);
    }

}
