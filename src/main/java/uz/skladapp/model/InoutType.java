package uz.skladapp.model;

import javax.persistence.*;

@Entity
@Table(name = "INOUT_TYPE")
public class InoutType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inout_type_ID;

    private String inout_type_name;

    public Long getInout_type_ID() {
        return inout_type_ID;
    }

    public void setInout_type_ID(Long inout_type_ID) {
        this.inout_type_ID = inout_type_ID;
    }

    public String getInout_type_name() {
        return inout_type_name;
    }

    public void setInout_type_name(String inout_type_name) {
        this.inout_type_name = inout_type_name;
    }
}
