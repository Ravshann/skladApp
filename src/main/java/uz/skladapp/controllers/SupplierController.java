package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.SupplierDAO;
import uz.skladapp.model.Supplier;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDAO dao;


    @RequestMapping(value = "/", produces = "application/json")
    public List<Supplier> getListProducts() {
        return dao.getList();
    }

    @PostMapping("/add")
    public void addPermission(@RequestBody String data) throws Exception {
        dao.save(data);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String id) throws Exception {
        dao.delete(Long.valueOf(id));
    }


}
