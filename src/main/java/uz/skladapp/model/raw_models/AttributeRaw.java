package uz.skladapp.model.raw_models;

public class AttributeRaw {
    private Long attribute_ID;
    private String attribute_name;
    private String attribute_value;

    public AttributeRaw(Long attribute_ID, String attribute_name) {
        this.attribute_ID = attribute_ID;
        this.attribute_name = attribute_name;
    }

    public AttributeRaw() {
    }

    public String getAttribute_value() {
        return attribute_value;
    }

    public void setAttribute_value(String attribute_value) {
        this.attribute_value = attribute_value;
    }

    public Long getAttribute_ID() {
        return attribute_ID;
    }

    public void setAttribute_ID(Long attribute_ID) {
        this.attribute_ID = attribute_ID;
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }
}
