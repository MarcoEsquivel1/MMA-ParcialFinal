package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Users.AsistenciaAdapter;
import sv.edu.catolica.mma_parcialfinal.apiResources.Users.AsistenciaResponse;

public class Meeting_Details_Activity extends AppCompatActivity {
    TextView fecha, codigo;
    public Bundle extras;
    public String token;
    public int rol_id;
    public CursosResponse datosCurso;
    public MeetingResponse datosMeeting;
    RecyclerView recyclerView;
    AsistenciaAdapter asistenciaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        fecha = findViewById(R.id.TvFecha);
        codigo = findViewById(R.id.TvClave);


        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");
        datosCurso = (CursosResponse) extras.getSerializable("datosCurso");
        datosMeeting= (MeetingResponse) extras.getSerializable("datosMeeting");
        recyclerView = findViewById(R.id.recycledView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        asistenciaAdapter = new AsistenciaAdapter();

        fecha.setText(datosMeeting.getStart_date());
        codigo.setText(datosMeeting.getSecret_code());
        String header = "Bearer " + token;
        int meeting_id = datosMeeting.getId();
        traerAsistencia(header,meeting_id);
    }

    public void traerAsistencia(String head, int meeting_id){
        String x = "application/json";
        Call<List<AsistenciaResponse>> listaAsistencias = ApiClient.getService().getAssistances(meeting_id,head, x);
        listaAsistencias.enqueue(new Callback<List<AsistenciaResponse>>() {
            @Override
            public void onResponse(Call<List<AsistenciaResponse>> call, Response<List<AsistenciaResponse>> response) {
                if (response.isSuccessful()){
                    List<AsistenciaResponse> asistenciaResponses = response.body();
                    String data = asistenciaResponses.toString();
                    Log.e("nombre", data);
                    Handler manejador = new Handler();
                    manejador.postDelayed(() -> {
                    asistenciaAdapter.setData(asistenciaResponses);
                    recyclerView.setAdapter(asistenciaAdapter);
                    },500);
                }else{
                    String message = "Ha ocurrido un error";
                    Toast.makeText(Meeting_Details_Activity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AsistenciaResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Log.e("Error", message);
            }
        });
    }
}