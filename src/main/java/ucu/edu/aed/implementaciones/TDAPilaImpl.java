package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDAPila;

public class TDAPilaImpl<T> extends TDAListaEnlazadaImpl<T> implements TDAPila<T> {

    // Obtener el tope de la pila: con la implementación actual de la lista
    // el tope está en la cabeza (primer elemento), por lo que tope() es obtener(0).
    @Override
    public T tope() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        // el primero (head) representa el tope de la pila
        return obtener(0);

    }

    // Saca el elemento del tope (head) y lo devuelve
    @Override
    public T saca() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        // remover(0) es O(1) con nuestra implementación actual de lista (inserción en head)
        return remover(0);
    }

    // Mete el elemento en el tope de la pila: insertar en la cabeza (índice 0)
    @Override
    public void mete(T dato) {
        // insertamos en la posición 0 para que el head represente el tope
        agregar(0, dato);
    }

}
