package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
