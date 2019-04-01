package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Client;
import uz.skladapp.model.Permission;
import uz.skladapp.model.repositories.ClientRepository;

import java.util.Optional;

@Component
public class ClientDAO {
    @Autowired
    private ClientRepository repository;

    public Iterable<Client> getAll() {
        return repository.findAll();
    }

    public Optional<Client> get(Long id) {
        return repository.findById(id);
    }

    public void create(String string) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Client object = new Client();

        //extracting data json
        object.setClient_name(json.get("client_name").asText());
        object.setRegion(json.get("region").asText());

        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Client update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);

        return repository.findById(id)
                .map(object -> {
                    //extracting data json
                    object.setClient_name(json.get("client_name").asText());
                    object.setRegion(json.get("region").asText());
                    return repository.save(object);
                })
                .get();
    }

}
