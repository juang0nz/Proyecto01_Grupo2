package ucu.edu.aed.implementaciones;

import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAArbolNario;
import ucu.edu.aed.tda.TDACola;
import ucu.edu.aed.tda.TDAElementoNario;
import ucu.edu.aed.tda.TDALista;


public class ArbolNario<T> implements TDAArbolNario<T> {

    private TDAElementoNario<T> raiz;

    public ArbolNario() {
        raiz = null;
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public TDAElementoNario<T> obtenerRaiz() {
        return raiz;
    }

    @Override
    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new ElementoNario<>(dato);
        } else {
            raiz.agregarHijo(new ElementoNario<>(dato));
        }
    }

    @Override
    public T buscar(Comparable<T> criterio) {
        if (raiz == null) {
            return null;
        }
        TDAElementoNario<T> resultado = raiz.buscar(criterio);
        return resultado != null ? resultado.getDato() : null;
    }

    @Override
    public void preOrden(Consumer<T> accion) {
        if (raiz != null) {
            raiz.preOrden(accion);
        }
    }
    public void eliminar (Comparable<T> criterio) {
        if (raiz != null) {
            if (raiz.getDato() != null && criterio.compareTo(raiz.getDato()) == 0) {
                raiz = null;
            } else {
                raiz.eliminarHijo(criterio);
            }
        }
    }
    @Override
    public void postOrden(Consumer<T> accion) {
        if (raiz != null) {
            raiz.postOrden(accion);
        }
    }

    @Override
    public void porNiveles(Consumer<T> accion) {

        if (raiz == null) {
            return;
        }
        TDACola<TDAElementoNario<T>> cola = new TDAColaImpl<>();
        cola.agregar(raiz);
        while (!cola.esVacio()) {
            TDAElementoNario<T> actual = cola.quitaDeCola();
            accion.accept(actual.getDato());
            TDALista<TDAElementoNario<T>> hijos = actual.getHijos();
            for (int i = 0; i < hijos.tamanio(); i++) {
                cola.agregar(hijos.obtener(i));
            }
        }
    }

    public void agregarHijo(Comparable<T> criterioPadre, T dato) {
        if (raiz == null) {
            raiz = new ElementoNario<>(dato);
        } else {
            TDAElementoNario<T> padre = raiz.buscar(criterioPadre);
            if (padre != null) {
                padre.agregarHijo(new ElementoNario<>(dato));
            }
        }
    }

//seteo la raiz del árbol con el dato proporcionado
    public void setRaiz(T dato) {
        this.raiz = new ElementoNario<>(dato);
    }

    public void mostrarEstructura() {
        if (raiz != null) {
            mostrarEstructura(raiz, 0);
    }
}

private void mostrarEstructura(TDAElementoNario<T> nodo, int nivel) {

    // Pongo espacios dependiendo de la profundidad
    for (int i = 0; i < nivel; i++) {
        System.out.print("   ");
    }

    // Muestro el dato de este nodo
    System.out.println("- " + nodo.getDato().toString());

    // Obtengo sus hijos
    TDALista<TDAElementoNario<T>> hijos = nodo.getHijos();

    // Hago lo mismo para cada hijo
    for (int i = 0; i < hijos.tamanio(); i++) {
        mostrarEstructura(hijos.obtener(i), nivel + 1);
    }
}

}