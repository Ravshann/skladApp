package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.ClientService;
import uz.skladapp.model.pure_models.Client;
import uz.skladapp.DTO.ClientDTO;


@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService dao;

    @GetMapping(value = "", produces = "application/json")
    public Iterable<ClientDTO> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ClientDTO get(@PathVariable("id") String id) {
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
    Client replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }

}
