package uz.skladapp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserRolePermissions {
    private Long user_ID;
    private Long role_ID;
    private String role_name;
    private List<PermissionDTO> permissions;

}
