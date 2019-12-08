package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.DefectedService;
import uz.skladapp.DTO.Defected;

import java.util.List;

@RestController
@RequestMapping("/defected")
public class DefectedController {
    @Autowired
    private DefectedService dao;

    @GetMapping(value = "", produces = "application/json")
    public List<Defected> getList() {
        return dao.getAllDefectedRecords();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public List<Defected> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id, "");
    }

    @GetMapping(value = "/supplier/{storage_id}", produces = "application/json")
    public List<Defected> getListBySupplierStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id, "supplier");
    }

    @PostMapping(value = "/department-records", produces = "application/json")
    public List<Defected> getListByCommonDepartment(@RequestBody String storages) throws Exception {
        return dao.getListByCommonDepartment(storages);
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String data) throws Exception {
        dao.save(data);
    }

    @PostMapping(value = "/update")
    public void update(@RequestBody String data) throws Exception {
        dao.update(data);
    }
}
