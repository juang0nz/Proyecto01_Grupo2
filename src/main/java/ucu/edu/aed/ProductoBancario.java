package ucu.edu.aed;
import java.util.Date;

public class ProductoBancario {
    int id;
    String tipo;
    Date fechaAlta;
    String estado;

    //constructor
    public ProductoBancario(int id, String tipo, Date fechaAlta, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
        this.estado = estado;

    }
    //getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
