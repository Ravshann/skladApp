package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
}
