package uz.skladapp.model.raw_models;

import uz.skladapp.model.pure_models.Company;
import uz.skladapp.model.pure_models.User;

public class DepartmentRaw {
    private Long department_ID;

    private String name;
    private String description;
    private String address;
    private Company company;
    private User department_manager;
    private String department_phone;

    public DepartmentRaw(Long department_ID, String name, String description, String address, Company company, User department_manager, String department_phone) {
        this.department_ID = department_ID;
        this.name = name;
        this.description = description;
        this.address = address;
        this.company = company;
        this.department_manager = department_manager;
        this.department_phone = department_phone;
    }


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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getDepartment_manager() {
        return department_manager;
    }

    public void setDepartment_manager(User department_manager) {
        this.department_manager = department_manager;
    }

    public String getDepartment_phone() {
        return department_phone;
    }

    public void setDepartment_phone(String department_phone) {
        this.department_phone = department_phone;
    }
}
