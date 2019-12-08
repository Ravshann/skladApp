package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.services.ProductAttributeService;
import uz.skladapp.DTO.AttributeDTO;

import java.util.List;

@RestController
@RequestMapping("/product_attribute")
public class ProductAttributeController {
    @Autowired
    private ProductAttributeService dao;

    @GetMapping("/{pro_id}")
    public List<AttributeDTO> getListProducts(@PathVariable("pro_id") String id) {
        return dao.getAttributesList(Long.valueOf(id));
    }

    @PostMapping(value = "/save")
    public void add(@RequestBody String object) throws Exception {
        dao.add(object);
    }

    @PostMapping("/delete")
    void delete(@RequestBody String object) throws Exception {
        dao.delete(object);
    }

    @PostMapping("/update")
    void replace(@RequestBody String attribute) throws Exception {
        dao.update(attribute);
    }


}
