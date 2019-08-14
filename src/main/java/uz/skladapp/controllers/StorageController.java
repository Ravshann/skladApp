package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.StorageDAO;
import uz.skladapp.model.pure_models.Storage;
import uz.skladapp.model.raw_models.StorageRaw;

@RestController
@RequestMapping("/storages")
public class StorageController {
    @Autowired
    StorageDAO dao;

    @GetMapping("")
    public @ResponseBody
    Iterable<StorageRaw> getAllStorages() {
        return dao.getStorageList();
    }

    @GetMapping("/{user_id}")
    public StorageRaw getStorage(@PathVariable("user_id") String id) {
        return dao.getStorageByStorageManagerID(Long.valueOf(id));
    }

    @PostMapping("")
    public void createStorage(@RequestBody String newStorageText) throws Exception {
        dao.saveStorage(newStorageText);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        dao.deleteById(id);
    }

    @PostMapping("/{id}")
    Storage replace(@RequestBody String storage, @PathVariable Long id) throws Exception {
        return dao.update(storage, id);
    }
}
