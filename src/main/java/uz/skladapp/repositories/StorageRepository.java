package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
