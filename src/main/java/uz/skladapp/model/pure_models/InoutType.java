package uz.skladapp.model.pure_models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INOUT_TYPE")
public class InoutType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inout_type_ID;

    private String inout_type_name;

}
