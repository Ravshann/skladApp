package uz.skladapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Defected {

    private Long record_ID;
    private String product_name;
    private Long product_ID;
    private String storage_name;
    private Long storage_ID;
    private String category_name;
    private Long category_ID;
    private Long supplier_storage_ID;
    private String supplier_storage_name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tashkent") // add this to get hour precision=====>>> hh:mm:ss
    private Date record_datetime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tashkent") // add this to get hour precision=====>>> hh:mm:ss
    private Date updated_time;
    private Float quantity;
    private String note;
}
