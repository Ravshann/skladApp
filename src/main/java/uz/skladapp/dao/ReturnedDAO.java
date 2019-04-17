package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.InOutRecord;
import uz.skladapp.model.repositories.InOutRecordRepository;
import uz.skladapp.model.special_models.Returned;

import java.util.ArrayList;
import java.util.List;
@Component
public class ReturnedDAO {

    @Autowired
    private InOutRecordRepository repository;

    @Autowired
    private InOutRecordDAO dao;

    public List<Returned> getAllReturnedRecords() {
        List<Returned> returneds = new ArrayList<>();
        List<InOutRecord> records = repository.findAllReturned();
        for (InOutRecord record : records) {
            Returned item = new Returned();
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
            item.setNote(record.getRecord_note());
            returneds.add(item);
        }
        return returneds;
    }

    public void save(String data) throws Exception {
        dao.create(data);
    }

    public void update(String data) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long rec_id = json.get("record_ID").asLong();
        dao.update(data, rec_id, Long.valueOf(3));


    }
}
