package rmiyap.com.munidenuncias.activities;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rmiyap.com.munidenuncias.R;
import rmiyap.com.munidenuncias.adapters.DenunciasAdapter;
import rmiyap.com.munidenuncias.models.Denuncias;
import rmiyap.com.munidenuncias.models.Login;
import rmiyap.com.munidenuncias.models.Usuario;
import rmiyap.com.munidenuncias.services.ApiService;
import rmiyap.com.munidenuncias.services.ApiServiceGenerator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView denunciasList;
    private static final int REGISTER_FORM_REQUEST = 100;
    public static Usuario user = null;
    private FloatingActionButton floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        denunciasList = findViewById(R.id.recyclerview);
        denunciasList.setLayoutManager(new LinearLayoutManager(this));

        denunciasList.setAdapter(new DenunciasAdapter());

        user = LoginActivity.usuairo;
        Toast.makeText(getApplicationContext(), "Tu usuario es: " + user.getId()+", y tu name es: "+ user.getNombres(), Toast.LENGTH_SHORT).show();

        if (user.getTipo().equalsIgnoreCase("ciudadano")) {

            initialize();

        } else if (user.getTipo().equalsIgnoreCase("sereno")){
            initialize1();
            Button button = findViewById(R.id.id_go);
            button.setVisibility(View.VISIBLE);
        }


        floating = findViewById(R.id.create);

        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.getTipo().equalsIgnoreCase("ciudadano")) {
                    registerDenuncia();
                } else if (user.getTipo().equalsIgnoreCase("sereno")){
                    registerReporte();
                }

            }
        });

//        Usuario user = LoginActivity.usuairo;


    }

    private void initialize1() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Log.d(TAG, String.valueOf(user.getId()));
        Call<List<Denuncias>> call = service.getDenuncias();

        call.enqueue(new Callback<List<Denuncias>>() {
            @Override
            public void onResponse(Call<List<Denuncias>> call, Response<List<Denuncias>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Denuncias> denuncias = response.body();
                        Log.d(TAG, "denuncias: " + denuncias);


                        DenunciasAdapter adapter = (DenunciasAdapter) denunciasList.getAdapter();
                        adapter.setDenuncias(denuncias);
                        adapter.notifyDataSetChanged();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Denuncias>> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Log.d(TAG, String.valueOf(user.getId()));
        Call<List<Denuncias>> call = service.getDenunciasFilter(String.valueOf(user.getId()));

        call.enqueue(new Callback<List<Denuncias>>() {
            @Override
            public void onResponse(Call<List<Denuncias>> call, Response<List<Denuncias>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Denuncias> denuncias = response.body();
                        Log.d(TAG, "denuncias: " + denuncias);


                        DenunciasAdapter adapter = (DenunciasAdapter) denunciasList.getAdapter();
                        adapter.setDenuncias(denuncias);
                        adapter.notifyDataSetChanged();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Denuncias>> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public void registerDenuncia() {

        startActivityForResult(new Intent(this, NewDenunciaActivity.class), REGISTER_FORM_REQUEST);

    }

    public void registerReporte() {

        startActivityForResult(new Intent(this, NewReporteActivity.class), REGISTER_FORM_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            initialize();
            initialize1();
        }
    }


    public void GoRepo(View view) {

        startActivity(new Intent(MainActivity.this, ReporterActivity.class));

    }
}
