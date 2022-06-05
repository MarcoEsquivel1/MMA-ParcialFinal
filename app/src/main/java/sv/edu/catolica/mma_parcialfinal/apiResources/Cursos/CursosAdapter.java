package sv.edu.catolica.mma_parcialfinal.apiResources.Cursos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sv.edu.catolica.mma_parcialfinal.R;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.CursosAdapterVH> {

    private List<CursosResponse> cursosResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public CursosAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<CursosResponse> cursosResponseList) {
        this.cursosResponseList = cursosResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CursosAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CursosAdapter.CursosAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_cursos,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CursosAdapterVH holder, int position) {
        CursosResponse cursosResponse = cursosResponseList.get(position);

        String nombreC = cursosResponse.getName();
        String descC = cursosResponse.getDescription();
        holder.nombreCurso.setText(nombreC);
        holder.descCurso.setText(descC);
        holder.btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedCurso(cursosResponse);
            }
        });
    }

    public interface ClickedItem{
        public void ClickedCurso(CursosResponse cursosResponse);
    }

    @Override
    public int getItemCount() {
        return cursosResponseList.size();
    }

    public class CursosAdapterVH extends RecyclerView.ViewHolder {
        TextView nombreCurso;
        TextView descCurso;
        Button btnMas;
        public CursosAdapterVH(@NonNull View itemView) {
            super(itemView);
            nombreCurso = itemView.findViewById(R.id.nombreCurso);
            descCurso = itemView.findViewById(R.id.descCurso);
            btnMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}
