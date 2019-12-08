package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Category;
import uz.skladapp.repositories.CategoryRepository;
import uz.skladapp.DTO.CategoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Iterable<CategoryDTO> getAll() {
        List<Category> originals = repository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();
        originals.forEach(object -> {
            CategoryDTO dto = new CategoryDTO(
                    object.getCategory_ID(),
                    object.getCategory_name(),
                    object.getCategory_notes(),
                    object.getUnit_measure());
            if (object.getParent_category_ID() != null) {
                dto.setParent_category_ID(object.getParent_category_ID().getCategory_ID());
                dto.setParent_category_name(object.getParent_category_ID().getCategory_name());
            }
            dtos.add(dto);
        });
        return dtos;
    }

    public CategoryDTO get(Long id) {

        Optional<Category> object = repository.findById(id);
        if (object.isPresent()) {
            CategoryDTO raw = new CategoryDTO(
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
            return new CategoryDTO();
    }

    public Category create(CategoryDTO dto) {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree(string);
        Category object = new Category();
        if (dto != null) {
            object.setCategory_name(dto.getCategory_name());
            object.setCategory_notes(dto.getCategory_notes());
            object.setUnit_measure(dto.getUnit_measure());
            Optional<Category> parent_category = repository.findById(dto.getParent_category_ID());
            object.setParent_category_ID(parent_category.get());
            return repository.save(object);
        } else return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Category update(CategoryDTO dto, Long id) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(object -> {

                    object.setCategory_name(dto.getCategory_name());
                    object.setCategory_notes(dto.getCategory_notes());
                    object.setUnit_measure(dto.getUnit_measure());
                    object.setParent_category_ID(null);
                    Optional<Category> parent_category = repository.findById(dto.getParent_category_ID());
                    object.setParent_category_ID(parent_category.get());
                    return repository.save(object);
                })
                .get();
    }
}
