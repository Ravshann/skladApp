package uz.skladapp.model.raw_models;

public class CategoryRaw {

    private Long category_ID;
    private Long parent_category_ID;
    private String parent_category_name;
    private String category_name;
    private String category_notes;
    private String unit_measure;

    public CategoryRaw(Long category_ID, String category_name, String category_notes, String unit_measure) {
        this.category_ID = category_ID;
        this.category_name = category_name;
        this.category_notes = category_notes;
        this.unit_measure = unit_measure;
    }

    public CategoryRaw() {
    }

    public String getParent_category_name() {
        return parent_category_name;
    }

    public void setParent_category_name(String parent_category_name) {
        this.parent_category_name = parent_category_name;
    }

    public Long getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(Long category_ID) {
        this.category_ID = category_ID;
    }

    public Long getParent_category_ID() {
        return parent_category_ID;
    }

    public void setParent_category_ID(Long parent_category_ID) {
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
