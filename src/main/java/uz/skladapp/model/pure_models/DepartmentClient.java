package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import uz.skladapp.model.ID_classes.DepartmentClientID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
@Entity
@Table(name = "DEPARTMENT_CLIENT")
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

}
