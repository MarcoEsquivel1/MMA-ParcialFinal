package sv.edu.catolica.mma_parcialfinal.apiResources.Auth;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private User user;
    private String x_access_token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getX_access_token() {
        return x_access_token;
    }

    public void setX_access_token(String x_access_token) {
        this.x_access_token = x_access_token;
    }
}
