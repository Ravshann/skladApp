package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.CategoryService;

import uz.skladapp.model.pure_models.Category;
import uz.skladapp.DTO.CategoryDTO;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService dao;


    @GetMapping(value = "", produces = "application/json")
    public Iterable<CategoryDTO> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public CategoryDTO get(@PathVariable("id") Long id) {
        return dao.get(id);
    }

    @PostMapping(value = "")
    public Category save(@RequestBody CategoryDTO object) {
        return dao.create(object);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Category replace(@RequestBody CategoryDTO dto, @PathVariable Long id) throws Exception {
        return dao.update(dto, id);
    }
}
