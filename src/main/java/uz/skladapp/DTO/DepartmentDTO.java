package uz.skladapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.skladapp.model.pure_models.Company;
import uz.skladapp.model.pure_models.User;

@Data
@AllArgsConstructor
public class DepartmentDTO {
    private Long department_ID;
    private String name;
    private String description;
    private String address;
    private Company company;
    private User department_manager;
    private String department_phone;

}
