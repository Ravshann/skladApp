package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Attributes;

public interface AttributeRepository extends JpaRepository<Attributes, Long> {
}
