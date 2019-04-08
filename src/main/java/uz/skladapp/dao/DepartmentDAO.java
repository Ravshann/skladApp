package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Company;
import uz.skladapp.model.Department;
import uz.skladapp.model.User;
import uz.skladapp.model.repositories.CompanyRepository;
import uz.skladapp.model.repositories.DepartmentRepository;
import uz.skladapp.model.repositories.UserRepository;
import uz.skladapp.model.special_models.DepartmentRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentDAO {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;


    public DepartmentRaw getDepartmentByID(Long id) {

        Department object = departmentRepository.findById(id).get();
        DepartmentRaw raw = new DepartmentRaw(
                object.getDepartment_ID(),
                object.getName(),
                object.getDescription(),
                object.getAddress(),
                object.getCompany_ID().getCompany_ID(),
                object.getDepartment_manager_ID().getUser_ID(),
                object.getDepartment_phone()
        );
        return raw;
    }

    public Iterable<DepartmentRaw> getAllDepartments() {
        List<Department> originals = departmentRepository.findAll();
        List<DepartmentRaw> raws = new ArrayList<>();
        for (Department object : originals) {
            DepartmentRaw raw = new DepartmentRaw(
                    object.getDepartment_ID(),
                    object.getName(),
                    object.getDescription(),
                    object.getAddress(),
                    object.getCompany_ID().getCompany_ID(),
                    object.getDepartment_manager_ID().getUser_ID(),
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
}
