package rmiyap.com.munidenuncias.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rmiyap.com.munidenuncias.R;
import rmiyap.com.munidenuncias.models.Login;
import rmiyap.com.munidenuncias.models.Usuario;
import rmiyap.com.munidenuncias.services.ApiService;
import rmiyap.com.munidenuncias.services.ApiServiceGenerator;

public class LoginActivity extends AppCompatActivity {

    public static final int SIGN_IN_CODE = 777;
    private EditText user,pass;
    private final static String TAG = LoginActivity.class.getSimpleName();
    public static Usuario usuairo = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username_input);
        pass = findViewById(R.id.password_input);

    }

    public void callLogin(View view) {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        String usuario = user.getText().toString();
        String clave = pass.getText().toString();

        final Login login = new Login(usuario, clave);
        Call<Usuario> call;
        call = service.login(login.getUsername(), login.getPassword());

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                try {
                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);
                    Toast.makeText(LoginActivity.this,"HTTP status code: " + statusCode, Toast.LENGTH_LONG).show();

                    if( response.isSuccessful()) {
                        usuairo = response.body();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }
                }catch(Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error de conexion", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void newUser(View view) {

        startActivity(new Intent(getApplicationContext(), NewUserActivity.class));

    }
}
