package ucu.edu.aed;

public class Cliente {
    private String nombre;
    int id;
    Prioridad prioridad;

    //constructor
    public Cliente(String nombre, int id, Prioridad prioridad) {
        this.nombre = nombre;
        this.id = id;
        this.prioridad = prioridad;

        //getters y setters
    }

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

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}
