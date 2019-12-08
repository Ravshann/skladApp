package uz.skladapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Long company_ID;
    private String name;
    //    private String logo;
    private String address;
    private String company_phone;
}
