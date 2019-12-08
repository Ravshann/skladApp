package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.PermissionService;
import uz.skladapp.model.pure_models.Permission;
import uz.skladapp.DTO.PermissionDTO;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService dao;


    @GetMapping(value = "", produces = "application/json")
    public Iterable<PermissionDTO> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public PermissionDTO get(@PathVariable("id") String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "")
    public void save(@RequestBody String object) throws Exception {
        dao.create(object);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Permission replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
