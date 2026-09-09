package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElemento;

public class ElementoABB<T> implements TDAElemento<T> {

    protected T dato;
    protected TDAElemento<T> hijoIzquierdo;
    protected TDAElemento<T> hijoDerecho;
    protected int altura;

    public ElementoABB(T dato) {
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.altura = 1;
    }
    
    @Override
    public void setHijoIzquierdo(TDAElemento<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    @Override
    public void setHijoDerecho(TDAElemento<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    @Override
    public TDAElemento<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    @Override
    public TDAElemento<T> getHijoDerecho() {
        return hijoDerecho;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Busca un nodo por un criterio de búsqueda.
     * Si no se encuentra, retorna nulo.
     */
    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) {

        TDAElemento<T> resultado = null;

        if (criterioBusqueda.compareTo(this.dato) == 0) {

            resultado = this;

        } else {

            if (criterioBusqueda.compareTo(this.dato) < 0) {

                // Lo que buscamos está a la izquierda
                if (hijoIzquierdo != null) {
                    resultado = hijoIzquierdo.buscar(criterioBusqueda);
                }

            } else {

                // Lo que buscamos está a la derecha
                if (hijoDerecho != null) {
                    resultado = hijoDerecho.buscar(criterioBusqueda);
                }
            }
        }

        return resultado;
    }

    /* ELMINAR EL NODO Y LO DEVUELVE */
    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {

        int comparacion = criterioBusqueda.compareTo(this.dato);

        if (comparacion < 0) {

            if (hijoIzquierdo != null) {
                hijoIzquierdo = hijoIzquierdo.eliminar(criterioBusqueda);
            }

            return this;

        } else if (comparacion > 0) {

            if (hijoDerecho != null) {
                hijoDerecho = hijoDerecho.eliminar(criterioBusqueda);
            }

            return this;
        }

        return quitarNodo();
    }

    private TDAElemento<T> quitarNodo() {

        // Caso 1: no tiene hijo izquierdo
        if (hijoIzquierdo == null) {
            return hijoDerecho;
        }

        // Caso 2: no tiene hijo derecho
        if (hijoDerecho == null) {
            return hijoIzquierdo;
        }

        // Caso 3: tiene los dos hijos
        TDAElemento<T> hijo = hijoIzquierdo;
        TDAElemento<T> padre = this;

        while (hijo.getHijoDerecho() != null) {
            padre = hijo;
            hijo = hijo.getHijoDerecho();
        }

        if (padre != this) {
            padre.setHijoDerecho(hijo.getHijoIzquierdo());
            hijo.setHijoIzquierdo(hijoIzquierdo);
        }

        hijo.setHijoDerecho(hijoDerecho);

        return hijo;
    }

    @Override
    /**
     * Agrega un nuevo elemento al árbol
     * Si el nuevoDato existe, no se agrega
     */
    public boolean insertar(T nuevoDato) {
        int comparacion = ((Comparable<T>) nuevoDato).compareTo(this.dato);
        // Comparamos el nuevo dato con el dato actual del nodo
        if (comparacion == 0) {
            return false; // El dato ya existe
        } else if (comparacion < 0) {
            if (hijoIzquierdo == null) {
                hijoIzquierdo = new ElementoABB<>(nuevoDato);
                return true;
            } else {
                return hijoIzquierdo.insertar(nuevoDato);
            }
            // Fin del caso izquierdo
        } else {
            if (hijoDerecho == null) {
                hijoDerecho = new ElementoABB<>(nuevoDato);
                return true;
            } else {
                return hijoDerecho.insertar(nuevoDato);
            }
            // Fin del caso derecho
        }
    }



    // recorrido in-order del árbol
    @Override
    public void inOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo != null) {
            hijoIzquierdo.inOrder(consumidor);
        }
        consumidor.accept(this);
        if (hijoDerecho != null) {
            hijoDerecho.inOrder(consumidor);
        }
    }

    // recorrido pre-order del árbol
    @Override
    public void preOrder(Consumer<TDAElemento<T>> consumidor) {
        consumidor.accept(this);
        if (hijoIzquierdo != null) {
            hijoIzquierdo.preOrder(consumidor);
        }
        if (hijoDerecho != null) {
            hijoDerecho.preOrder(consumidor);
        }
    }

    // recorrido post-order del árbol
    @Override
    public void postOrder(Consumer<TDAElemento<T>> consumidor) {
        if (hijoIzquierdo != null) {
            hijoIzquierdo.postOrder(consumidor);
        }
        if (hijoDerecho != null) {
            hijoDerecho.postOrder(consumidor);
        }
        consumidor.accept(this);
    }

    @Override
    /**
     * retornar true si el nodo es hoja
     */
    public boolean esHoja() {
        return hijoIzquierdo == null && hijoDerecho == null;
    }

    /**
     * retorna la cantidad de nodos que son hijas
     */
    @Override
    public int cantidadHojas() {
        int contador = 0;
        if (hijoIzquierdo != null) {
            contador += hijoIzquierdo.cantidadHojas();
        }
        if (hijoDerecho != null) {
            contador += hijoDerecho.cantidadHojas();
        }
        if (esHoja()) {
            contador++;
        }
        return contador;
    }

