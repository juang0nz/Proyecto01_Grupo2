package ucu.edu.aed.implementaciones;
import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElementoNario;
import ucu.edu.aed.tda.TDALista;

public class ElementoNario<T> implements TDAElementoNario<T> {

    private T dato;
    private TDALista<TDAElementoNario<T>> hijos;

    public ElementoNario(T dato) {
        this.dato = dato;
        this.hijos = new TDAListaConArregloImpl<>();
    }
    //getter for hijos
    @Override
    public TDALista<TDAElementoNario<T>> getHijos() {
        return hijos;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public void agregarHijo(TDAElementoNario<T> hijo) {
        hijos.agregar(hijo);
    }

    @Override
    public boolean eliminarHijo(Comparable<T> criterio) {
        for (int i = 0; i < hijos.tamanio(); i++) {
            TDAElementoNario<T> hijo = hijos.obtener(i);
            if (criterio.compareTo(hijo.getDato()) == 0) {
                hijos.remover(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public TDAElementoNario<T> buscar(Comparable<T> criterio) {
        if (criterio.compareTo(dato) == 0) {
            return this;
        }
        for (int i = 0; i < hijos.tamanio(); i++) {
            TDAElementoNario<T> resultado = hijos.obtener(i).buscar(criterio);
            if (resultado != null) {
                return resultado;
            }
        }
        return null;
    }

    @Override
    public void preOrden(Consumer<T> accion) {
        accion.accept(dato);
        for (int i = 0; i < hijos.tamanio(); i++) {
            hijos.obtener(i).preOrden(accion);
        }
    }

    @Override
    public void postOrden(Consumer<T> accion) {
        for (int i = 0; i < hijos.tamanio(); i++) {
            hijos.obtener(i).postOrden(accion);
        }
        accion.accept(dato);
    }
}
