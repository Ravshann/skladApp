package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.DepartmentClientDAO;
import uz.skladapp.model.raw_models.ClientRaw;

import java.util.List;

@RestController
@RequestMapping("/department_client")
public class DepartmentClientController {
    @Autowired
    private DepartmentClientDAO dao;


    @GetMapping("/{department}")
    public List<ClientRaw> getList(@PathVariable("department") String id) {
        return dao.getRawList(Long.valueOf(id));
    }

    @PostMapping("/save")
    public void create(@RequestBody String data) throws Exception {
        dao.create(data);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }
}
