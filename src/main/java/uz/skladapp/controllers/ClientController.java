package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ClientDAO;
import uz.skladapp.model.pure_models.Client;
import uz.skladapp.model.raw_models.ClientRaw;


@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientDAO dao;

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<ClientRaw> getList() {
        return dao.getAll();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ClientRaw get(@PathVariable("id") String id) {
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
