package ucu.edu.aed.tda;
import java.util.function.Consumer;
public interface TDAElementoNario<T> {

    T getDato();

    void setDato(T dato);

    void agregarHijo(TDAElementoNario<T> hijo);

    boolean eliminarHijo(Comparable<T> criterio);

    TDAElementoNario<T> buscar(Comparable<T> criterio);

    void preOrden(Consumer<T> accion);

    void postOrden(Consumer<T> accion);

    TDALista<TDAElementoNario<T>> getHijos();
}
