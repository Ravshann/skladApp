package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import uz.skladapp.model.ID_classes.SupplierDepartmentID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
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

}
