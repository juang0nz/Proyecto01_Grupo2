package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;

import ucu.edu.aed.tda.TDALista;

/**
 * Casos de prueba básicos para {@link TDAListaConArregloImpl}.
 */
public class TDAListaConArregloImplTest extends TestCase {

    public void testListaNuevaEsVacia() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();

        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }

    public void testAgregarAlFinal() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        assertEquals(3, lista.tamanio());
        assertEquals(Integer.valueOf(10), lista.obtener(0));
        assertEquals(Integer.valueOf(30), lista.obtener(2));
    }

    public void testAgregarEnIndiceEspecifico() {
        TDALista<String> lista = new TDAListaConArregloImpl<>();
        lista.agregar("a");
        lista.agregar("c");
        lista.agregar(1, "b");

        assertEquals(3, lista.tamanio());
        assertEquals("a", lista.obtener(0));
        assertEquals("b", lista.obtener(1));
        assertEquals("c", lista.obtener(2));
    }

    public void testObtenerConIndiceInvalidoLanzaExcepcion() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();
        lista.agregar(1);

        try {
            lista.obtener(-1);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }

        try {
            lista.obtener(1);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }
    }

    public void testRemoverPorIndice() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        assertEquals(Integer.valueOf(20), lista.remover(1));
        assertEquals(2, lista.tamanio());
        assertEquals(Integer.valueOf(10), lista.obtener(0));
        assertEquals(Integer.valueOf(30), lista.obtener(1));
    }

    public void testRemoverPorValor() {
        TDALista<String> lista = new TDAListaConArregloImpl<>();
        lista.agregar("a");
        lista.agregar("b");
        lista.agregar("c");

        assertTrue(lista.remover("b"));
        assertFalse(lista.contiene("b"));
        assertEquals(2, lista.tamanio());
    }

    public void testContieneYIndiceDe() {
        TDALista<String> lista = new TDAListaConArregloImpl<>();
        lista.agregar("uno");
        lista.agregar("dos");

        assertTrue(lista.contiene("dos"));
        assertFalse(lista.contiene("tres"));
        assertEquals(1, lista.indiceDe("dos"));
        assertEquals(-1, lista.indiceDe("tres"));
    }

    public void testBuscarConCriterio() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        assertEquals(Integer.valueOf(2), lista.buscar(n -> n % 2 == 0));
        assertNull(lista.buscar(n -> n > 100));
    }

    public void testOrdenar() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();
        lista.agregar(3);
        lista.agregar(1);
        lista.agregar(2);

        TDALista<Integer> ordenada = lista.ordenar(Comparator.naturalOrder());

        assertEquals(3, ordenada.tamanio());
        assertEquals(Integer.valueOf(1), ordenada.obtener(0));
        assertEquals(Integer.valueOf(3), ordenada.obtener(2));
    }

    public void testVaciar() {
        TDALista<Integer> lista = new TDAListaConArregloImpl<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        lista.vaciar();

        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }
}
