package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permission_ID;

    private String permission_name;
    private String permission_description;

    @JsonManagedReference
    @OneToMany(mappedBy="permission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolePermission> roles;

    //setters getters


    public Long getPermission_ID() {
        return permission_ID;
    }

    public void setPermission_ID(Long permission_ID) {
        this.permission_ID = permission_ID;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_description() {
        return permission_description;
    }

    public void setPermission_description(String permission_description) {
        this.permission_description = permission_description;
    }

    public List<RolePermission> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePermission> roles) {
        this.roles = roles;
    }
}
