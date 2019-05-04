package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ProductAttributeDAO;
import uz.skladapp.model.raw_models.AttributeRaw;

import java.util.List;

@RestController
@RequestMapping("/product_attribute")
public class ProductAttributeController {
    @Autowired
    private ProductAttributeDAO dao;

    @GetMapping("/{pro_id}")
    public List<AttributeRaw> getListProducts(@PathVariable("pro_id") String id) {
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
