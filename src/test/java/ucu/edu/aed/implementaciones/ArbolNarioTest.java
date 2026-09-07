package ucu.edu.aed.implementaciones;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import ucu.edu.aed.tda.TDAElementoNario;
import ucu.edu.aed.tda.TDALista;

/**
 * Tests para la implementación {@link ArbolNario}.
 * Estilo JUnit 3 (extends TestCase) consistente con el resto del proyecto.
 */
public class ArbolNarioTest extends TestCase {

    public void testArbolVacio() {
        ArbolNario<Integer> arbol = new ArbolNario<>();

        assertTrue(arbol.esVacio());
        assertNull(arbol.obtenerRaiz());
        assertNull(arbol.buscar(1));

        final List<Integer> resultado = new ArrayList<>();
        arbol.preOrden(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                resultado.add(integer);
            }
        });
        assertTrue(resultado.isEmpty());

        resultado.clear();
        arbol.postOrden(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                resultado.add(integer);
            }
        });
        assertTrue(resultado.isEmpty());

        resultado.clear();
        arbol.porNiveles(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                resultado.add(integer);
            }
        });
        assertTrue(resultado.isEmpty());
    }

    public void testInsertarRaizYBuscarRaiz() {
        ArbolNario<Integer> arbol = new ArbolNario<>();
        arbol.insertar(1); // inserta la raiz

        assertFalse(arbol.esVacio());
        assertNotNull(arbol.obtenerRaiz());
        assertEquals(Integer.valueOf(1), arbol.obtenerRaiz().getDato());
        assertEquals(Integer.valueOf(1), arbol.buscar(1));
    }

    public void testAgregarVariosHijosALaRaiz() {
        ArbolNario<Integer> arbol = new ArbolNario<>();
        arbol.insertar(1);
        TDAElementoNario<Integer> raiz = arbol.obtenerRaiz();

        ElementoNario<Integer> h2 = new ElementoNario<>(2);
        ElementoNario<Integer> h3 = new ElementoNario<>(3);
        ElementoNario<Integer> h4 = new ElementoNario<>(4);

        raiz.agregarHijo(h2);
        raiz.agregarHijo(h3);
        raiz.agregarHijo(h4);

        TDALista<TDAElementoNario<Integer>> hijos = raiz.getHijos();
        assertEquals(3, hijos.tamanio());
        assertEquals(Integer.valueOf(2), hijos.obtener(0).getDato());
        assertEquals(Integer.valueOf(3), hijos.obtener(1).getDato());
        assertEquals(Integer.valueOf(4), hijos.obtener(2).getDato());
    }

    public void testInsertarHijosEnDiferentesNivelesYProfundidadIlimitada() {
        // contruir un árbol con profundidad > 3
        ArbolNario<Integer> arbol = new ArbolNario<>();
        arbol.insertar(1);
        TDAElementoNario<Integer> r = arbol.obtenerRaiz();

        ElementoNario<Integer> n2 = new ElementoNario<>(2);
        ElementoNario<Integer> n3 = new ElementoNario<>(3);
        r.agregarHijo(n2);
        r.agregarHijo(n3);

        ElementoNario<Integer> n5 = new ElementoNario<>(5);
        ElementoNario<Integer> n6 = new ElementoNario<>(6);
        n2.agregarHijo(n5);
        n5.agregarHijo(n6); // profundidad 3 from root (1->2->5->6)

        assertEquals(Integer.valueOf(6), arbol.buscar(6));
        // comprobar que buscar en niveles intermedios funciona
        assertEquals(Integer.valueOf(5), arbol.buscar(5));
    }

    public void testBuscarIntermediosYHojasYElementoInexistente() {
        ArbolNario<Integer> arbol = crearArbolEjemplo();

        assertEquals(Integer.valueOf(1), arbol.buscar(1)); // raiz
        assertEquals(Integer.valueOf(5), arbol.buscar(5)); // intermedio
        assertEquals(Integer.valueOf(8), arbol.buscar(8)); // hoja
        assertNull(arbol.buscar(99)); // inexistente
    }

    public void testRecorridosPrePostYNiveles() {
        ArbolNario<Integer> arbol = crearArbolEjemplo();

        List<Integer> esperadoPre = Arrays.asList(1, 2, 5, 8, 6, 3, 7, 4);
        List<Integer> esperadoPost = Arrays.asList(8, 5, 6, 2, 7, 3, 4, 1);
        List<Integer> esperadoNiveles = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        assertEquals(esperadoPre, recorrerPreOrden(arbol));
        assertEquals(esperadoPost, recorrerPostOrden(arbol));
        assertEquals(esperadoNiveles, recorrerPorNiveles(arbol));

        // comprobar que porNiveles visita primero la raiz, luego todos los nodos del segundo nivel, etc.
        List<Integer> porNiveles = recorrerPorNiveles(arbol);
        assertEquals(Integer.valueOf(1), porNiveles.get(0)); // raiz primero
        // segundo nivel (índices 1..3) deben ser 2,3,4
        assertEquals(Arrays.asList(2, 3, 4), porNiveles.subList(1, 4));
        // tercero nivel contiene 5,6,7 (índices 4..6)
        assertEquals(Arrays.asList(5, 6, 7), porNiveles.subList(4, 7));
        // cuarto nivel sólo 8 at index 7
        assertEquals(Integer.valueOf(8), porNiveles.get(7));
    }

    public void testRecorridosSobreArbolVacioDevuelvenListaVacia() {
        ArbolNario<Integer> arbol = new ArbolNario<>();
        assertTrue(recorrerPreOrden(arbol).isEmpty());
        assertTrue(recorrerPostOrden(arbol).isEmpty());
        assertTrue(recorrerPorNiveles(arbol).isEmpty());
    }

    public void testEliminarHoja() {
        ArbolNario<Integer> arbol = crearArbolEjemplo();

        // localizar nodo 5 y eliminar su hijo 8 (hoja)
        TDAElementoNario<Integer> raiz = arbol.obtenerRaiz();
        TDALista<TDAElementoNario<Integer>> hijosRaiz = raiz.getHijos();
        // encontrar 2 en hijos de raiz
        TDAElementoNario<Integer> nodo2 = null;
        for (int i = 0; i < hijosRaiz.tamanio(); i++) {
            if (hijosRaiz.obtener(i).getDato().equals(2)) {
                nodo2 = hijosRaiz.obtener(i);
                break;
            }
        }
        assertNotNull(nodo2);
        // nodo2 tiene hijo 5 como primer hijo
        TDALista<TDAElementoNario<Integer>> hijos2 = nodo2.getHijos();
        TDAElementoNario<Integer> nodo5 = hijos2.obtener(0);
        assertEquals(Integer.valueOf(5), nodo5.getDato());

        // eliminar hoja 8 desde nodo5
        boolean eliminado = nodo5.eliminarHijo(8);
        assertTrue(eliminado);
        assertNull(arbol.buscar(8));

        // por niveles ya no contiene 8
        List<Integer> niveles = recorrerPorNiveles(arbol);
        assertFalse(niveles.contains(8));
    }

    public void testEliminarNodoConHijosRespetaReglaImplementada() {
        ArbolNario<Integer> arbol = crearArbolEjemplo();

        TDAElementoNario<Integer> raiz = arbol.obtenerRaiz();
        // eliminar el hijo 2 (que tiene hijos 5,6,8)
        boolean eliminado = raiz.eliminarHijo(2);
        assertTrue(eliminado);

        // 2 y su subárbol deben desaparecer
        assertNull(arbol.buscar(2));
        assertNull(arbol.buscar(5));
        assertNull(arbol.buscar(6));
        assertNull(arbol.buscar(8));

        // los nodos restantes: 1,3,4,7
        List<Integer> esperado = Arrays.asList(1, 3, 4, 7);
        assertEquals(esperado, recorrerPorNiveles(arbol));
    }

    public void testEliminarElementoInexistenteDevuelveFalse() {
        ArbolNario<Integer> arbol = crearArbolEjemplo();
        TDAElementoNario<Integer> raiz = arbol.obtenerRaiz();

        assertFalse(raiz.eliminarHijo(99));
    }

    public void testCasosBordeQueNoDebenFallar() {
        // intentos de eliminar en árbol vacío: obtenerRaiz() es null -> no llamar methods sobre null
        ArbolNario<Integer> arbol = new ArbolNario<>();
        assertNull(arbol.obtenerRaiz());

        // crear árbol con sólo raíz y eliminar un hijo inexistente
        arbol.insertar(10);
        TDAElementoNario<Integer> raiz = arbol.obtenerRaiz();
        assertFalse(raiz.eliminarHijo(5)); // no debe lanzar excepción

        // agregar y eliminar repetidamente
        ElementoNario<Integer> a = new ElementoNario<>(20);
        raiz.agregarHijo(a);
        assertTrue(raiz.eliminarHijo(20));
        assertFalse(raiz.eliminarHijo(20));
    }

    // ---------- helpers ---------------------------------

    private ArbolNario<Integer> crearArbolEjemplo() {
        // Construye el árbol especificado en la consigna:
        //             1
        //          /  |  \
        //         2   3   4
        //        / \   \
        //       5   6   7
        //      /
        //     8
        ArbolNario<Integer> arbol = new ArbolNario<>();
        arbol.insertar(1);
        TDAElementoNario<Integer> r = arbol.obtenerRaiz();

        ElementoNario<Integer> n2 = new ElementoNario<>(2);
        ElementoNario<Integer> n3 = new ElementoNario<>(3);
        ElementoNario<Integer> n4 = new ElementoNario<>(4);

        r.agregarHijo(n2);
        r.agregarHijo(n3);
        r.agregarHijo(n4);

        ElementoNario<Integer> n5 = new ElementoNario<>(5);
        ElementoNario<Integer> n6 = new ElementoNario<>(6);
        ElementoNario<Integer> n7 = new ElementoNario<>(7);
        ElementoNario<Integer> n8 = new ElementoNario<>(8);

        n2.agregarHijo(n5);
        n2.agregarHijo(n6);
        n5.agregarHijo(n8);
        n3.agregarHijo(n7);

        return arbol;
    }

    private List<Integer> recorrerPreOrden(ArbolNario<Integer> arbol) {
        final List<Integer> resultado = new ArrayList<>();
        arbol.preOrden(new Consumer<Integer>() {
            @Override
            public void accept(Integer elemento) {
                resultado.add(elemento);
            }
        });
        return resultado;
    }

    private List<Integer> recorrerPostOrden(ArbolNario<Integer> arbol) {
        final List<Integer> resultado = new ArrayList<>();
        arbol.postOrden(new Consumer<Integer>() {
            @Override
            public void accept(Integer elemento) {
                resultado.add(elemento);
            }
        });
        return resultado;
    }

    private List<Integer> recorrerPorNiveles(ArbolNario<Integer> arbol) {
        final List<Integer> resultado = new ArrayList<>();
        arbol.porNiveles(new Consumer<Integer>() {
            @Override
            public void accept(Integer elemento) {
                resultado.add(elemento);
            }
        });
        return resultado;
    }
}
