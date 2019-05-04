package uz.skladapp.model.raw_models;

public class SupplierRaw {
    private Long supplier_ID;

    private String supplier_name;

    public Long getSupplier_ID() {
        return supplier_ID;
    }

    public void setSupplier_ID(Long supplier_ID) {
        this.supplier_ID = supplier_ID;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public SupplierRaw(Long supplier_ID, String supplier_name) {

        this.supplier_ID = supplier_ID;
        this.supplier_name = supplier_name;
    }
}
