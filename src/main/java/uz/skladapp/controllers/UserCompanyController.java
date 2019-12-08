package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.UserCompanyService;
import uz.skladapp.model.pure_models.Company;

import java.util.List;

@RestController
@RequestMapping("/user_companies")
public class UserCompanyController {
    @Autowired
    private UserCompanyService dao;

    @GetMapping("/{owner}")
    public List<Company> getCompanies(@PathVariable("owner") String id) {
        return dao.getList(Long.valueOf(id));
    }

    @PostMapping("/save")
    public void addCompany(@RequestBody String data) throws Exception {
        dao.addCompany(data);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }


}
