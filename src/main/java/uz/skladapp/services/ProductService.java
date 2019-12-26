package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.pure_models.Category;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.ProductAttribute;
import uz.skladapp.repositories.CategoryRepository;
import uz.skladapp.repositories.ProductRepository;
import uz.skladapp.DTO.AttributeDTO;
import uz.skladapp.DTO.CategoryDTO;
import uz.skladapp.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductDTO get(Long id) {
        Product object = repository.findById(id).get();
        CategoryDTO categoryDTO = new CategoryDTO(
                object.getCategory_ID().getCategory_ID(),
                object.getCategory_ID().getCategoryName(),
                object.getCategory_ID().getCategory_notes(),
                object.getCategory_ID().getUnit_measure());
        List<AttributeDTO> attributes = new ArrayList<>();
        for (ProductAttribute attribute : object.getAttributes()) {
            AttributeDTO attributeDTO = new AttributeDTO(
                    attribute.getAttribute_ID(),
                    attribute.getAttribute().getAttribute_name());
            attributeDTO.setAttribute_value(attribute.getAttribute_value());
            attributes.add(attributeDTO);
        }

        ProductDTO raw = new ProductDTO(object.getProduct_ID(), object.getProductName(), categoryDTO, attributes);
        return raw;
    }

    public Iterable<ProductDTO> getAll() {
        List<Product> originals = repository.findAllByOrderByProductNameAsc();
        List<ProductDTO> raws = new ArrayList<>();
        for (Product object : originals) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    object.getCategory_ID().getCategory_ID(),
                    object.getCategory_ID().getCategoryName(),
                    object.getCategory_ID().getCategory_notes(),
                    object.getCategory_ID().getUnit_measure());
            List<AttributeDTO> attributes = new ArrayList<>();
            for (ProductAttribute attribute : object.getAttributes()) {
                AttributeDTO attributeDTO = new AttributeDTO(
                        attribute.getAttribute_ID(),
                        attribute.getAttribute().getAttribute_name());
                attributeDTO.setAttribute_value(attribute.getAttribute_value());
                attributes.add(attributeDTO);
            }

            ProductDTO raw = new ProductDTO(object.getProduct_ID(), object.getProductName(), categoryDTO, attributes);
            raws.add(raw);
        }
        return raws;

    }


    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);


        Product object = new Product();
        object.setProductName(json.get("product_name").asText());

        Long c_id = Long.valueOf(json.get("category_ID").asText());
        Optional<Category> c = categoryRepository.findById(c_id);
        object.setCategory_ID(c.get());

        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Product update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);


        return repository.findById(id)
                .map(object -> {
                    object.setProductName(json.get("product_name").asText());

                    Long c_id = Long.valueOf(json.get("category_ID").asText());
                    Optional<Category> c = categoryRepository.findById(c_id);
                    object.setCategory_ID(c.get());
                    return repository.save(object);
                })
                .get();
    }


    public void addAttribute(Attribute attribute, String value, Product product) {
        ProductAttribute association = new ProductAttribute();
        association.setAttribute(attribute);
        association.setProduct(product);
        association.setAttribute_ID(attribute.getAttribute_ID());
        association.setProduct_ID(product.getProduct_ID());
        association.setAttribute_value(value);

        if (product.getAttributes() == null)
            product.setAttributes(new ArrayList<>());

        product.getAttributes().add(association);

        attribute.getProducts().add(association);
    }

    public void removeAttribute(Attribute attribute, Product product) {
        for (Iterator<ProductAttribute> iterator = product.getAttributes().iterator(); iterator.hasNext(); ) {
            ProductAttribute productAttribute = iterator.next();

            if (productAttribute.getProduct().equals(product) && productAttribute.getAttribute().equals(attribute)) {
                iterator.remove();
                productAttribute.getAttribute().getProducts().remove(productAttribute);
                productAttribute.setProduct(null);
                productAttribute.setAttribute(null);
            }
        }
    }
}
