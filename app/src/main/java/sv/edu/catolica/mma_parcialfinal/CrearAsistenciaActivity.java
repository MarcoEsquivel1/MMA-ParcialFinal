package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;
import java.util.UUID;

public class CrearAsistenciaActivity extends AppCompatActivity {
    TextInputEditText etHora, etClave;
    int hour, minute;
    TextView nombreC, descC;
    public Bundle extras;
    public String token;
    public int rol_id;
    public CursosResponse datosCurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_asistencia);
        etHora=findViewById(R.id.TIHoraFinal);
        etClave=findViewById(R.id.TIClaveGenerada);
        nombreC=findViewById(R.id.TvNombreCurso);
        descC=findViewById(R.id.TvDescCurso);

        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");
        datosCurso = (CursosResponse) extras.getSerializable("datosCurso");

        nombreC.setText(datosCurso.getName());
        descC.setText(datosCurso.getDescription());
        String header = "Bearer " + token;

        UUID uuid = UUID.randomUUID();
        String claveGenerada = uuid.toString();
        String claveGenerada2 = claveGenerada.substring(0,14);
        etClave.setText(claveGenerada2);
        Calendar  calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY) + 1;
        minute = calendar.get(Calendar.MINUTE);
        etHora.setText(String.format(Locale.getDefault(), "%02d:%02d",hour,minute));

    }


    public void cambiarHoraOnClick(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                etHora.setText(String.format(Locale.getDefault(), "%02d:%02d",hour,minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Elija la hora de finalización");
        timePickerDialog.show();
    }



    public void CrearOnClick(View view) {
        if (TextUtils.isEmpty(etClave.getText().toString()) || TextUtils.isEmpty(etClave.getText().toString())){
            Toast.makeText(this, "Todos los campos son necesarios", Toast.LENGTH_LONG);
        }else{

        }
    }

    public void CancelarOnClick(View view) {
        Intent ventana = new Intent(this, ListaMeetingsActivity.class);
        ventana.putExtra("token",token);
        ventana.putExtra("rol", rol_id);
        ventana.putExtra("datosCurso", datosCurso);
        startActivity(ventana);
        finish();
    }

    public void crearAsistencia(){
        
    }

}