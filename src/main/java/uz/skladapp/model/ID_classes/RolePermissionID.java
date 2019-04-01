package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class RolePermissionID implements Serializable {
    private long role_ID;
    private long permission_ID;

    //setters getters


    public long getRole_ID() {

        return role_ID;
    }

    public void setRole_ID(long role_ID) {
        this.role_ID = role_ID;
    }

    public long getPermission_ID() {
        return permission_ID;
    }

    public void setPermission_ID(long permission_ID) {
        this.permission_ID = permission_ID;
    }

    // needed for spring
    @Override
    public int hashCode() {
        return (int)(role_ID + permission_ID);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof RolePermissionID) {
            RolePermissionID otherId = (RolePermissionID) object;
            return (otherId.permission_ID== this.permission_ID) && (otherId.role_ID == this.role_ID);
        }
        return false;
    }
}
