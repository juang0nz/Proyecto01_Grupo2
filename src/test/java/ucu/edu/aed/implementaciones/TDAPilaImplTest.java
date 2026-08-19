package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.NoSuchElementException;

/**
 * Casos de prueba básicos para {@link TDAPilaImpl} (política LIFO).
 */
public class TDAPilaImplTest extends TestCase {

    public void testPilaNuevaEsVacia() {
        TDAPilaImpl<Integer> pila = new TDAPilaImpl<>();
        assertTrue(pila.esVacio());
        assertEquals(0, pila.tamanio());
    }

    public void testMeteYTope() {
        TDAPilaImpl<Integer> pila = new TDAPilaImpl<>();
        pila.mete(1);
        pila.mete(2);
        pila.mete(3);

        // el tope debe ser el ultimo metido (LIFO)
        assertEquals(Integer.valueOf(3), pila.tope());
        assertEquals(3, pila.tamanio());
    }

    public void testSacaDevuelveEnOrdenLifo() {
        TDAPilaImpl<Integer> pila = new TDAPilaImpl<>();
        pila.mete(1);
        pila.mete(2);
        pila.mete(3);

        assertEquals(Integer.valueOf(3), pila.saca());
        assertEquals(Integer.valueOf(2), pila.saca());
        assertEquals(Integer.valueOf(1), pila.saca());
        assertTrue(pila.esVacio());
    }

    public void testTopeConPilaVaciaLanzaExcepcion() {
        TDAPilaImpl<Integer> pila = new TDAPilaImpl<>();
        try {
            pila.tope();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testSacaConPilaVaciaLanzaExcepcion() {
        TDAPilaImpl<Integer> pila = new TDAPilaImpl<>();
        try {
            pila.saca();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    public void testMeteUnSoloElemento() {
        TDAPilaImpl<String> pila = new TDAPilaImpl<>();
        pila.mete("unico");

        assertEquals("unico", pila.tope());
        assertEquals(1, pila.tamanio());
    }

    /**
     * Caso de uso real de la pila: validar que los corchetes de un texto
     * estén balanceados (mismo criterio que analizadorSintáctico.java).
     */
    public void testControlCorchetesBalanceados() {
        assertTrue(controlCorchetes("{{}}"));
        assertTrue(controlCorchetes(""));
    }

    public void testControlCorchetesDesbalanceados() {
        assertFalse(controlCorchetes("{{}"));
        assertFalse(controlCorchetes("}"));
        assertFalse(controlCorchetes("{}}"));
    }

    private boolean controlCorchetes(String texto) {
        TDAPilaImpl<Character> pila = new TDAPilaImpl<>();
        for (char c : texto.toCharArray()) {
            if (c == '{') {
                pila.mete(c);
            } else if (c == '}') {
                if (pila.esVacio()) {
                    return false;
                }
                pila.saca();
            }
        }
        return pila.esVacio();
    }
}
