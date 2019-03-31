package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.UserDAO;
import uz.skladapp.model.User;
import uz.skladapp.model.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userDAO.getUserList();
    }

    @RequestMapping("/")
    public Optional<User> getUser(@RequestParam("id") String id) {

        return userDAO.getUser(Long.valueOf(id));
    }

    @PostMapping("/create")
    public void createStorage(@RequestBody String newUserText) throws Exception {
        userDAO.saveUser(newUserText);
    }
}
