package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.*;
import uz.skladapp.model.repositories.*;

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
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private InoutTypeRepository inoutTypeRepository;

    public Optional<InOutRecord> get(Long id) {

        Optional<InOutRecord> object = inOutRecordRepository.findById(id);
        return object;
    }

    public Iterable<InOutRecord> getList() {
        return inOutRecordRepository.findAll();
    }


    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);


        for (JsonNode json : jsonArray) {
            InOutRecord object = new InOutRecord();

            Long s_id = Long.valueOf(json.get("storage_ID").toString());
            Optional<Storage> storage = storageRepository.findById(s_id);

            Long su_id = Long.valueOf(json.get("supplier_ID").toString());
            Optional<Supplier> supplier = supplierRepository.findById(su_id);


            Long p_id = Long.valueOf(json.get("product_ID").toString());
            Optional<Product> product = productRepository.findById(p_id);

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateformat.parse(json.get("record_datetime").asText());

            object.setQuantity(Float.valueOf(json.get("quantity").asText()));
//                object.setPrice(Float.valueOf(json.get("price").asText()));
            Long inout_t_id = Long.valueOf(json.get("inout_type_ID").asText());
            Optional<InoutType> inoutType = inoutTypeRepository.findById(inout_t_id);
            object.setInout_type_ID(inoutType.get());
            object.setSupplier_ID(supplier.get());
            object.setStorage_ID(storage.get());
            object.setProduct_ID(product.get());
            object.setRecord_time(date);

            //make changes in storage_product as well

            storage.get().changeQuantity(product.get(), object.getQuantity());

            inOutRecordRepository.save(object);
        }


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
                    Long inout_t_id = Long.valueOf(json.get("inout_type_ID").asText());
                    Optional<InoutType> inoutType = inoutTypeRepository.findById(inout_t_id);
                    object.setInout_type_ID(inoutType.get());
                    object.setStorage_ID(storage.get());
                    object.setProduct_ID(product.get());

                    return inOutRecordRepository.save(object);
                })
                .get();
    }
}
