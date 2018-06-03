package rmiyap.com.munidenuncias.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rmiyap.com.munidenuncias.R;
import rmiyap.com.munidenuncias.adapters.DenunciasAdapter;
import rmiyap.com.munidenuncias.adapters.InformeAdapter;
import rmiyap.com.munidenuncias.models.Reportes;
import rmiyap.com.munidenuncias.models.Usuario;
import rmiyap.com.munidenuncias.services.ApiService;
import rmiyap.com.munidenuncias.services.ApiServiceGenerator;

public class ReporterActivity extends AppCompatActivity {



    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView reportesAdapter;
    private static final int REGISTER_FORM_REQUEST = 100;
    public static Usuario user = null;
    private FloatingActionButton floating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporter);


        reportesAdapter = findViewById(R.id.recyclerview);
        reportesAdapter.setLayoutManager(new LinearLayoutManager(this));

        reportesAdapter.setAdapter(new InformeAdapter());

        initialize1();

    }

    private void initialize1() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);


        Call<List<Reportes>> call = service.getReporte();

        call.enqueue(new Callback<List<Reportes>>() {
            @Override
            public void onResponse(Call<List<Reportes>> call, Response<List<Reportes>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Reportes> denuncias = response.body();
                        Log.d(TAG, "denuncias: " + denuncias);

                        InformeAdapter adapter = (InformeAdapter) reportesAdapter.getAdapter();
                        adapter.setInforme(denuncias);
                        adapter.notifyDataSetChanged();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(ReporterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Reportes>> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(ReporterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}
