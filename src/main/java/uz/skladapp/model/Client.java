package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_ID;

    private String client_name;
    private String region;


    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentClient> departments;

    public Client() {
    }

    public Long getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(Long client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<DepartmentClient> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentClient> departments) {
        this.departments = departments;
    }
}
