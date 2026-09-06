package ucu.edu.aed.tda.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import ucu.edu.aed.tda.TDAElemento;

public class AVLTest extends TestCase {

    public void testArbolVacioYDuplicados() {
        AVL<Integer> arbol = new AVL<Integer>();

        assertTrue(arbol.esVacio());
        assertNull(arbol.obtenerRaiz());
        assertNull(arbol.buscar(10));
        assertFalse(arbol.eliminar(10));
        assertEquals(0, arbol.cantidadNodos());
        assertEquals(0, arbol.cantidadHojas());
        assertEquals(0, arbol.cantidadNodosInternos());

        assertTrue(arbol.insertar(10));
        assertTrue(arbol.insertar(15));
        assertTrue(arbol.insertar(20));
        assertFalse(arbol.insertar(15));

        assertEquals(Integer.valueOf(10), arbol.buscar(10));
        assertEquals(Integer.valueOf(20), arbol.buscar(20));
        assertEquals(3, arbol.cantidadNodos());
    }

    public void testBalanceoTrasInserciones() {
        AVL<Integer> arbol = new AVL<Integer>();
        int[] valores = {30, 20, 10, 25, 40, 35, 50};

        for (int valor : valores) {
            assertTrue(arbol.insertar(valor));
        }

        assertNotNull(arbol.obtenerRaiz());
        assertAVLBalanceado(arbol.obtenerRaiz());
        assertEquals(7, arbol.cantidadNodos());
        assertEquals(Integer.valueOf(30), arbol.buscar(30));
        assertEquals(Integer.valueOf(35), arbol.buscar(35));
    }

    public void testEliminacionMantieneBalance() {
        AVL<Integer> arbol = new AVL<Integer>();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 55, 65, 75, 85};

        for (int valor : valores) {
            assertTrue(arbol.insertar(valor));
        }

        assertTrue(arbol.eliminar(20));
        assertNull(arbol.buscar(20));
        assertTrue(arbol.eliminar(50));
        assertNull(arbol.buscar(50));
        assertEquals(13, arbol.cantidadNodos());
        assertAVLBalanceado(arbol.obtenerRaiz());
        assertFalse(arbol.eliminar(999));
    }

    public void testRecorridoInOrderQuedaOrdenado() {
        AVL<Integer> arbol = new AVL<Integer>();
        int[] valores = {15, 20, 10, 5, 13, 1, 17, 16};

        for (int valor : valores) {
            assertTrue(arbol.insertar(valor));
        }

        List<Integer> inOrder = new ArrayList<Integer>();
        arbol.inOrder(new java.util.function.Consumer<Integer>() {
            @Override
            public void accept(Integer elemento) {
                inOrder.add(elemento);
            }
        });

        assertEquals(Arrays.asList(1, 5, 10, 13, 15, 16, 17, 20), inOrder);
        assertAVLBalanceado(arbol.obtenerRaiz());
    }

    private void assertAVLBalanceado(TDAElemento<Integer> nodo) {
        if (nodo == null) {
            return;
        }

        assertTrue(nodo instanceof ElementoAVL);
        ElementoAVL<Integer> elemento = (ElementoAVL<Integer>) nodo;

        int factor = elemento.factorBalance();
        assertTrue("Factor de balance fuera de rango en nodo " + elemento.getDato() + ": " + factor,
                factor >= -1 && factor <= 1);

        assertAVLBalanceado(elemento.getHijoIzquierdo());
        assertAVLBalanceado(elemento.getHijoDerecho());
    }
}
