package uz.skladapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.Storage;
import uz.skladapp.model.pure_models.StorageProduct;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.raw_models.Remainder;
import uz.skladapp.model.raw_models.StorageQuantity;
import uz.skladapp.model.repositories.StorageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemainderDAO {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;

    public List<Remainder> getAll() {
        List<Product> products = productRepository.findAll();
        List<Remainder> remainderList = new ArrayList<>();
        for (Product product : products) {
            remainderList.add(getList(product));
        }
        return remainderList;
    }

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
            object.setStorageName(association.getStorage().getStorage_name());
            quantities.add(object);
            total += association.getCurrent_quantity();
        }

        remainder.setProductID(product.getProduct_ID());
        remainder.setProductName(product.getProduct_name());
        remainder.setCategoryID(product.getCategory_ID().getCategory_ID());
        remainder.setCategoryName(product.getCategory_ID().getCategory_name());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }


    public Remainder getList(Product product) {
        float total = 0;
        Remainder remainder = new Remainder();

        List<StorageProduct> storages = product.getStorages();
        List<StorageQuantity> quantities = new ArrayList<>();
        for (StorageProduct association : storages) {
            StorageQuantity object = new StorageQuantity();
            object.setQuantity(association.getCurrent_quantity());
            object.setStorageID(association.getStorage_ID());
            object.setStorageName(association.getStorage().getStorage_name());
            quantities.add(object);
            total += association.getCurrent_quantity();
        }

        remainder.setProductID(product.getProduct_ID());
        remainder.setProductName(product.getProduct_name());
        remainder.setCategoryID(product.getCategory_ID().getCategory_ID());
        remainder.setCategoryName(product.getCategory_ID().getCategory_name());
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
        object.setStorageName(association.getStorage().getStorage_name());
        quantities.add(object);
        total += association.getCurrent_quantity();
        remainder.setProductID(association.getProduct_ID());
        remainder.setProductName(association.getProduct().getProduct_name());
        remainder.setCategoryID(association.getProduct().getCategory_ID().getCategory_ID());
        remainder.setCategoryName(association.getProduct().getCategory_ID().getCategory_name());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }


    public List<Remainder> getListByStorage(String storage_id) {
        Optional<Storage> storage = storageRepository.findById(Long.valueOf(storage_id));
        List<StorageProduct> storageProducts = storage.get().getProducts();

        List<Remainder> remainderListByStorage = new ArrayList<>();
        for (StorageProduct association : storageProducts) {
            remainderListByStorage.add(getStorageProducts(association));
        }
        return remainderListByStorage;
    }
}
