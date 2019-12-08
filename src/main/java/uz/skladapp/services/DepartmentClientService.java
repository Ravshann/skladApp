package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Client;
import uz.skladapp.model.pure_models.Department;
import uz.skladapp.model.pure_models.DepartmentClient;
import uz.skladapp.repositories.ClientRepository;
import uz.skladapp.repositories.DepartmentRepository;
import uz.skladapp.DTO.ClientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentClientService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DepartmentService departmentService;


    public List<Client> getList(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        List<Client> clients = new ArrayList<>();
        for (DepartmentClient departmentClient : department.get().getClients()) {
            clients.add(departmentClient.getClient());

        }
        return clients;
    }

    public List<ClientDTO> getRawList(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        List<ClientDTO> clients = new ArrayList<>();
        for (DepartmentClient departmentClient : department.get().getClients()) {
            clients.add(new ClientDTO(
                    departmentClient.getClient().getClient_ID(),
                    departmentClient.getClient().getClient_name(),
                    departmentClient.getClient().getRegion(),
                    departmentClient.getClient().getClient_type()));

        }
        return clients;
    }

    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long id_d = Long.valueOf(json.get("department_ID").toString());
        Long id_c = Long.valueOf(json.get("client_ID").toString());
        Department department = departmentRepository.findById(id_d).get();
        Client client = clientRepository.findById(id_c).get();
        departmentService.addClient(client, department);
        departmentRepository.save(department);

    }

    public void delete(String object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(object);
        Long id_d = Long.valueOf(json.get("department_ID").toString());
        Long id_c = Long.valueOf(json.get("client_ID").toString());
        Department department = departmentRepository.findById(id_d).get();
        Client client = clientRepository.findById(id_c).get();
        departmentService.removeClient(client, department);
        departmentRepository.save(department);
    }
}
