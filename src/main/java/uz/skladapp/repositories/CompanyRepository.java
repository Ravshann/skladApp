package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
