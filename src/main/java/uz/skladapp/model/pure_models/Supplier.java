package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_ID;

    private String supplier_name;

    @JsonManagedReference
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierDepartment> departments;


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
