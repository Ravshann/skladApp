package uz.skladapp.model.raw_models;

public class CompanyRaw {
    private Long company_ID;
    private String name;
    //    private String logo;
    private String address;
    private String company_phone;

    public CompanyRaw() {
    }

    public CompanyRaw(Long company_ID, String name, String address, String company_phone) {
        this.company_ID = company_ID;
        this.name = name;

        this.address = address;
        this.company_phone = company_phone;
    }

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
}
