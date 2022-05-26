package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public byte tipoCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipoCuenta=0;

        Handler manejador = new Handler();

        manejador.postDelayed(() -> {
            finish();
            Intent ventanaClave;
            if (tipoCuenta == 0){
                ventanaClave = new Intent(this, DocenteActivity.class);
            }else {
                ventanaClave = new Intent(this, IngresoClaveActivity.class);
            }
            startActivity(ventanaClave);
        }, 3000);


    }
}