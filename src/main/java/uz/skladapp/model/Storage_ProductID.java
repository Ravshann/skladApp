//package uz.skladapp.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.Embeddable;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import java.io.Serializable;
//
//@Embeddable
//@Getter @Setter
//public class Storage_ProductID implements Serializable {
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "storage_ID", referencedColumnName = "storage_ID")
//    private Long storage_ID;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "product_ID", referencedColumnName = "product_ID")
//    private Long product_ID;
//
//    @Override
//    public int hashCode() {
//        return (int)(storage_ID + storage_ID);
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (object instanceof Storage_ProductID) {
//            Storage_ProductID otherId = (Storage_ProductID) object;
//            return (otherId.storage_ID == this.storage_ID) && (otherId.product_ID == this.product_ID);
//        }
//        return false;
//    }
//
//}
