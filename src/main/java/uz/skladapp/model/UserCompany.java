package uz.skladapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import uz.skladapp.model.ID_classes.UserCompanyID;

import javax.persistence.*;

@Entity
@Table(name="USER_COMPANY")
@IdClass(UserCompanyID.class)
public class UserCompany {
    @Id
    private long user_ID;
    @Id
    private long company_ID;

    @JsonBackReference // this needed to json generation
    @ManyToOne
    @JoinColumn(name = "company_ID", updatable = false, insertable = false, referencedColumnName = "company_ID")
    private Company company;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_ID", updatable = false, insertable = false, referencedColumnName = "user_ID")
    private User user;

    // setters getters


    public long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(long user_ID) {
        this.user_ID = user_ID;
    }

    public long getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(long company_ID) {
        this.company_ID = company_ID;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
