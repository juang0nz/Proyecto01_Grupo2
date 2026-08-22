package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDACola;
import ucu.edu.aed.tda.TDALista;

public class TDAColaCircularConArregloImpl<T> extends TDAListaConArregloImpl<T> implements TDACola<T> {

    private int frente; // marca el inicio de la cola
    private int fondo; //marca el final de la cola

    public TDAColaCircularConArregloImpl() {
        this(10);
    }

    public TDAColaCircularConArregloImpl(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        elementos = new Object[capacidad];
        frente = 0;
        fondo = 0;
        size = 0;
    }

    @Override
    public T frente() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return obtenerElemento(frente);
    }

    @Override
    public boolean poneEnCola(T dato) {
        if (size == elementos.length) {
            throw new IllegalStateException("La cola está llena");
        }
        elementos[fondo] = dato;
        fondo = (fondo + 1) % elementos.length;
        size++;
        return true;
    }

    @Override
    public T quitaDeCola() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        T dato = obtenerElemento(frente);
        elementos[frente] = null;
        frente = (frente + 1) % elementos.length;
        size--;
        return dato;
    }

    @Override
    public void agregar(T elem) {
        poneEnCola(elem);
    }

    @Override
    public void agregar(int index, T elem) {
        if (index == size) {
            poneEnCola(elem);
            return;
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
        int posicion = (frente + index) % elementos.length;
        int ultimo = (frente + size) % elementos.length;
        for (int i = size; i > index; i--) {
            int anterior = (frente + i - 1) % elementos.length;
            int actual = (frente + i) % elementos.length;
            elementos[actual] = elementos[anterior];
        }
        elementos[posicion] = elem;
        size++;
        fondo = (ultimo + 1) % elementos.length;
    }

    @Override
    public T obtener(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
        int posicion = (frente + index) % elementos.length;
        return obtenerElemento(posicion);
    }

    @Override
    public T remover(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
        if (index == 0) {
            return quitaDeCola();
        }
        int posicion = (frente + index) % elementos.length;
        T dato = obtenerElemento(posicion);

        for (int i = index; i < size - 1; i++) {
            int actual = (frente + i) % elementos.length;
            int siguiente = (frente + i + 1) % elementos.length;
            elementos[actual] = elementos[siguiente];
        }

        elementos[(frente + size - 1) % elementos.length] = null;
        fondo = (frente + size - 1) % elementos.length;
        size--;
        return dato;
    }

    @Override
    public boolean remover(T elem) {
        int index = indiceDe(elem);
        if (index == -1) {
            return false;
        }
        remover(index);
        return true;
    }

    @Override
    public boolean contiene(T elem) {
        return indiceDe(elem) != -1;
    }

    @Override
    public int indiceDe(T elem) {
        for (int i = 0; i < size; i++) {
            T actual = obtener(i);
            if ((elem == null && actual == null) || (elem != null && elem.equals(actual))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        for (int i = 0; i < size; i++) {
            T actual = obtener(i);
            if (criterio.test(actual)) {
                return actual;
            }
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        TDAListaConArregloImpl<T> ordenada = new TDAListaConArregloImpl<>();
        for (int i = 0; i < size; i++) {
            T actual = obtener(i);
            int posicion = 0;
            while (posicion < ordenada.size && comparator.compare(ordenada.obtener(posicion), actual) <= 0) {
                posicion++;
            }
            ordenada.agregar(posicion, actual);
        }
        return ordenada;
    }

    @Override
    public int tamanio() {
        return size;
    }

    @Override
    public boolean esVacio() {
        return size == 0;
    }

    @Override
    public void vaciar() {
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = null;
        }
        frente = 0;
        fondo = 0;
        size = 0;
    }

   
    private T obtenerElemento(int posicion) {
        return (T) elementos[posicion];
    }
}
