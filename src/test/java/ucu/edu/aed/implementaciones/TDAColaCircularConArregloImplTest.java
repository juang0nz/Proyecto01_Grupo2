package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.NoSuchElementException;

import ucu.edu.aed.tda.TDALista;

/**
 * Casos de prueba para {@link TDAColaCircularConArregloImpl}.
 */
public class TDAColaCircularConArregloImplTest extends TestCase {

    public void testConstructorConCapacidadInvalidaLanzaExcepcion() {
        try {
            new TDAColaCircularConArregloImpl<>(0);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // esperado
        }

        try {
            new TDAColaCircularConArregloImpl<>(-1);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // esperado
        }
    }

    public void testColaNuevaEsVacia() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);

        assertTrue(cola.esVacio());
        assertEquals(0, cola.tamanio());
    }

    public void testFrenteConColaVaciaLanzaExcepcion() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(2);

        try {
            cola.frente();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testFrenteDevuelvePrimerElemento() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);
        cola.poneEnCola(10);
        cola.poneEnCola(20);

        assertEquals(Integer.valueOf(10), cola.frente());
        assertEquals(2, cola.tamanio());
    }

    public void testPoneEnColaAgregaElementosEnOrden() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);

        assertEquals(Integer.valueOf(1), cola.frente());
        assertEquals(3, cola.tamanio());
    }

    public void testPoneEnColaCuandoEstaLlenaLanzaExcepcion() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(2);
        cola.poneEnCola(1);
        cola.poneEnCola(2);

        try {
            cola.poneEnCola(3);
            fail("Se esperaba IllegalStateException");
        } catch (IllegalStateException e) {
            // esperado
        }
    }

    public void testQuitaDeColaDevuelvePrimerElemento() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(3);
        cola.poneEnCola(5);
        cola.poneEnCola(7);

        assertEquals(Integer.valueOf(5), cola.quitaDeCola());
        assertEquals(Integer.valueOf(7), cola.frente());
    }

    public void testQuitaDeColaConColaVaciaLanzaExcepcion() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(2);

        try {
            cola.quitaDeCola();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testAgregarAlFinalConListaEnCola() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(3);
        cola.agregar(1);
        cola.agregar(2);

        assertEquals(Integer.valueOf(1), cola.frente());
        assertEquals(2, cola.tamanio());
    }

    public void testAgregarEnIndiceInvalidoLanzaExcepcion() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(3);
        cola.poneEnCola(1);

        try {
            cola.agregar(-1, 99);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }

        try {
            cola.agregar(5, 99);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }
    }

    public void testObtenerPorIndiceDevuelveElementoCorrecto() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);
        cola.poneEnCola(10);
        cola.poneEnCola(20);
        cola.poneEnCola(30);

        assertEquals(Integer.valueOf(20), cola.obtener(1));
        assertEquals(Integer.valueOf(30), cola.obtener(2));
    }

    public void testObtenerConIndiceInvalidoLanzaExcepcion() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(2);
        cola.poneEnCola(1);

        try {
            cola.obtener(-1);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }

        try {
            cola.obtener(1);
            fail("Se esperaba IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }
    }

    public void testRemoverPorIndiceDevuelveElemento() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);
        cola.poneEnCola(10);
        cola.poneEnCola(20);
        cola.poneEnCola(30);

        assertEquals(Integer.valueOf(20), cola.remover(1));
        assertEquals(2, cola.tamanio());
        assertEquals(Integer.valueOf(10), cola.obtener(0));
    }

    public void testRemoverPorValorDevuelveTrueYFalse() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);
        cola.poneEnCola(10);
        cola.poneEnCola(20);
        cola.poneEnCola(30);

        assertTrue(cola.remover(Integer.valueOf(20)));
        assertFalse(cola.remover(Integer.valueOf(999)));
        assertEquals(2, cola.tamanio());
    }

    public void testContieneDetectaPresenciaYAusencia() {
        TDAColaCircularConArregloImpl<String> cola = new TDAColaCircularConArregloImpl<>(3);
        cola.poneEnCola("uno");
        cola.poneEnCola("dos");

        assertTrue(cola.contiene("uno"));
        assertFalse(cola.contiene("tres"));
    }

    public void testIndiceDeDevuelvePosicionCorrecta() {
        TDAColaCircularConArregloImpl<String> cola = new TDAColaCircularConArregloImpl<>(3);
        cola.poneEnCola("uno");
        cola.poneEnCola("dos");

        assertEquals(1, cola.indiceDe("dos"));
        assertEquals(-1, cola.indiceDe("tres"));
    }

    public void testBuscarConCriterioDevuelvePrimeroCoincidente() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(5);
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);

        assertEquals(Integer.valueOf(2), cola.buscar(n -> n % 2 == 0));
        assertNull(cola.buscar(n -> n > 100));
    }

    public void testOrdenarDevuelveListaOrdenada() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(5);
        cola.poneEnCola(3);
        cola.poneEnCola(1);
        cola.poneEnCola(2);

        TDALista<Integer> ordenada = cola.ordenar(Comparator.naturalOrder());

        assertEquals(3, ordenada.tamanio());
        assertEquals(Integer.valueOf(1), ordenada.obtener(0));
        assertEquals(Integer.valueOf(2), ordenada.obtener(1));
        assertEquals(Integer.valueOf(3), ordenada.obtener(2));
    }

    public void testTamanioCambiaConAgregarYQuitar() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(5);
        cola.poneEnCola(10);
        cola.poneEnCola(20);

        assertEquals(2, cola.tamanio());
        cola.quitaDeCola();
        assertEquals(1, cola.tamanio());
    }

    public void testEsVacioYVaciar() {
        TDAColaCircularConArregloImpl<Integer> cola = new TDAColaCircularConArregloImpl<>(4);
        cola.poneEnCola(1);
        cola.poneEnCola(2);

        assertFalse(cola.esVacio());
        cola.vaciar();
        assertTrue(cola.esVacio());
        assertEquals(0, cola.tamanio());
    }
}
