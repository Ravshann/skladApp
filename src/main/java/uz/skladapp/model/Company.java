package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_ID;

    private String name;
    private String logo;
    private String address;
    private String company_phone;

    @JsonManagedReference
    @OneToMany(mappedBy="company")
    private List<UserCompany> users;

    public Long getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(Long company_ID) {
        this.company_ID = company_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public List<UserCompany> getUsers() {
        return users;
    }

    public void setUsers(List<UserCompany> users) {
        this.users = users;
    }
}
