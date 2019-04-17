package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
