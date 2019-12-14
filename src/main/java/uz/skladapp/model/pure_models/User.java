package uz.skladapp.model.pure_models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_ID;

    //foreign key
    @ManyToOne
    @JoinColumn(name = "supervisor")
    private User supervisor;

    private String username;
    private String first_name;
    private String last_name;
    private String password;

    //foreign key
    @OneToOne
    @JoinColumn(name = "company_ID")
    private Company company_ID;

    //foreign key
    @ManyToOne
    @JoinColumn(name = "role_ID")
    private Role role_ID;

    private String user_phone;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCompany> companies;

}
