package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.Department;
import uz.skladapp.model.Supplier;
import uz.skladapp.model.SupplierDepartment;
import uz.skladapp.model.repositories.DepartmentRepository;
import uz.skladapp.model.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SupplierDepartmentDAO {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Department> getList(Long id) {
        Optional<Supplier> role = supplierRepository.findById(Long.valueOf(id));
        List<Department> departments = new ArrayList<>();
        for (SupplierDepartment association : role.get().getDepartments()) {
            departments.add(association.getDepartment());

        }
        return departments;
    }

    public void add(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long id_r = Long.valueOf(json.get("supplier_ID").toString());
        Long id_p = Long.valueOf(json.get("department_ID").toString());
        Optional<Supplier> supplier = supplierRepository.findById(id_r);
        Optional<Department> department = departmentRepository.findById(id_p);
        supplier.get().addDepartment(department.get());
        supplierRepository.save(supplier.get());
    }

    public void delete(String object) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(object);
        Long id_r = Long.valueOf(json.get("supplier_ID").toString());
        Long id_p = Long.valueOf(json.get("department_ID").toString());
        Optional<Supplier> supplier = supplierRepository.findById(id_r);
        Optional<Department> department = departmentRepository.findById(id_p);
        supplier.get().removeDepartment(department.get());
        supplierRepository.save(supplier.get());
    }
}
