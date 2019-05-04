package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Department;
import uz.skladapp.model.pure_models.Supplier;
import uz.skladapp.model.pure_models.SupplierDepartment;
import uz.skladapp.model.repositories.DepartmentRepository;
import uz.skladapp.model.repositories.SupplierRepository;
import uz.skladapp.model.raw_models.DepartmentRaw;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDepartmentDAO {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SupplierDAO supplierDAO;


    public List<DepartmentRaw> getList(Long supplier_id) {
        Supplier supplier = supplierRepository.findById(supplier_id).get();
        List<DepartmentRaw> departments = new ArrayList<>();
        for (SupplierDepartment association : supplier.getDepartments()) {
            DepartmentRaw raw = new DepartmentRaw(
                    association.getDepartment().getDepartment_ID(),
                    association.getDepartment().getName(),
                    association.getDepartment().getDescription(),
                    association.getDepartment().getAddress(),
                    association.getDepartment().getCompany_ID().getCompany_ID(),
                    association.getDepartment().getDepartment_manager_ID().getUser_ID(),
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
        supplierDAO.addDepartment(department, supplier);
        supplierRepository.save(supplier);
    }

    public void delete(String object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(object);
        Long id_r = json.get("supplier_ID").asLong();
        Long id_p = json.get("department_ID").asLong();
        Supplier supplier = supplierRepository.findById(id_r).get();
        Department department = departmentRepository.findById(id_p).get();
        supplierDAO.removeDepartment(department, supplier);
        supplierRepository.save(supplier);
    }
}
