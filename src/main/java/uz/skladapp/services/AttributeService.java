package uz.skladapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Attribute;
import uz.skladapp.repositories.AttributeRepository;
import uz.skladapp.DTO.AttributeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository repository;

    public AttributeDTO getAttribute(Long id) {

        Optional<Attribute> attribute = repository.findById(id);
        if (attribute.isPresent())
            return new AttributeDTO(attribute.get().getAttribute_ID(), attribute.get().getAttribute_name());
        else
            return new AttributeDTO();
    }

    public Iterable<AttributeDTO> getAllAttributes() {
        List<Attribute> originals = repository.findAll();
        List<AttributeDTO> dtos = new ArrayList<>();

        //new version with java 8

        originals.forEach(
                object -> dtos.add(
                        new AttributeDTO(object.getAttribute_ID(), object.getAttribute_name())));

        return dtos;
    }


    public void createAttribute(AttributeDTO attributeDTO) {
        if (attributeDTO != null) {
            Attribute attribute = new Attribute();
            attribute.setAttribute_name(attributeDTO.getAttribute_name());
            repository.save(attribute);
        } else {
            System.out.println("attribute was not created: attribute_name is not provided");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Attribute update(AttributeDTO dto, Long id) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.readTree(string);
//        Predicate<JsonNode> notNull = object -> object.get("attribute_name") == null;
//        Predicate<JsonNode> notEmpty = object -> object.get("attribute_name").asText().isEmpty();
//        if (!notNull.test(json)) {
//            if (!notEmpty.test(json)) {
//                Optional<Attribute> optional = repository.findById(id)
//                        .map(attribute -> {
//                            attribute.setAttribute_name(json.get("attribute_name").asText());
//                            return repository.save(attribute);
//                        });
//                if (optional.isPresent()) {
//                    return optional.get();
//                } else {
//                    System.out.println("no attribute found by ID: wrong ID provided");
//                    return new Attribute();
//                }
//            } else {
//                System.out.println("attribute was not updated: attribute_name is empty");
//                return new Attribute();
//            }
//        } else {
//            System.out.println("attribute was not updated: attribute_name is expected, but provided ---> " + json.toString());
//            return new Attribute();
//        }
//    }
        if (dto != null) {
            Attribute attribute = repository.findById(id).get();
            attribute.setAttribute_name(dto.getAttribute_name());
            return repository.save(attribute);
        } else {
            System.out.println("something went wrong");
            return new Attribute();
        }
    }
}
