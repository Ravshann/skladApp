package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.AttributeDAO;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.raw_models.AttributeRaw;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    @Autowired
    private AttributeDAO dao;


    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<AttributeRaw> getList() {
        return dao.getAllAttributes();
    }


    @PostMapping(value = "")
    public void save(@RequestBody String attribute) throws Exception {
        dao.createAttribute(attribute);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Attribute replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    AttributeRaw get(@PathVariable String id) {
        return dao.getAttribute(Long.valueOf(id));
    }

}
