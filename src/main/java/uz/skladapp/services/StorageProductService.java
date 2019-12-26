package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.ProductAttribute;
import uz.skladapp.model.pure_models.Storage;
import uz.skladapp.model.pure_models.StorageProduct;
import uz.skladapp.repositories.ProductRepository;
import uz.skladapp.repositories.StorageRepository;
import uz.skladapp.DTO.AttributeDTO;
import uz.skladapp.DTO.CategoryDTO;
import uz.skladapp.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService {
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService dao;

    public List<ProductDTO> getList(String id) {

        Storage s = storageRepository.findById(Long.valueOf(id)).get();
        List<ProductDTO> raws = new ArrayList<>();
        for (StorageProduct object : s.getProducts()) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    object.getProduct().getCategory_ID().getCategory_ID(),
                    object.getProduct().getCategory_ID().getCategoryName(),
                    object.getProduct().getCategory_ID().getCategory_notes(),
                    object.getProduct().getCategory_ID().getUnit_measure());
            List<AttributeDTO> attributes = new ArrayList<>();
            for (ProductAttribute attribute : object.getProduct().getAttributes()) {
                AttributeDTO attributeDTO = new AttributeDTO(
                        attribute.getAttribute_ID(),
                        attribute.getAttribute().getAttribute_name());
                attributeDTO.setAttribute_value(attribute.getAttribute_value());
                attributes.add(attributeDTO);
            }

            ProductDTO raw = new ProductDTO(object.getProduct_ID(), object.getProduct().getProductName(), categoryDTO, attributes);
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
        productRepository.save(product.get());
    }
}
