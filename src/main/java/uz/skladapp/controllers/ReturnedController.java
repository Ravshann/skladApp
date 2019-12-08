package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.ReturnedService;
import uz.skladapp.DTO.Returned;

import java.util.List;

@RestController
@RequestMapping("/returned")
public class ReturnedController {
    @Autowired
    private ReturnedService dao;

    @GetMapping(value = "", produces = "application/json")
    public List<Returned> getList() {
        return dao.getAllReturnedRecords();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public List<Returned> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id);
    }

    @PostMapping(value = "/department-records", produces = "application/json")
    public List<Returned> getListByCommonDepartment(@RequestBody String storages) throws Exception {
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
