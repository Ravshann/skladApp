package uz.skladapp.model.special_models;

public class PermissionRaw {
    private Long permission_ID;
    private String permission_name;
    private String permission_description;

    public PermissionRaw(Long permission_ID, String permission_name, String permission_description) {
        this.permission_ID = permission_ID;
        this.permission_name = permission_name;
        this.permission_description = permission_description;
    }

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
}
