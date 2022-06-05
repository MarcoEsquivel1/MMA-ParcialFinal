package sv.edu.catolica.mma_parcialfinal.apiResources;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import sv.edu.catolica.mma_parcialfinal.apiResources.Assistances.AssistanceRequired;
import sv.edu.catolica.mma_parcialfinal.apiResources.Assistances.AssistanceResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.RegisterRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.RegisterResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursoRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursoResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingRequired;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingsRequired;

public interface UserService {

    @POST("auth/login/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("courses/")
    Call<List<CursosResponse>> getAllCourses(@Header("Authorization") String auth);

    @POST("courses/")
    Call<CursoResponse> createCourse(@Header("Authorization") String auth, @Body CursoRequest cursoRequest);

    @POST("meetings/all")
    Call<List<MeetingResponse>> getAllMeetings(@Header("Authorization") String auth, @Header("Accept") String x, @Body MeetingsRequired meetingsRequired);

    @POST("auth/register/")
    Call<RegisterResponse> registerUser(@Header("Accept") String x,@Body RegisterRequest registerRequest);

    @POST("meetings/")
    Call<MeetingResponse> createMeeting(@Header("Authorization") String auth, @Body MeetingRequired meetingRequired);

    @POST("assistances/")
    Call<AssistanceResponse> createAssistance(@Header("Authorization") String auth, @Body AssistanceRequired assistanceRequired);
}
