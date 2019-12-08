package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.InoutType;

public interface InoutTypeRepository extends JpaRepository<InoutType, Long> {
}
