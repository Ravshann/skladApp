package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.skladapp.model.pure_models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * FROM role WHERE NOT role.role_ID=5", nativeQuery = true)
    Iterable<Role> findAllButAdmin();
}
