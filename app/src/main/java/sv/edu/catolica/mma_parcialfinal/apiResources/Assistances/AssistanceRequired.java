package sv.edu.catolica.mma_parcialfinal.apiResources.Assistances;

import java.io.Serializable;

public class AssistanceRequired implements Serializable {
    private String secret_code;

    public String getSecret_code() {
        return secret_code;
    }

    public void setSecret_code(String secret_code) {
        this.secret_code = secret_code;
    }
}
