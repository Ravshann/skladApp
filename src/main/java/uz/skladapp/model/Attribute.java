package uz.skladapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attribute_ID;

    private String attribute_name;

    @JsonManagedReference
    @OneToMany(mappedBy="attribute")
    private List<ProductAttribute> products;


    //setters getters

    public Long getAttribute_ID() {
        return attribute_ID;
    }

    public void setAttribute_ID(Long attribute_ID) {
        this.attribute_ID = attribute_ID;
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }


    public List<ProductAttribute> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAttribute> products) {
        this.products = products;
    }
}
