package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=1 AND inout_record.storage_ID = :storage_id ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllOutgoingsByStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=2 AND inout_record.storage_ID = :storage_id ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllIncomingsByStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=3 AND inout_record.storage_ID = :storage_id ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllReturnedByStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=4 AND inout_record.storage_ID = :storage_id ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllDefectedByStorage(@Param("storage_id") Long storage_id);


    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=1 AND inout_record.storage_ID IN :storage_ids ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllOutgoingsByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=2 AND inout_record.storage_ID IN :storage_ids ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllIncomingsByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=3 AND inout_record.storage_ID IN :storage_ids ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllReturnedByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=4 AND inout_record.storage_ID IN :storage_ids ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllDefectedByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);



    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=1 OR inout_record.inout_type_ID=3 ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllOutgoingsAndReturns();

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=1 OR inout_record.inout_type_ID=3 AND inout_record.storage_ID = :storage_id ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllOutgoingsAndReturnsByStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM inout_record WHERE inout_record.inout_type_ID=1 OR inout_record.inout_type_ID=3 AND inout_record.storage_ID IN :storage_ids ORDER BY inout_record.record_ID DESC", nativeQuery = true)
    List<InOutRecord> findAllOutgoingsAndReturnsByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);


}
