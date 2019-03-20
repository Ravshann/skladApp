package uz.skladapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
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

//    @OneToMany(mappedBy = "parent_category_ID")
//    private List<Category> subCategorries;

//    @OneToMany(mappedBy = "category_ID")
//    private List<Product> subProducts;


}
