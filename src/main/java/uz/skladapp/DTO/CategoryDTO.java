package uz.skladapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private Long category_ID;
    private Long parent_category_ID;
    private String parent_category_name;
    private String category_name;
    private String category_notes;
    private String unit_measure;

    public CategoryDTO(Long category_ID, String category_name, String category_notes, String unit_measure) {
        this.category_ID = category_ID;
        this.category_name = category_name;
        this.category_notes = category_notes;
        this.unit_measure = unit_measure;
    }
}
