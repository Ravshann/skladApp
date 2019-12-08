package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.*;
import uz.skladapp.repositories.CompanyRepository;
import uz.skladapp.repositories.DepartmentRepository;
import uz.skladapp.repositories.UserRepository;
import uz.skladapp.DTO.DepartmentDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;




    public Iterable<DepartmentDTO> getAllDepartments() {
        List<Department> originals = departmentRepository.findAll();
        List<DepartmentDTO> raws = new ArrayList<>();
        for (Department object : originals) {
            DepartmentDTO raw = new DepartmentDTO(
                    object.getDepartment_ID(),
                    object.getName(),
                    object.getDescription(),
                    object.getAddress(),
                    object.getCompany_ID(),
                    object.getDepartment_manager_ID(),
                    object.getDepartment_phone()
            );
            raws.add(raw);
        }
        return raws;
    }


    public void createDepartment(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);

        Long c_id = Long.valueOf(json.get("company_ID").toString());
        Optional<Company> company = companyRepository.findById(c_id);

        Long m_id = (Long) Long.valueOf(json.get("department_manager_ID").toString());
        Optional<User> user = userRepository.findById(m_id);

        Department department = new Department();
        department.setDescription(json.get("description").asText());
        department.setName(json.get("name").asText());
        department.setAddress(json.get("address").asText());
        department.setDepartment_phone(json.get("department_phone").asText());
        department.setCompany_ID(company.get());
        department.setDepartment_manager_ID(user.get());

        departmentRepository.save(department);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);

        Long c_id = Long.valueOf(json.get("company_ID").toString());
        Optional<Company> company = companyRepository.findById(c_id);

        Long m_id = (Long) Long.valueOf(json.get("department_manager_ID").toString());
        Optional<User> user = userRepository.findById(m_id);

        Department department = new Department();

        return departmentRepository.findById(id)
                .map(object -> {
                    object.setDescription(json.get("description").asText());
                    object.setName(json.get("name").asText());
                    object.setAddress(json.get("address").asText());
                    object.setDepartment_phone(json.get("department_phone").asText());
                    object.setCompany_ID(company.get());
                    object.setDepartment_manager_ID(user.get());
                    return departmentRepository.save(object);
                })
                .get();
    }
    public void addClient(Client client, Department department) {
        DepartmentClient association = new DepartmentClient();
        association.setClient(client);
        association.setDepartment(department);
        association.setClient_ID(client.getClient_ID());
        association.setDepartment_ID(department.getDepartment_ID());

        if (department.getClients() == null)
            department.setClients(new ArrayList<>());

        department.getClients().add(association);

        client.getDepartments().add(association);

    }

    public void removeClient(Client client, Department department) {
        for (Iterator<DepartmentClient> iterator = department.getClients().iterator(); iterator.hasNext(); ) {
            DepartmentClient departmentClient = iterator.next();

            if (departmentClient.getDepartment().equals(this) && departmentClient.getClient().equals(client)) {
                iterator.remove();
                departmentClient.getClient().getDepartments().remove(departmentClient);
                departmentClient.setDepartment(null);
                departmentClient.setClient(null);
            }
        }
    }
}
