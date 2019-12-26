package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.skladapp.model.pure_models.Department;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT * FROM department d WHERE d.department_manager_ID = :user_id", nativeQuery = true)
    List<Department> findDepartmentByManager(@Param("user_id") Long user_id);

    List<Department> findAllByOrderByNameAsc();
}
