package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.IncomingDAO;
import uz.skladapp.model.raw_models.Incoming;

import java.util.List;

@RestController
@RequestMapping("/incoming")
public class IncomingController {
    @Autowired
    private IncomingDAO dao;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Incoming> getList() {
        return dao.getAllIncomingRecords();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public @ResponseBody
    List<Incoming> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id);
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String data) throws Exception{
        dao.save(data);
    }

    @PostMapping(value = "/update")
    public void update(@RequestBody String data) throws Exception{
        dao.update(data);
    }


}
