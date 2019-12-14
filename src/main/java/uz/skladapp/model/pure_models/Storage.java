package uz.skladapp.model.pure_models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storage_ID;

    private String address;
    private String storage_type;

    @ManyToOne
    @JoinColumn(name = "department_ID")
    private Department department_ID;

    @OneToOne
    @JoinColumn(name = "storage_manager_ID")
    private User storage_manager_ID;

    private String storage_name;
    private String storage_phone;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StorageProduct> products;
}
