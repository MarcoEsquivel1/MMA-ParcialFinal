package sv.edu.catolica.mma_parcialfinal.apiResources.Auth;

import java.io.Serializable;

public class RegisterResponse implements Serializable {
    private int role_id;
    private String x_access_token;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getX_access_token() {
        return x_access_token;
    }

    public void setX_access_token(String x_access_token) {
        this.x_access_token = x_access_token;
    }
}
