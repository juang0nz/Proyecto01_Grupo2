package ucu.edu.aed.tda;

public interface TDAColaPrioridad<T> {

    boolean insertar(T dato);

    T frente();

    T quitar();

    boolean esVacia();

    int tamanio();
}