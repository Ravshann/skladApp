package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Company;
import uz.skladapp.model.repositories.CompanyRepository;

import java.util.Optional;

@Component
public class CompanyDAO {
    @Autowired
    private CompanyRepository repository;

    public Iterable<Company> getAll() {
        return repository.findAll();
    }

    public Optional<Company> get(Long id) {
        return repository.findById(id);
    }

    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Company object = new Company();

        //extracting data json
        object.setAddress(json.get("address").asText());
        object.setCompany_phone(json.get("company_phone").asText());
        object.setLogo(json.get("logo").asText());
        object.setName(json.get("name").asText());

        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Company update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        return repository.findById(id)
                .map(object -> {
                    object.setAddress(json.get("address").asText());
                    object.setCompany_phone(json.get("company_phone").asText());
                    object.setLogo(json.get("logo").asText());
                    object.setName(json.get("name").asText());
                    return repository.save(object);
                })
                .get();
    }
}
