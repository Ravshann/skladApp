package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Permission;
import uz.skladapp.model.repositories.PermissionRepository;
import uz.skladapp.model.special_models.PermissionRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PermissionDAO {
    @Autowired
    private PermissionRepository repository;

    public Iterable<PermissionRaw> getAll() {
        List<Permission> originals = repository.findAll();
        List<PermissionRaw> raws = new ArrayList<>();
        for (Permission object : originals) {
            PermissionRaw raw = new PermissionRaw(object.getPermission_ID(), object.getPermission_name(), object.getPermission_description());
            raws.add(raw);
        }
        return raws;
    }

    public PermissionRaw get(Long id) {
        Permission object = repository.findById(id).get();
        PermissionRaw raw = new PermissionRaw(object.getPermission_ID(), object.getPermission_name(), object.getPermission_description());
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
