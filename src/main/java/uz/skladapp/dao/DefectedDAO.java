package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.InOutRecord;
import uz.skladapp.model.repositories.InOutRecordRepository;
import uz.skladapp.model.raw_models.Defected;

import java.util.ArrayList;
import java.util.List;
@Service
public class DefectedDAO {
    @Autowired
    private InOutRecordRepository repository;

    @Autowired
    private InOutRecordDAO dao;

    public List<Defected> getAllDefectedRecords() {
        List<Defected> defecteds = new ArrayList<>();
        List<InOutRecord> records = repository.findAllDefected();
        for (InOutRecord record : records) {
            Defected item = new Defected();
            item.setRecord_ID(record.getRecord_ID());
            item.setProduct_ID(record.getProduct_ID().getProduct_ID());
            item.setCategory_ID(record.getProduct_ID().getCategory_ID().getCategory_ID());
            item.setProduct_name(record.getProduct_ID().getProduct_name());
            item.setCategory_name(record.getProduct_ID().getCategory_ID().getCategory_name());
            item.setSupplier_name(record.getSupplier_ID().getSupplier_name());
            item.setSupplier_ID(record.getSupplier_ID().getSupplier_ID());
            item.setStorage_ID(record.getSupplier_ID().getSupplier_ID());
            item.setRecord_datetime(record.getRecord_time());
            item.setQuantity(record.getQuantity());
            item.setStorage_name(record.getStorage_ID().getStorage_name());
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

    public void update(String data) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(data);
        Long rec_id = json.get("record_ID").asLong();

        //here value 4 means id of defected type of product
        dao.update(data, rec_id, Long.valueOf(4));


    }

}
