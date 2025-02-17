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
public class InOutRecordService {
    @Autowired
    private InOutRecordRepository inOutRecordRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StorageService dao;

    @Autowired
    private InoutTypeRepository inoutTypeRepository;

    public Optional<InOutRecord> get(Long id) {

        Optional<InOutRecord> object = inOutRecordRepository.findById(id);
        return object;
    }

    public Iterable<InOutRecord> getList(int size, int index) {
        Pageable limit = PageRequest.of(index, size);
        return inOutRecordRepository.findAll(limit);
//        return inOutRecordRepository.findAll();

    }


    public void create(String string) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(string);


        for (JsonNode json : jsonArray) {

            InOutRecord object = new InOutRecord();
            //inout_types = [1:export, 2:import, 3:returned, 4:defected, 5:movement 6:pending_export, 7:pending_import]
            Long inout_t_id = json.get("inout_type_ID").asLong();
            if (inout_t_id == 2) {

                Long su_id = json.get("supplier_ID").asLong();
                Optional<Supplier> supplier = supplierRepository.findById(su_id);
                object.setSupplier_ID(supplier.get());
                object.setRecord_note(json.get("note").asText());
            } else if (inout_t_id == 1) {
                Float price = Float.valueOf(json.get("price").asText());
                object.setPrice(price);

                Long c_id = json.get("client_ID").asLong();
                Optional<Client> client = clientRepository.findById(c_id);
                object.setClient_ID(client.get());
                object.setRecord_note(json.get("note").asText());
            } else if (inout_t_id == 3) {
                Long c_id = json.get("client_ID").asLong();
                Optional<Client> client = clientRepository.findById(c_id);
                object.setClient_ID(client.get());
                object.setRecord_note(json.get("note").asText());
            }

            Optional<InoutType> inoutType = inoutTypeRepository.findById(inout_t_id);

            Long s_id = json.get("storage_ID").asLong();
            Storage storage = storageRepository.findById(s_id).get();
//            System.out.println(storage);
            Long p_id = json.get("product_ID").asLong();
            Product product = productRepository.findById(p_id).get();
//            System.out.println(product);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateformat.parse(json.get("record_datetime").asText());

            object.setQuantity(Float.valueOf(json.get("quantity").asText()));
//                object.setPrice(Float.valueOf(json.get("price").asText()));


            object.setInout_type_ID(inoutType.get());
            object.setStorage_ID(storage);
            object.setProduct_ID(product);
            object.setRecord_time(date);
            String message = dao.changeCurrentQuantity(storage, product, object.getQuantity(), inoutType.get());
            if (!message.equals("fail"))
                inOutRecordRepository.save(object);
        }


    }


    public InOutRecord update(String string, Long id, Long inout_t_id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date record_time = dateformat.parse(json.get("record_datetime").asText());
        Date updated_time = dateformat.parse(json.get("updated_datetime").asText());
        System.out.println(record_time);
        return inOutRecordRepository.findById(id)
                .map(object -> {

                    Long s_id = json.get("storage_ID").asLong();
                    Optional<Storage> storage = storageRepository.findById(s_id);

                    Long p_id = json.get("product_ID").asLong();
                    Optional<Product> product = productRepository.findById(p_id);



                    Optional<InoutType> inoutType = inoutTypeRepository.findById(inout_t_id);
                    if (inout_t_id == 2) {
                        Long su_id = Long.valueOf(json.get("supplier_ID").toString());
                        Optional<Supplier> supplier = supplierRepository.findById(su_id);
                        object.setSupplier_ID(supplier.get());

                        float difference = Float.valueOf(json.get("quantity").asText()) - object.getQuantity();
                        dao.changeCurrentQuantity(storage.get(), product.get(), difference, inoutType.get());
                        object.setQuantity(Float.valueOf(json.get("quantity").asText()));
                    } else if (inout_t_id == 1) {
                        Long c_id = Long.valueOf(json.get("client_ID").toString());
                        Optional<Client> client = clientRepository.findById(c_id);
                        object.setClient_ID(client.get());

                        Float price = Float.valueOf(json.get("price").asText());
                        object.setPrice(price);

                        float difference = Float.valueOf(json.get("quantity").asText()) - object.getQuantity();
                        dao.changeCurrentQuantity(storage.get(), product.get(), difference, inoutType.get());
                        object.setQuantity(Float.valueOf(json.get("quantity").asText()));
                    } else if (inout_t_id == 3) {
                        Long c_id = Long.valueOf(json.get("client_ID").toString());
                        Optional<Client> client = clientRepository.findById(c_id);
                        object.setClient_ID(client.get());

                        float difference = Float.valueOf(json.get("quantity").asText()) - object.getQuantity();
                        dao.changeCurrentQuantity(storage.get(), product.get(), difference, inoutType.get());

                        object.setQuantity(Float.valueOf(json.get("quantity").asText()));

                        String note = json.get("note").asText();
                        object.setRecord_note(note);
                    }

                    object.setUpdated_time(updated_time);
                    object.setRecord_time(record_time);
                    object.setStorage_ID(storage.get());
                    object.setProduct_ID(product.get());

                    return inOutRecordRepository.save(object);
                })
                .get();
    }
}
