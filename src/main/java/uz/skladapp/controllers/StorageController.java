package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.StorageDAO;
import uz.skladapp.model.Storage;

import java.util.Optional;

@RestController
@RequestMapping("/storages")
public class StorageController {
    @Autowired
    StorageDAO dao;

    @RequestMapping("")
    public @ResponseBody
    Iterable<Storage> getAllStorages() {
        return dao.getStorageList();
    }

    @GetMapping("/")
    public Optional<Storage> getStorage(@RequestParam("id") String id) {
        return dao.getStorageByID(Long.valueOf(id));
    }

    @PostMapping("/create")
    public void createStorage(@RequestBody String newStorageText) throws Exception {
        dao.saveStorage(newStorageText);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PutMapping("/update/{id}")
    Storage replace(@RequestBody String storage, @PathVariable Long id) throws Exception {
        return dao.update(storage, id);
    }
}
