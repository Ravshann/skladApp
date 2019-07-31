package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.RemainderDAO;
import uz.skladapp.model.raw_models.Remainder;

import java.util.List;


@RestController
@RequestMapping("/remainder")
public class RemainderController {
    @Autowired
    private RemainderDAO dao;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Remainder> getList() {
        return dao.getAll();
    }

    @GetMapping(value = "/{pr_id}", produces = "application/json")
    public Remainder getListProducts(@PathVariable("pr_id") String pr_id) {
        return dao.getList(pr_id);
    }

}
