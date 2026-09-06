package ucu.edu.aed.implementaciones;



public class AVL<T> extends ABB<T> {

    public AVL() {
        super();
    }

    /*
     * INSERTAR
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean insertar(Comparable<T> dato) {

        /*
         * La interfaz que nos dieron recibe Comparable<T>,
         * mientras que los nodos guardan T.
         */
        T nuevoDato = (T) dato;

        /*
         * Si está vacío, creamos directamente la raíz AVL.
         */
        if (raiz == null) {

            raiz = new ElementoAVL<T>(nuevoDato);

            return true;
        }

        /*
         * Primero verificamos si ya existe.
         */
        Comparable<T> criterioBusqueda = elemento -> ((Comparable<T>) nuevoDato)
                .compareTo(elemento);

        if (raiz.buscar(criterioBusqueda) != null) {
            return false;
        }

        /*
         * Insertamos recursivamente y actualizamos la raíz,
         * porque una rotación puede cambiarla.
         */
        raiz = insertarAVL(
                (ElementoAVL<T>) raiz,
                nuevoDato);

        return true;
    }

    /*
     * INSERCIÓN RECURSIVA AVL
     */
    @SuppressWarnings("unchecked")
    private ElementoAVL<T> insertarAVL(
            ElementoAVL<T> nodo,
            T dato) {

        Comparable<T> comparable = (Comparable<T>) dato;

        int comparacion = comparable.compareTo(nodo.getDato());

        /*
         * IZQUIERDA
         */
        if (comparacion < 0) {

            if (nodo.getHijoIzquierdo() == null) {

                nodo.setHijoIzquierdo(
                        new ElementoAVL<T>(dato));

            } else {

                ElementoAVL<T> hijoIzq = (ElementoAVL<T>) nodo.getHijoIzquierdo();

                nodo.setHijoIzquierdo(
                        insertarAVL(hijoIzq, dato));
            }
        }

        /*
         * DERECHA
         */
        else if (comparacion > 0) {

            if (nodo.getHijoDerecho() == null) {

                nodo.setHijoDerecho(
                        new ElementoAVL<T>(dato));

            } else {

                ElementoAVL<T> hijoDer = (ElementoAVL<T>) nodo.getHijoDerecho();

                nodo.setHijoDerecho(
                        insertarAVL(hijoDer, dato));
            }
        }

        /*
         * Cuando volvemos de la recursión,
         * revisamos si este nodo quedó desbalanceado.
         */
        return balancear(nodo);
    }

    /*
     * BALANCEAR
     */
    private ElementoAVL<T> balancear(
            ElementoAVL<T> nodo) {

        int balance = nodo.factorBalance();

        /*
         * MUY CARGADO A LA IZQUIERDA
         */
        if (balance > 1) {

            ElementoAVL<T> hijoIzq = (ElementoAVL<T>) nodo.getHijoIzquierdo();

            /*
             * CASO LL
             */
            if (hijoIzq.factorBalance() >= 0) {

                return nodo.rotacionLL();
            }

            /*
             * CASO LR
             */
            else {

                return nodo.rotacionLR();
            }
        }

        /*
         * MUY CARGADO A LA DERECHA
         */
        if (balance < -1) {

            ElementoAVL<T> hijoDer = (ElementoAVL<T>) nodo.getHijoDerecho();

            /*
             * CASO RR
             */
            if (hijoDer.factorBalance() <= 0) {

                return nodo.rotacionRR();
            }

            /*
             * CASO RL
             */
            else {

                return nodo.rotacionRL();
            }
        }

        /*
         * Si está balanceado no hacemos nada.
         */
        return nodo;
    }

    /*
     * ELIMINAR
     */
    @Override
    public boolean eliminar(
            Comparable<T> criterioBusqueda) {

        if (raiz == null) {
            return false;
        }

        /*
         * Verificamos primero que exista.
         */
        if (raiz.buscar(criterioBusqueda) == null) {
            return false;
        }

        /*
         * Eliminamos y actualizamos la raíz,
         * porque una eliminación + rotación
         * puede cambiarla.
         */
        raiz = eliminarAVL(
                (ElementoAVL<T>) raiz,
                criterioBusqueda);

        return true;
    }

    /*
     * ELIMINACIÓN RECURSIVA AVL
     */
    @SuppressWarnings("unchecked")
    private ElementoAVL<T> eliminarAVL(
            ElementoAVL<T> nodo,
            Comparable<T> criterioBusqueda) {

        if (nodo == null) {
            return null;
        }

        int comparacion = criterioBusqueda.compareTo(
                nodo.getDato());

        /*
         * BUSCAMOS A LA IZQUIERDA
         */
        if (comparacion < 0) {

            nodo.setHijoIzquierdo(
                    eliminarAVL(
                            (ElementoAVL<T>) nodo.getHijoIzquierdo(),
                            criterioBusqueda));
        }

        /*
         * BUSCAMOS A LA DERECHA
         */
        else if (comparacion > 0) {

            nodo.setHijoDerecho(
                    eliminarAVL(
                            (ElementoAVL<T>) nodo.getHijoDerecho(),
                            criterioBusqueda));
        }

        /*
         * ENCONTRAMOS EL NODO
         */
        else {

            /*
             * CASO 1:
             * no tiene hijo izquierdo.
             */
            if (nodo.getHijoIzquierdo() == null) {

                return (ElementoAVL<T>) nodo.getHijoDerecho();
            }

            /*
             * CASO 2:
             * no tiene hijo derecho.
             */
            if (nodo.getHijoDerecho() == null) {

                return (ElementoAVL<T>) nodo.getHijoIzquierdo();
            }

            /*
             * CASO 3:
             * tiene los dos hijos.
             *
             * Buscamos el mayor del
             * subárbol izquierdo.
             */
            ElementoAVL<T> mayor = buscarMayor(
                    (ElementoAVL<T>) nodo.getHijoIzquierdo());

            /*
             * Copiamos el dato del mayor
             * al nodo que queremos eliminar.
             */
            nodo.setDato(mayor.getDato());

            /*
             * Ahora eliminamos ese mayor
             * del subárbol izquierdo.
             */
            T datoMayor = mayor.getDato();

            Comparable<T> criterioMayor = elemento -> ((Comparable<T>) datoMayor)
                    .compareTo(elemento);

            nodo.setHijoIzquierdo(
                    eliminarAVL(
                            (ElementoAVL<T>) nodo.getHijoIzquierdo(),
                            criterioMayor));
        }

        /*
         * IMPORTANTE:
         *
         * Al volver de la eliminación,
         * rebalanceamos.
         */
        return balancear(nodo);
    }

    /*
     * BUSCAR MAYOR
     *
     * Se usa cuando eliminamos un nodo
     * que tiene dos hijos.
     */
    private ElementoAVL<T> buscarMayor(
            ElementoAVL<T> nodo) {

        ElementoAVL<T> actual = nodo;

        while (actual.getHijoDerecho() != null) {

            actual = (ElementoAVL<T>) actual.getHijoDerecho();
        }

        return actual;
    }
}