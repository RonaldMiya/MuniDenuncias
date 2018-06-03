package rmiyap.com.munidenuncias.models;

public class Denuncias {

    private String imagen;
    private String fecha;
    private String ubicacion;
    private String contenido;
    private String titulo;
    private int usuarios_id;
    private int id;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(int usuarios_id) {
        this.usuarios_id = usuarios_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String image) {
        this.imagen = image;
    }

    @Override
    public String toString() {
        return "Denuncias{" +
                "imagen='" + imagen + '\'' +
                ", fecha='" + fecha + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", contenido='" + contenido + '\'' +
                ", titulo='" + titulo + '\'' +
                ", usuarios_id=" + usuarios_id +
                ", id=" + id +
                '}';
    }
}
