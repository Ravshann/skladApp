package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.DefectedDAO;
import uz.skladapp.model.special_models.Defected;

import java.util.List;
@RestController
@RequestMapping("/defected")
public class DefectedController {
    @Autowired
    private DefectedDAO dao;

    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Defected> getList() {
        return dao.getAllDefectedRecords();
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String data) throws Exception{
        dao.save(data);
    }

    @PutMapping(value = "/update")
    public void update(@RequestBody String data) throws Exception{
        dao.update(data);
    }


}
