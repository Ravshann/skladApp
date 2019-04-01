package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class ProductAttributeID implements Serializable {
    private long product_ID;
    private long attribute_ID;

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


    //this is needed for  IDclasses
    public int hashCode() {
        return (int)(product_ID+attribute_ID);
    }

    public boolean equals(Object object) {
        if (object instanceof ProductAttributeID) {
            ProductAttributeID otherId = (ProductAttributeID) object;
            return (otherId.product_ID == this.product_ID) && (otherId.attribute_ID == this.attribute_ID);
        }
        return false;
    }
}
