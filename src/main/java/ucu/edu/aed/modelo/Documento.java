package ucu.edu.aed.modelo;
import java.util.Date;

public class Documento {
    int id;
    String tipo;
    Date fechaPresentacion;
    Date fechaVencimiento;

    //constructor
    public Documento(int id, String tipo, Date fechaPresentacion, Date fechaVencimiento) {
        this.id = id;
        this.tipo = tipo;
        this.fechaPresentacion = fechaPresentacion;
        this.fechaVencimiento = fechaVencimiento;
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

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    // vencido si la fecha de vencimiento ya pasó respecto a la fecha dada
    public boolean estaVencido(Date fechaReferencia) {
        return fechaVencimiento != null && fechaVencimiento.before(fechaReferencia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Documento)) {
            return false;
        }
        return this.id == ((Documento) obj).id;
    }


    @Override
    public String toString() {
        return "Documento{id=" + id + ", tipo='" + tipo + "'}";
    }
}
