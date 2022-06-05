package sv.edu.catolica.mma_parcialfinal.apiResources.Assistances;

import java.io.Serializable;

public class AssistanceResponse implements Serializable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
