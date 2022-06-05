package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosAdapter;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;

public class DocenteActivity extends AppCompatActivity implements CursosAdapter.ClickedItem{
    public FloatingActionButton fab;
    RecyclerView recyclerView;
    CursosAdapter cursosAdapter;
    public Bundle extras;
    public String token;
    public int rol_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        fab = findViewById(R.id.floating_action_button);

        recyclerView = findViewById(R.id.recycledView);

        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        cursosAdapter = new CursosAdapter(this::ClickedCurso);

        String header = "Bearer " + token;
        traerCursos(header);
    }

    public void fabOnClick(View view) {
        Intent ventanaClave = new Intent(this, CrearCursoActivity.class);
        ventanaClave.putExtra("token", token);
        ventanaClave.putExtra("rol", rol_id);
        startActivity(ventanaClave);
        finish();
    }


    public void traerCursos(String header){
        Call<List<CursosResponse>> listaCursos = ApiClient.getService().getAllCourses(header);

        listaCursos.enqueue(new Callback<List<CursosResponse>>() {
            @Override
            public void onResponse(Call<List<CursosResponse>> call, Response<List<CursosResponse>> response) {
                if (response.isSuccessful()){
                    List<CursosResponse> cursosResponses = response.body();
                    cursosAdapter.setData(cursosResponses);
                    recyclerView.setAdapter(cursosAdapter);
                }else{
                    String message = "Ha ocurrido un error";
                    Toast.makeText(DocenteActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CursosResponse>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(DocenteActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void ClickedCurso(CursosResponse cursosResponse) {
        Intent ventana = new Intent(this, ListaMeetingsActivity.class);
        ventana.putExtra("token", token);
        ventana.putExtra("rol", rol_id);
        ventana.putExtra("datosCurso", cursosResponse);
        startActivity(ventana);

    }
}