package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class StorageProductID implements Serializable {

    private Long storage_ID;
    private Long product_ID;


    //setters getters

    public Long getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(Long storage_ID) {
        this.storage_ID = storage_ID;
    }

    public Long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(Long product_ID) {
        this.product_ID = product_ID;
    }

    // needed for spring
    @Override
    public int hashCode() {
        return (int)(storage_ID + product_ID);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StorageProductID) {
            StorageProductID otherId = (StorageProductID) object;
            return (otherId.storage_ID == this.storage_ID) && (otherId.product_ID == this.product_ID);
        }
        return false;
    }

}
