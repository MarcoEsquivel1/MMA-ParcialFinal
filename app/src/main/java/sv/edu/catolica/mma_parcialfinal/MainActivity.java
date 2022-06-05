package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    public byte tipoCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipoCuenta=0;
        Bundle extras = getIntent().getExtras();
        String token = extras.getString("token");
        int role_id= extras.getInt("rol");
        Handler manejador = new Handler();

        manejador.postDelayed(() -> {
            finish();
            Intent ventanaClave;
            if (role_id == 2){
                ventanaClave = new Intent(this, DocenteActivity.class);
                ventanaClave.putExtra("token",token);
                ventanaClave.putExtra("rol", role_id);
                startActivity(ventanaClave);
            }else if (role_id == 3){
                ventanaClave = new Intent(this, IngresoClaveActivity.class);
                ventanaClave.putExtra("token",token);
                ventanaClave.putExtra("rol", role_id);
                startActivity(ventanaClave);
            }
        }, 3000);
    }
}