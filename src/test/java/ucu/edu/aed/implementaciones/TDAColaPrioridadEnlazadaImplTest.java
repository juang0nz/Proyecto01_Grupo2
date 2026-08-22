package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDALista;

/**
 * Casos de prueba para {@link TDAColaPrioridadEnlazadaImpl}.
 */
public class TDAColaPrioridadEnlazadaImplTest extends TestCase {

    public void testConstructorConComparadorNuloLanzaExcepcion() {
        try {
            new TDAColaPrioridadEnlazadaImpl<Integer>(null);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // esperado
        }
    }

    public void testColaNuevaEsVacia() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());

        assertTrue(cola.esVacio());
        assertEquals(0, cola.tamanio());
    }

    public void testFrenteConColaVaciaLanzaExcepcion() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());

        try {
            cola.frente();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testPoneEnColaMantieneOrdenPorPrioridad() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        cola.poneEnCola(10);
        cola.poneEnCola(3);
        cola.poneEnCola(7);

        assertEquals(Integer.valueOf(3), cola.frente());
        assertEquals(3, cola.tamanio());
        assertEquals(Integer.valueOf(7), cola.obtener(1));
    }

    public void testQuitaDeColaSacaElDeMayorPrioridad() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        cola.poneEnCola(10);
        cola.poneEnCola(3);
        cola.poneEnCola(7);

        assertEquals(Integer.valueOf(3), cola.quitaDeCola());
        assertEquals(Integer.valueOf(7), cola.frente());
    }

    public void testAgregarPorIndiceNoSeSoporta() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        cola.poneEnCola(2);

        try {
            cola.agregar(0, 1);
            fail("Se esperaba UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // esperado
        }
    }

    public void testObtenerYRemoverPorIndice() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        cola.poneEnCola(10);
        cola.poneEnCola(3);
        cola.poneEnCola(7);

        assertEquals(Integer.valueOf(3), cola.obtener(0));
        assertEquals(Integer.valueOf(3), cola.remover(0));
        assertEquals(Integer.valueOf(7), cola.obtener(0));
    }

    public void testContieneYIndiceDe() {
        TDAColaPrioridadEnlazadaImpl<String> cola = new TDAColaPrioridadEnlazadaImpl<String>(Comparator.<String>naturalOrder());
        cola.poneEnCola("b");
        cola.poneEnCola("a");

        assertTrue(cola.contiene("a"));
        assertFalse(cola.contiene("z"));
        assertEquals(0, cola.indiceDe("a"));
    }

    public void testBuscarYOrdenar() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        cola.poneEnCola(9);
        cola.poneEnCola(1);
        cola.poneEnCola(5);

        assertEquals(Integer.valueOf(1), cola.buscar(n -> n % 2 == 1));

        TDALista<Integer> ordenada = cola.ordenar(Comparator.<Integer>naturalOrder());
        assertEquals(Integer.valueOf(1), ordenada.obtener(0));
        assertEquals(Integer.valueOf(5), ordenada.obtener(1));
        assertEquals(Integer.valueOf(9), ordenada.obtener(2));
    }

    public void testVaciar() {
        TDAColaPrioridadEnlazadaImpl<Integer> cola = new TDAColaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        cola.poneEnCola(8);
        cola.poneEnCola(2);

        cola.vaciar();

        assertTrue(cola.esVacio());
        assertEquals(0, cola.tamanio());
    }
}
