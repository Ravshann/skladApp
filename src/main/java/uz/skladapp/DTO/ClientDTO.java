package uz.skladapp.DTO;

import lombok.Data;

@Data
public class ClientDTO {
    private Long client_ID;
    private String client_name;
    private String region;
    private String client_type;

    public ClientDTO() {
    }

    public ClientDTO(Long client_ID, String client_name, String region, String client_type) {
        this.client_ID = client_ID;
        this.client_name = client_name;
        this.region = region;
        this.client_type = client_type;
    }

}
