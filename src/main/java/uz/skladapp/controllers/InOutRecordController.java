package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.InOutRecordService;
import uz.skladapp.model.pure_models.InOutRecord;

import java.util.Optional;

@RestController
@RequestMapping("/in_out_record")
public class InOutRecordController {
    @Autowired
    private InOutRecordService dao;

    @GetMapping(value = "", produces = "application/json")
    public Iterable<InOutRecord> getList(@RequestParam("size") String size,@RequestParam("index") String index ) {
        return dao.getList(Integer.valueOf(size), Integer.valueOf(index));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<InOutRecord> get(@PathVariable String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "")
    public void save(@RequestBody String attribute) throws Exception {
        dao.create(attribute);
    }

}
