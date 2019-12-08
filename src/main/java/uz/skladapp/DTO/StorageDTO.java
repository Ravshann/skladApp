package uz.skladapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
    private Long storage_ID;

    private String address;
    private Long department_ID;
    private Long storage_manager_ID;

    private String storage_name;
    private String storage_phone;
    private String department_name;
    private String storage_manager_name;
    private String storage_type;

}
