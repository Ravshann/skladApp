package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.ProductService;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.DTO.ProductDTO;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService dao;


    @GetMapping(value = "", produces = "application/json")
    public Iterable<ProductDTO> getList() {
        return dao.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ProductDTO get(@PathVariable("id") Long id) {
        return dao.get(id);
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
    Product replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
