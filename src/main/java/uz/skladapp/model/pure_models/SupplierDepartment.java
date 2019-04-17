package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import uz.skladapp.model.ID_classes.SupplierDepartmentID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "SUPPLIER_DEPARTMENT")
@IdClass(SupplierDepartmentID.class)
public class SupplierDepartment {
    @Id
    private long supplier_ID;
    @Id
    private long department_ID;

    @JsonBackReference // this needed to json generation
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "supplier_ID", updatable = false, insertable = false, referencedColumnName = "supplier_ID")
    private Supplier supplier;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "department_ID", updatable = false, insertable = false, referencedColumnName = "department_ID")
    private Department department;


    public long getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(long supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public long getDepartment_ID() {
        return department_ID;
    }

    public void setDepartment_ID(long department_ID) {
        this.department_ID = department_ID;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
