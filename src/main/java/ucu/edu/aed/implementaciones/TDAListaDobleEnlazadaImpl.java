package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class TDAListaDobleEnlazadaImpl<T> implements TDALista<T> {

    private NodoDoble<T> primero;
    private NodoDoble<T> ultimo;
    private int tamanio;

    private static class NodoDoble<T> {
        private T dato;
        private NodoDoble<T> anterior;
        private NodoDoble<T> siguiente;

        private NodoDoble(T dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }

    @Override
    public void agregar(T elem) {
        NodoDoble<T> nuevo = new NodoDoble<T>(elem);
        if (esVacio()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
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

        if (index == 0) {
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
            tamanio++;
            return;
        }

        NodoDoble<T> actual = obtenerNodo(index);
        NodoDoble<T> anterior = actual.anterior;

        nuevo.anterior = anterior;
        nuevo.siguiente = actual;
        anterior.siguiente = nuevo;
        actual.anterior = nuevo;
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
            primero = null;
            ultimo = null;
        } else if (actual == primero) {
            primero = actual.siguiente;
            primero.anterior = null;
        } else if (actual == ultimo) {
            ultimo = actual.anterior;
            ultimo.siguiente = null;
        } else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }

        actual.anterior = null;
        actual.siguiente = null;
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
        NodoDoble<T> actual = primero;
        int index = 0;
        while (actual != null) {
            if ((elem == null && actual.dato == null) || (elem != null && elem.equals(actual.dato))) {
                return index;
            }
            actual = actual.siguiente;
            index++;
        }
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        NodoDoble<T> actual = primero;
        while (actual != null) {
            if (criterio.test(actual.dato)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        TDAListaDobleEnlazadaImpl<T> ordenada = new TDAListaDobleEnlazadaImpl<T>();
        T[] copia = (T[]) new Object[tamanio];
        NodoDoble<T> actual = primero;
        int i = 0;
        while (actual != null) {
            copia[i] = actual.dato;
            actual = actual.siguiente;
            i++;
        }
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
        primero = null;
        ultimo = null;
        tamanio = 0;
    }

    private NodoDoble<T> obtenerNodo(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }

        NodoDoble<T> actual;
        if (index < tamanio / 2) {
            actual = primero;
            for (int i = 0; i < index; i++) {
                actual = actual.siguiente;
            }
        } else {
            actual = ultimo;
            for (int i = tamanio - 1; i > index; i--) {
                actual = actual.anterior;
            }
        }
        return actual;
    }
}
