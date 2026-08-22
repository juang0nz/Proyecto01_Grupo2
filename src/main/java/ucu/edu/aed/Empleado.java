package ucu.edu.aed;

public class Empleado {
    private String nombre;
    private int id;


    //constructor
    public Empleado(String nombre, int id ) {
        this.nombre = nombre;
        this.id = id;
        
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



}
