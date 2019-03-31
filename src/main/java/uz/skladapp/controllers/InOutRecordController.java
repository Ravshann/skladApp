package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.InOutRecordDAO;
import uz.skladapp.model.InOutRecord;

import java.util.Optional;

@RestController
@RequestMapping("/in_out_record")
public class InOutRecordController {
    @Autowired
    private InOutRecordDAO dao;


    @RequestMapping(value = "", produces = "application/json")
    public @ResponseBody
    Iterable<InOutRecord> getList() {
        return dao.getList();
    }


    @RequestMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    Optional<InOutRecord> get(@PathVariable String id) {
        return dao.get(Long.valueOf(id));
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody String attribute) throws Exception {
        dao.create(attribute);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id) {
        dao.delete(id);
    }

    @PutMapping("/update/{id}")
    InOutRecord replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        return dao.update(attribute, id);
    }
}
