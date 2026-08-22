package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;

import ucu.edu.aed.tda.TDALista;

public class TDAListaCircularDobleEnlazadaImplTest extends TestCase {

    public void testListaNuevaEsVacia() {
        TDALista<Integer> lista = new TDAListaCircularDobleEnlazadaImpl<Integer>();
        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }

    public void testAgregarYObtener() {
        TDALista<Integer> lista = new TDAListaCircularDobleEnlazadaImpl<Integer>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);

        assertEquals(Integer.valueOf(1), lista.obtener(0));
        assertEquals(Integer.valueOf(3), lista.obtener(2));
    }

    public void testAgregarEnIndice() {
        TDALista<String> lista = new TDAListaCircularDobleEnlazadaImpl<String>();
        lista.agregar("a");
        lista.agregar("c");
        lista.agregar(1, "b");

        assertEquals("b", lista.obtener(1));
        assertEquals(3, lista.tamanio());
    }

    public void testRemoverPorIndice() {
        TDALista<Integer> lista = new TDAListaCircularDobleEnlazadaImpl<Integer>();
        lista.agregar(10);
        lista.agregar(20);
        lista.agregar(30);

        assertEquals(Integer.valueOf(20), lista.remover(1));
        assertEquals(Integer.valueOf(30), lista.obtener(1));
    }

    public void testRemoverPorValor() {
        TDALista<String> lista = new TDAListaCircularDobleEnlazadaImpl<String>();
        lista.agregar("a");
        lista.agregar("b");
        lista.agregar("c");

        assertTrue(lista.remover("b"));
        assertFalse(lista.contiene("b"));
    }

    public void testContieneYIndiceDe() {
        TDALista<String> lista = new TDAListaCircularDobleEnlazadaImpl<String>();
        lista.agregar("x");
        lista.agregar("y");

        assertTrue(lista.contiene("y"));
        assertEquals(1, lista.indiceDe("y"));
    }

    public void testBuscarYOrdenar() {
        TDALista<Integer> lista = new TDAListaCircularDobleEnlazadaImpl<Integer>();
        lista.agregar(5);
        lista.agregar(1);
        lista.agregar(3);

        assertEquals(Integer.valueOf(3), lista.buscar(n -> n == 3));
        TDALista<Integer> ordenada = lista.ordenar(Comparator.naturalOrder());
        assertEquals(Integer.valueOf(1), ordenada.obtener(0));
        assertEquals(Integer.valueOf(5), ordenada.obtener(2));
    }

    public void testVaciar() {
        TDALista<Integer> lista = new TDAListaCircularDobleEnlazadaImpl<Integer>();
        lista.agregar(1);
        lista.agregar(2);
        lista.vaciar();

        assertTrue(lista.esVacio());
        assertEquals(0, lista.tamanio());
    }
}
