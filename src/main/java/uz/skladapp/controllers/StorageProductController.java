package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.StorageProductDAO;
import uz.skladapp.model.Product;

import java.util.List;

@RestController
@RequestMapping("/storage_products")
public class StorageProductController {
    @Autowired
    private StorageProductDAO dao;


    @RequestMapping(value = "/{store}" , produces = "application/json")
    public List<Product> getListProducts(@PathVariable("store") String id) {
      return dao.getList(id);
    }

    @PostMapping("/add")
    public void addPermission(@RequestBody String ids)  throws Exception{
        dao.addProducts(ids);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }

    @PutMapping("/update/{id}")
    void replace(@RequestBody String attribute, @PathVariable(value = "id") String id) throws Exception{
        dao.update(attribute, Long.valueOf(id));
    }

}
