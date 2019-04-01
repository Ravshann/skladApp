package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class StorageProductID implements Serializable {

    private long storage_ID;
    private long product_ID;


    //setters getters


    public long getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(long storage_ID) {
        this.storage_ID = storage_ID;
    }

    public long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(long product_ID) {
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
