package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.AttributeService;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.DTO.AttributeDTO;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    @Autowired
    private AttributeService dao;


    @GetMapping(value = "", produces = "application/json")
    public Iterable<AttributeDTO> getList() {
        return dao.getAllAttributes();
    }


    @PostMapping(value = "")
    public void save(@RequestBody AttributeDTO attribute) throws Exception {
        dao.createAttribute(attribute);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    public Attribute replace(@RequestBody AttributeDTO attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public AttributeDTO get(@PathVariable Long id) {
        return dao.getAttribute(id);
    }

}
