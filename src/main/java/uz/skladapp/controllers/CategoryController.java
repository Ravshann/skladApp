package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.CategoryDAO;

import uz.skladapp.model.Attribute;
import uz.skladapp.model.Category;
import uz.skladapp.model.special_models.CategoryRaw;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<CategoryRaw> getList() {
        return dao.getAll();
    }


    @RequestMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    CategoryRaw get(@PathVariable("id") String id) {
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
    Category replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
