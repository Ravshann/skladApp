package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import uz.skladapp.model.ID_classes.ProductAttributeID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;


@Data
@Entity
@Table(name = "PRODUCT_ATTRIBUTE")
@IdClass(ProductAttributeID.class)
public class ProductAttribute {
    @Id
    private long product_ID;
    @Id
    private long attribute_ID;

    @Column(name = "attribute_value")
    private String attribute_value;


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

    public ProductAttribute(String attribute_value, Product product, Attribute attribute) {
        this.attribute_value = attribute_value;
        this.product = product;
        this.attribute = attribute;
        this.product_ID = product.getProduct_ID();
        this.attribute_ID = attribute.getAttribute_ID();
    }

}
