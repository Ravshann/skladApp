package uz.skladapp.model.ID_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class UserCompanyID implements Serializable {
    private long user_ID;
    private long company_ID;
}
