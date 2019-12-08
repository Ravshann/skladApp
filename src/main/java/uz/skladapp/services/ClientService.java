package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Client;
import uz.skladapp.repositories.ClientRepository;
import uz.skladapp.DTO.ClientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Iterable<ClientDTO> getAll() {
        List<Client> originals = repository.findAll();
        List<ClientDTO> raws = new ArrayList<>();
        originals.forEach(object ->
        {
            ClientDTO raw = new ClientDTO(object.getClient_ID(), object.getClient_name(), object.getRegion(), object.getClient_type());
            raws.add(raw);
        });
        return raws;
    }

    public ClientDTO get(Long id) {
        Optional<Client> object = repository.findById(id);
        if (object.isPresent())
            return new ClientDTO(
                    object.get().getClient_ID(),
                    object.get().getClient_name(),
                    object.get().getRegion()
                    , object.get().getClient_type());
        else
            return new ClientDTO();
    }

    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Client object = new Client();

        //extracting data json
        object.setClient_name(json.get("client_name").asText());
        object.setRegion(json.get("region").asText());
        object.setClient_type(json.get("client_type").asText());

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
                    object.setClient_type(json.get("client_type").asText());
                    return repository.save(object);
                })
                .get();
    }

    public void clientCreate(Client object) {
        repository.save(object);
    }


}
