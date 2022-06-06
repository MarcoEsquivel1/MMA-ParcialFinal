package sv.edu.catolica.mma_parcialfinal.apiResources.Users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sv.edu.catolica.mma_parcialfinal.R;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.AsistenciaAdapterVH> {
    private List<AsistenciaResponse> asistenciaResponseList;
    private Context contex;

    public AsistenciaAdapter() {
    }

    public void setData(List<AsistenciaResponse> asistenciaResponseList) {
        this.asistenciaResponseList = asistenciaResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AsistenciaAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contex = parent.getContext();
        return new AsistenciaAdapter.AsistenciaAdapterVH(LayoutInflater.from(contex).inflate(R.layout.row_asistencias,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AsistenciaAdapterVH holder, int position) {
        AsistenciaResponse asistenciaResponse = asistenciaResponseList.get(position);
        String name = asistenciaResponse.getStudent().getFirst_name() + " " + asistenciaResponse.getStudent().getLast_name();
        String email = asistenciaResponse.getStudent().getEmail();
        String date = asistenciaResponse.getRegistered_date();

        holder.nombre.setText(name);
        holder.correo.setText(email);
        holder.fecha.setText(date);
    }

    @Override
    public int getItemCount() {
        return asistenciaResponseList.size();
    }


    public class AsistenciaAdapterVH extends RecyclerView.ViewHolder {
        TextView nombre, correo, fecha;
        public AsistenciaAdapterVH(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            correo = itemView.findViewById(R.id.email);
            fecha = itemView.findViewById(R.id.fechaAsistencia);
        }
    }
}
