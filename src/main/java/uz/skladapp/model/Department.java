package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
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

    @JsonManagedReference
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartmentClient> clients;

    // Create an association object for the relationship and set its data.
    public void addClient(Client client) {
        DepartmentClient association = new DepartmentClient();
        association.setClient(client);
        association.setDepartment(this);
        association.setClient_ID(client.getClient_ID());
        association.setDepartment_ID(this.getDepartment_ID());

        if(this.clients == null)
            this.clients = new ArrayList<>();

        this.clients.add(association);
        // Also add the association object to the other class
        client.getDepartments().add(association);

    }

    public void removeClient(Client client) {
        for (Iterator<DepartmentClient> iterator = clients.iterator(); iterator.hasNext(); ) {
            DepartmentClient departmentClient= iterator.next();

            if (departmentClient.getDepartment().equals(this) && departmentClient.getClient().equals(client)) {
                iterator.remove();
                departmentClient.getClient().getDepartments().remove(departmentClient);
                departmentClient.setDepartment(null);
                departmentClient.setClient(null);
            }
        }
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

    public Company getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(Company company_ID) {
        this.company_ID = company_ID;
    }

    public User getDepartment_manager_ID() {
        return department_manager_ID;
    }

    public void setDepartment_manager_ID(User department_manager_ID) {
        this.department_manager_ID = department_manager_ID;
    }

    public String getDepartment_phone() {
        return department_phone;
    }

    public void setDepartment_phone(String department_phone) {
        this.department_phone = department_phone;
    }

    public List<DepartmentClient> getClients() {
        return clients;
    }

    public void setClients(List<DepartmentClient> clients) {
        this.clients = clients;
    }
}
