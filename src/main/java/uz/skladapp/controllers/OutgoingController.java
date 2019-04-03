package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.OutgoingDAO;
import uz.skladapp.model.special_models.Outgoing;

import java.util.List;

@RestController
@RequestMapping("/outgoing")
public class OutgoingController {
    @Autowired
    private OutgoingDAO dao;

    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Outgoing> getList() {
        return dao.getAllOutgoingRecords();
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
