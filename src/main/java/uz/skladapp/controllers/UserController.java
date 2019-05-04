package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.UserDAO;
import uz.skladapp.model.pure_models.Role;
import uz.skladapp.model.pure_models.User;
import uz.skladapp.model.raw_models.UserRolePermissions;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDAO dao;

    @GetMapping("")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return dao.getUserList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) {
        return dao.getUser(Long.valueOf(id));
    }

    @GetMapping("/role_permissions/{id}")
    public UserRolePermissions getUserPermissions(@PathVariable("id") String id) {
        return dao.getUserPermissions(Long.valueOf(id));
    }

    @PostMapping("")
    public void create(@RequestBody String data) throws Exception {
        dao.saveUser(data);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    User replace(@RequestBody String user, @PathVariable Long id) throws Exception {
        return dao.update(user, id);
    }

}
