package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Category;
import uz.skladapp.model.repositories.CategoryRepository;
import uz.skladapp.model.raw_models.CategoryRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryDAO {
    @Autowired
    private CategoryRepository repository;

    public Iterable<CategoryRaw> getAll() {
        List<Category> originals = repository.findAll();
        List<CategoryRaw> raws = new ArrayList<>();
        originals.forEach(object -> {
            CategoryRaw raw = new CategoryRaw(
                    object.getCategory_ID(),
                    object.getCategory_name(),
                    object.getCategory_notes(),
                    object.getUnit_measure());
            if (object.getParent_category_ID() != null) {
                raw.setParent_category_ID(object.getParent_category_ID().getCategory_ID());
                raw.setParent_category_name(object.getParent_category_ID().getCategory_name());
            }
            raws.add(raw);
        });
        return raws;
    }

    public CategoryRaw get(Long id) {

        Optional<Category> object = repository.findById(id);
        if (object.isPresent()) {
            CategoryRaw raw = new CategoryRaw(
                    object.get().getCategory_ID(),
                    object.get().getCategory_name(),
                    object.get().getCategory_notes(),
                    object.get().getUnit_measure());
            if (object.get().getParent_category_ID() != null) {
                raw.setParent_category_ID(object.get().getParent_category_ID().getCategory_ID());
                raw.setParent_category_name(object.get().getParent_category_ID().getCategory_name());
            }
            return raw;
        } else
            return new CategoryRaw();
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
        if (!json.get("parent_category_ID").asText().isEmpty()) {
            id = json.get("parent_category_ID").asLong();
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
                    object.setParent_category_ID(null);
                    if (!json.get("parent_category_ID").asText().isEmpty()) {
                        p_id = json.get("parent_category_ID").asLong();
                        Optional<Category> parent_category = repository.findById(p_id);
                        object.setParent_category_ID(parent_category.get());
                    }
                    return repository.save(object);
                })
                .get();
    }
}
