package uz.skladapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storage_ID;

    private String address;

    @ManyToOne
    @JoinColumn(name = "department_ID")
    private Department department_ID;

    @OneToOne
    @JoinColumn(name = "storage_manager_ID")
    private User storage_manager_ID;

    private String storage_name;
    private String storage_phone;

//    @OneToMany(mappedBy = "storage_ID", cascade = CascadeType.ALL)
//    private List<Storage_Product> storage_products;
}
