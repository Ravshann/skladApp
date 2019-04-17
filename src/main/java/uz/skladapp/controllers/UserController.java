package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.UserDAO;
import uz.skladapp.model.pure_models.User;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDAO dao;

    @RequestMapping("")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return dao.getUserList();
    }

    @RequestMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") String id) {

        return dao.getUser(Long.valueOf(id));
    }

    @PostMapping("/save")
    public void create(@RequestBody String data) throws Exception {
        dao.saveUser(data);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PutMapping("/update/{id}")
    User replace(@RequestBody String user, @PathVariable Long id) throws Exception {
        return dao.update(user, id);
    }
}
