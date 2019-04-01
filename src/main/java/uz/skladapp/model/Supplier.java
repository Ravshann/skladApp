package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_ID;
    private String supplier_name;

    @JsonManagedReference
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierDepartment> departments;

    // Create an association object for the relationship and set its data.
    public void addDepartment(Department department) {
        SupplierDepartment association = new SupplierDepartment();
        association.setDepartment(department);
        association.setSupplier(this);
        association.setDepartment_ID(department.getDepartment_ID());
        association.setSupplier_ID(this.getSupplier_ID());


        if (this.departments == null)
            this.departments = new ArrayList<>();

        this.departments.add(association);
        // Also add the association object to the other class
        department.getSuppliers().add(association);

    }

    public void removeDepartment(Department department) {
        for (Iterator<SupplierDepartment> iterator = departments.iterator(); iterator.hasNext(); ) {
            SupplierDepartment supplierDepartment = iterator.next();

            if (supplierDepartment.getSupplier().equals(this) && supplierDepartment.getDepartment().equals(department)) {
                iterator.remove();
                supplierDepartment.getDepartment().getSuppliers().remove(supplierDepartment);
                supplierDepartment.setSupplier(null);
                supplierDepartment.setDepartment(null);
            }
        }
    }


    public Long getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(Long supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public List<SupplierDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<SupplierDepartment> departments) {
        this.departments = departments;
    }
}
