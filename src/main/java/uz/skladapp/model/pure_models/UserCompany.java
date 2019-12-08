package uz.skladapp.model.pure_models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import uz.skladapp.model.ID_classes.UserCompanyID;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
@Entity
@Table(name = "USER_COMPANY")
@IdClass(UserCompanyID.class)
public class UserCompany {
    @Id
    private long user_ID;
    @Id
    private long company_ID;

    @JsonBackReference // this needed to json generation
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "company_ID", updatable = false, insertable = false, referencedColumnName = "company_ID")
    private Company company;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "user_ID", updatable = false, insertable = false, referencedColumnName = "user_ID")
    private User user;

}
