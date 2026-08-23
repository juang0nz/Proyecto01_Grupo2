package ucu.edu.aed;

public class Empleado {
    private String nombre;
    private int id;
    private Sector sector;


    //constructor
    public Empleado(String nombre, int id, Sector sector) {
        this.nombre = nombre;
        this.id = id;
        this.sector = sector;

    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object obj) {
        //si el objeto es el mismo, son iguales
        if (this == obj) {
            return true;
        }
        //si el objeto no es una instancia de Empleado, no son iguales
        if (!(obj instanceof Empleado)) {
            return false;
            //si el objeto es una instancia de Empleado, se comparan los ids
        }
        return this.id == ((Empleado) obj).id;
    }


    @Override
    public String toString() {
        return "Empleado{id=" + id + ", nombre='" + nombre + "', sector=" + sector.getNombre() + "}";
    }

}
