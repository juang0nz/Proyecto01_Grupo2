package ucu.edu.aed.implementaciones;

import ucu.edu.aed.tda.TDAConjunto;

public class TDAConjuntoImpl<T> extends TDAListaEnlazadaImpl<T>implements TDAConjunto<T> {

    // En un conjunto no se permiten duplicados. Sobrescribimos los métodos de
    // agregación para garantizar que un elemento solo se inserte si no existe
    // previamente (según equals()).
    @Override
    public void agregar(T elem) {
        if (!this.contiene(elem)) {
            super.agregar(elem);
        }
    }

    @Override
    public void agregar(int index, T elem) {
        if (!this.contiene(elem)) {
            super.agregar(index, elem);
        }
    }


    /*
     * Lenguaje natural
     * 
     * El método union recibe otro conjunto y crea un nuevo conjunto que contiene
     * los elementos de ambos conjuntos.
     * 
     * Primero se crea un conjunto vacío y se recorren todos los elementos del
     * conjunto actual, agregándolos al nuevo conjunto. Luego se recorre el conjunto
     * recibido como parámetro y, para cada elemento, se verifica si ya existe en el
     * nuevo conjunto. Si no existe, se agrega.
     * 
     * Finalmente, se devuelve el nuevo conjunto con todos los elementos de ambos
     * conjuntos sin elementos repetidos, manteniendo los dos conjuntos originales
     * sin modificaciones.
     * 
     * Precondiciones
     * otro debe ser un conjunto válido y distinto de null.
     * Ambos conjuntos contienen elementos del mismo tipo T.
     * 
     * Postcondiciones     *
     * Se devuelve un nuevo conjunto que contiene todos los elementos del conjunto
     * actual y de otro.
     * El conjunto resultante no contiene elementos duplicados.
     * El conjunto actual no se modifica.
     * El conjunto otro no se modifica.
     */
    /* union(otro)

    crear nuevoConjunto vacío

    actual ← primero

    mientras actual ≠ nulo hacer
        nuevoConjunto.agregar(actual.dato)
        actual ← actual.siguiente
    fin mientras

    actual ← primero de otro

    mientras actual ≠ nulo hacer

        si nuevoConjunto NO contiene actual.dato entonces
            nuevoConjunto.agregar(actual.dato)
        fin si

        actual ← actual.siguiente

    fin mientras

    devolver nuevoConjunto

fin union*/



@Override
public TDAConjunto<T> union(TDAConjunto<T> otro){
    TDAConjunto<T> nuevo = new TDAConjuntoImpl<>();
    Nodo <T> actual = primero;
    while (actual != null){
        nuevo.agregar(actual.getDato());
        actual = actual.getSiguiente();
    }

    TDAConjuntoImpl<T> otroImpl = (TDAConjuntoImpl<T>)otro;
    actual = otroImpl.primero;

    while (actual != null){
        if (!nuevo.contiene(actual.getDato())) {
            nuevo.agregar(actual.getDato());
            
        }
        actual = actual.getSiguiente();
        }
        return nuevo;
    };

/*
 * Lenguaje natural
 * 
 * El método interseccion recibe otro conjunto y crea un nuevo conjunto que
 * contiene únicamente los elementos que pertenecen a ambos conjuntos.
 * 
 * Para ello, se crea un conjunto vacío y se recorre el conjunto actual. Por
 * cada elemento, se verifica si ese elemento también está contenido en el
 * conjunto recibido como parámetro. Si está presente en ambos, se agrega al
 * nuevo conjunto.
 * 
 * Finalmente, se devuelve el nuevo conjunto con los elementos comunes, sin
 * modificar ninguno de los dos conjuntos originales.
 * 
 * Precondiciones
 * otro debe ser un conjunto válido y distinto de null.
 * Ambos conjuntos contienen elementos del mismo tipo T.
 * 
 * Postcondiciones
 * Se devuelve un nuevo conjunto.
 * El nuevo conjunto contiene únicamente los elementos presentes en ambos
 * conjuntos.
 * No contiene elementos duplicados.
 * El conjunto actual no se modifica.
 * El conjunto otro no se modifica.
 * Si no existen elementos en común, se devuelve un conjunto vacío.
 */

/*interseccion(otro)

    crear nuevoConjunto vacío

    actual ← primero

    mientras actual ≠ nulo hacer

        si otro contiene actual.dato entonces
            nuevoConjunto.agregar(actual.dato)
        fin si

        actual ← actual.siguiente

    fin mientras

    devolver nuevoConjunto

fin interseccion */
@Override
public TDAConjunto<T> interseccion(TDAConjunto<T> otro){

    TDAConjunto<T> nuevo = new TDAConjuntoImpl<>();
    Nodo<T> actual = primero;
    while (actual != null){
        if (otro.contiene(actual.getDato())){
            nuevo.agregar(actual.getDato());
        }
    actual = actual.getSiguiente();
    }
    return nuevo;


}


/*Lenguaje natural

El método diferencia recibe otro conjunto y crea un nuevo conjunto que contiene los elementos que pertenecen al conjunto actual pero que no pertenecen al conjunto recibido como parámetro.

Para ello, se crea un conjunto vacío y se recorre el conjunto actual. Por cada elemento, se verifica si dicho elemento está contenido en otro. Si no está contenido, se agrega al nuevo conjunto.

Finalmente, se devuelve el nuevo conjunto sin modificar ninguno de los dos conjuntos originales.

Precondiciones
otro debe ser un conjunto válido y distinto de null.
Ambos conjuntos contienen elementos del mismo tipo T.

Postcondiciones
Se devuelve un nuevo conjunto.
El resultado contiene únicamente elementos que pertenecen al conjunto actual y no pertenecen a otro.
No contiene elementos duplicados.
El conjunto actual no se modifica.
otro no se modifica.
Si todos los elementos del conjunto actual también están en otro, se devuelve un conjunto vacío. */

