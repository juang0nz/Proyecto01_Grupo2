package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

public class TDAListaEnlazadaImpl<T> implements TDALista<T> {

    protected  Nodo<T> primero;
    protected  int tamanio;

    /*
     * Lenguaje natural:
     * Se crea un nuevo nodo con el elemento recibido. Si la lista está vacía, el
     * nuevo nodo pasa a ser el primer elemento. Si la lista contiene elementos, se
     * recorre desde el primero hasta encontrar el último nodo, es decir, aquel cuyo
     * siguiente es null. Luego se enlaza el nuevo nodo como siguiente del último.
     * Finalmente, se incrementa el tamaño de la lista.
     * 
     * Precondiciones:
     * La lista debe estar correctamente inicializada. El elemento recibido debe ser
     * válido según las condiciones definidas para el TDA. Si tu implementación
     * permite null, no hace falta exigir elem != null.
     * 
     * Postcondiciones:
     * El elemento queda agregado al final de la lista. El orden de los elementos
     * existentes no cambia. El tamaño de la lista aumenta en una unidad.
     * 
     * Seudocódigo:
     * 
     * agregar(elem)
     * 
     * nuevoNodo <- nuevo Nodo(elem)
     * 
     * si primero = nulo entonces
     * primero <- nuevoNodo
     * sino
     * actual <- primero
     * 
     * mientras actual.siguiente <> nulo hacer
     * actual <- actual.siguiente
     * fin mientras
     * 
     * actual.siguiente <- nuevoNodo
     * fin si
     * 
     * tamanio <- tamanio + 1
     * 
     * fin agregar
     * 
     */

    // Implementación del método agregar al final de la lista:

    @Override
    public void agregar(T elem) {
        // si la lista es vacia:
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (primero == null) {
            primero = nuevoNodo;
        } else { // sino al pri9mero le digo actual y recorro los siguientes hasta encontrar el
                 // ultimo y ahi seteo el nuevoNodo
            Nodo<T> actual = primero;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamanio++;

    }

    /*
     * Lenguaje natural
     * 
     * Se recibe un índice y un elemento a insertar. Primero se verifica que el
     * índice sea válido, es decir, que esté entre 0 y el tamaño de la lista
     * inclusive.
     * 
     * Si el índice es 0, el nuevo nodo se inserta al comienzo de la lista, haciendo
     * que apunte al nodo que anteriormente era el primero.
     * 
     * Si el índice es distinto de 0, se recorre la lista hasta llegar al nodo
     * ubicado inmediatamente antes de la posición donde queremos insertar. Luego se
     * enlaza el nuevo nodo con el siguiente del nodo actual y, finalmente, el nodo
     * actual se enlaza con el nuevo nodo.
     * 
     * Al terminar, se incrementa el tamaño de la lista.
     * 
     * Precondiciones
     * index debe estar entre 0 y tamanio, inclusive.
     * La lista debe estar correctamente inicializada.
     * El elemento debe ser válido según las condiciones del TDA.
     * 
     * Una cosa importante:
     * 
     * 0 <= index <= tamanio
     * 
     * Permitís index == tamanio porque eso significa insertar al final.
     * 
     * Postcondiciones:
     * El elemento queda almacenado en la posición index.
     * Los elementos que estaban desde esa posición en adelante se desplazan una
     * posición hacia la derecha.
     * Los demás elementos mantienen su orden.
     * tamanio aumenta en 1.
     * 
     * agregar(index, elem)
     * 
     * si index < 0 o index > tamanio entonces
     *  lanzar error
     * fin si
     * 
     * nuevoNodo <- nuevo Nodo(elem)
     * 
     *  si index = 0 entonces
     * 
     *      nuevoNodo.siguiente <- primero
     *      primero <- nuevoNodo
     * 
     * sino
     * 
     *  actual <- primero
     *  i <- 0
     * 
     * mientras i < index - 1 hacer
     *  actual <- actual.siguiente
     *  i <- i + 1
     * fin mientras
     * 
     * nuevoNodo.siguiente <- actual.siguiente
     * actual.siguiente <- nuevoNodo
     * 
     * fin si
     * 
     * tamanio <- tamanio + 1
     * 
     * fin agregar
     */
    // agregar un elemento en un indice especifico:

    @Override
    public void agregar(int index, T elem) {

        if (index < 0 || index > tamanio) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> nuevoNodo = new Nodo<>(elem);
        // si el indice es 0 ahi lo pongo en el primero
        if (index == 0) {
            nuevoNodo.setSiguiente(primero); // primero hago esto sino pierdo el nodo
            primero = nuevoNodo;
        } else {
            // en caso de que sea un numero aceptable dentro del tamanio, y no sea el
            // primero recorro hasta el anterior al indice y
            Nodo<T> actual = primero;
            int i = 0;
            while (i < index - 1) {
                actual = actual.getSiguiente();
                i++;
            }

            nuevoNodo.setSiguiente(actual.getSiguiente()); // pone el nuevo nodo apuntando al mismo que apunta
                                                           // actual.siguiente
            actual.setSiguiente(nuevoNodo); // y despues pone a actual a apuntar a nuevoNodo asi lo pone en medio.
        }

        tamanio++;
    }


/*Lenguaje natural

Se recibe el índice del elemento que se desea obtener. Primero se verifica que el índice sea válido, es decir, que se encuentre entre 0 y tamanio - 1.

Luego se comienza desde el primer nodo de la lista y se avanza nodo por nodo hasta alcanzar la posición indicada por index.

Finalmente, se devuelve el dato almacenado en ese nodo.

Precondiciones

El índice debe corresponder a una posición existente de la lista:
0 <= index < tamanio

Postcondiciones
Se devuelve el elemento almacenado en la posición index.
La lista no se modifica.
El tamaño de la lista no cambia.
Los enlaces entre nodos permanecen iguales.

obtener(index)

    si index < 0 o index >= tamanio entonces
        lanzar error
    fin si

    actual <- primero
    i <- 0

    mientras i < index hacer
        actual <- actual.siguiente
        i <- i + 1
    fin mientras

    devolver actual.dato

fin obtener */


    @Override
    public T obtener(int index) {

        // verifico que este dentro el indice
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }
        Nodo<T> actual = primero;
        int i = 0;
        // avanza hasta el numero del index -1 sirve si empieza en 0
        while (i < index) {
            actual = actual.getSiguiente();
            i++;
        }
        // retorna el dato que quedo en actual
        return actual.getDato();
    }

