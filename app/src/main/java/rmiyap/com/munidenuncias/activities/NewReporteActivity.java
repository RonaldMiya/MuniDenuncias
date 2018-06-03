package rmiyap.com.munidenuncias.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rmiyap.com.munidenuncias.R;
import rmiyap.com.munidenuncias.models.ResponseMessage;
import rmiyap.com.munidenuncias.models.Usuario;
import rmiyap.com.munidenuncias.services.ApiService;
import rmiyap.com.munidenuncias.services.ApiServiceGenerator;

public class NewReporteActivity extends AppCompatActivity {

    private static final String TAG = NewReporteActivity.class.getSimpleName();
    private EditText usuario, titulo, descripcion;
    private Usuario user = LoginActivity.usuairo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reporte);

        titulo = findViewById(R.id.titulo_input);
        descripcion = findViewById(R.id.contenido_input);

    }

    public void callRegisterReporte(View view) {

        String titulo= this.titulo.getText().toString();
        String contenido = descripcion.getText().toString();
        String usuarios_id = String.valueOf(user.getId());

        if (titulo.isEmpty() || contenido.isEmpty()) {
            Toast.makeText(this, "Todos los campos son requeridos, excepto la imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<ResponseMessage> call = null;

        call = service.createReporte(titulo, contenido, usuarios_id);

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        ResponseMessage responseMessage = response.body();
                        Log.d(TAG, "responseMessage: " + responseMessage);

                        Toast.makeText(NewReporteActivity.this, responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(NewReporteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(NewReporteActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }
}
