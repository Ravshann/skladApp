package uz.skladapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttributeDTO {
    private Long attribute_ID;
    private String attribute_name;
    private String attribute_value;

    public AttributeDTO(Long attribute_ID, String attribute_name) {
        this.attribute_ID = attribute_ID;
        this.attribute_name = attribute_name;
    }

}
