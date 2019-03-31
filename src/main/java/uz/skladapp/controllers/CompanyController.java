package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.CompanyDAO;
import uz.skladapp.model.Company;

import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<Company> getList() {
        return dao.getAll();
    }


    @RequestMapping(value = "/", produces = "application/json")
    public @ResponseBody
    Optional<Company> get(@RequestParam("id") String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String object) throws Exception {
        dao.create(object);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PutMapping("/update/{id}")
    Company replace(@RequestBody String attribute, @PathVariable Long id) throws Exception{
        return dao.update(attribute, id);
    }
}
