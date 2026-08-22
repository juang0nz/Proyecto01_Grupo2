package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;

import ucu.edu.aed.tda.TDALista;

public class TDAListaCircularEnlazadaImplTest extends TestCase {

    public void testListaNuevaEsVacia() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }

    public void testAgregarYObtenerConVariosElementos() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        assertEquals(3, lista.tamanio());
        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(2), lista.obtener(1));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    public void testAgregarEnIndiceCeroConListaVacia() {
        TDALista<String> lista = new TDAListaCircularEnlazadaImpl<String>();
        lista.agregar(0, "x");

        assertEquals(1, lista.tamanio());
        assertEquals("x", lista.obtener(0));
    }

    public void testAgregarEnIndiceIntermedio() {
        TDALista<String> lista = new TDAListaCircularEnlazadaImpl<String>();
        lista.agregar("a");
        lista.agregar("c");
        lista.agregar(1, "b");

        assertEquals(3, lista.tamanio());
        assertEquals("a", lista.obtener(0));
        assertEquals("b", lista.obtener(1));
        assertEquals("c", lista.obtener(2));
    }

    public void testAgregarEnIndiceInvalidoLanzaExcepcion() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        try {
            lista.agregar(1, 10);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }
    }

    public void testRemoverPrimeroYUltimo() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        assertEquals(Integer.valueOf(10), lista.remover(0));
        assertEquals(Integer.valueOf(30), lista.remover(1));
        assertEquals(1, lista.tamanio());
        assertEquals(Integer.valueOf(20), lista.obtener(0));
    }

    public void testRemoverUnicoElementoDejaListaVacia() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        lista.agregar(99);

        assertEquals(Integer.valueOf(99), lista.remover(0));
        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }

    public void testRemoverPorValorExistenteYNoExistente() {
        TDALista<String> lista = new TDAListaCircularEnlazadaImpl<String>();
        lista.agregar("uno");
        lista.agregar("dos");

        assertTrue(lista.remover("uno"));
        assertFalse(lista.remover("tres"));
        assertEquals(1, lista.tamanio());
    }

    public void testContieneEIndiceDe() {
        TDALista<String> lista = new TDAListaCircularEnlazadaImpl<String>();
        lista.agregar("a");
        lista.agregar("b");

        assertTrue(lista.contiene("b"));
        assertFalse(lista.contiene("c"));
        assertEquals(1, lista.indiceDe("b"));
        assertEquals(-1, lista.indiceDe("c"));
    }

    public void testBuscarYOrdenar() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        lista.agregar(5);
        lista.agregar(1);
        lista.agregar(3);

        assertEquals(Integer.valueOf(3), lista.buscar(n -> n == 3));
        assertNull(lista.buscar(n -> n > 10));

        TDALista<Integer> ordenada = lista.ordenar(Comparator.naturalOrder());
        assertEquals(Integer.valueOf(1), ordenada.obtener(0));
        assertEquals(Integer.valueOf(3), ordenada.obtener(1));
        assertEquals(Integer.valueOf(5), ordenada.obtener(2));
    }

    public void testVaciar() {
        TDALista<Integer> lista = new TDAListaCircularEnlazadaImpl<Integer>();
        lista.agregar(1);
        lista.agregar(2);

        lista.vaciar();

        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }
}
