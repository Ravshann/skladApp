package uz.skladapp.model.special_models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Outgoing {
    private Long record_ID;
    private String product_name;
    private Long product_ID;
    private String category_name;
    private Long category_ID;
    private String client_region;
    private String client_name;
    @JsonFormat(pattern = "yyyy-MM-dd") // add this to get hour precision=====>>> hh:mm:ss
    private Date record_datetime;
    private Float quantity;

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public Long getRecord_ID() {
        return record_ID;
    }

    public void setRecord_ID(Long record_ID) {
        this.record_ID = record_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(Long product_ID) {
        this.product_ID = product_ID;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Long getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(Long category_ID) {
        this.category_ID = category_ID;
    }

    public String getClient_region() {
        return client_region;
    }

    public void setClient_region(String client_region) {
        this.client_region = client_region;
    }

    public Date getRecord_datetime() {
        return record_datetime;
    }

    public void setRecord_datetime(Date record_datetime) {
        this.record_datetime = record_datetime;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
