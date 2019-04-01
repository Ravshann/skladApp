package uz.skladapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.InOutRecord;
import uz.skladapp.model.repositories.InOutRecordRepository;
import uz.skladapp.model.special_models.Outgoing;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutgoingDAO {
    @Autowired
    private InOutRecordRepository repository;

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
            item.setRecord_datetime(record.getRecord_time());
            item.setClient_name(record.getClient_ID().getClient_name());
            item.setQuantity(record.getQuantity());

            outgoings.add(item);
        }
        return outgoings;
    }
}
