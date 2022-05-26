package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CrearCursoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_curso);
    }

    public void CrearOnClick(View view) {
        Intent ventanaHome = new Intent(this, DocenteActivity.class);
        startActivity(ventanaHome);
    }

    public void CancelarOnClick(View view) {
        Intent ventanaHome = new Intent(this, DocenteActivity.class);
        startActivity(ventanaHome);
    }
}