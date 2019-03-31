package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Attribute;
import uz.skladapp.model.repositories.AttributeRepository;

import java.util.Optional;

@Component
public class AttributeDAO {
    @Autowired
    private AttributeRepository repository;

    public Optional<Attribute> getAttribute(Long id) {

        Optional<Attribute> attribute = repository.findById(id);
        System.out.println(attribute.toString());
        return attribute;
    }

    public Iterable<Attribute> getAllAttributes() {
        return repository.findAll();
    }


    public void createAttribute(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Attribute attribute = new Attribute();
        attribute.setAttribute_name(json.get("name").asText());
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
                    attribute.setAttribute_name(json.get("name").asText());
                    return repository.save(attribute);
                })
                .get();
    }
}
