package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import uz.skladapp.model.ID_classes.UserPermissionID;

import javax.persistence.*;

@Entity
@Table(name="ROLE_PERMISSION")
@IdClass(UserPermissionID.class)
public class RolePermission {
    @Id
    private long role_ID;
    @Id
    private long permission_ID;

    @JsonBackReference // this needed to json generation
    @ManyToOne
    @JoinColumn(name = "permission_ID", updatable = false, insertable = false, referencedColumnName = "permission_ID")
    private Permission permission;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role_ID", updatable = false, insertable = false, referencedColumnName = "role_ID")
    private Role role;

    // setters getters


    public long getRole_ID() {
        return role_ID;
    }

    public void setRole_ID(long role_ID) {
        this.role_ID = role_ID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getPermission_ID() {
        return permission_ID;
    }

    public void setPermission_ID(long permission_ID) {
        this.permission_ID = permission_ID;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }


}
