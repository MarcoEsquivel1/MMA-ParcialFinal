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

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH> {
    private List<AsistenciaResponse> asistenciaResponseList;
    private Context contex;

    public UsersAdapter() {
    }

    public void setData(List<AsistenciaResponse> asistenciaResponseList) {
        this.asistenciaResponseList = asistenciaResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contex = parent.getContext();
        return new UsersAdapter.UserAdapterVH(LayoutInflater.from(contex).inflate(R.layout.row_asistencias, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
        AsistenciaResponse asistenciaResponse = asistenciaResponseList.get(position);


    }

    @Override
    public int getItemCount() {
        return asistenciaResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {
        TextView nombre, fecha;
        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            fecha = itemView.findViewById(R.id.fechaAsistencia);
        }
    }
}
