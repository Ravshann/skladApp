package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.CompanyService;
import uz.skladapp.model.pure_models.Company;
import uz.skladapp.DTO.CompanyDTO;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService dao;


    @GetMapping(value = "", produces = "application/json")
    public Iterable<CompanyDTO> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public CompanyDTO get(@PathVariable("id") String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "")
    public void save(@RequestBody CompanyDTO object) throws Exception {
        dao.create(object);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Company replace(@RequestBody CompanyDTO object, @PathVariable Long id) {
        return dao.update(object, id);
    }
}
