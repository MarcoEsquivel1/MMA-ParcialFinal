package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.User;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursoRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursoResponse;

public class CrearCursoActivity extends AppCompatActivity {
    public Bundle extras;
    public String token;
    public int rol_id;
    public TextInputEditText TINombre, TIDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_curso);
        TINombre = findViewById(R.id.TINombreCurso);
        TIDescripcion = findViewById(R.id.TIDescripcion);

        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");
        Toast.makeText(CrearCursoActivity.this, token, Toast.LENGTH_LONG).show();
    }

    public void CrearOnClick(View view) {
        if (TextUtils.isEmpty(TINombre.getText().toString()) || TextUtils.isEmpty(TIDescripcion.getText().toString())){
            String message = "Todos los campos son necesarios";
            Toast.makeText(CrearCursoActivity.this, message, Toast.LENGTH_LONG).show();
        }else{
            CursoRequest cursoRequest = new CursoRequest();
            cursoRequest.setName(TINombre.getText().toString());
            cursoRequest.setDescription(TIDescripcion.getText().toString());
            String header = "Bearer " + token;
            crearCurso(header, cursoRequest);
        }
    }

    public void CancelarOnClick(View view) {
        Intent ventanaHome = new Intent(this, DocenteActivity.class);
        ventanaHome.putExtra("token", token);
        ventanaHome.putExtra("rol", rol_id);
        startActivity(ventanaHome);
        finish();
    }

    public void crearCurso(String header, CursoRequest cursoRequest){
        Call<CursoResponse> cursoResponseCall = ApiClient.getService().createCourse(header,cursoRequest);
        cursoResponseCall.enqueue(new Callback<CursoResponse>() {
            @Override
            public void onResponse(Call<CursoResponse> call, Response<CursoResponse> response) {
                if (response.isSuccessful()){
                    String message = "Creado Correctamente";
                    Toast.makeText(CrearCursoActivity.this, message, Toast.LENGTH_LONG).show();
                    Intent ventanaHome = new Intent(CrearCursoActivity.this, DocenteActivity.class);
                    ventanaHome.putExtra("token", token);
                    ventanaHome.putExtra("rol", rol_id);
                    startActivity(ventanaHome);
                    finish();
                }else{
                    String message = "Ha ocurrido un error";
                    Toast.makeText(CrearCursoActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CursoResponse> call, Throwable t) {

            }
        });
    }
}