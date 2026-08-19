package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.NoSuchElementException;

/**
 * Casos de prueba básicos para {@link TDAColaImpl} (política FIFO).
 */
public class TDAColaImplTest extends TestCase {

    public void testColaNuevaEsVacia() {
        TDAColaImpl<Integer> cola = new TDAColaImpl<>();
        assertTrue(cola.esVacio());
        assertEquals(0, cola.tamanio());
    }

    public void testPoneEnColaYFrente() {
        TDAColaImpl<Integer> cola = new TDAColaImpl<>();
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);

        // el frente debe ser el primero en entrar (FIFO)
        assertEquals(Integer.valueOf(1), cola.frente());
        assertEquals(3, cola.tamanio());
    }

    public void testQuitaDeColaDevuelveEnOrdenFifo() {
        TDAColaImpl<Integer> cola = new TDAColaImpl<>();
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);

        assertEquals(Integer.valueOf(1), cola.quitaDeCola());
        assertEquals(Integer.valueOf(2), cola.quitaDeCola());
        assertEquals(Integer.valueOf(3), cola.quitaDeCola());
        assertTrue(cola.esVacio());
    }

    public void testPoneEnColaDevuelveTrue() {
        TDAColaImpl<Integer> cola = new TDAColaImpl<>();
        assertTrue(cola.poneEnCola(10));
    }

    public void testFrenteConColaVaciaLanzaExcepcion() {
        TDAColaImpl<Integer> cola = new TDAColaImpl<>();
        try {
            cola.frente();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testQuitaDeColaConColaVaciaLanzaExcepcion() {
        TDAColaImpl<Integer> cola = new TDAColaImpl<>();
        try {
            cola.quitaDeCola();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testPoneEnColaUnSoloElemento() {
        TDAColaImpl<String> cola = new TDAColaImpl<>();
        cola.poneEnCola("unico");

        assertEquals("unico", cola.frente());
        assertEquals(1, cola.tamanio());
    }
}
