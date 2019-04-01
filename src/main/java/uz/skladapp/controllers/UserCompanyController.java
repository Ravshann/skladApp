package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.UserCompanyDAO;
import uz.skladapp.model.Company;

import java.util.List;

@RestController
@RequestMapping("/user_companies")
public class UserCompanyController {
    @Autowired
    private UserCompanyDAO dao;

    @RequestMapping("/{owner}")
    public List<Company> getCompanies(@PathVariable("owner") String id) {
        return dao.getList(Long.valueOf(id));
    }

    @PostMapping("/add")
    public void addCompany(@RequestBody String ids) throws Exception {
        dao.addCompany(ids);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }


}
