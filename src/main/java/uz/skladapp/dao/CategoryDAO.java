package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Category;
import uz.skladapp.model.repositories.CategoryRepository;

import java.util.Optional;

@Component
public class CategoryDAO {
    @Autowired
    private CategoryRepository repository;

    public Iterable<Category> getAll() {
        return repository.findAll();
    }

    public Optional<Category> get(Long id) {
        return repository.findById(id);
    }

    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Category object = new Category();

        //extracting data from json
        object.setCategory_name(json.get("category_name").asText());
        object.setCategory_notes(json.get("category_notes").asText());
        object.setUnit_measure(json.get("unit_measure").asText());
        Long id = null;
        if (!json.get("parent_category_ID").toString().equals("null")) {
            id = Long.valueOf(json.get("parent_category_ID").asText());
            Optional<Category> parent_category = repository.findById(id);
            object.setParent_category_ID(parent_category.get());
        }
        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Category update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(object -> {
                    object.setCategory_name(json.get("category_name").asText());
                    object.setCategory_notes(json.get("category_notes").asText());
                    object.setUnit_measure(json.get("unit_measure").asText());
                    Long p_id = null;
                    if (!json.get("parent_category_ID").toString().equals("null")) {
                        p_id = Long.valueOf(json.get("parent_category_ID").asText());
                        Optional<Category> parent_category = repository.findById(p_id);
                        object.setParent_category_ID(parent_category.get());
                    }
                    return repository.save(object);
                })
                .get();
    }
}
