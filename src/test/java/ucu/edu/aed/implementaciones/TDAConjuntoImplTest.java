package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

/**
 * Casos de prueba básicos para {@link TDAConjuntoImpl}.
 */
public class TDAConjuntoImplTest extends TestCase {

    public void testUnionCombinaElementosSinDuplicados() {
        TDAConjuntoImpl<Integer> a = new TDAConjuntoImpl<>();
        a.agregar(1);
        a.agregar(2);
        a.agregar(3);

        TDAConjuntoImpl<Integer> b = new TDAConjuntoImpl<>();
        b.agregar(3);
        b.agregar(4);

        TDAConjuntoImpl<Integer> resultado = (TDAConjuntoImpl<Integer>) a.union(b);

        // resultado debe contener 1,2,3,4 y tamaño 4
        assertEquals(4, resultado.tamanio());
        assertTrue(resultado.contiene(1));
        assertTrue(resultado.contiene(2));
        assertTrue(resultado.contiene(3));
        assertTrue(resultado.contiene(4));

        // los conjuntos originales no deben modificarse
        assertEquals(3, a.tamanio());
        assertEquals(2, b.tamanio());
    }

    public void testInterseccionDevuelveElementosComunes() {
        TDAConjuntoImpl<Integer> a = new TDAConjuntoImpl<>();
        a.agregar(1);
        a.agregar(2);
        a.agregar(3);

        TDAConjuntoImpl<Integer> b = new TDAConjuntoImpl<>();
        b.agregar(2);
        b.agregar(3);
        b.agregar(4);

        TDAConjuntoImpl<Integer> inter = (TDAConjuntoImpl<Integer>) a.interseccion(b);

        assertEquals(2, inter.tamanio());
        assertTrue(inter.contiene(2));
        assertTrue(inter.contiene(3));
    }

    public void testInterseccionSinElementosComunDevuelveVacio() {
        TDAConjuntoImpl<Integer> a = new TDAConjuntoImpl<>();
        a.agregar(1);
        a.agregar(2);

        TDAConjuntoImpl<Integer> b = new TDAConjuntoImpl<>();
        b.agregar(3);
        b.agregar(4);

        TDAConjuntoImpl<Integer> inter = (TDAConjuntoImpl<Integer>) a.interseccion(b);

        assertEquals(0, inter.tamanio());
    }

    public void testDiferenciaDevuelveElementosQueNoEstanEnOtro() {
        TDAConjuntoImpl<Integer> a = new TDAConjuntoImpl<>();
        a.agregar(1);
        a.agregar(2);
        a.agregar(3);

        TDAConjuntoImpl<Integer> b = new TDAConjuntoImpl<>();
        b.agregar(2);
        b.agregar(4);

        TDAConjuntoImpl<Integer> diff = (TDAConjuntoImpl<Integer>) a.diferencia(b);

        assertEquals(2, diff.tamanio());
        assertTrue(diff.contiene(1));
        assertTrue(diff.contiene(3));
        assertFalse(diff.contiene(2));
    }

    public void testEsSubconjuntoDeTrueYFalse() {
        TDAConjuntoImpl<Integer> a = new TDAConjuntoImpl<>();
        a.agregar(1);
        a.agregar(2);

        TDAConjuntoImpl<Integer> b = new TDAConjuntoImpl<>();
        b.agregar(1);
        b.agregar(2);
        b.agregar(3);

        assertTrue(a.esSubconjuntoDe(b));
        assertFalse(b.esSubconjuntoDe(a));
    }

}