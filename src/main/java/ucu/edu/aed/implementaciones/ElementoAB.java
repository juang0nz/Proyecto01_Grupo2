package ucu.edu.aed.implementaciones;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class ElementoAB <T> implements TDAElemento<T> {

    public ElementoAB() {
        this(null);
    }

    public ElementoAB(T dato) {
        this.dato = dato;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    private T dato;
    private TDAElemento<T> hijoIzquierdo;
    private TDAElemento<T> hijoDerecho;

    @Override
    public int altura() {

        int izquierda = 0;
        int derecha = 0;

        if (hijoIzquierdo != null) {
            izquierda = hijoIzquierdo.altura();
        }

        if (hijoDerecho != null) {
            derecha = hijoDerecho.altura();
        }

        return 1 + Math.max(izquierda, derecha);
    }


    @Override
    public int cantidadNodos() {

        int izquierda = 0;
        int derecha = 0;

        if (hijoIzquierdo != null) {
            izquierda = hijoIzquierdo.cantidadNodos();
        }

        if (hijoDerecho != null) {
            derecha = hijoDerecho.cantidadNodos();
        }

        return 1 + izquierda + derecha;
    }

    @Override
    public int cantidadHojas() {

        if (hijoIzquierdo == null && hijoDerecho == null) {
            return 1;
        }

        int izquierda = 0;
        int derecha = 0;

        if (hijoIzquierdo != null) {
            izquierda = hijoIzquierdo.cantidadHojas();
        }

        if (hijoDerecho != null) {
            derecha = hijoDerecho.cantidadHojas();
        }

        return izquierda + derecha;
    }

    @Override
    public int cantidadNodosInternos() {

        if (hijoIzquierdo == null && hijoDerecho == null) {
            return 0;
        }

        int izquierda = 0;
        int derecha = 0;

        if (hijoIzquierdo != null) {
            izquierda = hijoIzquierdo.cantidadNodosInternos();
        }

        if (hijoDerecho != null) {
            derecha = hijoDerecho.cantidadNodosInternos();
        }

        return 1 + izquierda + derecha;
    }

    public int tamanio() {
        return cantidadNodos();
    }

    public int hojas() {
        return cantidadHojas();
    }

    public int internos() {
        return cantidadNodosInternos();
    }

    public TDALista<TDAElemento<T>> completos() {
        TDALista<TDAElemento<T>> resultado = new TDAListaConArregloImpl<>();
        completosRecursivo(this, resultado);
        return resultado;
    }
// Métodos auxiliares para obtener los nodos completos y los nodos en un nivel específico.
    private void completosRecursivo(TDAElemento<T> nodo, TDALista<TDAElemento<T>> resultado) {
        if (nodo == null) {
            return;
        }

        if (nodo.getHijoIzquierdo() != null && nodo.getHijoDerecho() != null) {
            resultado.agregar(nodo);
        }
// completosRecursivo es un método auxiliar que recorre el árbol en busca de nodos completos (con ambos hijos).
        completosRecursivo(nodo.getHijoIzquierdo(), resultado);
        completosRecursivo(nodo.getHijoDerecho(), resultado);
    }
// enNivelRecursivo es un método auxiliar que recorre el árbol en busca de nodos en un nivel específico.
    public TDALista<TDAElemento<T>> enNivel(int nivel) {
        TDALista<TDAElemento<T>> resultado = new TDAListaConArregloImpl<>();
        enNivelRecursivo(this, nivel, resultado);
        return resultado;
    }
// enNivel es un método que devuelve una lista de nodos en un nivel específico del árbol.
    private void enNivelRecursivo(TDAElemento<T> nodo, int nivel, TDALista<TDAElemento<T>> resultado) {
        if (nodo == null) {
            return;
        }

        if (nivel == 0) {
            resultado.agregar(nodo);
            return;
        }

        enNivelRecursivo(nodo.getHijoIzquierdo(), nivel - 1, resultado);
        enNivelRecursivo(nodo.getHijoDerecho(), nivel - 1, resultado);
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
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public TDAElemento<T> buscar(Comparable<T> criterioBusqueda) {
        return null;
    }

    @Override
    public TDAElemento<T> eliminar(Comparable<T> criterioBusqueda) {
        return null;
    }

    @Override
    public boolean insertar(T nuevoDato) {
        return false;
    }

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
    public boolean esHoja() {
        return hijoIzquierdo == null && hijoDerecho == null;
    }

    @Override
    public int obtenerNivel(Comparable<T> criterioBusqueda) {
        return -1;
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
    public void listarHojasConNivel(BiConsumer<T, Integer> consumidor, int nivel) {
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

    @Override
    public T menorClave() {
        TDAElemento<T> actual = this;

        while (actual.getHijoIzquierdo() != null) {
            actual = actual.getHijoIzquierdo();
        }

        return actual.getDato();
    }

    @Override
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
            return nodo.getHijoIzquierdo().mayorClave();
        }

        return anterior;
    }
}