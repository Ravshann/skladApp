package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    @Column(unique = true)
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


    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCompany> companies;


    // Create an association object for the relationship and set its data.
    public void addCompany(Company company) {
        UserCompany association = new UserCompany();
        association.setCompany(company);
        association.setUser(this);
        association.setCompany_ID(company.getCompany_ID());
        association.setUser_ID(this.getUser_ID());


        if (this.companies == null)
            this.companies = new ArrayList<>();

        this.companies.add(association);
        // Also add the association object to the other class
        company.getUsers().add(association);
    }

    public void removeCompany(Company object) {
        for (Iterator<UserCompany> iterator = companies.iterator(); iterator.hasNext(); ) {
            UserCompany userCompany = iterator.next();

            if (userCompany.getCompany().equals(this) && userCompany.getCompany().equals(object)) {
                iterator.remove();
                userCompany.getCompany().getUsers().remove(userCompany);
                userCompany.setUser(null);
                userCompany.setCompany(null);
            }
        }
    }


    //setters getters


    public List<UserCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(List<UserCompany> companies) {
        this.companies = companies;
    }

    public Long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Long user_ID) {
        this.user_ID = user_ID;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Company getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(Company company_ID) {
        this.company_ID = company_ID;
    }

    public Role getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(Role role_ID) {
        this.role_ID = role_ID;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }


}
