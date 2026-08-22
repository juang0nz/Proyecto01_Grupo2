package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class TDAPilaPrioridadEnlazadaImplTest extends TestCase {

    public void testConstructorConComparadorNuloLanzaExcepcion() {
        try {
            new TDAPilaPrioridadEnlazadaImpl<Integer>(null);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // esperado
        }
    }

    public void testPilaNuevaEsVacia() {
        TDAPilaPrioridadEnlazadaImpl<Integer> pila = new TDAPilaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        assertTrue(pila.esVacio());
        assertEquals(0, pila.tamanio());
    }

    public void testTopeYMeten() {
        TDAPilaPrioridadEnlazadaImpl<Integer> pila = new TDAPilaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        pila.mete(10);
        pila.mete(3);
        pila.mete(7);

        assertEquals(Integer.valueOf(3), pila.tope());
        assertEquals(3, pila.tamanio());
    }

    public void testSacaElDeMayorPrioridad() {
        TDAPilaPrioridadEnlazadaImpl<Integer> pila = new TDAPilaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        pila.mete(10);
        pila.mete(3);
        pila.mete(7);

        assertEquals(Integer.valueOf(3), pila.saca());
        assertEquals(Integer.valueOf(7), pila.tope());
    }

    public void testTopeVaciaLanzaExcepcion() {
        TDAPilaPrioridadEnlazadaImpl<Integer> pila = new TDAPilaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        try {
            pila.tope();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testInsertarPorIndiceNoSeSoporta() {
        TDAPilaPrioridadEnlazadaImpl<Integer> pila = new TDAPilaPrioridadEnlazadaImpl<Integer>(Comparator.<Integer>naturalOrder());
        pila.mete(1);
        try {
            pila.agregar(0, 2);
            fail("Se esperaba UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // esperado
        }
    }
}
