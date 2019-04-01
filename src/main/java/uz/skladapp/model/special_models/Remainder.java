package uz.skladapp.model.special_models;

import java.util.List;

public class Remainder {
    private Long productID;
    private String productName;
    private Long categoryID;
    private String categoryName;
    private Float total;
    private List<StorageQuantity> storageQuantities;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<StorageQuantity> getStorageQuantities() {
        return storageQuantities;
    }

    public void setStorageQuantities(List<StorageQuantity> storageQuantities) {
        this.storageQuantities = storageQuantities;
    }
}
