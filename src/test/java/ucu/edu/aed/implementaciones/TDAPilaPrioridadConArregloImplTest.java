package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class TDAPilaPrioridadConArregloImplTest extends TestCase {

    public void testConstructorConComparadorNuloLanzaExcepcion() {
        try {
            new TDAPilaPrioridadConArregloImpl<Integer>(null);
            fail("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // esperado
        }
    }

    public void testPilaNuevaEsVacia() {
        TDAPilaPrioridadConArregloImpl<Integer> pila = new TDAPilaPrioridadConArregloImpl<Integer>(Comparator.<Integer>naturalOrder());
        assertTrue(pila.esVacio());
        assertEquals(0, pila.tamanio());
    }

    public void testTopeYMeten() {
        TDAPilaPrioridadConArregloImpl<Integer> pila = new TDAPilaPrioridadConArregloImpl<Integer>(Comparator.<Integer>naturalOrder());
        pila.mete(10);
        pila.mete(3);
        pila.mete(7);

        assertEquals(Integer.valueOf(3), pila.tope());
        assertEquals(3, pila.tamanio());
    }

    public void testSacaElDeMayorPrioridad() {
        TDAPilaPrioridadConArregloImpl<Integer> pila = new TDAPilaPrioridadConArregloImpl<Integer>(Comparator.<Integer>naturalOrder());
        pila.mete(10);
        pila.mete(3);
        pila.mete(7);

        assertEquals(Integer.valueOf(3), pila.saca());
        assertEquals(Integer.valueOf(7), pila.tope());
    }

    public void testTopeVaciaLanzaExcepcion() {
        TDAPilaPrioridadConArregloImpl<Integer> pila = new TDAPilaPrioridadConArregloImpl<Integer>(Comparator.<Integer>naturalOrder());
        try {
            pila.tope();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testInsertarPorIndiceNoSeSoporta() {
        TDAPilaPrioridadConArregloImpl<Integer> pila = new TDAPilaPrioridadConArregloImpl<Integer>(Comparator.<Integer>naturalOrder());
        pila.mete(1);
        try {
            pila.agregar(0, 2);
            fail("Se esperaba UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // esperado
        }
    }
}
