package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDACola;

public class TDAColaPrioridadConArregloImpl<T> extends TDAListaConArregloImpl<T> implements TDACola<T> {

    private final Comparator<T> comparador;

    public TDAColaPrioridadConArregloImpl(Comparator<T> comparador) {
        if (comparador == null) {
            throw new IllegalArgumentException("El comparador no puede ser nulo");
        }
        this.comparador = comparador;
    }

    @Override
    public T frente() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return obtener(0);
    }

    @Override
    public boolean poneEnCola(T dato) {
        if (dato == null) {
            return false;
        }

        int posicion = 0;
        while (posicion < tamanio() && comparador.compare(obtener(posicion), dato) <= 0) {
            posicion++;
        }

        super.agregar(posicion, dato);
        return true;
    }

    @Override
    public T quitaDeCola() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return remover(0);
    }

    @Override
    public void agregar(T elem) {
        poneEnCola(elem);
    }

    @Override
    public void agregar(int index, T elem) {
        throw new UnsupportedOperationException("No se soporta inserción por índice en una cola con prioridad");
    }
}
