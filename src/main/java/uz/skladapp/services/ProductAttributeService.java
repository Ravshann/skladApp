package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.ProductAttribute;
import uz.skladapp.repositories.AttributeRepository;
import uz.skladapp.repositories.ProductRepository;
import uz.skladapp.DTO.AttributeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductAttributeService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private ProductService productService;


    public List<AttributeDTO> getAttributesList(Long product_id) {
        Optional<Product> product = productRepository.findById(product_id);
        List<AttributeDTO> attributes = new ArrayList<>();
        for (ProductAttribute association : product.get().getAttributes()) {
            AttributeDTO raw = new AttributeDTO(association.getAttribute().getAttribute_ID(), association.getAttribute().getAttribute_name());
            raw.setAttribute_value(association.getAttribute_value());
            attributes.add(raw);
        }
        return attributes;
    }

    public void add(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);
        for (JsonNode json : jsonArray) {
            Long id_product = json.get("product_ID").asLong();
            Long id_attribute = json.get("attribute_ID").asLong();
            String value = json.get("attribute_value").asText();
            Product product = productRepository.findById(id_product).get();
            Attribute attribute = attributeRepository.findById(id_attribute).get();
            productService.addAttribute(attribute, value, product);
            productRepository.save(product);
        }
    }

    public void delete(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);
        for (JsonNode json : jsonArray) {
            Long id_product = json.get("product_ID").asLong();
            Long id_attribute = json.get("attribute_ID").asLong();
            Product product = productRepository.findById(id_product).get();
            Attribute attribute = attributeRepository.findById(id_attribute).get();
            productService.removeAttribute(attribute, product);
            productRepository.save(product);
        }
    }

    public void update(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);
        for (JsonNode json : jsonArray) {
            Long id_product = json.get("product_ID").asLong();
            productRepository.findById(id_product)
                    .map(object -> {
                        Long id_attribute = json.get("attribute_ID").asLong();
                        String value = json.get("attribute_value").asText();
                        Product product = productRepository.findById(id_product).get();
                        Attribute attribute = attributeRepository.findById(id_attribute).get();
                        productService.removeAttribute(attribute, product);
                        productService.addAttribute(attribute, value, product);
                        productRepository.save(object);
                        return 0;
                    })
                    .get();
        }
    }
}


