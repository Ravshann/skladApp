package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.SupplierDepartmentDAO;
import uz.skladapp.model.Department;
import uz.skladapp.model.SupplierDepartment;

import java.util.List;

@RestController
@RequestMapping("/supplier_department")
public class SupplierDepartmentController {
    @Autowired
    private SupplierDepartmentDAO dao;


    @RequestMapping("/{supplier_id}")
    public List<Department> getDepartments(@PathVariable("supplier_id") String id) {
        return dao.getList(Long.valueOf(id));
    }

    @PostMapping("/add")
    public void add(@RequestBody String data)  throws Exception{
        dao.add(data);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }
}
