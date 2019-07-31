package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.model.repositories.AttributeRepository;
import uz.skladapp.model.raw_models.AttributeRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class AttributeDAO {
    @Autowired
    private AttributeRepository repository;

    public AttributeRaw getAttribute(Long id) {

        Optional<Attribute> attribute = repository.findById(id);
        if (attribute.isPresent())
            return new AttributeRaw(attribute.get().getAttribute_ID(), attribute.get().getAttribute_name());
        else
            return new AttributeRaw();
    }

    public Iterable<AttributeRaw> getAllAttributes() {
        List<Attribute> originals = repository.findAll();
        List<AttributeRaw> raws = new ArrayList<>();

        //new version with java 8

        originals.forEach(
                object -> raws.add(new AttributeRaw(
                        object.getAttribute_ID(),
                        object.getAttribute_name())));

        return raws;
    }


    public void createAttribute(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        if (!json.get("attribute_name").asText().isEmpty() || !json.get("attribute_name").isNull()) {
            Attribute attribute = new Attribute();
            attribute.setAttribute_name(json.get("attribute_name").asText());
            repository.save(attribute);
        } else {
            System.out.println("attribute was not created: attribute_name is not provided");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Attribute update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Predicate<JsonNode> notNull = object -> object.get("attribute_name") == null;
        Predicate<JsonNode> notEmpty = object -> object.get("attribute_name").asText().isEmpty();
        if (!notNull.test(json)) {
            if (!notEmpty.test(json)) {
                Optional<Attribute> optional = repository.findById(id)
                        .map(attribute -> {
                            attribute.setAttribute_name(json.get("attribute_name").asText());
                            return repository.save(attribute);
                        });
                if (optional.isPresent()) {
                    return optional.get();
                } else {
                    System.out.println("no attribute found by ID: wrong ID provided");
                    return new Attribute();
                }
            } else {
                System.out.println("attribute was not updated: attribute_name is empty");
                return new Attribute();
            }
        } else {
            System.out.println("attribute was not updated: attribute_name is expected, but provided ---> " + json.toString());
            return new Attribute();
        }
    }
}
