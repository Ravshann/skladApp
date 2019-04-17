package uz.skladapp.model.pure_models;


import javax.persistence.*;

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


    public Long getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(Long category_ID) {
        this.category_ID = category_ID;
    }

    public Category getParent_category_ID() {
        return parent_category_ID;
    }

    public void setParent_category_ID(Category parent_category_ID) {
        this.parent_category_ID = parent_category_ID;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_notes() {
        return category_notes;
    }

    public void setCategory_notes(String category_notes) {
        this.category_notes = category_notes;
    }

    public String getUnit_measure() {
        return unit_measure;
    }

    public void setUnit_measure(String unit_measure) {
        this.unit_measure = unit_measure;
    }
}
