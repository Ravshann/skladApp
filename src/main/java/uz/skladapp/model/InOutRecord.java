package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

;

@Entity
@Table(name = "INOUT_RECORD")
public class InOutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_ID;

    private float quantity;
    private float price;
    private String inout_type;


    @OneToOne
    @JoinColumn(name = "client_ID")
    private Client client_ID;

    @OneToOne
    @JoinColumn(name = "supplier_ID")
    private Supplier supplier_ID;

    @JsonFormat(pattern = "yyyy-MM-dd") // add this to get hour precision ====>>>>> hh:mm:ss
    @Temporal(TemporalType.TIMESTAMP)
    private Date record_time;

    @ManyToOne
    @JoinColumn(name = "storage_ID")
    private Storage storage_ID;

    @ManyToOne
    @JoinColumn(name = "product_ID")
    private Product product_ID;

    public Date getRecord_time() {
        return record_time;
    }

    public void setRecord_time(Date record_time) {
        this.record_time = record_time;
    }


    public Client getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(Client client_ID) {
        this.client_ID = client_ID;
    }

    public Supplier getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(Supplier supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public Long getRecord_ID() {
        return record_ID;
    }

    public void setRecord_ID(Long record_ID) {
        this.record_ID = record_ID;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getInout_type() {
        return inout_type;
    }

    public void setInout_type(String inout_type) {
        this.inout_type = inout_type;
    }

    public Storage getStorage_ID() {
        return storage_ID;
    }

    public void setStorage_ID(Storage storage_ID) {
        this.storage_ID = storage_ID;
    }

    public Product getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(Product product_ID) {
        this.product_ID = product_ID;
    }
}
