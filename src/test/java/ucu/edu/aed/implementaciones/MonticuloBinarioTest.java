package ucu.edu.aed.implementaciones;

import java.util.NoSuchElementException;

import junit.framework.TestCase;

public class MonticuloBinarioTest extends TestCase {

    public void testMonticuloVacio() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);

        assertTrue(monticulo.esVacia());
        assertEquals(0, monticulo.tamanio());
    }

    // un monticulo vacio no tiene frente
    public void testFrenteEnMonticuloVacio() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);
        try {
            monticulo.frente();
            fail("se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
        }
    }

    // un monticulo vacio no puede quitar
    public void testQuitarEnMonticuloVacio() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);
        try {
            monticulo.quitar();
            fail("se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) {
        }
    }

    // insertar null no debe modificar el monticulo
    public void testInsertarNull() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);

        assertFalse(monticulo.insertar(null));
        assertTrue(monticulo.esVacia());
    }

    // caso con un solo elemento
    public void testUnSoloElemento() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);
        assertTrue(monticulo.insertar(elem("unico", 5, 1)));

        assertEquals(1, monticulo.tamanio());
        assertFalse(monticulo.esVacia());
        assertEquals("unico", monticulo.frente().getDato());
        assertEquals("unico", monticulo.quitar().getDato());
        assertTrue(monticulo.esVacia());
    }

    // el numero de prioridad mas chico sale primero
    public void testSalePrimeroLaMayorPrioridad() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);
        monticulo.insertar(elem("C", 3, 1));
        monticulo.insertar(elem("A", 1, 2));
        monticulo.insertar(elem("B", 2, 3));

        assertEquals(3, monticulo.tamanio());
        assertEquals("A", monticulo.frente().getDato());
        assertEquals("A", monticulo.quitar().getDato());
        assertEquals("B", monticulo.quitar().getDato());
        assertEquals("C", monticulo.quitar().getDato());
        assertTrue(monticulo.esVacia());
    }

    // misma prioridad: se respeta el orden de llegada
    public void testDesempatePorOrdenDeLlegada() {
        MonticuloBinario<String> monticulo = new MonticuloBinario<String>(4);
        monticulo.insertar(elem("primero", 1, 10));
        monticulo.insertar(elem("segundo", 1, 20));
        monticulo.insertar(elem("tercero", 1, 30));

        assertEquals("primero", monticulo.quitar().getDato());
        assertEquals("segundo", monticulo.quitar().getDato());
        assertEquals("tercero", monticulo.quitar().getDato());
    }

    // el arreglo se amplia solo cuando se llena
    public void testAmpliaCapacidadAlLlenarse() {
        MonticuloBinario<Integer> monticulo = new MonticuloBinario<Integer>(1);
        for (int i = 5; i >= 1; i--) {
            monticulo.insertar(new ElementoPrioridad<Integer>(i, i, i));
        }

        assertEquals(5, monticulo.tamanio());
        assertEquals(Integer.valueOf(1), monticulo.quitar().getDato());
        assertEquals(Integer.valueOf(2), monticulo.quitar().getDato());
    }

    // capacidad inicial 0: igual debe poder insertar
    public void testCapacidadInicialCero() {
        MonticuloBinario<Integer> monticulo = new MonticuloBinario<Integer>(0);

        assertTrue(monticulo.insertar(new ElementoPrioridad<Integer>(7, 1, 1)));
        assertEquals(Integer.valueOf(7), monticulo.frente().getDato());
        assertEquals(1, monticulo.tamanio());
    }

    // insertando desordenado, quitar debe devolver en orden ascendente
    public void testInsercionDesordenadaMantienePropiedadDeHeap() {
        MonticuloBinario<Integer> monticulo = new MonticuloBinario<Integer>(8);
        int[] prioridades = {7, 2, 9, 1, 5, 3, 8, 4, 6};
        for (int i = 0; i < prioridades.length; i++) {
            monticulo.insertar(new ElementoPrioridad<Integer>(prioridades[i], prioridades[i], i));
        }

        int anterior = Integer.MIN_VALUE;
        while (!monticulo.esVacia()) {
            int actual = monticulo.quitar().getDato().intValue();
            assertTrue(actual >= anterior);
            anterior = actual;
        }
    }

    private ElementoPrioridad<String> elem(String dato, int prioridad, long ordenLlegada) {
        return new ElementoPrioridad<String>(dato, prioridad, ordenLlegada);
    }
}