package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ClientDAO;
import uz.skladapp.model.Client;

import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientDAO dao;

    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<Client> getList() {
        return dao.getAll();
    }


    @RequestMapping(value = "/", produces = "application/json")
    public @ResponseBody
    Optional<Client> get(@RequestParam("id") String id) {
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
    Client replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }

}
