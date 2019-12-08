package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.skladapp.model.ID_classes.StorageProductID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
@NoArgsConstructor
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

    @Column(name = "total_quantity")
    private float total_quantity;

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


    public StorageProduct(float current_quantity, float price, Product product, Storage storage) {
        this.current_quantity = current_quantity;
        this.price = price;
        this.product = product;
        this.storage = storage;
        this.storage_ID = storage.getStorage_ID();
        this.product_ID = product.getProduct_ID();
    }

}
