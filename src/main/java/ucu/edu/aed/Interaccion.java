package ucu.edu.aed;
import java.util.Date;

public class Interaccion {
Date fecha;
TipoInteraccion tipo;
String descripcion;


//constructor
public Interaccion(Date fecha, TipoInteraccion tipo, String descripcion) {
    this.fecha = fecha;
    this.tipo = tipo;
    this.descripcion = descripcion;
}

//getters y setters
public Date getFecha() {
    return fecha;
}

public void setFecha(Date fecha) {
    this.fecha = fecha;
}

public TipoInteraccion getTipo() {
    return tipo;
}

public void setTipo(TipoInteraccion tipo) {
    this.tipo = tipo;
}

public String getDescripcion() {
    return descripcion;
}

public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

}
