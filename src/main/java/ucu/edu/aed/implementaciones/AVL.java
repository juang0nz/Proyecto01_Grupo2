package ucu.edu.aed.implementaciones;

import ucu.edu.aed.tda.TDAElemento;


public class AVL<T> extends ABB<T> {

    public AVL() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean insertar(Comparable<T> dato) {

        T nuevoDato = (T) dato;

        if (raiz == null) {
            raiz = new ElementoABB<T>(nuevoDato);
            return true;
        }

        Comparable<T> criterioBusqueda = elemento -> ((Comparable<T>) nuevoDato)
                .compareTo(elemento);

        if (raiz.buscar(criterioBusqueda) != null) {
            return false;
        }

        raiz = insertarAVL((ElementoABB<T>) raiz, nuevoDato);

        return true;
    }

    @SuppressWarnings("unchecked")
    private ElementoABB<T> insertarAVL(ElementoABB<T> nodo, T dato) {

        if (nodo == null) {
            return new ElementoABB<>(dato);
        }

        Comparable<T> comparable = (Comparable<T>) dato;

        int comparacion = comparable.compareTo(nodo.getDato());

        if (comparacion < 0) {
            nodo.setHijoIzquierdo(insertarAVL((ElementoABB<T>) nodo.getHijoIzquierdo(), dato));
        } else if (comparacion > 0) {
            nodo.setHijoDerecho(insertarAVL((ElementoABB<T>) nodo.getHijoDerecho(), dato));
        } else {
            return nodo;
        }

        nodo.actualizarAltura();
        return balancear(nodo);
    }

    private ElementoABB<T> balancear(ElementoABB<T> nodo) {
        if (nodo == null) {
            return null;
        }

        int alturaIzq = (nodo.getHijoIzquierdo() != null) ? nodo.getHijoIzquierdo().altura() : 0;
        int alturaDer = (nodo.getHijoDerecho() != null) ? nodo.getHijoDerecho().altura() : 0;
        int balance = alturaIzq - alturaDer;

        ElementoABB<T> left = (ElementoABB<T>) nodo.getHijoIzquierdo();
        ElementoABB<T> right = (ElementoABB<T>) nodo.getHijoDerecho();

        if (balance > 1) {
            int leftLeftHeight = (left != null && left.getHijoIzquierdo() != null) ? left.getHijoIzquierdo().altura() : 0;
            int leftRightHeight = (left != null && left.getHijoDerecho() != null) ? left.getHijoDerecho().altura() : 0;

            if (leftLeftHeight >= leftRightHeight) {
                return rotacionLL(nodo);
            }

            nodo.setHijoIzquierdo(rotacionRR(left));
            return rotacionLL(nodo);
        }

        if (balance < -1) {
            int rightLeftHeight = (right != null && right.getHijoIzquierdo() != null) ? right.getHijoIzquierdo().altura() : 0;
            int rightRightHeight = (right != null && right.getHijoDerecho() != null) ? right.getHijoDerecho().altura() : 0;

            if (rightRightHeight >= rightLeftHeight) {
                return rotacionRR(nodo);
            }

            nodo.setHijoDerecho(rotacionLL(right));
            return rotacionRR(nodo);
        }

        return nodo;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean eliminar(Comparable<T> criterioBusqueda) {
        if (raiz == null) {
            return false;
        }

        TDAElemento<T> encontrado = raiz.buscar(criterioBusqueda);
        if (encontrado == null) {
            return false;
        }

        raiz = eliminarAVL((ElementoABB<T>) raiz, criterioBusqueda);
        return true;
    }

    @SuppressWarnings("unchecked")
    private ElementoABB<T> eliminarAVL(ElementoABB<T> nodo, Comparable<T> criterioBusqueda) {
        if (nodo == null) {
            return null;
        }

        int comparacion = criterioBusqueda.compareTo(nodo.getDato());

        if (comparacion < 0) {
            nodo.setHijoIzquierdo(eliminarAVL((ElementoABB<T>) nodo.getHijoIzquierdo(), criterioBusqueda));
        } else if (comparacion > 0) {
            nodo.setHijoDerecho(eliminarAVL((ElementoABB<T>) nodo.getHijoDerecho(), criterioBusqueda));
        } else {
            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                return null;
            }
            if (nodo.getHijoIzquierdo() == null) {
                return (ElementoABB<T>) nodo.getHijoDerecho();
            }
            if (nodo.getHijoDerecho() == null) {
                return (ElementoABB<T>) nodo.getHijoIzquierdo();
            }

            ElementoABB<T> mayor = buscarMayor((ElementoABB<T>) nodo.getHijoIzquierdo());
            nodo.setDato(mayor.getDato());
            nodo.setHijoIzquierdo(eliminarAVL((ElementoABB<T>) nodo.getHijoIzquierdo(), (Comparable<T>) mayor.getDato()));
        }

        if (nodo != null) {
            nodo.actualizarAltura();
            return balancear(nodo);
        }

        return null;
    }

    private ElementoABB<T> buscarMayor(ElementoABB<T> nodo) {
        while (nodo.getHijoDerecho() != null) {
            nodo = (ElementoABB<T>) nodo.getHijoDerecho();
        }
        return nodo;
    }

    private ElementoABB<T> rotacionLL(ElementoABB<T> k2) {
        ElementoABB<T> k1 = (ElementoABB<T>) k2.getHijoIzquierdo();
        k2.setHijoIzquierdo(k1.getHijoDerecho());
        k1.setHijoDerecho(k2);

        k2.actualizarAltura();
        k1.actualizarAltura();

        return k1;
    }

    private ElementoABB<T> rotacionRR(ElementoABB<T> k1) {
        ElementoABB<T> k2 = (ElementoABB<T>) k1.getHijoDerecho();
        k1.setHijoDerecho(k2.getHijoIzquierdo());
        k2.setHijoIzquierdo(k1);

        k1.actualizarAltura();
        k2.actualizarAltura();

        return k2;
    }

}