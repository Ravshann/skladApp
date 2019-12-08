package uz.skladapp.model.ID_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
public class DepartmentClientID implements Serializable {
    private long department_ID;
    private long client_ID;
}
