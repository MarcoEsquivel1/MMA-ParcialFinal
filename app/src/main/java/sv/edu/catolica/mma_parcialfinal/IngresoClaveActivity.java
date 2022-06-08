package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Assistances.AssistanceRequired;
import sv.edu.catolica.mma_parcialfinal.apiResources.Assistances.AssistanceResponse;

public class IngresoClaveActivity extends AppCompatActivity {
    public Bundle extras;
    public String token;
    public int rol_id;
    TextInputEditText ETclave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_clave);
        ETclave = findViewById(R.id.TIClave);
        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");
    }

    public void AsistenciaOnClick(View view) {
        if (TextUtils.isEmpty(ETclave.getText().toString())){
            Toast.makeText(this, "Debe introducir una clave", Toast.LENGTH_LONG).show();
        }else{
            AssistanceRequired assistanceRequired = new AssistanceRequired();
            assistanceRequired.setSecret_code(ETclave.getText().toString());
            String header = "Bearer " + token;
            enviarClave(header,assistanceRequired);
        }
    }

    public void enviarClave(String head, AssistanceRequired assistanceRequired){
        Call<AssistanceResponse> assistanceResponseCall = ApiClient.getService().createAssistance(head, assistanceRequired);
        assistanceResponseCall.enqueue(new Callback<AssistanceResponse>() {
            @Override
            public void onResponse(Call<AssistanceResponse> call, Response<AssistanceResponse> response) {
                if (response.isSuccessful()){
                    String message = "Asistencia marcada correctamente";
                    Toast.makeText(IngresoClaveActivity.this, message, Toast.LENGTH_LONG).show();
                }else{
                    String message = "Clave utilizada o caducada";
                    Toast.makeText(IngresoClaveActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AssistanceResponse> call, Throwable t) {
                String message = "Ha ocurrido un error";
                Toast.makeText(IngresoClaveActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

}