package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDAPila;

public class TDAPilaImpl<T> extends TDAListaEnlazadaImpl<T> implements TDAPila<T> {

    // opbtener el tope de la pila con este metodo
    @Override
    public T tope() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return obtener(tamanio() - 1);

    }

    // este saca el ultimo el elemento de la Pila y lo devuelve
    @Override
    public T saca() {
        if (esVacio()) {
            throw new NoSuchElementException();
        }
        return remover(tamanio() - 1);
    }

    // mete el elemento en el tope de la pila
    @Override
    public void mete(T dato) {
        agregar(dato);
    }

}
