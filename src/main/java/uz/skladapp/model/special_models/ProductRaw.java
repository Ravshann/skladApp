package uz.skladapp.model.special_models;

import java.util.List;

public class ProductRaw {
    private Long product_ID;

    private String product_name;
    private CategoryRaw category;
    private List<AttributeRaw> attributes;

    public ProductRaw(Long product_ID, String product_name, CategoryRaw category, List<AttributeRaw> attributes) {
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.category = category;
        this.attributes = attributes;
    }

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

    public CategoryRaw getCategory() {
        return category;
    }

    public void setCategory(CategoryRaw category) {
        this.category = category;
    }

    public List<AttributeRaw> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeRaw> attributes) {
        this.attributes = attributes;
    }
}
