package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.InOutRecord;
import uz.skladapp.model.Product;
import uz.skladapp.model.Storage;
import uz.skladapp.model.repositories.InOutRecordRepository;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.repositories.StorageRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class InOutRecordDAO {
    @Autowired
    private InOutRecordRepository inOutRecordRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;

    public Optional<InOutRecord> get(Long id) {

        Optional<InOutRecord> object = inOutRecordRepository.findById(id);
        return object;
    }

    public Iterable<InOutRecord> getList() {
        return inOutRecordRepository.findAll();
    }


    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        InOutRecord object = new InOutRecord();

        Long s_id = Long.valueOf(json.get("storage_ID").toString());
        Optional<Storage> storage = storageRepository.findById(s_id);

        Long p_id = (Long) Long.valueOf(json.get("product_ID").toString());
        Optional<Product> product = productRepository.findById(p_id);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newdate = dateformat.parse("2015-07-16 17:07:21");

        object.setQuantity(Float.valueOf(json.get("quantity").asText()));
        object.setPrice(Float.valueOf(json.get("price").asText()));
        object.setInout_type(json.get("inout_type").asText());
        object.setStorage_ID(storage.get());
        object.setProduct_ID(product.get());
        object.setRecord_time(newdate);

        inOutRecordRepository.save(object);
    }

    public void delete(Long id) {
        inOutRecordRepository.deleteById(id);
    }

    public InOutRecord update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        return inOutRecordRepository.findById(id)
                .map(object -> {

                    Long s_id = Long.valueOf(json.get("storage_ID").toString());
                    Optional<Storage> storage = storageRepository.findById(s_id);

                    Long p_id = (Long) Long.valueOf(json.get("product_ID").toString());
                    Optional<Product> product = productRepository.findById(p_id);

                    object.setQuantity(Float.valueOf(json.get("quantity").asText()));
                    object.setPrice(Float.valueOf(json.get("price").asText()));
                    object.setInout_type(json.get("inout_type").asText());
                    object.setStorage_ID(storage.get());
                    object.setProduct_ID(product.get());

                    return inOutRecordRepository.save(object);
                })
                .get();
    }
}
