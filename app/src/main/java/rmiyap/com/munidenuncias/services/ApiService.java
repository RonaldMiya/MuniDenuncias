package rmiyap.com.munidenuncias.services;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rmiyap.com.munidenuncias.models.Denuncias;
import rmiyap.com.munidenuncias.models.Reportes;
import rmiyap.com.munidenuncias.models.ResponseMessage;
import rmiyap.com.munidenuncias.models.Usuario;

public interface ApiService {

    String API_BASE_URL = "https://denunncias-rmiya.c9users.io";

    @GET("/api/v1/denuncias")
    Call<List<Denuncias>> getDenuncias();

    @GET("/api/v1/reportes")
    Call<List<Reportes>> getReporte();

    @GET("/api/v1/denuncias/{id_user}/ver")
    Call<List<Denuncias>> getDenunciasFilter(@Path("id_user") String id_user);

    @FormUrlEncoded
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> createDenuncia(@Field("titulo") String titulo,
                                         @Field("contenido") String contendio,
                                         @Field("usuarios_id") String usuarios_id,
                                         @Field("ubicacion") String ubicacion);

    @FormUrlEncoded
    @POST("/api/v1/reportes")
    Call<ResponseMessage> createReporte(@Field("titulo") String titulo,
                                         @Field("contenido") String contendio,
                                         @Field("usuarios_id") String usuarios_id);

    @FormUrlEncoded
    @POST("/api/v1/usuarios")
    Call<ResponseMessage> createUsuario(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("nombres") String nombre,
                                        @Field("correo") String correo
                                        );

    @Multipart
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> createDenunciaWithImage(
            @Part("titulo") RequestBody titulo,
            @Part("contendido") RequestBody contenido,
            @Part("usuarios_id") RequestBody usuarios_id,
            @Part("ubicacion") RequestBody ubicacion,
            @Part MultipartBody.Part imagen
    );

    @FormUrlEncoded
    @POST("api/v1/login")
    Call<Usuario> login(@Field("username")String usuario,
                        @Field("password") String clave);
}