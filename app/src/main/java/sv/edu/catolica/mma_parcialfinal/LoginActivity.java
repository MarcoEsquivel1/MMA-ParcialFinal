package sv.edu.catolica.mma_parcialfinal;

import androidx.annotation.NonNull;
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
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginRequest;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginResponse;

public class LoginActivity extends AppCompatActivity {
    public TextInputEditText TICorreo, TIPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TICorreo = findViewById(R.id.TICorreo);
        TIPass = findViewById(R.id.TIContraseña);
    }


    public void iniciarSesionOnClick(View view) {
        if (TextUtils.isEmpty(TICorreo.getText().toString()) || TextUtils.isEmpty(TIPass.getText().toString())){
            String message = "Todos los campos son necesarios";
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
        }else{
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(TICorreo.getText().toString());
            loginRequest.setPassword(TIPass.getText().toString());
            iniciarSesion(loginRequest);
        }
    }

    public void RegistrarseOnClick(View view) {
        Intent ventanaClave = new Intent(this, RegistrarseActivity.class);
        startActivity(ventanaClave);
        finish();
    }

    public void iniciarSesion(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    String token = response.body().getX_access_token();
                    int role_id = response.body().getRole_id();
                    String message = "Inicio Sesion correctamente";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                    Intent ventana = new Intent(LoginActivity.this, MainActivity.class);
                    ventana.putExtra("token",token);
                    ventana.putExtra("rol", role_id);
                    startActivity(ventana);
                    finish();
                }else{
                    String message = "Ha ocurrido un error";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}