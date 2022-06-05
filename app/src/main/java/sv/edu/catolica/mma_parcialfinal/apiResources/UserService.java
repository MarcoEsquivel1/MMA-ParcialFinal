package sv.edu.catolica.mma_parcialfinal.apiResources;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;

public interface UserService {

    @POST("auth/login/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("courses/")
    Call<List<CursosResponse>> getAllCourses(@Header("Authorization") String auth);
}
