package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.OutgoingAndReturnedService;
import uz.skladapp.DTO.OutgoingReturned;

import java.util.List;

@RestController
@RequestMapping("/outgoing-returned")
public class OutgoingAndReturnedController {
    @Autowired
    OutgoingAndReturnedService dao;

    @GetMapping(value = "", produces = "application/json")
    public List<OutgoingReturned> getList() {
        return dao.findAll();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public List<OutgoingReturned> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id);
    }

    @PostMapping(value = "/department-records", produces = "application/json")
    public List<OutgoingReturned> getListByCommonDepartment(@RequestBody String storages) throws Exception {
        return dao.getListByCommonDepartment(storages);
    }
}
