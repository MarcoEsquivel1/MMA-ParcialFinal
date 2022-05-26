package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VistaCursoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_curso);
    }

    public void fabOnClick(View view) {
        Intent ventanaClave = new Intent(this, CrearAsistenciaActivity.class);
        startActivity(ventanaClave);
    }
}