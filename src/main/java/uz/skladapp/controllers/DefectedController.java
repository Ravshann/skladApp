package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.DefectedDAO;
import uz.skladapp.model.raw_models.Defected;

import java.util.List;
@RestController
@RequestMapping("/defected")
public class DefectedController {
    @Autowired
    private DefectedDAO dao;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Defected> getList() {
        return dao.getAllDefectedRecords();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public @ResponseBody
    List<Defected> getListByStorage(@PathVariable("storage_id") String storage_id) {
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
