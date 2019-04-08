package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Client;
import uz.skladapp.model.Department;
import uz.skladapp.model.DepartmentClient;
import uz.skladapp.model.repositories.ClientRepository;
import uz.skladapp.model.repositories.DepartmentRepository;
import uz.skladapp.model.special_models.ClientRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentClientDAO {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ClientRepository clientRepository;


    public List<Client> getList(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        List<Client> clients = new ArrayList<>();
        for (DepartmentClient departmentClient : department.get().getClients()) {
            clients.add(departmentClient.getClient());

        }
        return clients;
    }

    public List<ClientRaw> getRawList(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        List<ClientRaw> clients = new ArrayList<>();
        for (DepartmentClient departmentClient : department.get().getClients()) {
            clients.add(new ClientRaw(
                    departmentClient.getClient().getClient_ID(),
                    departmentClient.getClient().getClient_name(),
                    departmentClient.getClient().getRegion()));

        }
        return clients;
    }

    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long id_d = Long.valueOf(json.get("department_ID").toString());
        Long id_c = Long.valueOf(json.get("client_ID").toString());
        Optional<Department> department = departmentRepository.findById(id_d);
        Optional<Client> client = clientRepository.findById(id_c);
        department.get().addClient(client.get());
        departmentRepository.save(department.get());

    }

    public void delete(String object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(object);
        Long id_d = Long.valueOf(json.get("department_ID").toString());
        Long id_c = Long.valueOf(json.get("client_ID").toString());
        Optional<Department> department = departmentRepository.findById(id_d);
        Optional<Client> client = clientRepository.findById(id_c);
        department.get().removeClient(client.get());
        departmentRepository.save(department.get());
    }
}
