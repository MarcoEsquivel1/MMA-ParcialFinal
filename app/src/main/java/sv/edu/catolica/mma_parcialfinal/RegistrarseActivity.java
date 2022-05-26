package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrarseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }

    public void continuarOnClick(View view) {
        Intent ventana = new Intent(this, TipoCuentaActivity.class);
        startActivity(ventana);
    }

    public void cancelarOnClick(View view) {
        Intent ventana = new Intent(this, LoginActivity.class);
        startActivity(ventana);
    }
}