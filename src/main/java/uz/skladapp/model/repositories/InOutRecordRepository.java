package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.skladapp.model.InOutRecord;

import java.util.List;

public interface InOutRecordRepository extends JpaRepository<InOutRecord, Long> {
    @Query(value = "SELECT * FROM INOUT_RECORD WHERE INOUT_RECORD.inout_type_ID=1", nativeQuery = true)
    List<InOutRecord> findAllOutgoings();

    @Query(value = "SELECT * FROM INOUT_RECORD WHERE INOUT_RECORD.inout_type_ID=2", nativeQuery = true)
    List<InOutRecord> findAllIncomings();

    @Query(value = "SELECT * FROM INOUT_RECORD WHERE INOUT_RECORD.inout_type_ID=3", nativeQuery = true)
    List<InOutRecord> findAllReturned();
}
