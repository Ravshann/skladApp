package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.*;
import uz.skladapp.repositories.DepartmentRepository;
import uz.skladapp.repositories.StorageRepository;
import uz.skladapp.repositories.UserRepository;
import uz.skladapp.DTO.StorageDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<StorageDTO> getDefectedStoragesList() {
        List<Storage> originals = storageRepository.findDefectedStorages();
        List<StorageDTO> raws = new ArrayList<>();
        for (Storage object : originals) {
            StorageDTO raw = new StorageDTO(
                    object.getStorage_ID(),
                    object.getAddress(),
                    object.getDepartment_ID().getDepartment_ID(),
                    object.getStorage_manager_ID().getUser_ID(),
                    object.getStorage_name(),
                    object.getStorage_phone(),
                    object.getStorage_manager_ID().getFirst_name(),
                    object.getDepartment_ID().getName(),
                    object.getStorage_type()
            );
            raws.add(raw);
        }
        return raws;
    }

    public Iterable<StorageDTO> getStorageList() {
        List<Storage> originals = storageRepository.findAll();
        List<StorageDTO> raws = new ArrayList<>();
        for (Storage object : originals) {
            StorageDTO raw = new StorageDTO(
                    object.getStorage_ID(),
                    object.getAddress(),
                    object.getDepartment_ID().getDepartment_ID(),
                    object.getStorage_manager_ID().getUser_ID(),
                    object.getStorage_name(),
                    object.getStorage_phone(),
                    object.getStorage_manager_ID().getFirst_name(),
                    object.getDepartment_ID().getName(),
                    object.getStorage_type()
            );
            raws.add(raw);
        }
        return raws;
    }

    public Iterable<StorageDTO> getStoragesByDepartmentManagerID(Long user_id) {
        List<Department> departments = departmentRepository.findDepartmentByManager(user_id);
        if (departments != null) {
            List<StorageDTO> raws = new ArrayList<>();
            departments.forEach(department -> {
                List<Storage> originals = storageRepository.findStorageByDepartmentID(department.getDepartment_ID());
                for (Storage object : originals) {
                    StorageDTO raw = new StorageDTO(
                            object.getStorage_ID(),
                            object.getAddress(),
                            object.getDepartment_ID().getDepartment_ID(),
                            object.getStorage_manager_ID().getUser_ID(),
                            object.getStorage_name(),
                            object.getStorage_phone(),
                            object.getStorage_manager_ID().getFirst_name(),
                            object.getDepartment_ID().getName(),
                            object.getStorage_type()
                    );
                    raws.add(raw);
                }
            });
            return raws;
        } else {
            List<StorageDTO> raws = new ArrayList<>();
            return raws;
        }

    }

    public StorageDTO getStorageByID(Long id) {
        Storage object = storageRepository.findById(id).get();
        StorageDTO raw = new StorageDTO(object.getStorage_ID(),
                object.getAddress(),
                object.getDepartment_ID().getDepartment_ID(),
                object.getStorage_manager_ID().getUser_ID(),
                object.getStorage_name(),
                object.getStorage_phone(),
                object.getStorage_manager_ID().getFirst_name(),
                object.getDepartment_ID().getName(),
                object.getStorage_type()
        );
        return raw;
    }

    public StorageDTO getStorageByStorageManagerID(Long user_id) {
        List<Storage> objects = storageRepository.findStorageByManager(user_id);

        if (!objects.isEmpty())
            return new StorageDTO(
                    objects.get(0).getStorage_ID(),
                    objects.get(0).getAddress(),
                    objects.get(0).getDepartment_ID().getDepartment_ID(),
                    objects.get(0).getStorage_manager_ID().getUser_ID(),
                    objects.get(0).getStorage_name(),
                    objects.get(0).getStorage_phone(),
                    objects.get(0).getStorage_manager_ID().getFirst_name(),
                    objects.get(0).getDepartment_ID().getName(),
                    objects.get(0).getStorage_type()
            );
        else
            return new StorageDTO();
    }

    public void saveStorage(String newText) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(newText);
        Long d_id = json.get("department_ID").asLong();
        Optional<Department> department = departmentRepository.findById(d_id);
        Long u_id = json.get("storage_manager_ID").asLong();
        Optional<User> user = userRepository.findById(u_id);

        Storage newStorage = new Storage();

        newStorage.setAddress(json.get("address").asText());
        newStorage.setDepartment_ID(department.get());
        newStorage.setStorage_manager_ID(user.get());
        newStorage.setStorage_name(json.get("storage_name").asText());
        newStorage.setStorage_phone(json.get("storage_phone").asText());
        newStorage.setStorage_type(json.get("storage_type").asText());
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
                    storage.setStorage_type(json.get("storage_type").asText());
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
            storage.setProducts(new ArrayList<>());

        storage.getProducts().add(association);

        product.getStorages().add(association);
        storageRepository.save(storage);
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

    public String changeCurrentQuantity(Storage storage, Product product, Float quantity, InoutType inoutType) {
        boolean found = false;
        String message = "fail";
        if (storage.getProducts().isEmpty()) {
            addProduct(storage, product, quantity, 0);
        } else {
            for (Iterator<StorageProduct> iterator = storage.getProducts().iterator(); iterator.hasNext(); ) {
                StorageProduct storageProduct = iterator.next();

                if (storageProduct.getStorage().equals(storage) && storageProduct.getProduct().equals(product)) {
                    found = true;
                    if (inoutType.getInout_type_name().equals("import") || inoutType.getInout_type_name().equals("returned")) {
                        storageProduct.setCurrent_quantity(quantity + storageProduct.getCurrent_quantity());
                        storageProduct.setTotal_quantity(quantity + storageProduct.getTotal_quantity());
                        message = "success import";
                    } else if (inoutType.getInout_type_name().equals("export") && storageProduct.getCurrent_quantity() >= quantity) {
                        storageProduct.setCurrent_quantity(storageProduct.getCurrent_quantity() - quantity);
                        storageProduct.setTotal_quantity(storageProduct.getTotal_quantity() - quantity);
                        message = "success export";
                    }

                }
            }
            if (!found)
                addProduct(storage, product, quantity, 0);
        }
        return message;

    }


}
