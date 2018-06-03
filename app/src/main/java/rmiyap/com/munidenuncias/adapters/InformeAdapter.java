package rmiyap.com.munidenuncias.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rmiyap.com.munidenuncias.R;
import rmiyap.com.munidenuncias.models.Reportes;

public class InformeAdapter extends RecyclerView.Adapter<InformeAdapter.ViewHolder> {

    private List<Reportes> reportes;

    private Activity activity;

    public InformeAdapter(){
            this.reportes = new ArrayList<>();
            }

    public void setInforme(List<Reportes> reportes){
            this.reportes = reportes;
            }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreText;
        public TextView ubicacionText;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.titulo_text);
            ubicacionText = itemView.findViewById(R.id.description_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte, parent, false);
        return new InformeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Reportes report = this.reportes.get(position);

//        Toast.makeText(activity, "SIze" + reportes.size(), Toast.LENGTH_SHORT).show();

        holder.nombreText.setText(report.getTitulo());
        holder.ubicacionText.setText(report.getContenido());

    }

    @Override
    public int getItemCount() {
        return reportes.size();
    }

}


