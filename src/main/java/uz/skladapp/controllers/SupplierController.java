package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.SupplierDAO;
import uz.skladapp.model.special_models.SupplierRaw;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public List<SupplierRaw> getListProducts() {
        return dao.getList();
    }

    @PostMapping("/save")
    public void addPermission(@RequestBody String data) throws Exception {
        dao.save(data);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") String id) throws Exception {
        dao.delete(Long.valueOf(id));
    }

    @PutMapping("/update/{id}")
    void replace(@RequestBody String data, @PathVariable Long id) throws Exception {
        dao.update(data, id);
    }

}
