package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
