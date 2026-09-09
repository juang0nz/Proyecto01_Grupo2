package ucu.edu.aed.implementaciones;


public class ElementoAVL<T> extends ElementoABB<T> {

    public ElementoAVL(T dato) {
        super(dato);
    }

    /*
     * Factor de balance:
     *
     * altura(izquierda) - altura(derecha)
     */
    public int factorBalance() {

        int alturaIzq = 0;
        int alturaDer = 0;

        if (getHijoIzquierdo() != null) {
            alturaIzq = getHijoIzquierdo().altura();
        }

        if (getHijoDerecho() != null) {
            alturaDer = getHijoDerecho().altura();
        }

        return alturaIzq - alturaDer;
    }

    /*
     * CASO 1 - LL
     *
     * Rotación simple hacia la derecha.
     *
     * k2 k1
     * / / \
     * k1 -> A k2
     * / \ /
     * A B B
     */
    public ElementoAVL<T> rotacionLL() {

        ElementoAVL<T> k2 = this;

        ElementoAVL<T> k1 = (ElementoAVL<T>) k2.getHijoIzquierdo();

        k2.setHijoIzquierdo(k1.getHijoDerecho());

        k1.setHijoDerecho(k2);

        return k1;
    }

    /*
     * CASO 4 - RR
     *
     * Rotación simple hacia la izquierda.
     *
     * k1 k2
     * \ / \
     * k2 -> k1 C
     * / \ \
     * B C B
     */
    public ElementoAVL<T> rotacionRR() {

        ElementoAVL<T> k1 = this;

        ElementoAVL<T> k2 = (ElementoAVL<T>) k1.getHijoDerecho();

        k1.setHijoDerecho(k2.getHijoIzquierdo());

        k2.setHijoIzquierdo(k1);

        return k2;
    }

    /*
     * CASO 2 - LR
     *
     * Primero RR sobre el hijo izquierdo.
     * Después LL sobre el nodo actual.
     */
    public ElementoAVL<T> rotacionLR() {

        ElementoAVL<T> hijoIzq = (ElementoAVL<T>) getHijoIzquierdo();

        setHijoIzquierdo(
                hijoIzq.rotacionRR());

        return rotacionLL();
    }

    /*
     * CASO 3 - RL
     *
     * Primero LL sobre el hijo derecho.
     * Después RR sobre el nodo actual.
     */
    public ElementoAVL<T> rotacionRL() {

        ElementoAVL<T> hijoDer = (ElementoAVL<T>) getHijoDerecho();

        setHijoDerecho(
                hijoDer.rotacionLL());

        return rotacionRR();
    }
    
}