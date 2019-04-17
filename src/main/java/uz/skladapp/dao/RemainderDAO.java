package uz.skladapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.skladapp.model.pure_models.Product;
import uz.skladapp.model.pure_models.StorageProduct;
import uz.skladapp.model.repositories.ProductRepository;
import uz.skladapp.model.special_models.Remainder;
import uz.skladapp.model.special_models.StorageQuantity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemainderDAO {

    @Autowired
    private ProductRepository productRepository;

    public List<Remainder> getAll() {
        List<Product> products = productRepository.findAll();
        List<Remainder> remainderList = new ArrayList<>();
        for (Product product : products) {
            remainderList.add(getList(product.getProduct_ID().toString()));
        }
        return remainderList;
    }

    public Remainder getList(String id) {
        float total = 0;
        Remainder remainder = new Remainder();
        Optional<Product> product = productRepository.findById(Long.valueOf(id));
        List<StorageProduct> storages = product.get().getStorages();
        List<StorageQuantity> quantities = new ArrayList<>();
        for (StorageProduct association : storages) {
            StorageQuantity object = new StorageQuantity();
            object.setQuantity(association.getCurrent_quantity());
            object.setStorageID(association.getStorage_ID());
            object.setStorageName(association.getStorage().getStorage_name());
            quantities.add(object);
            total += association.getCurrent_quantity();
        }

        remainder.setProductID(Long.valueOf(id));
        remainder.setProductName(product.get().getProduct_name());
        remainder.setCategoryID(product.get().getCategory_ID().getCategory_ID());
        remainder.setCategoryName(product.get().getCategory_ID().getCategory_name());
        remainder.setStorageQuantities(quantities);
        remainder.setTotal(total);
        return remainder;
    }


}
