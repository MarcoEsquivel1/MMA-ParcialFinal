package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.LoginResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Auth.User;

public class MainActivity extends AppCompatActivity {
    public byte tipoCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipoCuenta=0;
        Bundle extras = getIntent().getExtras();
        String token = extras.getString("token");
        User datos = (User) extras.getSerializable("datos");
        Handler manejador = new Handler();

        manejador.postDelayed(() -> {
            finish();
            Intent ventanaClave;
            if (datos.getRole_id() == 2){
                ventanaClave = new Intent(this, DocenteActivity.class);
                ventanaClave.putExtra("token",token);
                ventanaClave.putExtra("datos", datos);
                startActivity(ventanaClave);
            }else if (datos.getRole_id() == 3){
                ventanaClave = new Intent(this, IngresoClaveActivity.class);
                ventanaClave.putExtra("token",token);
                ventanaClave.putExtra("datos", datos);
                startActivity(ventanaClave);
            }
        }, 3000);
    }
}