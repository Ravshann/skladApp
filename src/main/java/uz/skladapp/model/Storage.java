package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Entity

public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storage_ID;

    private String address;

    @ManyToOne
    @JoinColumn(name = "department_ID")
    private Department department_ID;

    @OneToOne
    @JoinColumn(name = "storage_manager_ID")
    private User storage_manager_ID;

    private String storage_name;
    private String storage_phone;

    @JsonManagedReference
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StorageProduct> products;


    // Create an association object for the relationship and set its data.
//    public void addProduct(Product product, float current_quantity, float price) {
//        StorageProduct association = new StorageProduct();
//        association.setProduct(product);
//        association.setStorage(this);
//        association.setProduct_ID(product.getProduct_ID());
//        association.setStorage_ID(this.getStorage_ID());
//        association.setCurrent_quantity(current_quantity);
//        association.setTotal_quantity(current_quantity);
//        association.setPrice(price);
//
//        if (this.products == null)
//            this.products = new ArrayList<>();
//
//        this.products.add(association);
//        // Also add the association object to the other class.
//        product.getStorages().add(association);
//    }
//
//    public void removeProduct(Product product) {
//        for (Iterator<StorageProduct> iterator = products.iterator(); iterator.hasNext(); ) {
//            StorageProduct storageProduct = iterator.next();
//
//            if (storageProduct.getStorage().equals(this) && storageProduct.getProduct().equals(product)) {
//                iterator.remove();
//                storageProduct.getProduct().getStorages().remove(storageProduct);
//                storageProduct.setStorage(null);
//                storageProduct.setProduct(null);
//            }
//        }
//    }
//
//    public void changeCurrentQuantity(Product product, Float quantity, InoutType inoutType) {
//        boolean found = false;
//        if (products.isEmpty()) {
//            addProduct(product, quantity, 0);
//        } else {
//            for (Iterator<StorageProduct> iterator = products.iterator(); iterator.hasNext(); ) {
//                StorageProduct storageProduct = iterator.next();
//
//                if (storageProduct.getStorage().equals(this) && storageProduct.getProduct().equals(product)) {
//                    found = true;
//                    if (inoutType.getInout_type_name().equals("import") || inoutType.getInout_type_name().equals("returned")) {
//                        storageProduct.setCurrent_quantity(quantity + storageProduct.getCurrent_quantity());
//                        storageProduct.setTotal_quantity(quantity + storageProduct.getTotal_quantity());
//                    } else if (inoutType.getInout_type_name().equals("export") && storageProduct.getCurrent_quantity() >= quantity) {
//                        storageProduct.setCurrent_quantity(storageProduct.getCurrent_quantity() - quantity);
//                        storageProduct.setTotal_quantity(storageProduct.getTotal_quantity() - quantity);
//                    }
//                }
//
//            }
//            if (!found)
//                addProduct(product, quantity, 0);
//        }
//
//
//    }

    public Long getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(Long storage_ID) {
        this.storage_ID = storage_ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment_ID() {
        return department_ID;
    }

    public void setDepartment_ID(Department department_ID) {
        this.department_ID = department_ID;
    }

    public User getStorage_manager_ID() {
        return storage_manager_ID;
    }

    public void setStorage_manager_ID(User storage_manager_ID) {
        this.storage_manager_ID = storage_manager_ID;
    }

    public String getStorage_name() {
        return storage_name;
    }

    public void setStorage_name(String storage_name) {
        this.storage_name = storage_name;
    }

    public String getStorage_phone() {
        return storage_phone;
    }

    public void setStorage_phone(String storage_phone) {
        this.storage_phone = storage_phone;
    }

    public List<StorageProduct> getProducts() {
        return products;
    }

    public void setProducts(List<StorageProduct> products) {
        this.products = products;
    }


}
