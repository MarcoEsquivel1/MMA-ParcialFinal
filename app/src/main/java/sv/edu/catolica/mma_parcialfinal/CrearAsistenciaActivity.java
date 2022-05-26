package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class CrearAsistenciaActivity extends AppCompatActivity {
    TextInputEditText etHora;
    int hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_asistencia);
        etHora=findViewById(R.id.TIHoraFinal);
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
        Intent ventanaClave = new Intent(this, VistaCursoActivity.class);
        startActivity(ventanaClave);
    }

    public void CancelarOnClick(View view) {
        Intent ventanaClave = new Intent(this, VistaCursoActivity.class);
        startActivity(ventanaClave);
    }
}