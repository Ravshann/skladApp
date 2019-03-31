package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.*;
import uz.skladapp.model.repositories.CategoryRepository;
import uz.skladapp.model.repositories.ProductRepository;

import java.util.Optional;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Optional<Product> get(Long id) {
        return repository.findById(id);
    }

    public Iterable<Product> getAll() {
        return repository.findAll();
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
}
