package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.skladapp.model.pure_models.Storage;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query(value = "SELECT * FROM storage s WHERE s.storage_manager_ID = :user_id", nativeQuery = true)
    List<Storage> findStorageByManager(@Param("user_id") Long user_id);

}
