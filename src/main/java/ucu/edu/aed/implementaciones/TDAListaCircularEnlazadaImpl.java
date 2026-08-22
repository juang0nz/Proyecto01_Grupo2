package ucu.edu.aed.implementaciones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class TDAListaCircularEnlazadaImpl<T> implements TDALista<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamanio;

    @Override
    public void agregar(T elem) {
        Nodo<T> nuevo = new Nodo<T>(elem);
        if (esVacio()) {
            primero = nuevo;
            ultimo = nuevo;
            nuevo.setSiguiente(nuevo);
        } else {
            nuevo.setSiguiente(primero);
            ultimo.setSiguiente(nuevo);
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

        Nodo<T> nuevo = new Nodo<T>(elem);
        if (index == 0) {
            if (esVacio()) {
                primero = nuevo;
                ultimo = nuevo;
                nuevo.setSiguiente(nuevo);
            } else {
                nuevo.setSiguiente(primero);
                primero = nuevo;
                ultimo.setSiguiente(primero);
            }
            tamanio++;
            return;
        }

        Nodo<T> anterior = obtenerNodo(index - 1);
        nuevo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevo);
        tamanio++;
    }

    @Override
    public T obtener(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }
        return obtenerNodo(index).getDato();
    }

    @Override
    public T remover(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> removido;
        if (tamanio == 1) {
            removido = primero;
            primero = null;
            ultimo = null;
            tamanio--;
            return removido.getDato();
        }

        if (index == 0) {
            removido = primero;
            primero = primero.getSiguiente();
            ultimo.setSiguiente(primero);
            tamanio--;
            return removido.getDato();
        }

        Nodo<T> anterior = obtenerNodo(index - 1);
        removido = anterior.getSiguiente();
        anterior.setSiguiente(removido.getSiguiente());
        if (removido == ultimo) {
            ultimo = anterior;
        }
        tamanio--;
        return removido.getDato();
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
        Nodo<T> actual = primero;
        for (int i = 0; i < tamanio; i++) {
            T dato = actual.getDato();
            if ((elem == null && dato == null) || (elem != null && elem.equals(dato))) {
                return i;
            }
            actual = actual.getSiguiente();
        }
        return -1;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = primero;
        for (int i = 0; i < tamanio; i++) {
            T dato = actual.getDato();
            if (criterio.test(dato)) {
                return dato;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        TDAListaCircularEnlazadaImpl<T> ordenada = new TDAListaCircularEnlazadaImpl<T>();
        T[] copia = (T[]) new Object[tamanio];

        Nodo<T> actual = primero;
        for (int i = 0; i < tamanio; i++) {
            copia[i] = actual.getDato();
            actual = actual.getSiguiente();
        }

        Arrays.sort(copia, (o1, o2) -> comparator.compare(o1, o2));
        for (int i = 0; i < copia.length; i++) {
            ordenada.agregar(copia[i]);
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

    private Nodo<T> obtenerNodo(int index) {
        Nodo<T> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }
}
