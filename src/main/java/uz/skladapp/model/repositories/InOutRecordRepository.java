package uz.skladapp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.skladapp.model.pure_models.InOutRecord;

import java.util.List;

public interface InOutRecordRepository extends JpaRepository<InOutRecord, Long> {
    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=1 ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllOutgoings();

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=2 ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllIncomings();

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=3 ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllReturned();

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=4 ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllDefected();
}
