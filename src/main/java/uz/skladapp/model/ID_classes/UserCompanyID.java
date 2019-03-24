package uz.skladapp.model.ID_classes;

import java.io.Serializable;

public class UserCompanyID implements Serializable {
    private long user_ID;
    private long company_ID;

    //setters getters


    public long getUser_ID() {
        return user_ID; }

    public void setUser_ID(long user_ID) {
        this.user_ID = user_ID;
    }

    public long getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(long company_ID) {
        this.company_ID = company_ID;
    }
    // needed for spring
    @Override
    public int hashCode() {
        return (int)(user_ID + company_ID);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof UserCompanyID) {
            UserCompanyID otherId = (UserCompanyID) object;
            return (otherId.company_ID== this.company_ID) && (otherId.user_ID == this.user_ID);
        }
        return false;
    }
}
