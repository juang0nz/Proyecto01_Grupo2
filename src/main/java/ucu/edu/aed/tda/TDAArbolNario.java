package ucu.edu.aed.tda;
import java.util.function.Consumer;



public interface TDAArbolNario<T> {

    void insertar(T dato);

    T buscar(Comparable<T> criterio);

    boolean esVacio();

    void preOrden(Consumer<T> accion);

    void postOrden(Consumer<T> accion);

    void porNiveles(Consumer<T> accion);

    TDAElementoNario<T> obtenerRaiz();
}
