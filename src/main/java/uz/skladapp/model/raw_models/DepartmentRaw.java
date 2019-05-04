package uz.skladapp.model.raw_models;

public class DepartmentRaw {
    private Long department_ID;

    private String name;
    private String description;
    private String address;
    private Long company_ID;
    private Long department_manager_ID;
    private String department_phone;

    public DepartmentRaw(Long department_ID, String name, String description, String address, Long company_ID, Long department_manager_ID, String department_phone) {
        this.department_ID = department_ID;
        this.name = name;
        this.description = description;
        this.address = address;
        this.company_ID = company_ID;
        this.department_manager_ID = department_manager_ID;
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

    public Long getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(Long company_ID) {
        this.company_ID = company_ID;
    }

    public Long getDepartment_manager_ID() {
        return department_manager_ID;
    }

    public void setDepartment_manager_ID(Long department_manager_ID) {
        this.department_manager_ID = department_manager_ID;
    }

    public String getDepartment_phone() {
        return department_phone;
    }

    public void setDepartment_phone(String department_phone) {
        this.department_phone = department_phone;
    }
}
