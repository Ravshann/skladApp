package uz.skladapp.repositories;

import org.springframework.data.repository.CrudRepository;
import uz.skladapp.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
