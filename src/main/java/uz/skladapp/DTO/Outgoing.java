package uz.skladapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Outgoing {
    private Long record_ID;
    private String product_name;
    private Long product_ID;
    private String storage_name;
    private Long storage_ID;
    private String category_name;
    private Long category_ID;
    private String client_region;
    private String client_name;
    private Long client_ID;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tashkent") // add this to get hour precision=====>>> hh:mm:ss
    private Date record_datetime;
    private Float quantity;
    private Float price;

}
