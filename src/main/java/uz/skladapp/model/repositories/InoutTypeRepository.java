package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.InoutType;

public interface InoutTypeRepository extends JpaRepository<InoutType, Long> {
}
