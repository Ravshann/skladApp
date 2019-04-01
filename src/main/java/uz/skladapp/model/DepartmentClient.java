package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import uz.skladapp.model.ID_classes.DepartmentClientID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name="DEPARTMENT_CLIENT")
@IdClass(DepartmentClientID.class)
public class DepartmentClient {
    @Id
    private long department_ID;
    @Id
    private long client_ID;

    @JsonBackReference // this needed to json generation
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "department_ID", updatable = false, insertable = false, referencedColumnName = "department_ID")
    private Department department;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "client_ID", updatable = false, insertable = false, referencedColumnName = "client_ID")
    private Client client;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
