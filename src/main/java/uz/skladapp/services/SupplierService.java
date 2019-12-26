package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Department;
import uz.skladapp.model.pure_models.Supplier;
import uz.skladapp.model.pure_models.SupplierDepartment;
import uz.skladapp.repositories.SupplierRepository;
import uz.skladapp.DTO.SupplierDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repository;


    public List<SupplierDTO> getList() {
        List<Supplier> originals = repository.findAllByOrderBySupplierNameAsc();
        List<SupplierDTO> raws = new ArrayList<>();
        for (Supplier object : originals) {
            SupplierDTO raw = new SupplierDTO(object.getSupplier_ID(), object.getSupplierName());
            raws.add(raw);
        }
        return raws;
    }

    public void save(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Supplier object = new Supplier();
        object.setSupplierName(json.get("supplier_name").asText());
        repository.save(object);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(String data, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);

        repository.findById(id)
                .map(object -> {
                    //extracting data json
                    object.setSupplierName(json.get("supplier_name").asText());
                    return repository.save(object);
                })
                .get();
    }


    // Create an association object for the relationship and set its data.
    public void addDepartment(Department department, Supplier supplier) {
        SupplierDepartment association = new SupplierDepartment();
        association.setDepartment(department);
        association.setSupplier(supplier);
        association.setDepartment_ID(department.getDepartment_ID());
        association.setSupplier_ID(supplier.getSupplier_ID());


        if (supplier.getDepartments() == null)
            supplier.setDepartments(new ArrayList<>());

        supplier.getDepartments().add(association);

        department.getSuppliers().add(association);

    }

    public void removeDepartment(Department department, Supplier supplier) {
        for (Iterator<SupplierDepartment> iterator = supplier.getDepartments().iterator(); iterator.hasNext(); ) {
            SupplierDepartment supplierDepartment = iterator.next();

            if (supplierDepartment.getSupplier().equals(supplier) && supplierDepartment.getDepartment().equals(department)) {
                iterator.remove();
                supplierDepartment.getDepartment().getSuppliers().remove(supplierDepartment);
                supplierDepartment.setSupplier(null);
                supplierDepartment.setDepartment(null);
            }
        }
    }

}
