package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.skladapp.dao.RemainderDAO;
import uz.skladapp.model.Permission;
import uz.skladapp.model.Product;
import uz.skladapp.model.special_models.Remainder;

import java.util.List;

@RestController
@RequestMapping("/remainder")
public class RemainderController {
    @Autowired
    private RemainderDAO dao;

    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    List<Remainder> getList() {
        return dao.getAll();
    }

    @RequestMapping(value = "/{pr_id}", produces = "application/json")
    public Remainder getListProducts(@PathVariable("pr_id") String pr_id) {
        return dao.getList(pr_id);
    }

}
