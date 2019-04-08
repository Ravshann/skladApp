package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Supplier;
import uz.skladapp.model.repositories.SupplierRepository;
import uz.skladapp.model.special_models.SupplierRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SupplierDAO {
    @Autowired
    private SupplierRepository repository;


    public List<SupplierRaw> getList() {
        List<Supplier> originals = repository.findAll();
        List<SupplierRaw> raws = new ArrayList<>();
        for (Supplier object : originals) {
            SupplierRaw raw = new SupplierRaw(object.getSupplier_ID(), object.getSupplier_name());
            raws.add(raw);
        }
        return raws;
    }

    public void save(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Supplier object = new Supplier();
        object.setSupplier_name(json.get("supplier_name").asText());
        repository.save(object);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(String data, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);

        repository.findById(id)
                .map(object -> {
                    //extracting data json
                    object.setSupplier_name(json.get("supplier_name").asText());
                    return repository.save(object);
                })
                .get();
    }

}
