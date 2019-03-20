package uz.skladapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_ID;

    private String product_name;

    //foreign key
    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category_ID;

//    @OneToMany(mappedBy = "product_ID", cascade = CascadeType.ALL)
//    private List<Storage_Product> storage_products;
}
