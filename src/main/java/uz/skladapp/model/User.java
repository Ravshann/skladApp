package uz.skladapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_ID;

    //foreign key
    @ManyToOne
    @JoinColumn(name = "supervisor")
    private User supervisor;

    private String first_name;
    private String last_name;
    private String email;
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


}
