package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Supplier;
import uz.skladapp.model.repositories.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Component
public class SupplierDAO {
    @Autowired
    private SupplierRepository repository;


    public List<Supplier> getList() {
        return repository.findAll();
    }

    public Optional<Supplier> get(Long id) {
        return repository.findById(id);
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

}
