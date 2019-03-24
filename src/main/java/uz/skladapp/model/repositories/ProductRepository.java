package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
