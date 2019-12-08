package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.SupplierService;
import uz.skladapp.DTO.SupplierDTO;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService dao;


    @GetMapping(value = "", produces = "application/json")
    public List<SupplierDTO> getListProducts() {
        return dao.getList();
    }

    @PostMapping("")
    public void addPermission(@RequestBody String data) throws Exception {
        dao.save(data);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id) throws Exception {
        dao.delete(Long.valueOf(id));
    }

    @PostMapping("/{id}")
    void replace(@RequestBody String data, @PathVariable Long id) throws Exception {
        dao.update(data, id);
    }

}
