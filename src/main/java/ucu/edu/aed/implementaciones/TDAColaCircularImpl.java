package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

public class TDAColaCircularImpl<T> {

private Object[] vector;
private int frente;
private int fondo;
private int cantidadElementos;

public TDAColaCircularImpl(int capacidad){
    //arranco con el vector vacio y los dos "carteles" en 0
    vector = new Object[capacidad];
    frente = 0;
    fondo = 0;
    cantidadElementos = 0;
}

// agrega un elemento al fondo de la cola
public boolean poneEnCola(T dato){
    //me fijo si esta llena
    if (cantidadElementos == vector.length) {
    throw new IllegalStateException("La cola está llena");
    }
    //pongo el dato en la posicion del fondo
    vector[fondo] = dato;
    //muevo el cartel fondo, el modulo hace que de la vuelta al llegar al final
    fondo = (fondo + 1) % vector.length;
    //sumo uno al contador
    cantidadElementos++;
    return true;
}

//saca el elemento del frente y lo devuelve
@SuppressWarnings("unchecked")
public T quitaDeCola (){
    //me fijo si es vacia
    if (esVacio()) {
    throw new NoSuchElementException();
    }
    //guardo el dato que esta en la posicion frente
    T dato = (T) vector[frente];
    //muevo el cartel frente, tambien con modulo
    frente = (frente + 1) % vector.length;
    //resto uno al contador
    cantidadElementos--;
    return dato;
}

//devuelve el elemento del frente sin sacarlo
@SuppressWarnings("unchecked")
public T frente(){
    //me fijo si es vacia
    if (esVacio()) {
    throw new NoSuchElementException();
    }
    //obtengo el dato en la posicion frente
    return (T) vector[frente];
}

//esta vacia si el contador esta en 0
public boolean esVacio(){
    return cantidadElementos == 0;
}

//esta llena si el contador llego al tamanio del vector
public boolean estaLlena(){
    return cantidadElementos == vector.length;
}

//devuelve cuantos elementos hay cargados
public int tamanio(){
    return cantidadElementos;
}

}
/*
comparacion: cola con lista enlazada vs cola circular 

memoria:
lista enlazada gasta mas por elemento porque cada nodo tiene que guardar el puntero al siguiente, pero no tiene limite, crece como quiera
circular es mas compacto porque no tiene ese puntero extra si se llena hay que tirar error 

tiempo de ejecucion:
las dos dan O(1) en poneEnCola y quitaDeCola
la lista enlazada da O(1) 
el vector circular da O(1) 
*/