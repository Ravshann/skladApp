package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
