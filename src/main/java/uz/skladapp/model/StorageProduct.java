package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import uz.skladapp.model.ID_classes.StorageProductID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;


@Entity
@Table(name = "STORAGE_PRODUCT")
@IdClass(StorageProductID.class)
public class StorageProduct {
    @Id
    private long product_ID;
    @Id
    private long storage_ID;

    @Column(name = "current_quantity")
    private float current_quantity;

    @Column(name = "price")
    private float price;


    @JsonBackReference // this needed to json generation
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "product_ID", updatable = false, insertable = false, referencedColumnName = "product_ID")
    private Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "storage_ID", updatable = false, insertable = false, referencedColumnName = "storage_ID")
    private Storage storage;

    //
    public StorageProduct() {
    }

    public StorageProduct(float current_quantity, float price, Product product, Storage storage) {
        this.current_quantity = current_quantity;
        this.price = price;
        this.product = product;
        this.storage = storage;
        this.storage_ID = storage.getStorage_ID();
        this.product_ID = product.getProduct_ID();
    }

    //getters setters

    public long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(long product_ID) {
        this.product_ID = product_ID;
    }

    public long getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(long storage_ID) {
        this.storage_ID = storage_ID;
    }

    public float getCurrent_quantity() {
        return current_quantity;
    }

    public void setCurrent_quantity(float current_quantity) {
        this.current_quantity = current_quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
