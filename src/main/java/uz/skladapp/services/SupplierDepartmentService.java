package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Department;
import uz.skladapp.model.pure_models.Supplier;
import uz.skladapp.model.pure_models.SupplierDepartment;
import uz.skladapp.repositories.DepartmentRepository;
import uz.skladapp.repositories.SupplierRepository;
import uz.skladapp.DTO.DepartmentDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierDepartmentService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SupplierService supplierService;


    public List<DepartmentDTO> getList(Long supplier_id) {
        Supplier supplier = supplierRepository.findById(supplier_id).get();
        List<DepartmentDTO> departments = new ArrayList<>();
        for (SupplierDepartment association : supplier.getDepartments()) {
            DepartmentDTO raw = new DepartmentDTO(
                    association.getDepartment().getDepartment_ID(),
                    association.getDepartment().getName(),
                    association.getDepartment().getDescription(),
                    association.getDepartment().getAddress(),
                    association.getDepartment().getCompany_ID(),
                    association.getDepartment().getDepartment_manager_ID(),
                    association.getDepartment().getDepartment_phone()
            );
            departments.add(raw);

        }
        return departments;
    }

    public void add(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long id_r = json.get("supplier_ID").asLong();
        Long id_p = json.get("department_ID").asLong();
        Supplier supplier = supplierRepository.findById(id_r).get();
        Department department = departmentRepository.findById(id_p).get();
        supplierService.addDepartment(department, supplier);
        supplierRepository.save(supplier);
    }

    public void delete(String object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(object);
        Long id_r = json.get("supplier_ID").asLong();
        Long id_p = json.get("department_ID").asLong();
        Supplier supplier = supplierRepository.findById(id_r).get();
        Department department = departmentRepository.findById(id_p).get();
        supplierService.removeDepartment(department, supplier);
        supplierRepository.save(supplier);
    }
}
