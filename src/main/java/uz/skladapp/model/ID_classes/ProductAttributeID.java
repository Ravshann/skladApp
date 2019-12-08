package uz.skladapp.model.ID_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class ProductAttributeID implements Serializable {
    private long product_ID;
    private long attribute_ID;
}
