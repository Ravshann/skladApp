package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
