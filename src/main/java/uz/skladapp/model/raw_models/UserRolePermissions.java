package uz.skladapp.model.raw_models;

import java.util.List;

public class UserRolePermissions {
    private Long user_ID;
    private Long role_ID;
    private String role_name;
    private List<PermissionRaw> permissions;

    public Long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Long user_ID) {
        this.user_ID = user_ID;
    }

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

    public List<PermissionRaw> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionRaw> permissions) {
        this.permissions = permissions;
    }
}
