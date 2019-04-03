package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Product;
import uz.skladapp.model.Storage;
import uz.skladapp.model.StorageProduct;
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

    @Autowired
    private StorageDAO dao;

    public List<Product> getList(String id) {
        System.out.println(id);
        Optional<Storage> s = storageRepository.findById(Long.valueOf(id));
        List<Product> products = new ArrayList<>();
        for (StorageProduct pro : s.get().getProducts()) {
            products.add(pro.getProduct());
        }
        return products;
    }

    public void addProducts(String body) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode newJson = mapper.readTree(body);
        Long id_s = Long.valueOf(newJson.get("storage_ID").toString());
        Long id_p = Long.valueOf(newJson.get("product_ID").toString());
        Optional<Storage> storage = storageRepository.findById(id_s);
        Optional<Product> product = productRepository.findById(id_p);
        Float quant = Float.valueOf(newJson.get("current_quantity").toString());
        Float price = Float.valueOf(newJson.get("price").toString());
        dao.addProduct(storage.get(), product.get(), quant, price);
       // storage.get().addProduct(product.get(), quant, price);
        productRepository.save(product.get());

    }

    public void update(String data, Long id) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);

        storageRepository.findById(id)
                .map(object -> {
                    Long id_s = Long.valueOf(json.get("storage_ID").toString());
                    Long id_p = Long.valueOf(json.get("product_ID").toString());
                    Optional<Storage> storage = storageRepository.findById(id_s);
                    Optional<Product> product = productRepository.findById(id_p);
                    Float quant = Float.valueOf(json.get("current_quantity").toString());
                    Float price = Float.valueOf(json.get("price").toString());
                    dao.removeProduct(storage.get(), product.get());
                    dao.addProduct(storage.get(), product.get(), quant, price);
                    //storage.get().removeProduct(product.get());
                   // storage.get().addProduct(product.get(), quant, price);
                    storageRepository.save(object);


                    return 0;
                })
                .get();
    }

    public void delete(String object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(object);
        Long id_s = Long.valueOf(json.get("storage_ID").toString());
        Long id_p = Long.valueOf(json.get("product_ID").toString());
        Optional<Storage> storage = storageRepository.findById(id_s);
        Optional<Product> product = productRepository.findById(id_p);
        dao.removeProduct(storage.get(), product.get());
        // storage.get().removeProduct(product.get());
        productRepository.save(product.get());
    }
}
