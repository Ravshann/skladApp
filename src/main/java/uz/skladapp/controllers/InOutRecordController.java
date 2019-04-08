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
    Iterable<InOutRecord> getList(@RequestParam("size") String size,@RequestParam("index") String index ) {
        return dao.getList(Integer.valueOf(size), Integer.valueOf(index));
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

}
