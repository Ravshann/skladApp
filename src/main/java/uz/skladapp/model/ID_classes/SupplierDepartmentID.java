package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class SupplierDepartmentID implements Serializable {
    private long supplier_ID;
    private long department_ID;

    //setters getters


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

    // needed for spring
    @Override
    public int hashCode() {
        return (int) (department_ID + supplier_ID);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof SupplierDepartmentID) {
            SupplierDepartmentID otherId = (SupplierDepartmentID) object;
            return (otherId.supplier_ID == this.supplier_ID) && (otherId.department_ID == this.department_ID);
        }
        return false;
    }
}

