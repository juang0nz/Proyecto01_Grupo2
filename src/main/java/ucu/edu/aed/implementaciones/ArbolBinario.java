package ucu.edu.aed.implementaciones;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolBinario;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class ArbolBinario <T> implements TDAArbolBinario <T>{

    private TDAElemento<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public TDAElemento<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(TDAElemento<T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public T buscar(Comparable<T> predicate) {
        if (raiz == null) {
            return null;
        }
        TDAElemento<T> encontrado = raiz.buscar(predicate);
        return encontrado == null ? null : encontrado.getDato();
    }

    @Override
    public TDAElemento<T> obtenerRaiz() {
        return raiz;
    }

    @Override
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        if (raiz == null) {
            return false;
        }

        TDAElemento<T> encontrado = raiz.buscar(criterioBusqueda);
        if (encontrado == null) {
            return false;
        }

        raiz = raiz.eliminar(criterioBusqueda);
        return true;
    }

    @Override
    public boolean insertar(Comparable<T> dato) {
        if (raiz == null) {
            raiz = new ElementoAB<>((T) dato);
            return true;
        }
        return raiz.insertar((T) dato);
    }

    @Override
    public void inOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.inOrder(elemento -> consumidor.accept(elemento.getDato()));
        }
    }

    @Override
    public void preOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.preOrder(elemento -> consumidor.accept(elemento.getDato()));
        }
    }

    @Override
    public void postOrder(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.postOrder(elemento -> consumidor.accept(elemento.getDato()));
        }
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public int cantidadNodos() {
        if (raiz == null) {
            return 0;
        }
        return raiz.cantidadNodos();
    }

    @Override
    public int cantidadHojas() {
        if (raiz == null) {
            return 0;
        }
        return raiz.cantidadHojas();
    }

    @Override
    public int cantidadNodosInternos() {
        if (raiz == null) {
            return 0;
        }
        return raiz.cantidadNodosInternos();
    }

    @Override
    public int altura() {
        if (raiz == null) {
            return 0;
        }
        return raiz.altura();
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
        if (raiz == null) {
            return new TDAListaConArregloImpl<>();
        }
        return ((ElementoAB<T>) raiz).completos();
    }

    public TDALista<TDAElemento<T>> enNivel(int nivel) {
        if (raiz == null) {
            return new TDAListaConArregloImpl<>();
        }
        return ((ElementoAB<T>) raiz).enNivel(nivel);
    }

    @Override
    public int cantidadNodosEnNivel(int nivel) {
        if (raiz == null) {
            return 0;
        }
        return raiz.cantidadNodosEnNivel(nivel);
    }

    @Override
    public void listarHojasConNivel(BiConsumer<T, Integer> consumidor) {
        if (raiz != null && consumidor != null) {
            raiz.listarHojasConNivel(consumidor, 0);
        }
    }

    @Override
    public boolean esArbolDeBusqueda() {
        if (raiz == null) {
            return true;
        }
        return raiz.esArbolDeBusqueda(null, null);
    }

}
