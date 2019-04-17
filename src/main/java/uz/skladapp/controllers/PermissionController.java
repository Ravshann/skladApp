package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.PermissionDAO;
import uz.skladapp.model.pure_models.Permission;
import uz.skladapp.model.special_models.PermissionRaw;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<PermissionRaw> getList() {
        return dao.getAll();
    }


    @RequestMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    PermissionRaw get(@PathVariable("id") String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String object) throws Exception {
        dao.create(object);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PutMapping("/update/{id}")
    Permission replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
