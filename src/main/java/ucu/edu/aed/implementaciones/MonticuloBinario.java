package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDAColaPrioridad;

public class MonticuloBinario<T>
        implements TDAColaPrioridad<ElementoPrioridad<T>> {

    private ElementoPrioridad<T>[] elementos;
    private int tamanio;

    /**
     * Constructor del montículo.
     *
     * @param capacidadInicial capacidad inicial del arreglo
     */
    @SuppressWarnings("unchecked")
    public MonticuloBinario(int capacidadInicial) {

        elementos = (ElementoPrioridad<T>[])
                new ElementoPrioridad[capacidadInicial];

        tamanio = 0;
    }

    /**
     * Inserta un elemento en el montículo.
     *
     * Primero se agrega al final y luego se lo hace "flotar"
     * hasta recuperar la propiedad del montículo.
     */
    @Override
    public boolean insertar(ElementoPrioridad<T> dato) {

        if (dato == null) {
            return false;
        }

        if (tamanio == elementos.length) {
            ampliar();
        }

        // Se agrega al final del montículo.
        elementos[tamanio] = dato;

        // Recuperamos la propiedad del heap.
        flotar(tamanio);

        tamanio++;

        return true;
    }

    /**
     * Retorna el elemento de mayor prioridad sin eliminarlo.
     *
     * En un montículo, el elemento prioritario siempre está
     * ubicado en la raíz (posición 0).
     */
    @Override
    public ElementoPrioridad<T> frente() {

        if (esVacia()) {
            throw new NoSuchElementException(
                    "El montículo está vacío");
        }

        return elementos[0];
    }

    /**
     * Elimina y retorna el elemento de mayor prioridad.
     */
    @Override
    public ElementoPrioridad<T> quitar() {

        if (esVacia()) {
            throw new NoSuchElementException(
                    "El montículo está vacío");
        }

        // Guardamos el elemento que vamos a devolver.
        ElementoPrioridad<T> eliminado = elementos[0];

        // Reducimos el tamaño.
        tamanio--;

        /*
         * El último elemento pasa a ocupar temporalmente
         * la raíz.
         */
        elementos[0] = elementos[tamanio];

        // Limpiamos la última posición.
        elementos[tamanio] = null;

        /*
         * Si todavía quedan elementos, hacemos descender
         * la nueva raíz hasta recuperar el orden.
         */
        if (tamanio > 0) {
            hundir(0);
        }

        return eliminado;
    }

    /**
     * Indica si el montículo está vacío.
     */
    @Override
    public boolean esVacia() {
        return tamanio == 0;
    }

    /**
     * Retorna la cantidad de elementos almacenados.
     */
    @Override
    public int tamanio() {
        return tamanio;
    }

    // =========================================================
    // OPERACIONES INTERNAS DEL MONTÍCULO
    // =========================================================

    /**
     * Hace subir un elemento mientras tenga mayor prioridad
     * que su padre.
     */
    private void flotar(int posicion) {

        int actual = posicion;

        while (actual > 0) {

            int padre = (actual - 1) / 2;

            if (tieneMayorPrioridad(
                    elementos[actual],
                    elementos[padre])) {

                intercambiar(actual, padre);

                actual = padre;

            } else {

                // Ya está correctamente ubicado.
                break;
            }
        }
    }

    /**
     * Hace bajar un elemento hasta recuperar
     * la propiedad del montículo.
     */
    private void hundir(int posicion) {

        int actual = posicion;

        while (true) {

            int hijoIzquierdo = 2 * actual + 1;
            int hijoDerecho = 2 * actual + 2;

            int mejor = actual;

            /*
             * Comparamos con el hijo izquierdo.
             */
            if (hijoIzquierdo < tamanio
                    && tieneMayorPrioridad(
                            elementos[hijoIzquierdo],
                            elementos[mejor])) {

                mejor = hijoIzquierdo;
            }

            /*
             * Comparamos con el hijo derecho.
             */
            if (hijoDerecho < tamanio
                    && tieneMayorPrioridad(
                            elementos[hijoDerecho],
                            elementos[mejor])) {

                mejor = hijoDerecho;
            }

            /*
             * Si el elemento actual ya tiene mayor prioridad
             * que sus hijos, terminamos.
             */
            if (mejor == actual) {
                break;
            }

            intercambiar(actual, mejor);

            actual = mejor;
        }
    }

    /**
     * Determina cuál de dos elementos debe aparecer primero
     * en la cola de prioridad.
     *
     * En esta implementación:
     *
     * prioridad 1 > prioridad 2 > prioridad 3
     *
     * Es decir, cuanto menor sea el número,
     * mayor es la prioridad.
     *
     * Si ambos tienen la misma prioridad,
     * se atiende primero al que llegó antes.
     */
    private boolean tieneMayorPrioridad(
            ElementoPrioridad<T> a,
            ElementoPrioridad<T> b) {

        if (a.getPrioridad() < b.getPrioridad()) {
            return true;
        }

        if (a.getPrioridad() > b.getPrioridad()) {
            return false;
        }

        // Misma prioridad: respetamos orden de llegada.
        return a.getOrdenLlegada() < b.getOrdenLlegada();
    }

    /**
     * Intercambia dos posiciones del arreglo.
     */
    private void intercambiar(int posicionA, int posicionB) {

        ElementoPrioridad<T> auxiliar =
                elementos[posicionA];

        elementos[posicionA] =
                elementos[posicionB];

        elementos[posicionB] =
                auxiliar;
    }

    /**
     * Duplica la capacidad del arreglo cuando se llena.
     */
    @SuppressWarnings("unchecked")
    private void ampliar() {

        int nuevaCapacidad;

        if (elementos.length == 0) {
            nuevaCapacidad = 1;
        } else {
            nuevaCapacidad = elementos.length * 2;
        }

        ElementoPrioridad<T>[] nuevo =
                (ElementoPrioridad<T>[])
                        new ElementoPrioridad[nuevaCapacidad];

        for (int i = 0; i < tamanio; i++) {
            nuevo[i] = elementos[i];
        }

        elementos = nuevo;
    }
}
