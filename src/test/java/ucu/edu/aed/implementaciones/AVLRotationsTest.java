package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

public class AVLRotationsTest extends TestCase {

    public void testLLRotation() {
        AVL<Integer> avl = new AVL<>();
        avl.insertar(3);
        avl.insertar(2);
        avl.insertar(1); // should trigger LL rotation

        Integer root = (Integer) avl.obtenerRaiz().getDato();
        Integer left = (Integer) avl.obtenerRaiz().getHijoIzquierdo().getDato();
        Integer right = (Integer) avl.obtenerRaiz().getHijoDerecho().getDato();

        assertEquals(Integer.valueOf(2), root);
        assertEquals(Integer.valueOf(1), left);
        assertEquals(Integer.valueOf(3), right);
    }

    public void testRRRotation() {
        AVL<Integer> avl = new AVL<>();
        avl.insertar(1);
        avl.insertar(2);
        avl.insertar(3); // should trigger RR rotation

        Integer root = (Integer) avl.obtenerRaiz().getDato();
        Integer left = (Integer) avl.obtenerRaiz().getHijoIzquierdo().getDato();
        Integer right = (Integer) avl.obtenerRaiz().getHijoDerecho().getDato();

        assertEquals(Integer.valueOf(2), root);
        assertEquals(Integer.valueOf(1), left);
        assertEquals(Integer.valueOf(3), right);
    }

    public void testLRRotation() {
        AVL<Integer> avl = new AVL<>();
        avl.insertar(3);
        avl.insertar(1);
        avl.insertar(2); // should trigger LR rotation

        Integer root = (Integer) avl.obtenerRaiz().getDato();
        Integer left = (Integer) avl.obtenerRaiz().getHijoIzquierdo().getDato();
        Integer right = (Integer) avl.obtenerRaiz().getHijoDerecho().getDato();

        assertEquals(Integer.valueOf(2), root);
        assertEquals(Integer.valueOf(1), left);
        assertEquals(Integer.valueOf(3), right);
    }

    public void testRLRotation() {
        AVL<Integer> avl = new AVL<>();
        avl.insertar(1);
        avl.insertar(3);
        avl.insertar(2); // should trigger RL rotation

        Integer root = (Integer) avl.obtenerRaiz().getDato();
        Integer left = (Integer) avl.obtenerRaiz().getHijoIzquierdo().getDato();
        Integer right = (Integer) avl.obtenerRaiz().getHijoDerecho().getDato();

        assertEquals(Integer.valueOf(2), root);
        assertEquals(Integer.valueOf(1), left);
        assertEquals(Integer.valueOf(3), right);
    }
}
