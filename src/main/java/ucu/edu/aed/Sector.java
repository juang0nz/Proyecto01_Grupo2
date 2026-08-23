package ucu.edu.aed;

public class Sector {
    String nombre;
//constructor
    public Sector(String nombre) {
        this.nombre = nombre;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sector)) {
            return false;
        }
        return this.nombre.equals(((Sector) obj).nombre);
    }

    @Override
    public String toString() {
        return "Sector{" + nombre + "}";
    }
}
