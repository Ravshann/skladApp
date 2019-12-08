package uz.skladapp.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailDTO {
    private String username;
    private String token;
    private String role;
    private long user_ID;
}
