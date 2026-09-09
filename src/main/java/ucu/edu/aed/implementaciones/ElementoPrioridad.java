package ucu.edu.aed.implementaciones;

public class ElementoPrioridad<T> {

    private T dato;
    private int prioridad;
    private long ordenLlegada;

    public ElementoPrioridad(T dato, int prioridad, long ordenLlegada) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.ordenLlegada = ordenLlegada;
    }

    public T getDato() {
        return dato;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public long getOrdenLlegada() {
        return ordenLlegada;
    }
}