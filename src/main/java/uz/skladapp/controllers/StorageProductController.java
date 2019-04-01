package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.StorageProductDAO;
import uz.skladapp.model.Product;
import uz.skladapp.model.Storage;
import uz.skladapp.model.StorageProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/storage_products")
public class StorageProductController {
    @Autowired
    private StorageProductDAO dao;


    @RequestMapping("/{store}")
    public List<Product> getListProducts(@PathVariable("store") String id) {
      return dao.getListProducts(id);
    }

    @PostMapping("/add")
    public void addPermission(@RequestBody String ids)  throws Exception{
        dao.addProducts(ids);
    }

    @PostMapping("/delete")
    void replace(@RequestBody String object) throws Exception {
        dao.delete(object);
    }

    @PutMapping("/update/{id}")
    void replace(@RequestBody String product, @PathVariable Long id) throws Exception{
        dao.update(product, id);
    }

}
