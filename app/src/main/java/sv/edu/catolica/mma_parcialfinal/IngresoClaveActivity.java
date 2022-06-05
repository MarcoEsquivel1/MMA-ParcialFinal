package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.User;

public class IngresoClaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_clave);
        Bundle extras = getIntent().getExtras();
        String token = extras.getString("token");
        User datos = (User) extras.getSerializable("datos");
        String message = "Inicio Sesion correctamente";
        Toast.makeText(IngresoClaveActivity.this, token, Toast.LENGTH_LONG).show();
    }

    public void AsistenciaOnClick(View view) {

    }

    // Get input text





}