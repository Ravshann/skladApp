package uz.skladapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.dao.ProductAttributeDAO;
import uz.skladapp.model.Attribute;
import uz.skladapp.model.Product;


import java.util.List;

@RestController
@RequestMapping("/product_attribute")
public class ProductAttributeController {
    @Autowired
    private ProductAttributeDAO dao;

    //    @RequestMapping(value = "/", produces = "application/json")
//    public @ResponseBody
//    List<Attribute> get(@RequestParam("id") String id) {
//        System.out.println("here");
//        return dao.getAttributesList(Long.valueOf(id));
//    }
    @RequestMapping("/{pro_id}")
    public List<Attribute> getListProducts(@PathVariable("pro_id") String id) {
        return dao.getAttributesList(Long.valueOf(id));
    }

    @PostMapping(value = "/add")
    public void add(@RequestBody String object) throws Exception {
        dao.add(object);
    }

    @PostMapping("/delete")
    void replace(@RequestBody String object) throws Exception {
        dao.delete(object);
    }

    @PutMapping("/update/{id}")
    void replace(@RequestBody String attribute, @PathVariable Long id) throws Exception {
        dao.update(attribute, id);
    }


}
