package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ProductDAO;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.raw_models.ProductRaw;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductDAO dao;


    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<ProductRaw> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    ProductRaw get(@PathVariable("id") String id) {
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
    Product replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
