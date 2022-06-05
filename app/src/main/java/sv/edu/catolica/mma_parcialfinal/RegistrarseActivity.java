package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.RegisterRequest;

public class RegistrarseActivity extends AppCompatActivity {
    TextInputEditText nombre, apellido, correo, pass;
    RegisterRequest registerRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        nombre = findViewById(R.id.TINombre);
        apellido = findViewById(R.id.TIApellido);
        correo = findViewById(R.id.TICorreo);
        pass = findViewById(R.id.TIPass);
        registerRequest = new RegisterRequest();
    }

    public void continuarOnClick(View view) {
        if (TextUtils.isEmpty(nombre.getText().toString()) ||TextUtils.isEmpty(apellido.getText().toString()) || TextUtils.isEmpty(correo.getText().toString()) || TextUtils.isEmpty(pass.getText().toString()) ){
            String message = "Todos los campos son necesarios";
            Toast.makeText(RegistrarseActivity.this, message, Toast.LENGTH_LONG).show();
        }else{
            String name = nombre.getText().toString();
            String lastName = apellido.getText().toString();
            String email = correo.getText().toString();
            String passw = pass.getText().toString();

            registerRequest.setFirst_name(name);
            registerRequest.setLast_name(lastName);
            registerRequest.setEmail(email);
            registerRequest.setPassword(passw);
            registerRequest.setPassword_confirmation(passw);
            Intent ventana = new Intent(this, TipoCuentaActivity.class);
            ventana.putExtra("datos", registerRequest);
            startActivity(ventana);
            finish();
        }
    }

    public void cancelarOnClick(View view) {
        Intent ventana = new Intent(this, LoginActivity.class);
        startActivity(ventana);
        finish();
    }
}