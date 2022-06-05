package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.User;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosAdapter;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;

public class DocenteActivity extends AppCompatActivity {
    public FloatingActionButton fab;
    RecyclerView recyclerView;
    CursosAdapter cursosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        fab = findViewById(R.id.floating_action_button);

        recyclerView = findViewById(R.id.recycledView);
        Bundle extras = getIntent().getExtras();

        String token = extras.getString("token");
        User datos = (User) extras.getSerializable("datos");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        cursosAdapter = new CursosAdapter();

        String header = "Bearer " + token;
        traerCursos(header);
    }

    public void fabOnClick(View view) {
        Intent ventanaClave = new Intent(this, CrearCursoActivity.class);
        startActivity(ventanaClave);
    }


    public void traerCursos(String header){
        Call<List<CursosResponse>> listaCursos = ApiClient.getService().getAllCourses(header);

        listaCursos.enqueue(new Callback<List<CursosResponse>>() {
            @Override
            public void onResponse(Call<List<CursosResponse>> call, Response<List<CursosResponse>> response) {
                if (response.isSuccessful()){
                    Log.e("Success", response.body().toString());
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
}