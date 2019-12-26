package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.InOutRecord;
import uz.skladapp.DTO.OutgoingReturned;
import uz.skladapp.repositories.InOutRecordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutgoingAndReturnedService {
    @Autowired
    private InOutRecordRepository repository;


    public List<OutgoingReturned> findAll() {
        List<OutgoingReturned> outgoing_returns = new ArrayList<>();
        List<InOutRecord> records = repository.findAllOutgoingsAndReturns();
        for (InOutRecord record : records) {
            OutgoingReturned item = new OutgoingReturned();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProductName());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategoryName());
            item.setClient_region(record.getClient_ID().getRegion());
            item.setClient_ID(record.getClient_ID().getClient_ID());
            item.setClient_name(record.getClient_ID().getClientName());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorageName());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setPrice(record.getPrice());
            item.setType(record.getInout_type_ID().getInout_type_name());
            outgoing_returns.add(item);
        }
        return outgoing_returns;
    }

    public List<OutgoingReturned> getListByStorage(String storage_id) {
        List<OutgoingReturned> outgoing_returns = new ArrayList<>();
        List<InOutRecord> records = repository.findAllOutgoingsAndReturnsByStorage(Long.valueOf(storage_id));
        for (InOutRecord record : records) {
            OutgoingReturned item = new OutgoingReturned();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProductName());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategoryName());
            item.setClient_region(record.getClient_ID().getRegion());
            item.setClient_ID(record.getClient_ID().getClient_ID());
            item.setClient_name(record.getClient_ID().getClientName());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorageName());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setPrice(record.getPrice());
            item.setType(record.getInout_type_ID().getInout_type_name());
            outgoing_returns.add(item);
        }
        return outgoing_returns;
    }

    public List<OutgoingReturned> getListByCommonDepartment(String storages) throws Exception {
        List<Long> storage_ids = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(storages);

        for (JsonNode json : jsonArray) {
            Long id = json.get("storage_ID").asLong();
            storage_ids.add(id);
        }

        List<OutgoingReturned> outgoing_returns = new ArrayList<>();
        List<InOutRecord> records = repository.findAllOutgoingsAndReturnsByCommonDepartment(storage_ids);
        for (InOutRecord record : records) {
            OutgoingReturned item = new OutgoingReturned();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProductName());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategoryName());
            item.setClient_region(record.getClient_ID().getRegion());
            item.setClient_ID(record.getClient_ID().getClient_ID());
            item.setClient_name(record.getClient_ID().getClientName());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorageName());
            item.setStorage_ID(record.getStorage_ID().getStorage_ID());
            item.setPrice(record.getPrice());
            outgoing_returns.add(item);
        }
        return outgoing_returns;
    }


}
