package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
