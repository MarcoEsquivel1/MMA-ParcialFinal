package sv.edu.catolica.mma_parcialfinal.apiResources;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginResponse;

public interface UserService {

    @POST("auth/login/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
