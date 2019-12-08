package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.RemainderService;
import uz.skladapp.DTO.Remainder;

import java.util.List;


@RestController
@RequestMapping("/remainder")
public class RemainderController {
    @Autowired
    private RemainderService dao;

    @GetMapping(value = "", produces = "application/json")
    //pass true to get remainders without defected storage
    public List<Remainder> getList() {
        return dao.getAll(true);
    }

    @GetMapping(value = "/defected-goods", produces = "application/json")
    //pass false to get only defected goods remainders
    public List<Remainder> getListForDefected() {
        return dao.getAll(false);
    }

    //this method is not used by frontend
    @GetMapping(value = "/{pr_id}", produces = "application/json")
    public Remainder getListProducts(@PathVariable("pr_id") String pr_id) {
        return dao.getList(pr_id);
    }

    @GetMapping(value = "/storage/{storage_id}", produces = "application/json")
    public List<Remainder> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id);
    }

    @PostMapping(value = "/department-storages", produces = "application/json")
    public List<Remainder> getListByCommonDepartment(@RequestBody String storages) throws Exception {
        return dao.getListByCommonDepartment(storages);
    }


}
