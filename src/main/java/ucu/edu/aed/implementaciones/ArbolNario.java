package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolNario;
import ucu.edu.aed.tda.TDACola;
import ucu.edu.aed.tda.TDAElementoNario;
import ucu.edu.aed.tda.TDALista;


public class ArbolNario<T> implements TDAArbolNario<T> {

    private TDAElementoNario<T> raiz;

    public ArbolNario() {
        raiz = null;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public TDAElementoNario<T> obtenerRaiz() {
        return raiz;
    }

    @Override
    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new ElementoNario<>(dato);
        } else {
            raiz.agregarHijo(new ElementoNario<>(dato));
        }
    }

    @Override
    public T buscar(Comparable<T> criterio) {
        if (raiz == null) {
            return null;
        }
        TDAElementoNario<T> resultado = raiz.buscar(criterio);
        return resultado != null ? resultado.getDato() : null;
    }

    @Override
    public void preOrden(Consumer<T> accion) {
        if (raiz != null) {
            raiz.preOrden(accion);
        }
    }

    @Override
    public void postOrden(Consumer<T> accion) {
        if (raiz != null) {
            raiz.postOrden(accion);
        }
    }

    @Override
    public void porNiveles(Consumer<T> accion) {

        if (raiz == null) {
            return;
        }
        TDACola<TDAElementoNario<T>> cola = new TDAColaImpl<>();
        cola.agregar(raiz);
        while (!cola.esVacio()) {
            TDAElementoNario<T> actual = cola.quitaDeCola();
            accion.accept(actual.getDato());
            TDALista<TDAElementoNario<T>> hijos = actual.getHijos();
            for (int i = 0; i < hijos.tamanio(); i++) {
                cola.agregar(hijos.obtener(i));
            }
        }
    }

}