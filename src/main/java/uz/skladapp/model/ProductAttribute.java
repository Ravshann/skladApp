package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import uz.skladapp.model.ID_classes.ProductAttributeID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

/*
======================
note!
do not use lombok library if you are going to access getter methods of object later

======================

 */


@Entity
@Table(name = "PRODUCT_ATTRIBUTE")
@IdClass(ProductAttributeID.class)
public class ProductAttribute {
    @Id
    private long product_ID;
    @Id
    private long attribute_ID;

    @Column(name = "value")
    private String value;


    @JsonBackReference // this needed to json generation
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "product_ID", updatable = false, insertable = false, referencedColumnName = "product_ID")
    private Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "attribute_ID", updatable = false, insertable = false, referencedColumnName = "attribute_ID")
    private Attribute attribute;

    public ProductAttribute() {
    }

    public ProductAttribute(String value, Product product, Attribute attribute) {
        this.value = value;
        this.product = product;
        this.attribute = attribute;
        this.product_ID = product.getProduct_ID();
        this.attribute_ID = attribute.getAttribute_ID();
    }

    //setters getters


    public long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(long product_ID) {
        this.product_ID = product_ID;
    }

    public long getAttribute_ID() {
        return attribute_ID;
    }

    public void setAttribute_ID(long attribute_ID) {
        this.attribute_ID = attribute_ID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
