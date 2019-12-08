package uz.skladapp.model.ID_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class SupplierDepartmentID implements Serializable {
    private long supplier_ID;
    private long department_ID;

}

