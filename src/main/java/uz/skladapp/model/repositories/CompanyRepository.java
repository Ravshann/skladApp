package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
