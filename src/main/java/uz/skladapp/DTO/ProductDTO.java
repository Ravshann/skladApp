package uz.skladapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long product_ID;
    private String product_name;
    private CategoryDTO category;
    private List<AttributeDTO> attributes;
}
