package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.ProductAttribute;
import uz.skladapp.model.repositories.AttributeRepository;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.raw_models.AttributeRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductAttributeDAO {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private ProductDAO productDAO;


    public List<AttributeRaw> getAttributesList(Long product_id) {
        Optional<Product> product = productRepository.findById(product_id);
        List<AttributeRaw> attributes = new ArrayList<>();
        for (ProductAttribute association : product.get().getAttributes()) {
            AttributeRaw raw = new AttributeRaw(association.getAttribute().getAttribute_ID(), association.getAttribute().getAttribute_name());
            raw.setValue(association.getValue());
            attributes.add(raw);
        }
        return attributes;
    }

    public void add(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);
        for (JsonNode json : jsonArray) {
            Long id_product = Long.valueOf(json.get("product_ID").toString());
            Long id_attribute = Long.valueOf(json.get("attribute_ID").toString());
            String value = json.get("value").asText();
            Product product = productRepository.findById(id_product).get();
            Attribute attribute = attributeRepository.findById(id_attribute).get();
            productDAO.addAttribute(attribute, value, product);
            productRepository.save(product);
        }
    }

    public void delete(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);
        for (JsonNode json : jsonArray) {
            Long id_product = Long.valueOf(json.get("product_ID").toString());
            Long id_attribute = Long.valueOf(json.get("attribute_ID").toString());
            Product product = productRepository.findById(id_product).get();
            Attribute attribute = attributeRepository.findById(id_attribute).get();
            productDAO.removeAttribute(attribute, product);
            productRepository.save(product);
        }
    }

    public void update(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long id_product = json.get("product_ID").asLong();
        productRepository.findById(id_product)
                .map(object -> {
                    Long id_attribute = json.get("attribute_ID").asLong();
                    String value = json.get("value").asText();
                    Product product = productRepository.findById(id_product).get();
                    Attribute attribute = attributeRepository.findById(id_attribute).get();
                    productDAO.removeAttribute(attribute, product);
                    productDAO.addAttribute(attribute, value, product);
                    productRepository.save(object);
                    return 0;
                })
                .get();
    }
}


