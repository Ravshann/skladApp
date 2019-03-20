package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
