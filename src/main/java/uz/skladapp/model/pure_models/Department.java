package uz.skladapp.model.pure_models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_ID;

    private String name;
    private String description;
    private String address;


    @ManyToOne
    @JoinColumn(name = "company_ID")
    private Company company_ID;

    @OneToOne
    @JoinColumn(name = "department_manager_ID")
    private User department_manager_ID;

    @JsonManagedReference
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierDepartment> suppliers;

    private String department_phone;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentClient> clients;


    public Long getDepartment_ID() {
        return department_ID;
    }

    public void setDepartment_ID(Long department_ID) {
        this.department_ID = department_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(Company company_ID) {
        this.company_ID = company_ID;
    }

    public User getDepartment_manager_ID() {
        return department_manager_ID;
    }

    public void setDepartment_manager_ID(User department_manager_ID) {
        this.department_manager_ID = department_manager_ID;
    }

    public String getDepartment_phone() {
        return department_phone;
    }

    public void setDepartment_phone(String department_phone) {
        this.department_phone = department_phone;
    }

    public List<SupplierDepartment> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierDepartment> suppliers) {
        this.suppliers = suppliers;
    }

    public List<DepartmentClient> getClients() {
        return clients;
    }

    public void setClients(List<DepartmentClient> clients) {
        this.clients = clients;
    }
}
