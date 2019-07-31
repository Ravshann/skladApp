package uz.skladapp.model.raw_models;

public class StorageRaw {
    private Long storage_ID;

    private String address;
    private Long department_ID;
    private Long storage_manager_ID;

    private String storage_name;
    private String storage_phone;
    private String department_name;
    private String storage_manager_name;

    public StorageRaw(Long storage_ID, String address, Long department_ID, Long storage_manager_ID, String storage_name, String storage_phone, String storage_manager_name, String department_name) {
        this.storage_ID = storage_ID;
        this.address = address;
        this.department_ID = department_ID;
        this.storage_manager_ID = storage_manager_ID;
        this.storage_name = storage_name;
        this.storage_phone = storage_phone;
        this.storage_manager_name = storage_manager_name;
        this.department_name = department_name;
    }

    public Long getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(Long storage_ID) {
        this.storage_ID = storage_ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDepartment_ID() {
        return department_ID;
    }

    public void setDepartment_ID(Long department_ID) {
        this.department_ID = department_ID;
    }

    public Long getStorage_manager_ID() {
        return storage_manager_ID;
    }

    public void setStorage_manager_ID(Long storage_manager_ID) {
        this.storage_manager_ID = storage_manager_ID;
    }

    public String getStorage_name() {
        return storage_name;
    }

    public void setStorage_name(String storage_name) {
        this.storage_name = storage_name;
    }

    public String getStorage_phone() {
        return storage_phone;
    }

    public void setStorage_phone(String storage_phone) {
        this.storage_phone = storage_phone;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getStorage_manager_name() {
        return storage_manager_name;
    }

    public void setStorage_manager_name(String storage_manager_name) {
        this.storage_manager_name = storage_manager_name;
    }
}
