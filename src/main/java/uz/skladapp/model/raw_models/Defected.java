package uz.skladapp.model.raw_models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Defected {

    private Long record_ID;
    private String product_name;
    private Long product_ID;
    private String storage_name;
    private Long storage_ID;
    private String category_name;
    private Long category_ID;
    private Long supplier_ID;
    private String supplier_name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tashkent") // add this to get hour precision=====>>> hh:mm:ss
    private Date record_datetime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Tashkent") // add this to get hour precision=====>>> hh:mm:ss
    private Date updated_time;
    private Float quantity;
    private String note;

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getStorage_name() {
        return storage_name;
    }

    public void setStorage_name(String storage_name) {
        this.storage_name = storage_name;
    }

    public Long getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(Long storage_ID) {
        this.storage_ID = storage_ID;
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

    public Long getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(Long supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
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
