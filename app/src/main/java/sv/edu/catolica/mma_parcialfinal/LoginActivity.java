package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void iniciarSesionOnClick(View view) {
        Intent ventanaHome = new Intent(this, MainActivity.class);
        startActivity(ventanaHome);
    }

    public void RegistrarseOnClick(View view) {
        Intent ventanaClave = new Intent(this, RegistrarseActivity.class);
        startActivity(ventanaClave);
    }
}