    @Override /**
               * retorna la cantidad de nodos que no son hojas
               */
    public int cantidadNodosInternos() {
        int contador = 0;
        // Contar los nodos internos de los subárboles izquierdo y derecho
        if (hijoIzquierdo != null) {
            contador += hijoIzquierdo.cantidadNodosInternos();
        }
        // Contar los nodos internos del subárbol derecho si existe
        if (hijoDerecho != null) {
            contador += hijoDerecho.cantidadNodosInternos();
        }
        // Contar el nodo actual si no es hoja
        if (!esHoja()) {
            contador++;
        }
        return contador;
    }

    @Override
    /**
     * retorna la cantidad de nodos que los compone
     */
    public int cantidadNodos() {
        int contador = 1; // Contar el nodo actual

        if (hijoIzquierdo != null) {
            contador += hijoIzquierdo.cantidadNodos();
        }
        if (hijoDerecho != null) {
            contador += hijoDerecho.cantidadNodos();
        }
        return contador;
    }

    @Override
    public int altura() {
        // Retornar la altura almacenada (actualizada por actualizarAltura)
        return this.altura;
    }

    @Override
    /**
     * retornar el nivel relativo del nodo que coincide con el criterio de búsqueda
     * si no se encuentra, retorna -1
     */
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        if (criterioBusqueda.compareTo((T) this.dato) == 0) {
            return 0;
        }
        int nivel = -1;
        if (hijoIzquierdo != null) {
            nivel = hijoIzquierdo.obtenerNivel(criterioBusqueda);
        }
        if (nivel == -1 && hijoDerecho != null) {
            nivel = hijoDerecho.obtenerNivel(criterioBusqueda);
        }
        return (nivel == -1) ? -1 : nivel + 1;
    }
    
    public int ObtenerTamanio() {
        return cantidadNodos();
    }
    //ejercicio 11
    public T menorClave() {
        TDAElemento<T> actual = this;

        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }

        return actual.getDato();
    }

    public T mayorClave() {
        TDAElemento<T> actual = this;

        while (actual.getHijoDerecho() != null) {
            actual = actual.getHijoDerecho();
        }

        return actual.getDato();
    }

    @Override
    public T claveAnterior(Comparable<T> criterio) {
        return claveAnteriorRec(this, criterio, null);
    }

    private T claveAnteriorRec(TDAElemento<T> nodo, Comparable<T> criterio, T anterior) {
        if (nodo == null) {
            return anterior;
        }

        int comparacion = criterio.compareTo(nodo.getDato());

        if (comparacion < 0) {
            return claveAnteriorRec(nodo.getHijoIzquierdo(), criterio, anterior);
        }

        if (comparacion > 0) {
            return claveAnteriorRec(nodo.getHijoDerecho(), criterio, nodo.getDato());
        }

        if (nodo.getHijoIzquierdo() != null) {
            return ((ElementoABB<T>) nodo.getHijoIzquierdo()).mayorClave();
        }

        return anterior;
    }

    @Override
    public int cantidadNodosEnNivel(int nivel) {
        if (nivel < 0) {
            return 0;
        }

        if (nivel == 0) {
            return 1;
        }

        int izquierda = 0;
        int derecha = 0;

        if (hijoIzquierdo != null) {
            izquierda = hijoIzquierdo.cantidadNodosEnNivel(nivel - 1);
        }

        if (hijoDerecho != null) {
            derecha = hijoDerecho.cantidadNodosEnNivel(nivel - 1);
        }

        return izquierda + derecha;
    }

    @Override
    public void listarHojasConNivel(java.util.function.BiConsumer<T, Integer> consumidor, int nivel) {
        if (consumidor == null) {
            return;
        }

        if (hijoIzquierdo == null && hijoDerecho == null) {
            consumidor.accept(dato, nivel);
            return;
        }

        if (hijoIzquierdo != null) {
            hijoIzquierdo.listarHojasConNivel(consumidor, nivel + 1);
        }

        if (hijoDerecho != null) {
            hijoDerecho.listarHojasConNivel(consumidor, nivel + 1);
        }
    }

    public void actualizarAltura() {
    int alturaIzquierda = (hijoIzquierdo != null) ? hijoIzquierdo.altura() : 0;
    int alturaDerecha = (hijoDerecho != null) ? hijoDerecho.altura() : 0;

    this.altura = 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    @Override
    public boolean esArbolDeBusqueda(T minimo, T maximo) {
        if (dato == null) {
            return true;
        }
        

        Comparable<T> comparable = (Comparable<T>) dato;

        if (minimo != null && comparable.compareTo(minimo) <= 0) {
            return false;
        }

        if (maximo != null && comparable.compareTo(maximo) >= 0) {
            return false;
        }

        boolean izquierdaValida = true;
        boolean derechaValida = true;

        if (hijoIzquierdo != null) {
            izquierdaValida = hijoIzquierdo.esArbolDeBusqueda(minimo, dato);
        }

        if (hijoDerecho != null) {
            derechaValida = hijoDerecho.esArbolDeBusqueda(dato, maximo);
        }

        return izquierdaValida && derechaValida;
    }
}
