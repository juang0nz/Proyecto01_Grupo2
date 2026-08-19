package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;

import ucu.edu.aed.tda.TDALista;

/**
 * Casos de prueba básicos para {@link TDAListaEnlazadaImpl}.
 */
public class TDAListaEnlazadaImplTest extends TestCase {

    public void testListaNuevaEsVacia() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }

    public void testAgregarAlFinal() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        assertEquals(3, lista.tamanio());
        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    public void testAgregarEnIndiceEspecifico() {
        TDALista<String> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar("a");
        lista.agregar("c");
        lista.agregar(1, "b"); // queda a, b, c

        assertEquals(3, lista.tamanio());
        assertEquals("a", lista.obtener(0));
        assertEquals("b", lista.obtener(1));
        assertEquals("c", lista.obtener(2));
    }

    public void testAgregarEnIndiceCeroConListaVacia() {
        TDALista<String> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(0, "x");

        assertEquals(1, lista.tamanio());
        assertEquals("x", lista.obtener(0));
    }

    public void testAgregarEnIndiceInvalidoLanzaExcepcion() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        try {
            lista.agregar(5, 10);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }
    }

    public void testObtenerConIndiceInvalidoLanzaExcepcion() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
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

    public void testRemoverPorIndicePrimero() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        int removido = lista.remover(0);

        assertEquals(10, removido);
        assertEquals(2, lista.tamanio());
        assertEquals(Integer.valueOf(20), lista.obtener(0));
    }

    public void testRemoverPorIndiceIntermedio() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        int removido = lista.remover(1);

        assertEquals(20, removido);
        assertEquals(2, lista.tamanio());
        assertEquals(Integer.valueOf(10), lista.obtener(0));
        assertEquals(Integer.valueOf(30), lista.obtener(1));
    }

    public void testRemoverPorValorExistente() {
        TDALista<String> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar("a");
        lista.agregar("b");
        lista.agregar("c");

        assertTrue(lista.remover("b"));
        assertEquals(2, lista.tamanio());
        assertFalse(lista.contiene("b"));
    }

    public void testRemoverPorValorInexistenteDevuelveFalse() {
        TDALista<String> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar("a");

        assertFalse(lista.remover("z"));
        assertEquals(1, lista.tamanio());
    }

    public void testContieneYIndiceDe() {
        TDALista<String> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar("uno");
        lista.agregar("dos");

        assertTrue(lista.contiene("dos"));
        assertFalse(lista.contiene("tres"));
        assertEquals(1, lista.indiceDe("dos"));
        assertEquals(-1, lista.indiceDe("tres"));
    }

    public void testBuscarConCriterio() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        Integer encontrado = lista.buscar(n -> n % 2 == 0);
        assertEquals(Integer.valueOf(2), encontrado);

        Integer noEncontrado = lista.buscar(n -> n > 100);
        assertNull(noEncontrado);
    }

    public void testOrdenar() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(3);
        lista.agregar(1);
        lista.agregar(2);

        TDALista<Integer> ordenada = lista.ordenar(Comparator.naturalOrder());

        assertEquals(3, ordenada.tamanio());
        assertEquals(Integer.valueOf(1), ordenada.obtener(0));
        assertEquals(Integer.valueOf(2), ordenada.obtener(1));
        assertEquals(Integer.valueOf(3), ordenada.obtener(2));
    }

    public void testVaciar() {
        TDALista<Integer> lista = new TDAListaEnlazadaImpl<>();
        lista.agregar(1);
        lista.agregar(2);

        lista.vaciar();

        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }
}
