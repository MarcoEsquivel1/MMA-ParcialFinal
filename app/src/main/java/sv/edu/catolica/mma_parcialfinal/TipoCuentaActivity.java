package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class TipoCuentaActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_cuenta);

        textInputLayout=findViewById(R.id.text_input_layout);
        autoCompleteTextView=findViewById(R.id.TITipoCuenta);

        Resources res = getResources();
        String[] tipoCuentas = res.getStringArray(R.array.tipoCuenta);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                TipoCuentaActivity.this,
                R.layout.dropdow_item,
                tipoCuentas
        );

        autoCompleteTextView.setAdapter(adapter);
    }

    public void continuarOnClick(View view) {
        Intent ventanaClave = new Intent(this, MainActivity.class);
        startActivity(ventanaClave);
    }

    public void cancelarOnClick(View view) {
        Intent ventanaClave = new Intent(this, RegistrarseActivity.class);
        startActivity(ventanaClave);
    }
}