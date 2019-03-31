package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.skladapp.model.Department;
import uz.skladapp.model.pure_model.DepartmentPure;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
