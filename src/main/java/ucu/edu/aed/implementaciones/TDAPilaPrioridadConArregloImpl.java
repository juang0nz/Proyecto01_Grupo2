package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDAPila;

public class TDAPilaPrioridadConArregloImpl<T> extends TDAListaConArregloImpl<T> implements TDAPila<T> {

    private final Comparator<T> comparador;

    public TDAPilaPrioridadConArregloImpl(Comparator<T> comparador) {
        if (comparador == null) {
            throw new IllegalArgumentException("El comparador no puede ser nulo");
        }
        this.comparador = comparador;
    }

    @Override
    public T tope() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return obtener(0);
    }

    @Override
    public T saca() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return remover(0);
    }

    @Override
    public void mete(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }

        int posicion = 0;
        while (posicion < tamanio() && comparador.compare(obtener(posicion), dato) <= 0) {
            posicion++;
        }
        super.agregar(posicion, dato);
    }

    @Override
    public void agregar(T elem) {
        mete(elem);
    }

    @Override
    public void agregar(int index, T elem) {
        throw new UnsupportedOperationException("No se soporta inserción por índice en una pila con prioridad");
    }
}
