package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.OutgoingService;
import uz.skladapp.DTO.Outgoing;

import java.util.List;

@RestController
@RequestMapping("/outgoing")
public class OutgoingController {
    @Autowired
    private OutgoingService dao;

    @GetMapping(value = "", produces = "application/json")
    public List<Outgoing> getList() {
        return dao.getAllOutgoingRecords();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public List<Outgoing> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id);
    }

    @PostMapping(value = "/department-records", produces = "application/json")
    public List<Outgoing> getListByCommonDepartment(@RequestBody String storages) throws Exception {
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