    /* diferencia(otro)

    crear nuevoConjunto vacío

    actual ← primero

    mientras actual ≠ nulo hacer

        si otro NO contiene actual.dato entonces
            nuevoConjunto.agregar(actual.dato)
        fin si

        actual ← actual.siguiente

    fin mientras

    devolver nuevoConjunto

fin diferencia*/

@Override
public TDAConjunto<T> diferencia(TDAConjunto<T> otro){
    TDAConjunto<T> nuevo = new TDAConjuntoImpl<>();
    Nodo<T> actual = primero;
    while (actual != null){
        if (!otro.contiene(actual.getDato())){
            nuevo.agregar(actual.getDato());
        }
        actual= actual.getSiguiente();
    }
return nuevo;
}
/*Lenguaje natural

El método esSubconjuntoDe determina si todos los elementos del conjunto actual también pertenecen al conjunto recibido como parámetro.

Para ello, se recorre el conjunto actual y se verifica cada elemento en el conjunto otro. Si se encuentra al menos un elemento que no pertenece a otro, se retorna false inmediatamente.

Si se termina de recorrer todo el conjunto sin encontrar ningún elemento que falte en otro, se retorna true.

Precondiciones
otro debe ser un conjunto válido y distinto de null.
Ambos conjuntos contienen elementos del mismo tipo T.

Postcondiciones
Retorna true si todos los elementos del conjunto actual pertenecen también a otro.
Retorna false si existe al menos un elemento del conjunto actual que no pertenece a otro.
Ninguno de los dos conjuntos es modificado. */
/* esSubconjuntoDe(otro)

    actual ← primero

    mientras actual ≠ nulo hacer

        si otro NO contiene actual.dato entonces
            devolver false
        fin si

        actual ← actual.siguiente

    fin mientras

    devolver true

fin esSubconjuntoDe*/


@Override
public boolean esSubconjuntoDe(TDAConjunto<T> otro){
    Nodo<T> actual = primero;
    while ( actual != null){
        if (!otro.contiene(actual.getDato())){
            return false;
        }
        actual = actual.getSiguiente();
    }
    return true;
}

}

















    

