package uz.skladapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    private String department_phone;


}
