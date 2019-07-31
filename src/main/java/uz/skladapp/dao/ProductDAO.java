package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.pure_models.Category;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.ProductAttribute;
import uz.skladapp.model.repositories.CategoryRepository;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.raw_models.AttributeRaw;
import uz.skladapp.model.raw_models.CategoryRaw;
import uz.skladapp.model.raw_models.ProductRaw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductRaw get(Long id) {
        Product object = repository.findById(id).get();
        CategoryRaw categoryRaw = new CategoryRaw(
                object.getCategory_ID().getCategory_ID(),
                object.getCategory_ID().getCategory_name(),
                object.getCategory_ID().getCategory_notes(),
                object.getCategory_ID().getUnit_measure());
        List<AttributeRaw> attributes = new ArrayList<>();
        for (ProductAttribute attribute : object.getAttributes()) {
            AttributeRaw attributeRaw = new AttributeRaw(
                    attribute.getAttribute_ID(),
                    attribute.getAttribute().getAttribute_name());
            attributeRaw.setAttribute_value(attribute.getAttribute_value());
            attributes.add(attributeRaw);
        }

        ProductRaw raw = new ProductRaw(object.getProduct_ID(), object.getProduct_name(), categoryRaw, attributes);
        return raw;
    }

    public Iterable<ProductRaw> getAll() {
        List<Product> originals = repository.findAll();
        List<ProductRaw> raws = new ArrayList<>();
        for (Product object : originals) {
            CategoryRaw categoryRaw = new CategoryRaw(
                    object.getCategory_ID().getCategory_ID(),
                    object.getCategory_ID().getCategory_name(),
                    object.getCategory_ID().getCategory_notes(),
                    object.getCategory_ID().getUnit_measure());
            List<AttributeRaw> attributes = new ArrayList<>();
            for (ProductAttribute attribute : object.getAttributes()) {
                AttributeRaw attributeRaw = new AttributeRaw(
                        attribute.getAttribute_ID(),
                        attribute.getAttribute().getAttribute_name());
                attributeRaw.setAttribute_value(attribute.getAttribute_value());
                attributes.add(attributeRaw);
            }

            ProductRaw raw = new ProductRaw(object.getProduct_ID(), object.getProduct_name(), categoryRaw, attributes);
            raws.add(raw);
        }
        return raws;

    }


    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);


        Product object = new Product();
        object.setProduct_name(json.get("product_name").asText());

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
                    object.setProduct_name(json.get("product_name").asText());

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
