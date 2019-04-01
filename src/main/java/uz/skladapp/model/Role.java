package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_ID;
    private String role_name;

    @JsonManagedReference
    @OneToMany(mappedBy="role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolePermission> permissions;

    public Role() {
    }

    // Create an association object for the relationship and set its data.
    public void addPermission(Permission permission) {
        RolePermission association = new RolePermission();
        association.setPermission(permission);
        association.setRole(this);
        association.setPermission_ID(permission.getPermission_ID());
        association.setRole_ID(this.getRole_ID());


        if(this.permissions == null)
            this.permissions = new ArrayList<>();

        this.permissions.add(association);
        // Also add the association object to the other class
        permission.getRoles().add(association);

    }

    public void removePermission(Permission permission) {
        for (Iterator<RolePermission> iterator = permissions.iterator(); iterator.hasNext(); ) {
            RolePermission rolePermission = iterator.next();

            if (rolePermission.getRole().equals(this) && rolePermission.getPermission().equals(permission)) {
                iterator.remove();
                rolePermission.getPermission().getRoles().remove(rolePermission);
                rolePermission.setRole(null);
                rolePermission.setPermission(null);
            }
        }
    }

    //setters getters


    public Long getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(Long role_ID) {
        this.role_ID = role_ID;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}
