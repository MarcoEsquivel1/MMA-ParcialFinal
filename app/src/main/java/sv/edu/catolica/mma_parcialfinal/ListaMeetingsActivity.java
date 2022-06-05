package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.User;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingsAdapter;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingsRequired;

public class ListaMeetingsActivity extends AppCompatActivity {
    public FloatingActionButton fab;
    RecyclerView recyclerView;
    MeetingsAdapter meetingsAdapter;
    public Bundle extras;
    public String token;
    public int rol_id;
    public CursosResponse datosCurso;
    TextView nombre, desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_meetings);
        fab = findViewById(R.id.floating_action_button);
        nombre = findViewById(R.id.TvNombreCurso);
        desc = findViewById(R.id.TvDescCurso);
        recyclerView = findViewById(R.id.recycledView);

        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");
        datosCurso = (CursosResponse) extras.getSerializable("datosCurso");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        meetingsAdapter = new MeetingsAdapter();
        String header = "Bearer " + token;
        int id_curso = datosCurso.getId();
        MeetingsRequired meetingsRequired = new MeetingsRequired();
        meetingsRequired.setCourse_id(id_curso);
        nombre.setText(datosCurso.getName());
        desc.setText(datosCurso.getDescription());

        traerMeetings(header, meetingsRequired);
    }

    public void fabOnClick2(View view) {
        Intent ventana = new Intent(this, CrearAsistenciaActivity.class);
        ventana.putExtra("token", token);
        ventana.putExtra("rol", rol_id);
        ventana.putExtra("datosCurso", datosCurso);
        startActivity(ventana);
        finish();
    }

    public void traerMeetings(String header, MeetingsRequired meetingsRequired){
        String x = "application/json";
        Call<List<MeetingResponse>> listaMeetings = ApiClient.getService().getAllMeetings(header, x,meetingsRequired);

        listaMeetings.enqueue(new Callback<List<MeetingResponse>>() {
            @Override
            public void onResponse(Call<List<MeetingResponse>> call, Response<List<MeetingResponse>> response) {
                if (response.isSuccessful()){
                    List<MeetingResponse> meetingResponses = response.body();
                    meetingsAdapter.setData(meetingResponses);
                    recyclerView.setAdapter(meetingsAdapter);
                }else{
                    String message = "Ha ocurrido un error";
                    Toast.makeText(ListaMeetingsActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MeetingResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Log.e("Error", message);
            }
        });

    }
}