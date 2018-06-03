package rmiyap.com.munidenuncias.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rmiyap.com.munidenuncias.R;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Denuncias denu = this.denuncia.get(position);

        holder.nombreText.setText(denu.getTitulo());
        holder.ubicacionText.setText(denu.getUbicacion());

        String url = ApiService.API_BASE_URL + "/images/" + denu.getImagen();

        Log.i("IMAGE URL", "La ruta es: " + url);
        Picasso.with(holder.itemView.getContext()).load(url).into(holder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.denuncia.size();
    }


}
