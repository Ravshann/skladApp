package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.repositories.AttributeRepository;
import uz.skladapp.model.special_models.AttributeRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AttributeDAO {
    @Autowired
    private AttributeRepository repository;

    public AttributeRaw getAttribute(Long id) {

        Optional<Attribute> attribute = repository.findById(id);
        return new AttributeRaw(attribute.get().getAttribute_ID(), attribute.get().getAttribute_name());
    }

    public Iterable<AttributeRaw> getAllAttributes() {
        List<Attribute> originals = repository.findAll();
        List<AttributeRaw> raws = new ArrayList<>();
        for (Attribute object: originals)
        {
            raws.add(new AttributeRaw(object.getAttribute_ID(), object.getAttribute_name()));
        }
        return raws;
    }


    public void createAttribute(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Attribute attribute = new Attribute();
        attribute.setAttribute_name(json.get("attribute_name").asText());
        repository.save(attribute);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Attribute update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(attribute -> {
                    attribute.setAttribute_name(json.get("attribute_name").asText());
                    return repository.save(attribute);
                })
                .get();
    }
}
