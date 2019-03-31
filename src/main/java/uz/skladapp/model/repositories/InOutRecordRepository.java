package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.skladapp.model.InOutRecord;

public interface InOutRecordRepository extends JpaRepository<InOutRecord, Long> {
}
