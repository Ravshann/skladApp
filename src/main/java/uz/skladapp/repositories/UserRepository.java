package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.skladapp.model.pure_models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


    @Query(value = "SELECT * FROM user WHERE user.role_ID=3", nativeQuery = true)
    List<User> findAllDepartmentManagers();

    @Query(value = "SELECT * FROM user WHERE user.role_ID=4", nativeQuery = true)
    List<User> findAllStorageManagers();

    @Query(value = "SELECT * FROM user WHERE NOT user.role_ID=5", nativeQuery = true)
    Iterable<User> findAllButAdmin();

    User findByUsernameAndPassword(String username, String password);
}

