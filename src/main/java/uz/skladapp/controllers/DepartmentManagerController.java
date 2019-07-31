package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.skladapp.dao.UserDAO;
import uz.skladapp.model.pure_models.User;

@RestController
@RequestMapping("/department_managers")
public class DepartmentManagerController {
    @Autowired
    private UserDAO dao;

    @GetMapping("")
    public @ResponseBody
    Iterable<User> getAllDepartmentManagers() {
        return dao.getAllDepartmentManagers();
    }
}
