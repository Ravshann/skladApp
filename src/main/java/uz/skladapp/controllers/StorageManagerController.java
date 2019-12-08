package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.skladapp.services.UserService;
import uz.skladapp.model.pure_models.User;


@RestController
@RequestMapping("/storage_managers")
public class StorageManagerController {
    @Autowired
    private UserService dao;

    @GetMapping("")
    public Iterable<User> getAllStorageManagers() {
        return dao.getAllStorageManagers();
    }
}