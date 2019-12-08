package uz.skladapp.model.ID_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class RolePermissionID implements Serializable {
    private long role_ID;
    private long permission_ID;
}
