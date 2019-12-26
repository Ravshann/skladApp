package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.AlternativeInOutRecord;
import uz.skladapp.DTO.Defected;
import uz.skladapp.repositories.AlternativeInOutRecordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefectedService {
    @Autowired
    private AlternativeInOutRecordRepository repository;

    @Autowired
    private AlternativeInOutRecordService dao;

    public List<Defected> getAllDefectedRecords() {
        List<Defected> defecteds = new ArrayList<>();
        List<AlternativeInOutRecord> records = repository.findAllDefected();
        for (AlternativeInOutRecord record : records) {
            Defected item = new Defected();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProductName());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategoryName());
            item.setSupplier_storage_name(record.getSupplier_storage_ID().getStorageName());
            item.setSupplier_storage_ID(record.getSupplier_storage_ID().getStorage_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorageName());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setNote(record.getRecord_note());
            item.setUpdated_time(record.getUpdated_time());
            defecteds.add(item);
        }
        return defecteds;
    }

    public void save(String data) throws Exception {
        dao.create(data);
    }

    public void update(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long rec_id = json.get("record_ID").asLong();

        //here value 4 means id of defected type of product
        dao.update(data, rec_id, Long.valueOf(4));


    }

    public List<Defected> getListByStorage(String storage_id, String type) {
        List<Defected> defecteds = new ArrayList<>();
        List<AlternativeInOutRecord> records = null;
        if (type.contains("supplier"))
            records = repository.findAllDefectedBySupplierStorage(Long.valueOf(storage_id));
        else
            records = repository.findAllDefectedByStorage(Long.valueOf(storage_id));
        for (AlternativeInOutRecord record : records) {
            Defected item = new Defected();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProductName());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategoryName());
            item.setSupplier_storage_name(record.getSupplier_storage_ID().getStorageName());
            item.setSupplier_storage_ID(record.getSupplier_storage_ID().getStorage_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorageName());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setNote(record.getRecord_note());
            item.setUpdated_time(record.getUpdated_time());
            defecteds.add(item);
        }
        return defecteds;
    }

    public List<Defected> getListByCommonDepartment(String storages_json) throws Exception {
        List<Long> storage_ids = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(storages_json);

        for (JsonNode json : jsonArray) {
            Long id = json.get("storage_ID").asLong();
            storage_ids.add(id);
        }
        List<Defected> defecteds = new ArrayList<>();
        List<AlternativeInOutRecord> records = repository.findAllDefectedByCommonDepartment(storage_ids);
        for (AlternativeInOutRecord record : records) {
            Defected item = new Defected();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProductName());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategoryName());
            item.setSupplier_storage_name(record.getSupplier_storage_ID().getStorageName());
            item.setSupplier_storage_ID(record.getSupplier_storage_ID().getStorage_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorageName());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setNote(record.getRecord_note());
            item.setUpdated_time(record.getUpdated_time());
            defecteds.add(item);
        }
        return defecteds;
    }
}
