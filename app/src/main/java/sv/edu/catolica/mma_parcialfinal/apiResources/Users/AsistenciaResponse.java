package sv.edu.catolica.mma_parcialfinal.apiResources.Users;

import java.io.Serializable;

public class AsistenciaResponse implements Serializable {
    private String registered_date;
    private Student student;

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
