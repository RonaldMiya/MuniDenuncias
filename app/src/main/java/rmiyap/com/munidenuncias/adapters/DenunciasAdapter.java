package rmiyap.com.munidenuncias.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rmiyap.com.munidenuncias.R;
import rmiyap.com.munidenuncias.activities.DetailActivity;
import rmiyap.com.munidenuncias.models.Denuncias;
import rmiyap.com.munidenuncias.services.ApiService;

public class DenunciasAdapter extends RecyclerView.Adapter<DenunciasAdapter.ViewHolder>{

    private List<Denuncias> denuncia;

    private Activity activity;

    public DenunciasAdapter(){
        this.denuncia = new ArrayList<>();
    }

    public void setDenuncias(List<Denuncias> denuncia){
        this.denuncia = denuncia;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView nombreText;
        public TextView ubicacionText;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_image);
            nombreText = itemView.findViewById(R.id.nombre_text);
            ubicacionText = itemView.findViewById(R.id.ubicacion_text);
        }
    }

    @Override
    public DenunciasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denuncia, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Denuncias denu = this.denuncia.get(position);

        holder.nombreText.setText(denu.getTitulo());
        holder.ubicacionText.setText(denu.getUbicacion());

        String url = ApiService.API_BASE_URL + "/images/" + denu.getImage();
        Picasso.with(holder.itemView.getContext()).load(url).into(holder.fotoImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("ID", denu.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.denuncia.size();
    }


}
