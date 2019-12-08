package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import uz.skladapp.model.ID_classes.RolePermissionID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
@Entity
@Table(name = "ROLE_PERMISSION")
@IdClass(RolePermissionID.class)
public class RolePermission {
    @Id
    private long role_ID;
    @Id
    private long permission_ID;

    @JsonBackReference // this needed to json generation
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "permission_ID", updatable = false, insertable = false, referencedColumnName = "permission_ID")
    private Permission permission;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "role_ID", updatable = false, insertable = false, referencedColumnName = "role_ID")
    private Role role;

}
