package rmiyap.com.munidenuncias.models;

public class Reportes {

    private int id;
    private int usuario_id;
    private String titulo;
    private String contenido;
    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reportes{" +
                "id=" + id +
                ", usuario_id=" + usuario_id +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
