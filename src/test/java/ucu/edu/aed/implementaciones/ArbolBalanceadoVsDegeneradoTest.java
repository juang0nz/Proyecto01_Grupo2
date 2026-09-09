package ucu.edu.aed.implementaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import junit.framework.TestCase;

public class ArbolBalanceadoVsDegeneradoTest extends TestCase {

    // mismos datos en orden creciente:
    // el ABB se degenera en una lista (altura n, una sola hoja);
    // el AVL se mantiene balanceado (altura ~ log2 n)
    public void testInsercionOrdenadaDegeneraElABBPeroNoElAVL() {
        ABB<Integer> abb = new ABB<Integer>();
        AVL<Integer> avl = new AVL<Integer>();

        for (int i = 1; i <= 7; i++) {
            assertTrue(abb.insertar(i));
            assertTrue(avl.insertar(i));
        }

        // misma cantidad de nodos y mismo recorrido ordenado
        assertEquals(7, abb.cantidadNodos());
        assertEquals(7, avl.cantidadNodos());
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), recorrerInOrder(abb));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), recorrerInOrder(avl));

        // ABB degenerado: cadena de 7, una unica hoja
        assertEquals(7, abb.altura());
        assertEquals(1, abb.cantidadHojas());

        // AVL balanceado: altura 3 y 4 hojas
        assertEquals(3, avl.altura());
        assertEquals(4, avl.cantidadHojas());

        assertTrue(avl.altura() < abb.altura());
    }

    private List<Integer> recorrerInOrder(ABB<Integer> arbol) {
        final List<Integer> resultado = new ArrayList<Integer>();
        arbol.inOrder(new Consumer<Integer>() {
            @Override
            public void accept(Integer elemento) {
                resultado.add(elemento);
            }
        });
        return resultado;
    }
}
