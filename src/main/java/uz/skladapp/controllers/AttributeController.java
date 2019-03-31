package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.AttributeDAO;
import uz.skladapp.model.Attribute;

import java.util.Optional;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    @Autowired
    private AttributeDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<Attribute> getList() {
        return dao.getAllAttributes();
    }


    @RequestMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    Optional<Attribute> get(@PathVariable String id) {
        return dao.getAttribute(Long.valueOf(id));
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String attribute) throws Exception {
        dao.createAttribute(attribute);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PutMapping("/update/{id}")
    Attribute replace(@RequestBody String attribute, @PathVariable Long id) throws Exception{
        return dao.update(attribute, id);
    }

}
