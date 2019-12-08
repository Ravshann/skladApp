package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.InOutRecord;
import uz.skladapp.repositories.InOutRecordRepository;
import uz.skladapp.DTO.Incoming;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomingService {
    @Autowired
    private InOutRecordRepository repository;
    @Autowired
    private InOutRecordService dao;

    public List<Incoming> getAllIncomingRecords() {
        List<Incoming> incomings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllIncomings();
        for (InOutRecord record : records) {
            Incoming item = new Incoming();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setSupplier_ID(record.getSupplier_ID().getSupplier_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setSupplier_name(record.getSupplier_ID().getSupplier_name());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setEdited_datetime(record.getUpdated_time());

            incomings.add(item);
        }
        return incomings;
    }

    public void save(String data) throws Exception {
        dao.create(data);
    }

    public void update(String data) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long rec_id = json.get("record_ID").asLong();

        dao.update(data, rec_id, Long.valueOf(2));


    }

    public List<Incoming> getListByStorage(String storage_id) {
        List<Incoming> incomings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllIncomingsByStorage(Long.valueOf(storage_id));
        for (InOutRecord record : records) {
            Incoming item = new Incoming();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setSupplier_ID(record.getSupplier_ID().getSupplier_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setSupplier_name(record.getSupplier_ID().getSupplier_name());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setEdited_datetime(record.getUpdated_time());

            incomings.add(item);
        }
        return incomings;
    }

    public List<Incoming> getListByCommonDepartment(String storages_json) throws Exception {
        List<Long> storage_ids = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(storages_json);

        for (JsonNode json : jsonArray) {
            Long id = json.get("storage_ID").asLong();
            storage_ids.add(id);
        }
        List<Incoming> incomings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllIncomingsByCommonDepartment(storage_ids);
        for (InOutRecord record : records) {
            Incoming item = new Incoming();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setSupplier_ID(record.getSupplier_ID().getSupplier_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setSupplier_name(record.getSupplier_ID().getSupplier_name());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setEdited_datetime(record.getUpdated_time());

            incomings.add(item);
        }
        return incomings;
    }
}
