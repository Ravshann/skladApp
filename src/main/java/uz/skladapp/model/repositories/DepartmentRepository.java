package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
