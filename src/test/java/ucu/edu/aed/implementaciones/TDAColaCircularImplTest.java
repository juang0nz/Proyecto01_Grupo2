package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.NoSuchElementException;

/**
 * Casos de prueba para {@link TDAColaCircularImpl}, incluyendo
 * cola vacía, cola llena y wraparound del vector.
 */
public class TDAColaCircularImplTest extends TestCase {

    public void testColaNuevaEsVacia() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(4);
        assertTrue(cola.esVacio());
        assertEquals(0, cola.tamanio());
    }

    public void testFrenteConColaVaciaLanzaExcepcion() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(4);
        try {
            cola.frente();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testQuitaDeColaConColaVaciaLanzaExcepcion() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(4);
        try {
            cola.quitaDeCola();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testPoneEnColaYFrente() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(4);
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        assertEquals(Integer.valueOf(1), cola.frente());
    }

    public void testColaLlenaLanzaExcepcion() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(3);
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);
        assertTrue(cola.estaLlena());

        try {
            cola.poneEnCola(4);
            fail("Se esperaba IllegalStateException por cola llena");
        } catch (IllegalStateException e) {
            // esperado
        }
    }

    public void testWraparoundDelVector() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(3);

        cola.poneEnCola(1); // [1, _, _]  fondo=1
        cola.poneEnCola(2); // [1, 2, _]  fondo=2
        assertEquals(Integer.valueOf(1), cola.quitaDeCola()); // frente avanza a 1

        cola.poneEnCola(3); // [_, 2, 3]  fondo=0 (dio la vuelta)
        cola.poneEnCola(4); // [4, 2, 3]  fondo=1 (ocupa la posicion 0, wraparound real)

        assertEquals(3, cola.tamanio());
        assertEquals(Integer.valueOf(2), cola.quitaDeCola());
        assertEquals(Integer.valueOf(3), cola.quitaDeCola());
        assertEquals(Integer.valueOf(4), cola.quitaDeCola());
        assertTrue(cola.esVacio());
    }

    public void testQuitarYVolverAPonerReutilizaEspacio() {
        TDAColaCircularImpl<Integer> cola = new TDAColaCircularImpl<>(2);
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        assertTrue(cola.estaLlena());

        cola.quitaDeCola();
        cola.poneEnCola(3);

        assertEquals(Integer.valueOf(2), cola.quitaDeCola());
        assertEquals(Integer.valueOf(3), cola.quitaDeCola());
    }
}