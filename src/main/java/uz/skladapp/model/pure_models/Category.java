package uz.skladapp.model.pure_models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_ID;

    @ManyToOne
    @JoinColumn(name = "parent_category_ID")
    private Category parent_category_ID;
    private String category_name;
    private String category_notes;
    private String unit_measure;

}
