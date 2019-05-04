package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ReturnedDAO;
import uz.skladapp.model.raw_models.Returned;

import java.util.List;
@RestController
@RequestMapping("/returned")
public class ReturnedController {
    @Autowired
    private ReturnedDAO dao;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Returned> getList() {
        return dao.getAllReturnedRecords();
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
