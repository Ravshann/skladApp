package uz.skladapp.model.raw_models;

public class StorageQuantity {
    private Long storageID;
    private String storageName;
    private Float quantity;

    public Long getStorageID() {
        return storageID;
    }

    public void setStorageID(Long storageID) {
        this.storageID = storageID;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
