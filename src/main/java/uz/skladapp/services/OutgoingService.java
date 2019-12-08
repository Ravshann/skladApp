package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.InOutRecord;
import uz.skladapp.repositories.InOutRecordRepository;
import uz.skladapp.DTO.Outgoing;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutgoingService {
    @Autowired
    private InOutRecordRepository repository;

    @Autowired
    private InOutRecordService dao;

    public List<Outgoing> getAllOutgoingRecords() {
        List<Outgoing> outgoings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllOutgoings();
        for (InOutRecord record : records) {
            Outgoing item = new Outgoing();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setClient_region(record.getClient_ID().getRegion());
            item.setClient_ID(record.getClient_ID().getClient_ID());
            item.setClient_name(record.getClient_ID().getClient_name());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setPrice(record.getPrice());
            outgoings.add(item);
        }
        return outgoings;
    }

    public void save(String data) throws Exception {
        dao.create(data);
    }

    public void update(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long rec_id = json.get("record_ID").asLong();
        dao.update(data, rec_id, Long.valueOf(1));
    }

    public List<Outgoing> getListByStorage(String storage_id) {
        List<Outgoing> outgoings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllOutgoingsByStorage(Long.valueOf(storage_id));
        for (InOutRecord record : records) {
            Outgoing item = new Outgoing();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setClient_region(record.getClient_ID().getRegion());
            item.setClient_ID(record.getClient_ID().getClient_ID());
            item.setClient_name(record.getClient_ID().getClient_name());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setPrice(record.getPrice());
            outgoings.add(item);
        }
        return outgoings;
    }

    public List<Outgoing> getListByCommonDepartment(String storages_json) throws Exception {
        List<Long> storage_ids = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(storages_json);

        for (JsonNode json : jsonArray) {
            Long id = json.get("storage_ID").asLong();
            storage_ids.add(id);
        }
        List<Outgoing> outgoings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllOutgoingsByCommonDepartment(storage_ids);
        for (InOutRecord record : records) {
            Outgoing item = new Outgoing();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setClient_region(record.getClient_ID().getRegion());
            item.setClient_ID(record.getClient_ID().getClient_ID());
            item.setClient_name(record.getClient_ID().getClient_name());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setPrice(record.getPrice());
            outgoings.add(item);
        }
        return outgoings;
    }
}
