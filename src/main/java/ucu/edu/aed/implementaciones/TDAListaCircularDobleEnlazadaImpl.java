package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class TDAListaCircularDobleEnlazadaImpl<T> implements TDALista<T> {

    private NodoDoble<T> cabeza;
    private int tamanio;

    private static class NodoDoble<T> {
        private T dato;
        private NodoDoble<T> anterior;
        private NodoDoble<T> siguiente;

        private NodoDoble(T dato) {
            this.dato = dato;
        }
    }

    @Override
    public void agregar(T elem) {
        NodoDoble<T> nuevo = new NodoDoble<T>(elem);
        if (esVacio()) {
            cabeza = nuevo;
            cabeza.anterior = cabeza;
            cabeza.siguiente = cabeza;
        } else {
            NodoDoble<T> ultimo = cabeza.anterior;
            nuevo.anterior = ultimo;
            nuevo.siguiente = cabeza;
            ultimo.siguiente = nuevo;
            cabeza.anterior = nuevo;
        }
        tamanio++;
    }

    @Override
    public void agregar(int index, T elem) {
        if (index < 0 || index > tamanio) {
            throw new IndexOutOfBoundsException();
        }
        if (index == tamanio) {
            agregar(elem);
            return;
        }
        NodoDoble<T> nuevo = new NodoDoble<T>(elem);
        if (esVacio()) {
            cabeza = nuevo;
            cabeza.anterior = cabeza;
            cabeza.siguiente = cabeza;
            tamanio++;
            return;
        }

        NodoDoble<T> actual = obtenerNodo(index);
        NodoDoble<T> anterior = actual.anterior;

        nuevo.anterior = anterior;
        nuevo.siguiente = actual;
        anterior.siguiente = nuevo;
        actual.anterior = nuevo;

        if (index == 0) {
            cabeza = nuevo;
        }
        tamanio++;
    }

    @Override
    public T obtener(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }
        return obtenerNodo(index).dato;
    }

    @Override
    public T remover(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }

        NodoDoble<T> actual = obtenerNodo(index);
        T dato = actual.dato;

        if (tamanio == 1) {
            cabeza = null;
        } else {
            NodoDoble<T> anterior = actual.anterior;
            NodoDoble<T> siguiente = actual.siguiente;
            anterior.siguiente = siguiente;
            siguiente.anterior = anterior;
            if (actual == cabeza) {
                cabeza = siguiente;
            }
        }

        tamanio--;
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
        if (esVacio()) {
            return -1;
        }
        NodoDoble<T> actual = cabeza;
        int index = 0;
        do {
            if ((elem == null && actual.dato == null) || (elem != null && elem.equals(actual.dato))) {
                return index;
            }
            actual = actual.siguiente;
            index++;
        } while (actual != cabeza);
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        if (esVacio()) {
            return null;
        }
        NodoDoble<T> actual = cabeza;
        do {
            if (criterio.test(actual.dato)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        TDAListaCircularDobleEnlazadaImpl<T> ordenada = new TDAListaCircularDobleEnlazadaImpl<T>();
        T[] copia = (T[]) new Object[tamanio];
        NodoDoble<T> actual = cabeza;
        int i = 0;
        do {
            copia[i] = actual.dato;
            actual = actual.siguiente;
            i++;
        } while (actual != cabeza);

        java.util.Arrays.sort(copia, (o1, o2) -> comparator.compare(o1, o2));
        for (int j = 0; j < copia.length; j++) {
            ordenada.agregar(copia[j]);
        }
        return ordenada;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }

    @Override
    public boolean esVacio() {
        return tamanio == 0;
    }

    @Override
    public void vaciar() {
        cabeza = null;
        tamanio = 0;
    }

    private NodoDoble<T> obtenerNodo(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }
        NodoDoble<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }
}
