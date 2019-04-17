package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.pure_models.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
