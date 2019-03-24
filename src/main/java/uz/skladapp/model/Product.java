package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_ID;

    private String product_name;

    //foreign key
    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category_ID;

//    @OneToMany(mappedBy = "product_ID", cascade = CascadeType.ALL)
//    private List<StorageProduct> storage_products;

    @JsonManagedReference
    @OneToMany(mappedBy="product")
    private List<ProductAttribute> attributes;

    @JsonManagedReference
    @OneToMany(mappedBy="product")
    private List<StorageProduct> storages;


    // Create an association object for the relationship and set its data.
    public void addAttribute(Attribute attribute, String value) {
        ProductAttribute association = new ProductAttribute();
        association.setAttribute(attribute);
        association.setProduct(this);
        association.setAttribute_ID(attribute.getAttribute_ID());
        association.setProduct_ID(this.getProduct_ID());
        association.setValue(value);

        if(this.attributes == null)
            this.attributes = new ArrayList<>();

        this.attributes.add(association);
        // Also add the association object to the other class
        attribute.getProducts().add(association);
    }

    //
    public Long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(Long product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Category getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(Category category_ID) {
        this.category_ID = category_ID;
    }

    public List<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<StorageProduct> getStorages() {
        return storages;
    }

    public void setStorages(List<StorageProduct> storages) {
        this.storages = storages;
    }
}