    // removemos un dato y devolvemos segun el indice que nos pasan

    /*
     * Lenguaje natural
     * 
     * Se recibe el índice del elemento que se desea eliminar. Primero se verifica
     * que el índice sea válido.
     * 
     * Si el índice es 0, se guarda el dato del primer nodo y se actualiza primero
     * para que apunte al siguiente nodo de la lista.
     * 
     * Si el índice es distinto de 0, se recorre la lista hasta llegar al nodo
     * anterior al que se desea eliminar. Luego se guarda el dato del nodo a
     * eliminar y se modifica el enlace del nodo anterior para que apunte al nodo
     * siguiente del eliminado.
     * 
     * Finalmente, se disminuye el tamaño de la lista y se devuelve el dato
     * eliminado.
     * 
     * Precondiciones
     * 
     * El índice debe corresponder a una posición existente:
     * 
     * 0 <= index < tamanio
     * 
     * La lista no puede estar vacía, pero eso ya queda cubierto indirectamente por
     * la validación del índice.
     * 
     * Postcondiciones
     * El elemento que estaba en la posición index es eliminado.
     * Se devuelve el dato del nodo eliminado.
     * Los nodos posteriores se desplazan una posición hacia la izquierda.
     * El tamaño disminuye en 1.
     * El resto de los elementos conserva su orden.
     * remover(index)

    si index < 0 o index >= tamanio entonces
        lanzar error
    fin si

    si index = 0 entonces

        dato <- primero.dato
        primero <- primero.siguiente
        tamanio <- tamanio - 1

        devolver dato

    fin si

    actual <- primero
    i <- 0

    mientras i < index - 1 hacer
        actual <- actual.siguiente
        i <- i + 1
    fin mientras

    dato <- actual.siguiente.dato

    actual.siguiente <- actual.siguiente.siguiente

    tamanio <- tamanio - 1

    devolver dato

fin remover
     */


