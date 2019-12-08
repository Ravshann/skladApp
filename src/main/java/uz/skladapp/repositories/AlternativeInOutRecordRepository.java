package uz.skladapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.skladapp.model.pure_models.AlternativeInOutRecord;

import java.util.List;

public interface AlternativeInOutRecordRepository extends JpaRepository<AlternativeInOutRecord, Long> {


    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=4 ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllDefected();


    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=4 AND alternative_inout_record.storage_ID = :storage_id ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllDefectedByStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=4 AND alternative_inout_record.supplier_storage_ID = :storage_id ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllDefectedBySupplierStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=4 AND alternative_inout_record.storage_ID IN :storage_ids ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllDefectedByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);


    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=5 ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllExchange();

    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=5 AND alternative_inout_record.storage_ID = :storage_id ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllExchangeByStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=5 AND alternative_inout_record.supplier_storage_ID = :storage_id ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllExchangeBySupplierStorage(@Param("storage_id") Long storage_id);

    @Query(value = "SELECT * FROM alternative_inout_record WHERE alternative_inout_record.inout_type_ID=5 AND alternative_inout_record.storage_ID IN :storage_ids ORDER BY alternative_inout_record.record_ID DESC", nativeQuery = true)
    List<AlternativeInOutRecord> findAllExchangeByCommonDepartment(@Param("storage_ids") List<Long> storage_ids);
}
