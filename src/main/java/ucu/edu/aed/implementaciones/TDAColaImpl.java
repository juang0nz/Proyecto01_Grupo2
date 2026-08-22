package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDACola;

public class TDAColaImpl<T> extends TDAListaEnlazadaImpl<T> implements TDACola<T> {

// sacamos el primer elemento porque en la cola se inserta al final
@Override
public T frente(){
    //me fijo si es vacia
    if (esVacio()) {
    throw new NoSuchElementException();
    }
    //obtengo el del indice 0
    return obtener(0);
}
// agrega al final de la lista
@Override
public boolean  poneEnCola(T dato){
    agregar(dato);
    return true;
}

//quitar de cola, seria remover en este caso
@Override
public T quitaDeCola (){
    //me fijo si es vacia
    if (esVacio()) {
    throw new NoSuchElementException();
    }
    //saco el primero que es el del indice 0
    return remover(0);

}











}