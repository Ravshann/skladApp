package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ProductDAO;
import uz.skladapp.model.Product;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<Product> getList() {
        return dao.getAll();
    }


    @RequestMapping(value = "/", produces = "application/json")
    public @ResponseBody
    Optional<Product> get(@RequestParam("id") String id) {
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
    Product replace(@RequestBody String attribute, @PathVariable Long id) throws Exception{
        return dao.update(attribute, id);
    }
}
