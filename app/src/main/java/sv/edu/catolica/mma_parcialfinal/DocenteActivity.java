package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DocenteActivity extends AppCompatActivity {
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);
        fab = findViewById(R.id.floating_action_button);

    }

    public void fabOnClick(View view) {
        Intent ventanaClave = new Intent(this, CrearCursoActivity.class);
        startActivity(ventanaClave);
    }
}