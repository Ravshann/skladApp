package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.CompanyDAO;
import uz.skladapp.model.pure_models.Company;
import uz.skladapp.model.raw_models.CompanyRaw;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyDAO dao;


    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<CompanyRaw> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    CompanyRaw get(@PathVariable("id") String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "")
    public void save(@RequestBody String object) throws Exception {
        dao.create(object);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Company replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
