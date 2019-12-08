package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "INOUT_RECORD")
public class InOutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_ID;

    private float quantity;
    private float price;


    @ManyToOne
    @JoinColumn(name = "inout_type_ID")
    private InoutType inout_type_ID;


    @ManyToOne
    @JoinColumn(name = "client_ID")
    private Client client_ID;

    @ManyToOne
    @JoinColumn(name = "supplier_ID")
    private Supplier supplier_ID;

    @JsonFormat(pattern = "yyyy-MM-dd") // add this to get hour precision ====>>>>> hh:mm:ss
    @Temporal(TemporalType.TIMESTAMP)
    private Date record_time;

    @JsonFormat(pattern = "yyyy-MM-dd") // add this to get hour precision ====>>>>> hh:mm:ss
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_time;

    @ManyToOne
    @JoinColumn(name = "storage_ID")
    private Storage storage_ID;

    @ManyToOne
    @JoinColumn(name = "product_ID")
    private Product product_ID;

    private String record_note;

}
