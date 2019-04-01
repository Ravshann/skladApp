package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.skladapp.dao.IncomingDAO;
import uz.skladapp.model.special_models.Incoming;

import java.util.List;

@RestController
@RequestMapping("/incoming")
public class IncomingController {
    @Autowired
    private IncomingDAO dao;

    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Incoming> getList() {
        return dao.getAllIncomingRecords();
    }
}
