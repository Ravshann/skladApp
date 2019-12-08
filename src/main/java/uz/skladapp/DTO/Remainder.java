package uz.skladapp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Remainder {
    private Long productID;
    private String productName;
    private Long categoryID;
    private String categoryName;
    private Float total;
    private List<StorageQuantity> storageQuantities;

}
