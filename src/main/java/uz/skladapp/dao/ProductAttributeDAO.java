package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Attribute;
import uz.skladapp.model.Product;
import uz.skladapp.model.ProductAttribute;
import uz.skladapp.model.repositories.AttributeRepository;
import uz.skladapp.model.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductAttributeDAO {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;


    public List<Attribute> getAttributesList(Long product_id) {
        Optional<Product> product = productRepository.findById(product_id);
        List<Attribute> attributes = new ArrayList<>();
        for (ProductAttribute association : product.get().getAttributes()) {
            attributes.add(association.getAttribute());
        }
        return attributes;
    }

    public void add(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long id_product = Long.valueOf(json.get("product_ID").toString());
        Long id_attribute = Long.valueOf(json.get("attribute_ID").toString());
        String value = json.get("value").asText();
        Optional<Product> product = productRepository.findById(id_product);
        Optional<Attribute> attribute = attributeRepository.findById(id_attribute);
        product.get().addAttribute(attribute.get(), value);
        productRepository.save(product.get());
    }

    public void delete(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long id_product = Long.valueOf(json.get("product_ID").toString());
        Long id_attribute = Long.valueOf(json.get("attribute_ID").toString());
        Optional<Product> product = productRepository.findById(id_product);
        Optional<Attribute> attribute = attributeRepository.findById(id_attribute);
        product.get().removeAttribute(attribute.get());
        productRepository.save(product.get());
    }

    public void update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);

        productRepository.findById(id)
                .map(object -> {
                    Long id_product = Long.valueOf(json.get("product_ID").toString());
                    Long id_attribute = Long.valueOf(json.get("attribute_ID").toString());
                    String value = json.get("value").asText();
                    Optional<Product> product = productRepository.findById(id_product);
                    Optional<Attribute> attribute = attributeRepository.findById(id_attribute);
                    product.get().removeAttribute(attribute.get());
                    product.get().addAttribute(attribute.get(), value);
                    productRepository.save(object);
                    return 0;
                })
                .get();
    }
}


