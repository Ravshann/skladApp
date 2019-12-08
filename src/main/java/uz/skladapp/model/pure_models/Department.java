package uz.skladapp.model.pure_models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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

}
