package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.RoleDAO;
import uz.skladapp.dao.StorageDAO;
import uz.skladapp.model.Role;
import uz.skladapp.model.Storage;
import uz.skladapp.model.repositories.RoleRepository;

import java.util.Optional;

@RestController
@RequestMapping("/storages")
public class StorageController {
    @Autowired
    StorageDAO storageDAO;

    @RequestMapping("")
    public @ResponseBody
    Iterable<Storage> getAllStorages() {
        return storageDAO.getStorageList();
    }

    @GetMapping("/")
    public Optional<Storage> getStorage(@RequestParam("id") String id) {
        return storageDAO.getStorageByID(Long.valueOf(id));
    }

    @PostMapping("/create")
    public void createStorage(@RequestBody String newStorageText) throws Exception {
        storageDAO.saveStorage(newStorageText);
    }
}
