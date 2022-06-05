package sv.edu.catolica.mma_parcialfinal.apiResources.Meetings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import sv.edu.catolica.mma_parcialfinal.R;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MeetingsAdapterVH> {

    private List<MeetingResponse> meetingResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public MeetingsAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<MeetingResponse> meetingResponseList) {
        this.meetingResponseList = meetingResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeetingsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MeetingsAdapter.MeetingsAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_meetings,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingsAdapterVH holder, int position) {
        MeetingResponse meetingResponse = meetingResponseList.get(position);
        String fecha = meetingResponse.getCreated_at();
        String hora = meetingResponse.getFinish_time();

        holder.fechaCreacion.setText((CharSequence) fecha);
        holder.horaFinal.setText(hora);
        holder.btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedMeeting(meetingResponse);
            }
        });
    }

    public interface ClickedItem{
        public void ClickedMeeting(MeetingResponse meetingResponse);
    }

    @Override
    public int getItemCount() {
        return meetingResponseList.size();
    }

    public class MeetingsAdapterVH extends RecyclerView.ViewHolder {
        TextView fechaCreacion, horaFinal;
        Button btnMas;
        public MeetingsAdapterVH(@NonNull View itemView) {
            super(itemView);
            fechaCreacion = itemView.findViewById(R.id.fechaCreacion);
            horaFinal = itemView.findViewById(R.id.horaFinal);
            btnMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}
