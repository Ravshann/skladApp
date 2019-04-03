package uz.skladapp.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.*;
import uz.skladapp.model.repositories.DepartmentRepository;
import uz.skladapp.model.repositories.StorageRepository;
import uz.skladapp.model.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Component
public class StorageDAO {
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;


    public Iterable<Storage> getStorageList() {
        return storageRepository.findAll();
    }

    public Optional<Storage> getStorageByID(Long id) {
        Optional<Storage> storage = storageRepository.findById(id);
        return storage;
    }

    public void saveStorage(String newText) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(newText);
        Long d_id = Long.valueOf(json.get("department_ID").toString());
        Optional<Department> department = departmentRepository.findById(d_id);
        Long u_id = Long.valueOf(json.get("storage_manager_ID").toString());
        Optional<User> user = userRepository.findById(u_id);

        Storage newStorage = new Storage();

        newStorage.setAddress(json.get("address").asText());
        newStorage.setDepartment_ID(department.get());
        newStorage.setStorage_manager_ID(user.get());
        newStorage.setStorage_name(json.get("storage_name").asText());
        newStorage.setStorage_phone(json.get("storage_phone").asText());
        storageRepository.save(newStorage);
    }

    public void deleteById(Long id) {
        storageRepository.deleteById(id);
    }

    public Storage update(String string, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(string);
        Long d_id = Long.valueOf(json.get("department_ID").toString());
        Optional<Department> department = departmentRepository.findById(d_id);
        Long u_id = Long.valueOf(json.get("storage_manager_ID").toString());
        Optional<User> user = userRepository.findById(u_id);
        return storageRepository.findById(id)
                .map(storage -> {
                    storage.setAddress(json.get("address").asText());
                    storage.setDepartment_ID(department.get());
                    storage.setStorage_manager_ID(user.get());
                    storage.setStorage_name(json.get("storage_name").asText());
                    storage.setStorage_phone(json.get("storage_phone").asText());
                    return storageRepository.save(storage);
                })
                .get();
    }

    public void addProduct(Storage storage, Product product, float current_quantity, float price) {

        StorageProduct association = new StorageProduct();
        association.setProduct(product);
        association.setStorage(storage);
        association.setProduct_ID(product.getProduct_ID());
        association.setStorage_ID(storage.getStorage_ID());
        association.setCurrent_quantity(current_quantity);
        association.setTotal_quantity(current_quantity);
        association.setPrice(price);

        if (storage.getProducts() == null)
            storage.setProducts( new ArrayList<>());

        storage.getProducts().add(association);
        // Also add the association object to the other class.
        product.getStorages().add(association);
        storageRepository.save(storage);
    }

    public void changeCurrentQuantity(Storage storage, Product product, Float quantity, InoutType inoutType) {
        boolean found = false;
        if (storage.getProducts().isEmpty()) {
            addProduct(storage,product, quantity, 0);
        } else {
            for (Iterator<StorageProduct> iterator = storage.getProducts().iterator(); iterator.hasNext(); ) {
                StorageProduct storageProduct = iterator.next();

                if (storageProduct.getStorage().equals(storage) && storageProduct.getProduct().equals(product)) {
                    found = true;
                    if (inoutType.getInout_type_name().equals("import") || inoutType.getInout_type_name().equals("returned")) {
                        storageProduct.setCurrent_quantity(quantity + storageProduct.getCurrent_quantity());
                        storageProduct.setTotal_quantity(quantity + storageProduct.getTotal_quantity());
                    } else if (inoutType.getInout_type_name().equals("export") && storageProduct.getCurrent_quantity() >= quantity) {
                        storageProduct.setCurrent_quantity(storageProduct.getCurrent_quantity() - quantity);
                        storageProduct.setTotal_quantity(storageProduct.getTotal_quantity() - quantity);
                    }
                }

            }
            if (!found)
                addProduct(storage,product, quantity, 0);
        }


    }
    public void removeProduct(Storage storage, Product product) {
        for (Iterator<StorageProduct> iterator = storage.getProducts().iterator(); iterator.hasNext(); ) {
            StorageProduct storageProduct = iterator.next();

            if (storageProduct.getStorage().equals(this) && storageProduct.getProduct().equals(product)) {
                iterator.remove();
                storageProduct.getProduct().getStorages().remove(storageProduct);
                storageProduct.setStorage(null);
                storageProduct.setProduct(null);
            }
        }
        storageRepository.save(storage);
    }
}
