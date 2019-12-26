package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.AlternativeInOutRecord;
import uz.skladapp.DTO.Exchange;
import uz.skladapp.repositories.AlternativeInOutRecordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService {
    @Autowired
    private AlternativeInOutRecordRepository repository;

    @Autowired
    private AlternativeInOutRecordService dao;

    public List<Exchange> getAllExchangeRecords() {
        List<Exchange> exchanges = new ArrayList<>();
        List<AlternativeInOutRecord> records = repository.findAllExchange();
        for (AlternativeInOutRecord record : records) {
            Exchange item = new Exchange();
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
            exchanges.add(item);
        }
        return exchanges;
    }

    public void save(String data) throws Exception {
        dao.create(data);
    }

    public void update(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long rec_id = json.get("record_ID").asLong();

        //here value 4 means id of defected type of product
        dao.update(data, rec_id, Long.valueOf(5));


    }

    public List<Exchange> getListByStorage(String storage_id, String type) {
        List<Exchange> exchanges = new ArrayList<>();
        List<AlternativeInOutRecord> records = null;
        if (type.contains("supplier"))
            records = repository.findAllExchangeBySupplierStorage(Long.valueOf(storage_id));
        else
            records = repository.findAllExchangeByStorage(Long.valueOf(storage_id));
        for (AlternativeInOutRecord record : records) {
            Exchange item = new Exchange();
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
            exchanges.add(item);
        }
        return exchanges;
    }

    public List<Exchange> getListByCommonDepartment(String storages_json) throws Exception {
        List<Long> storage_ids = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(storages_json);

        for (JsonNode json : jsonArray) {
            Long id = json.get("storage_ID").asLong();
            storage_ids.add(id);
        }
        List<Exchange> exchanges = new ArrayList<>();
        List<AlternativeInOutRecord> records = repository.findAllExchangeByCommonDepartment(storage_ids);
        for (AlternativeInOutRecord record : records) {
            Exchange item = new Exchange();
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
            exchanges.add(item);
        }
        return exchanges;
    }
}
