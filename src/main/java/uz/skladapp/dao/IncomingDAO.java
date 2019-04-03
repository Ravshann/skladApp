package uz.skladapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.InOutRecord;
import uz.skladapp.model.repositories.InOutRecordRepository;
import uz.skladapp.model.special_models.Incoming;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncomingDAO {
    @Autowired
    private InOutRecordRepository repository;
    @Autowired
    private InOutRecordDAO dao;

    public List<Incoming> getAllIncomingRecords() {
        List<Incoming> incomings = new ArrayList<>();
        List<InOutRecord> records = repository.findAllIncomings();
        System.out.println(records);
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

            incomings.add(item);
        }
        return incomings;
    }

    public void save(String data) throws Exception {
        dao.create(data);
    }
}
