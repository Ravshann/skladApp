package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.ExchangeService;
import uz.skladapp.DTO.Exchange;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeService dao;

    @GetMapping(value = "", produces = "application/json")
    public List<Exchange> getList() {
        return dao.getAllExchangeRecords();
    }

    @GetMapping(value = "/{storage_id}", produces = "application/json")
    public List<Exchange> getListByStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id, "");
    }

    @GetMapping(value = "/supplier/{storage_id}", produces = "application/json")
    public List<Exchange> getListBySupplierStorage(@PathVariable("storage_id") String storage_id) {
        return dao.getListByStorage(storage_id, "supplier");
    }

    @PostMapping(value = "/department-records", produces = "application/json")
    public List<Exchange> getListByCommonDepartment(@RequestBody String storages) throws Exception {
        return dao.getListByCommonDepartment(storages);
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String data) throws Exception {
        dao.save(data);
    }

    @PostMapping(value = "/update")
    public void update(@RequestBody String data) throws Exception {
        dao.update(data);
    }


}
