package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.*;
import uz.skladapp.repositories.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class AlternativeInOutRecordService {
    @Autowired
    private AlternativeInOutRecordRepository alternativeInOutRecordRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private StorageService dao;

    @Autowired
    private InoutTypeRepository inoutTypeRepository;

    public Optional<AlternativeInOutRecord> get(Long id) {

        Optional<AlternativeInOutRecord> object = alternativeInOutRecordRepository.findById(id);
        return object;
    }

    public Iterable<AlternativeInOutRecord> getList(int size, int index) {
        Pageable limit = PageRequest.of(index, size);
        return alternativeInOutRecordRepository.findAll(limit);
//        return alternativeInOutRecordRepository.findAll();

    }


    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);


        for (JsonNode json : jsonArray) {

            AlternativeInOutRecord object = new AlternativeInOutRecord();
            //inout_types = [1:export, 2:import, 3:returned, 4:defected, 5:movement 6:pending_export, 7:pending_import]
            Long inout_t_id = json.get("inout_type_ID").asLong();
            if (inout_t_id == 4 || inout_t_id == 5) {
                Long su_id = json.get("supplier_storage_ID").asLong();
                Optional<Storage> supplier_storage = storageRepository.findById(su_id);
                object.setSupplier_storage_ID(supplier_storage.get());
                object.setRecord_note(json.get("note").asText());
            }

            Optional<InoutType> inoutType = inoutTypeRepository.findById(inout_t_id);

            Long s_id = Long.valueOf(json.get("storage_ID").toString());
            Optional<Storage> storage = storageRepository.findById(s_id);

            Long p_id = Long.valueOf(json.get("product_ID").toString());
            Optional<Product> product = productRepository.findById(p_id);

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateformat.parse(json.get("record_datetime").asText());

            object.setQuantity(Float.valueOf(json.get("quantity").asText()));
//                object.setPrice(Float.valueOf(json.get("price").asText()));


            object.setInout_type_ID(inoutType.get());
            object.setStorage_ID(storage.get());
            object.setProduct_ID(product.get());
            object.setRecord_time(date);

            //add to receiver storage - import
            InoutType export_import = inoutTypeRepository.findById(Long.valueOf(2)).get();
            dao.changeCurrentQuantity(storage.get(), product.get(), object.getQuantity(), export_import);
            //subtract from sender storage - export
            export_import = inoutTypeRepository.findById(Long.valueOf(1)).get();
            dao.changeCurrentQuantity(object.getSupplier_storage_ID(), product.get(), object.getQuantity(), export_import);

            alternativeInOutRecordRepository.save(object);
        }


    }


    public AlternativeInOutRecord update(String string, Long id, Long inout_t_id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date record_time = dateformat.parse(json.get("record_datetime").asText());
        Date updated_time = dateformat.parse(json.get("updated_datetime").asText());

        return alternativeInOutRecordRepository.findById(id)
                .map(object -> {

                    Long s_id = json.get("storage_ID").asLong();
                    Optional<Storage> storage = storageRepository.findById(s_id);

                    Long p_id = json.get("product_ID").asLong();
                    Optional<Product> product = productRepository.findById(p_id);

                    //object.setPrice(Float.valueOf(json.get("price").asText()));
                    Optional<InoutType> inoutType = inoutTypeRepository.findById(inout_t_id);
                    if (inout_t_id == 4 || inout_t_id == 5) {
                        Long su_id = json.get("supplier_storage_ID").asLong();
                        Optional<Storage> supplier_storage = storageRepository.findById(su_id);
                        object.setSupplier_storage_ID(supplier_storage.get());

                        float difference = Float.valueOf(json.get("quantity").asText()) - object.getQuantity();
                        //dao.changeCurrentQuantity(storage.get(), product.get(), difference, inoutType.get());

                        //add to receiver storage - import
                        InoutType export_import = inoutTypeRepository.findById(Long.valueOf(2)).get();
                        dao.changeCurrentQuantity(storage.get(), product.get(), difference, export_import);
                        //subtract from sender storage - export
                        export_import = inoutTypeRepository.findById(Long.valueOf(1)).get();
                        dao.changeCurrentQuantity(object.getSupplier_storage_ID(), product.get(), difference, export_import);
                        object.setQuantity(Float.valueOf(json.get("quantity").asText()));
                    }

                    object.setUpdated_time(updated_time);
                    object.setRecord_time(record_time);
                    object.setStorage_ID(storage.get());
                    object.setProduct_ID(product.get());

                    return alternativeInOutRecordRepository.save(object);
                })
                .get();
    }
}
