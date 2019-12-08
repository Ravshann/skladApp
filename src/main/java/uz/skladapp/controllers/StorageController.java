package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.StorageService;
import uz.skladapp.model.pure_models.Storage;
import uz.skladapp.DTO.StorageDTO;

@RestController
@RequestMapping("/storages")
public class StorageController {
    @Autowired
    StorageService dao;

    @GetMapping("")
    public @ResponseBody
    Iterable<StorageDTO> getAllStorages() {
        return dao.getStorageList();
    }

    @GetMapping("/{user_id}")
    public StorageDTO getStorage(@PathVariable("user_id") String id) {
        return dao.getStorageByStorageManagerID(Long.valueOf(id));
    }

    @GetMapping("regional/{user_id}")
    public Iterable<StorageDTO> getRegionalStorages(@PathVariable("user_id") String id) {
        return dao.getStoragesByDepartmentManagerID(Long.valueOf(id));
    }

    @GetMapping("defected")
    public Iterable<StorageDTO> getDefectedStorages() {
        return dao.getDefectedStoragesList();
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
