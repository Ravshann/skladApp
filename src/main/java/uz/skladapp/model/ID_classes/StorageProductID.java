package uz.skladapp.model.ID_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class StorageProductID implements Serializable {

    private long storage_ID;
    private long product_ID;
}
