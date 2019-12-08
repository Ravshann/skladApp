package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Permission;
import uz.skladapp.repositories.PermissionRepository;
import uz.skladapp.DTO.PermissionDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository repository;

    public Iterable<PermissionDTO> getAll() {
        List<Permission> originals = repository.findAll();
        List<PermissionDTO> raws = new ArrayList<>();
        for (Permission object : originals) {
            PermissionDTO raw = new PermissionDTO(object.getPermission_ID(), object.getPermission_name(), object.getPermission_description());
            raws.add(raw);
        }
        return raws;
    }

    public PermissionDTO get(Long id) {
        Permission object = repository.findById(id).get();
        PermissionDTO raw = new PermissionDTO(object.getPermission_ID(), object.getPermission_name(), object.getPermission_description());
        return raw;
    }

    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Permission object = new Permission();

        //extracting data json
        object.setPermission_name(json.get("permission_name").asText());
        object.setPermission_description(json.get("permission_description").asText());

        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Permission update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);

        return repository.findById(id)
                .map(object -> {
                    //extracting data json
                    object.setPermission_name(json.get("permission_name").asText());
                    object.setPermission_description(json.get("permission_description").asText());
                    return repository.save(object);
                })
                .get();
    }
}
