package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
