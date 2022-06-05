package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.edu.catolica.mma_parcialfinal.apiResources.ApiClient;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.RegisterRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.RegisterResponse;

public class TipoCuentaActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    public Bundle extras;
    public RegisterRequest registerRequest;
    TextInputEditText carnetET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_cuenta);

        extras = getIntent().getExtras();
        registerRequest = (RegisterRequest) extras.getSerializable("datos");

        textInputLayout=findViewById(R.id.text_input_layout);
        autoCompleteTextView=findViewById(R.id.TITipoCuenta);
        carnetET=findViewById(R.id.TICarnet);

        Resources res = getResources();
        String[] tipoCuentas = res.getStringArray(R.array.tipoCuenta);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                TipoCuentaActivity.this,
                R.layout.dropdow_item,
                tipoCuentas
        );
        autoCompleteTextView.setAdapter(adapter);
    }

    public void continuarOnClick(View view) {
        if (TextUtils.isEmpty(autoCompleteTextView.getText().toString())){
            String message = "Elija un tipo de cuenta";
            Toast.makeText(TipoCuentaActivity.this, message, Toast.LENGTH_LONG).show();
        }else if (TextUtils.equals(autoCompleteTextView.getText().toString(), "Estudiante") || TextUtils.equals(autoCompleteTextView.getText().toString(), "Profesor")){
            int idRol;
            if (TextUtils.equals(autoCompleteTextView.getText().toString(), "Estudiante")){
                idRol=3;
                registerRequest.setRole_id(idRol);
            }else if (TextUtils.equals(autoCompleteTextView.getText().toString(), "Profesor")){
                idRol=2;
                registerRequest.setRole_id(idRol);
            }

            if (TextUtils.isEmpty(carnetET.getText().toString())){

            }else{
                    String carnet = carnetET.getText().toString();
                    registerRequest.setUid(carnet);
            }
            registrarse(registerRequest);
        }


    }

    public void cancelarOnClick(View view) {
        Intent ventanaClave = new Intent(this, RegistrarseActivity.class);
        startActivity(ventanaClave);
    }

    public void registrarse(RegisterRequest registerRequest){
        String x = "application/json";
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(x,registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String token = response.body().getX_access_token();
                    int role_id = response.body().getRole_id();
                    String message = "Inicio Sesion correctamente";
                    Toast.makeText(TipoCuentaActivity.this, message, Toast.LENGTH_LONG).show();

                    Intent ventana = new Intent(TipoCuentaActivity.this, MainActivity.class);
                    ventana.putExtra("token",token);
                    ventana.putExtra("rol", role_id);
                    startActivity(ventana);
                    finish();
                }else{
                    String message = "Ha ocurrido un error";
                    Toast.makeText(TipoCuentaActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Log.e("Error", message);
            }
        });
    }
}