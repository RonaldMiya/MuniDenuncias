package rmiyap.com.munidenuncias.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    EditText usuario, titulo, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reporte);

        usuario = findViewById(R.id.user_input);
        titulo = findViewById(R.id.titulo_input);
        descripcion = findViewById(R.id.contenido_input);

        Usuario user = LoginActivity.usuairo;
        String user_id = String.valueOf(user.getId());
        usuario.setText(user_id);

    }

    public void callRegisterReporte(View view) {

        String titulo= this.titulo.getText().toString();
        String contenido = descripcion.getText().toString();
        String usuarios_id = usuario.getText().toString();

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