    @Override
    public T remover(int index) {

        // verifico que este dentro el indice
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException();
        }
        // si es el primero
        if (index == 0) {
            T dato = primero.getDato(); // obtengo dato
            primero = primero.getSiguiente(); // pongo como primero el que le seguia si esta
            tamanio--; // disminuyo tamanio
            return dato; // retorno el dato
        }
        Nodo<T> actual = primero;
        int i = 0;
        while (i < index - 1) {
            actual = actual.getSiguiente();
            i++;
        }
        T dato = actual.getSiguiente().getDato(); // guardo dato a eliminar, al siguiente donde me quede
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        tamanio--;
        return dato;
    }

/*
 * Lenguaje natural
 * 
 * Se recibe el elemento que se desea eliminar.
 * 
 * Primero se verifica si la lista está vacía. Si lo está, se devuelve false
 * porque no existe ningún elemento para eliminar.
 * 
 * Si el elemento buscado se encuentra en el primer nodo, se actualiza primero
 * para que apunte al segundo nodo, se disminuye el tamaño y se devuelve true.
 * 
 * Si no está en el primer nodo, se recorre la lista buscando el elemento. Para
 * poder eliminarlo, actual se mantiene en el nodo anterior al que se está
 * comparando.
 * 
 * Cuando se encuentra el elemento, se modifica el enlace del nodo anterior para
 * que apunte al nodo siguiente del eliminado, se disminuye el tamaño y se
 * devuelve true.
 * 
 * Si se recorre toda la lista sin encontrarlo, se devuelve false.
 * 
 * Precondiciones
 * 
 * La lista debe estar correctamente inicializada.
 * 
 * Además, con tu implementación actual, elem y los datos almacenados no
 * deberían ser null, porque utilizás:
 * 
 * primero.getDato().equals(elem)
 * 
 * Si getDato() devuelve null, tendrías un NullPointerException.
 * 
 * Postcondiciones
 * 
 * Si el elemento existe:
 * 
 * Se elimina la primera aparición del elemento.
 * tamanio disminuye en 1.
 * Se devuelve true.
 * 
 * Si el elemento no existe:
 * 
 * La lista permanece sin modificaciones.
 * tamanio no cambia.
 * Se devuelve false.
 * 
 remover(elem)

    si primero = nulo entonces
        devolver falso
    fin si

    si primero.dato = elem entonces
        primero <- primero.siguiente
        tamanio <- tamanio - 1
        devolver verdadero
    fin si

    actual <- primero

    mientras actual.siguiente <> nulo hacer

        si actual.siguiente.dato = elem entonces

            actual.siguiente <- actual.siguiente.siguiente
            tamanio <- tamanio - 1

            devolver verdadero
        fin si

        actual <- actual.siguiente

    fin mientras

    devolver falso

fin remover
 */
    @Override
    public boolean remover(T elem) {
        // si la lista es vacia retorna false de una
        if (primero == null) {
            return false;
        }
        // en caso de que sea el primero:
        if (primero.getDato().equals(elem)) {
            primero = primero.getSiguiente();
            tamanio--;
            return true;
        }
        // en caso de que toque recorrer la lista para ver si esta el elemento:
        Nodo<T> actual = primero;

        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(elem)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente()); // estando parado en el anterior, muevo el
                                                                           // puntero para el de adelante asi elimino a
                                                                           // un nodo.
                tamanio--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /*
     * Lenguaje natural
     * 
     * Se comienza desde el primer nodo de la lista y se recorren los nodos uno por
     * uno.
     * 
     * En cada nodo se compara su dato con el elemento buscado. Si se encuentra un
     * elemento igual, se devuelve true inmediatamente.
     * 
     * Si se llega al final de la lista sin encontrarlo, se devuelve false.
     * 
     * Precondiciones
     * 
     * La lista debe estar correctamente inicializada.
     * 
     * Con tu implementación actual, los datos almacenados no deberían ser null,
     * porque hacés:
     * 
     * actual.getDato().equals(elem)
     * 
     * Si querés permitir null, eso habría que manejarlo aparte.
     * 
     * Postcondiciones
     * Si el elemento existe en la lista, devuelve true.
     * Si el elemento no existe, devuelve false.
     * La lista no se modifica.
     * tamanio no cambia.
     * 
contiene(elem)

    actual <- primero

    mientras actual <> nulo hacer

        si actual.dato = elem entonces
            devolver verdadero
        fin si

        actual <- actual.siguiente

    fin mientras

    devolver falso

fin contiene 
     */
    // verifico si contiene el elemento que necesito en la lista
    @Override
    public boolean contiene(T elem) {
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

/*
 * Lenguaje natural:
 * Se recorre la lista desde el primer nodo utilizando un contador que
 * representa el índice actual. En cada nodo se compara su dato con el elemento
 * buscado. Si son iguales, se devuelve el índice actual. Si se llega al final
 * de la lista sin encontrar el elemento, se devuelve -1.
 * 
 * Precondiciones:
 * La lista debe estar correctamente inicializada. Con esta implementación, los
 * datos almacenados no deberían ser null.
 * 
 * Postcondiciones:
 * Si el elemento existe, devuelve el índice de su primera aparición. Si no
 * existe, devuelve -1. La lista y su tamaño no se modifican.
 */
/* indiceDe(elem)

    i <- 0
    actual <- primero

    mientras actual <> nulo hacer

        si actual.dato = elem entonces
            devolver i
        fin si

        i <- i + 1
        actual <- actual.siguiente

    fin mientras

    devolver -1

fin indiceDe*/
    // aca lo que hacemos es recorrer la lista y si esta el elem se devuelve el
    // indice, sino -1
    @Override
    public int indiceDe(T elem) {
        int i = 0;
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                return i;
            }
            i++;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    // para buscar con un cierto criterio
    /*
     * Libro encontrado = lista.buscar(
     * libro -> libro.getTitulo().equals("Dune")
     * );
     */
    /*
     * Lenguaje natural
     * 
     * Se recibe un criterio de búsqueda y se recorre la lista desde el primer nodo.
     * Para cada nodo se verifica si su dato cumple con el criterio recibido. Si lo
     * cumple, se devuelve el dato de ese nodo. Si se llega al final de la lista sin
     * encontrar ningún elemento que cumpla el criterio, se devuelve null.
     * 
     * Precondiciones
     * La lista debe estar correctamente inicializada.
     * El criterio recibido debe ser válido y distinto de null.
     * Postcondiciones
     * Si existe un elemento que cumple el criterio, se devuelve el primer elemento
     * que lo cumple.
     * Si ningún elemento cumple el criterio, se devuelve null.
     * La lista no se modifica.
     * El tamaño de la lista no cambia.
     */
    /* buscar(criterio)

    actual <- primero

    mientras actual <> nulo hacer

        si criterio(actual.dato) entonces
            devolver actual.dato
        fin si

        actual <- actual.siguiente

    fin mientras

    devolver nulo

fin buscar*/
    @Override
    public T buscar(Predicate<T> criterio) {

        Nodo<T> actual = primero;
        while (actual != null) {
            if (criterio.test(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

/*
 * Lenguaje natural
 * 
 * Se crea una nueva lista vacía que contendrá los elementos ordenados.
 * 
 * Se recorre la lista original desde el primer nodo. Para cada elemento, se
 * busca en la nueva lista la posición en la que debe ser insertado según el
 * Comparator recibido.
 * 
 * Mientras los elementos de la nueva lista deban aparecer antes o en la misma
 * posición que el elemento actual según el comparador, se avanza una posición.
 * 
 * Una vez encontrada la posición correspondiente, el elemento se inserta en esa
 * posición de la nueva lista.
 * 
 * El proceso se repite para todos los elementos de la lista original.
 * Finalmente, se devuelve la nueva lista ordenada.
 * 
 * Precondiciones
 * La lista debe estar correctamente inicializada.
 * El Comparator<T> recibido debe ser válido y distinto de null.
 * Los elementos deben poder ser comparados mediante el comparador recibido.
 * Postcondiciones
 * Se devuelve una nueva lista con los elementos ordenados según el Comparator.
 * La lista original no se modifica.
 * La nueva lista contiene los mismos elementos que la original.
 * El tamaño de la lista original no cambia.
 * La lista devuelta tiene la misma cantidad de elementos que la original.
 */
/*ordenar(comparador)

    listaOrdenada <- nueva lista vacía
    actual <- primero

    mientras actual <> nulo hacer

        dato <- actual.dato
        posicion <- 0

        mientras posicion < listaOrdenada.tamanio Y
                 comparar(listaOrdenada.obtener(posicion), dato) <= 0 hacer

            posicion <- posicion + 1

        fin mientras

        listaOrdenada.agregar(posicion, dato)

        actual <- actual.siguiente

    fin mientras

    devolver listaOrdenada

fin ordenar
 */

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator) {
        // creo la lista que tengo que devolver luego
        TDAListaEnlazadaImpl<T> lista = new TDAListaEnlazadaImpl<>();

        Nodo<T> actual = primero;
        while (actual != null) {

            T dato = actual.getDato();
            int posicion = 0;
            // si cumple la comparacion en el dato, guarda posicion y dato para agragar en
            // la lista nueva
            while (posicion < lista.tamanio() && comparator.compare(lista.obtener(posicion), dato) <= 0) {
                posicion++;
            }

            lista.agregar(posicion, dato);
            actual = actual.getSiguiente();
        }
        return lista;
    }

    /*
     * @Override
     * public int tamanio() {
     * // si la lista es vacia retorna false de una
     * if (primero == null) {
     * return 0;
     * }
     * Nodo<T> actual = primero;
     * int contador = 1;
     * while (actual.getSiguiente() != null){
     * contador++;
     * actual = actual.getSiguiente();
     * }
     * 
     * return contador;
     * }
     */
    // otra forma usando el atributo tamanio


    /*
     * Lenguaje natural:
     * Devuelve la cantidad de elementos que contiene actualmente la lista
     * utilizando el atributo tamanio, que se actualiza cada vez que se agrega o
     * elimina un elemento.
     * 
     * Precondiciones:
     * El atributo tamanio debe estar correctamente actualizado por las operaciones
     * que modifican la lista.
     * 
     * Postcondiciones:
     * Se devuelve la cantidad actual de elementos de la lista. La lista no se
     * modifica.
     */
/*tamanio()

    devolver tamanio

fin tamanio */
    @Override
    public int tamanio() {
        return tamanio;
    }


    /*
     * Lenguaje natural
     * 
     * Se verifica si el primer nodo de la lista es null. Si primero es null,
     * significa que la lista no contiene ningún nodo y se devuelve true. En caso
     * contrario, se devuelve false.
     * 
     * Precondiciones
     * 
     * La lista debe estar correctamente inicializada.
     * 
     * Postcondiciones
     * Devuelve true si la lista está vacía.
     * Devuelve false si contiene al menos un elemento.
     * La lista no se modifica.
     * El tamaño no cambia.
     */
    /* esVacio()

    si primero = nulo entonces
        devolver verdadero
    sino
        devolver falso
    fin si

    fin esVacio*/

    // verifica si la lista es vacia
    @Override
    public boolean esVacio() {
        // si la lista es vacia retorna true de una
        return primero == null;
    }

/*
 * Lenguaje natural
 * 
 * Se elimina la referencia al primer nodo de la lista asignando null a primero.
 * De esta forma, los nodos dejan de ser accesibles desde la lista. Luego se
 * establece el tamaño en 0.
 * 
 * En Java, los nodos que ya no tengan referencias serán posteriormente
 * liberados por el Garbage Collector, por lo que no necesitás eliminarlos uno
 * por uno.
 * 
 * Precondiciones
 * 
 * La lista debe estar correctamente inicializada. No importa si ya está vacía.
 * 
 * Postcondiciones
 * La lista queda vacía.
 * primero queda en null.
 * tamanio queda en 0.
 * Los elementos que estaban almacenados dejan de ser accesibles desde la lista.
 */
/*vaciar()

    primero <- nulo
    tamanio <- 0

fin vaciar */
    // para vaciar una lista, java permite apuntar el primero a null y luego
    // actualizo el tamanio
    @Override
    public void vaciar() {
        primero = null;
        tamanio = 0;
    }

}
