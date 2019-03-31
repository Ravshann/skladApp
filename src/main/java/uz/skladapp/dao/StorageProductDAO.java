package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import uz.skladapp.model.*;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.repositories.StorageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StorageProductDAO {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getListProducts(String id) {
        Optional<Storage> s = storageRepository.findById(Long.valueOf(id));
        List<Product> products= new ArrayList<>();
        for (StorageProduct pro : s.get().getProducts()) {
            products.add(pro.getProduct());
        }
        return products;
    }

    public void addProducts(String ids) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newJson = mapper.readTree(ids);
        Long id_s = Long.valueOf(newJson.get("storage_ID").toString());
        Long id_p = Long.valueOf(newJson.get("product_ID").toString());
        Optional<Storage> storage = storageRepository.findById(id_s);
        Optional<Product> product= productRepository.findById(id_p);
        Float quant = Float.valueOf(newJson.get("current_quantity").toString());
        Float price = Float.valueOf(newJson.get("price").toString());
        storage.get().addProduct(product.get(), quant, price);
        productRepository.save(product.get());

    }
}
