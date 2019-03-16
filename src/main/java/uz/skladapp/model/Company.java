package uz.skladapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long company_ID;

    private String name;
    private String logo;
    private String address;
    private String company_phone;


    public long getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(long company_ID) {
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

    public String getPhone() {
        return company_phone;
    }

    public void setPhone(String phone) {
        this.company_phone = phone;
    }
}
