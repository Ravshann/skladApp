package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.DepartmentClientDAO;
import uz.skladapp.model.Client;
import uz.skladapp.model.special_models.ClientRaw;

import java.util.List;

@RestController
@RequestMapping("/department_client")
public class DepartmentClientController {
    @Autowired
    private DepartmentClientDAO dao;


    @RequestMapping("/{department}")
    public List<ClientRaw> getList(@PathVariable("department") String id) {
        return dao.getRawList(Long.valueOf(id));
    }

    @PostMapping("/save")
    public void create(@RequestBody String ids) throws Exception {
        dao.create(ids);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }
}
