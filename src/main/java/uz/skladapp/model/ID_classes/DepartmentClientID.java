package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class DepartmentClientID implements Serializable {
    private long department_ID;
    private long client_ID;

    public long getDepartment_ID() {
        return department_ID;
    }

    public void setDepartment_ID(long department_ID) {
        this.department_ID = department_ID;
    }

    public long getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(long client_ID) {
        this.client_ID = client_ID;
    }

    //this is needed for  IDclasses
    public int hashCode() {
        return (int) (department_ID + client_ID);
    }

    public boolean equals(Object object) {
        if (object instanceof DepartmentClientID) {
            DepartmentClientID otherId = (DepartmentClientID) object;
            return (otherId.department_ID == this.department_ID) && (otherId.client_ID == this.client_ID);
        }
        return false;
    }
}
