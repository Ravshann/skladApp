package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.SupplierDepartmentService;
import uz.skladapp.DTO.DepartmentDTO;

import java.util.List;

@RestController
@RequestMapping("/supplier_department")
public class SupplierDepartmentController {
    @Autowired
    private SupplierDepartmentService dao;


    @GetMapping("/{id}")
    public List<DepartmentDTO> getDepartments(@PathVariable("id") String supplier_id) {
        return dao.getList(Long.valueOf(supplier_id));
    }

    @PostMapping("/save")
    public void add(@RequestBody String data) throws Exception {
        dao.add(data);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }
}
