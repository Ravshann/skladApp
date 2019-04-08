package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Product;
import uz.skladapp.model.ProductAttribute;
import uz.skladapp.model.Storage;
import uz.skladapp.model.StorageProduct;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.repositories.StorageRepository;
import uz.skladapp.model.special_models.AttributeRaw;
import uz.skladapp.model.special_models.CategoryRaw;
import uz.skladapp.model.special_models.ProductRaw;

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

    public List<ProductRaw> getList(String id) {

        Storage s = storageRepository.findById(Long.valueOf(id)).get();
        List<ProductRaw> raws = new ArrayList<>();
        for (StorageProduct object : s.getProducts()) {
            CategoryRaw categoryRaw = new CategoryRaw(
                    object.getProduct().getCategory_ID().getCategory_ID(),
                    object.getProduct().getCategory_ID().getCategory_name(),
                    object.getProduct().getCategory_ID().getCategory_notes(),
                    object.getProduct().getCategory_ID().getUnit_measure());
            List<AttributeRaw> attributes = new ArrayList<>();
            for (ProductAttribute attribute : object.getProduct().getAttributes()) {
                AttributeRaw attributeRaw = new AttributeRaw(
                        attribute.getAttribute_ID(),
                        attribute.getAttribute().getAttribute_name());
                attributeRaw.setValue(attribute.getValue());
                attributes.add(attributeRaw);
            }

            ProductRaw raw = new ProductRaw(object.getProduct_ID(), object.getProduct().getProduct_name(), categoryRaw, attributes);
            raws.add(raw);
        }
        return raws;

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
