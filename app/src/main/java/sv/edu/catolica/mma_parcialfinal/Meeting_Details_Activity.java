package sv.edu.catolica.mma_parcialfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import sv.edu.catolica.mma_parcialfinal.apiResources.Cursos.CursosResponse;
import sv.edu.catolica.mma_parcialfinal.apiResources.Meetings.MeetingResponse;

public class Meeting_Details_Activity extends AppCompatActivity {
    TextView fecha, codigo;
    public Bundle extras;
    public String token;
    public int rol_id;
    public CursosResponse datosCurso;
    public MeetingResponse datosMeeting;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        fecha = findViewById(R.id.TvFecha);
        codigo = findViewById(R.id.TvClave);
        recyclerView = findViewById(R.id.recycledView);

        extras = getIntent().getExtras();
        token = extras.getString("token");
        rol_id = extras.getInt("rol");
        datosCurso = (CursosResponse) extras.getSerializable("datosCurso");
        datosMeeting= (MeetingResponse) extras.getSerializable("datosMeeting");

        fecha.setText(datosMeeting.getStart_date());
        codigo.setText(datosMeeting.getSecret_code());

    }
}