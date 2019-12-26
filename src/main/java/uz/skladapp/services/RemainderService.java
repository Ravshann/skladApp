package uz.skladapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.Storage;
import uz.skladapp.model.pure_models.StorageProduct;
import uz.skladapp.repositories.ProductRepository;
import uz.skladapp.DTO.Remainder;
import uz.skladapp.DTO.StorageQuantity;
import uz.skladapp.repositories.StorageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemainderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;

    public List<Remainder> getAll(boolean returnNormal) {
        List<Product> products = productRepository.findAll();
        List<Remainder> remainderList = new ArrayList<>();
        for (Product product : products) {
            remainderList.add(getList(product, returnNormal));
        }
        return remainderList;
    }


    //this method is overloaded
    //this method returns remainders of given product in all storages
    //this method is not used by frontend
    public Remainder getList(String product_id) {
        float total = 0;
        Remainder remainder = new Remainder();
        Product product = productRepository.findById(Long.valueOf(product_id)).get();
        List<StorageProduct> storages = product.getStorages();
        List<StorageQuantity> quantities = new ArrayList<>();
        for (StorageProduct association : storages) {
            StorageQuantity object = new StorageQuantity();
            object.setQuantity(association.getCurrent_quantity());
            object.setStorageID(association.getStorage_ID());
            object.setStorageName(association.getStorage().getStorageName());
            quantities.add(object);
            total += association.getCurrent_quantity();
        }

        remainder.setProductID(product.getProduct_ID());
        remainder.setProductName(product.getProductName());
        remainder.setCategoryID(product.getCategory_ID().getCategory_ID());
        remainder.setCategoryName(product.getCategory_ID().getCategoryName());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }

    //this method is overloaded
    //this method returns remainders of given storages
    public Remainder getList(String storages_json, Product product) throws Exception {

        List<StorageProduct> storages = new ArrayList<>();
        List<StorageQuantity> quantities = new ArrayList<>();
        float total = 0;
        Remainder remainder = new Remainder();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonArray = mapper.readTree(storages_json);

        for (JsonNode json : jsonArray) {
            StorageProduct result = product.getStorages().stream()
                    .filter(x -> x.getStorage_ID() == json.get("storage_ID").asLong())
                    .findAny()
                    .orElse(null);
            if (result != null)
                storages.add(result);
        }

        for (StorageProduct association : storages) {
            StorageQuantity object = new StorageQuantity();
            object.setQuantity(association.getCurrent_quantity());
            object.setStorageID(association.getStorage_ID());
            object.setStorageName(association.getStorage().getStorageName());
            quantities.add(object);
            total += association.getCurrent_quantity();
        }

        remainder.setProductID(product.getProduct_ID());
        remainder.setProductName(product.getProductName());
        remainder.setCategoryID(product.getCategory_ID().getCategory_ID());
        remainder.setCategoryName(product.getCategory_ID().getCategoryName());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }

    //this method is overloaded
    //this method returns remainders of given product
    //if returnNormal param get false value, it returns only defected storages goods
    public Remainder getList(Product product, boolean returnNormal) {
        float total = 0;
        Remainder remainder = new Remainder();

        List<StorageProduct> storages = product.getStorages();
        List<StorageQuantity> quantities = new ArrayList<>();
        for (StorageProduct association : storages) {
            //don't count defected storages
            Storage s = storageRepository.getOne(association.getStorage_ID()); // find storage of association
            //storage type should be null or not defected
            if (returnNormal) {
                if (s.getStorage_type() == null || !(s.getStorage_type().equals("defected"))) {
                    StorageQuantity object = new StorageQuantity();
                    object.setQuantity(association.getCurrent_quantity());
                    object.setStorageID(association.getStorage_ID());
                    object.setStorageName(association.getStorage().getStorageName());
                    quantities.add(object);
                    total += association.getCurrent_quantity();
                }
            } else {
                if (s.getStorage_type() != null && s.getStorage_type().equals("defected")) {
                    StorageQuantity object = new StorageQuantity();
                    object.setQuantity(association.getCurrent_quantity());
                    object.setStorageID(association.getStorage_ID());
                    object.setStorageName(association.getStorage().getStorageName());
                    quantities.add(object);
                    total += association.getCurrent_quantity();
                }
            }
        }

        remainder.setProductID(product.getProduct_ID());
        remainder.setProductName(product.getProductName());
        remainder.setCategoryID(product.getCategory_ID().getCategory_ID());
        remainder.setCategoryName(product.getCategory_ID().getCategoryName());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }

    public Remainder getStorageProducts(StorageProduct association) {
        float total = 0;
        Remainder remainder = new Remainder();
        List<StorageQuantity> quantities = new ArrayList<>();
        StorageQuantity object = new StorageQuantity();
        object.setQuantity(association.getCurrent_quantity());
        object.setStorageID(association.getStorage_ID());
        object.setStorageName(association.getStorage().getStorageName());
        quantities.add(object);
        total += association.getCurrent_quantity();
        remainder.setProductID(association.getProduct_ID());
        remainder.setProductName(association.getProduct().getProductName());
        remainder.setCategoryID(association.getProduct().getCategory_ID().getCategory_ID());
        remainder.setCategoryName(association.getProduct().getCategory_ID().getCategoryName());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }

    //utilizes getStorageProducts method
    public List<Remainder> getListByStorage(String storage_id) {
        Optional<Storage> storage = storageRepository.findById(Long.valueOf(storage_id));
        List<StorageProduct> storageProducts = storage.get().getProducts();

        List<Remainder> remainderListByStorage = new ArrayList<>();
        for (StorageProduct association : storageProducts) {
            remainderListByStorage.add(getStorageProducts(association));
        }
        return remainderListByStorage;
    }

    public List<Remainder> getListByCommonDepartment(String storages) throws Exception {

        List<Product> products = productRepository.findAll();
        List<Remainder> remainderList = new ArrayList<>();
        for (Product product : products) {
            remainderList.add(getList(storages, product));
        }
        return remainderList;

    }
}
