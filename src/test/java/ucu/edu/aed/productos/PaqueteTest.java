package ucu.edu.aed.productos;

import junit.framework.TestCase;
import ucu.edu.aed.comisiones.SaldoPorMoneda;
import ucu.edu.aed.modelo.Cliente;
import ucu.edu.aed.modelo.Prioridad;
import ucu.edu.aed.tda.TDALista;

public class PaqueteTest extends TestCase {

    public void testFabricaCreaPaquetesConRaiz() {
        FabricaPaquetes fabrica = new FabricaPaquetes();
        Paquete oro = fabrica.crearPaqueteOro();

        assertNotNull(oro);
        assertEquals(0, oro.getRaiz().getId());
        assertNotNull(fabrica.crearPaquetePremium());
        assertNotNull(fabrica.crearPaqueteBasico());
    }

    // contratar da de alta todos los componentes del paquete (6, sin la raiz)
    public void testContratarAgregaTodosLosComponentesAlCliente() {
        Paquete oro = new FabricaPaquetes().crearPaqueteOro();
        Cliente cliente = new Cliente("Leo", 1, Prioridad.NORMAL);

        oro.contratar(cliente);

        assertEquals(6, cliente.getProductos().tamanio());
    }

    // la posicion consolidada agrupa los saldos por moneda
    public void testPosicionConsolidadaPorMoneda() {
        Paquete oro = new FabricaPaquetes().crearPaqueteOro();
        TDALista<SaldoPorMoneda> posicion = oro.posicionConsolidada();

        // UYU: 0 + 15000 + 3000 - 2000 - 500 + 0 = 15500
        assertEquals(15500.0, saldoDe(posicion, "UYU"), 0.0001);
        assertEquals(500.0, saldoDe(posicion, "USD"), 0.0001);
    }

    // obtener el subarbol de la tarjeta (id 3) trae la tarjeta y su extension (id 4)
    public void testObtenerSubarbolTraeNodoYDescendientes() {
        Paquete oro = new FabricaPaquetes().crearPaqueteOro();

        assertEquals(2, oro.obtenerSubarbol(3).tamanio());
    }

    // regla del grupo: la baja de un componente da de baja tambien lo que contiene,
    // pero no afecta a los hermanos
    public void testBajaDeComponenteAplicaLaReglaDeCascada() {
        Paquete oro = new FabricaPaquetes().crearPaqueteOro();

        assertTrue(oro.darBajaProducto(3));

        // la tarjeta y su extension desaparecen
        assertEquals(0, oro.obtenerSubarbol(3).tamanio());
        assertEquals(0, oro.obtenerSubarbol(4).tamanio());

        // los hermanos siguen: cuenta(1)+auxiliar(6), caja de ahorros(2), seguro(5)
        assertEquals(2, oro.obtenerSubarbol(1).tamanio());
        assertEquals(1, oro.obtenerSubarbol(2).tamanio());
        assertEquals(1, oro.obtenerSubarbol(5).tamanio());

        // la consolidacion refleja la baja: UYU pasa a 18000
        TDALista<SaldoPorMoneda> posicion = oro.posicionConsolidada();
        assertEquals(18000.0, saldoDe(posicion, "UYU"), 0.0001);
        assertEquals(500.0, saldoDe(posicion, "USD"), 0.0001);
    }

    private double saldoDe(TDALista<SaldoPorMoneda> lista, String moneda) {
        for (int i = 0; i < lista.tamanio(); i++) {
            if (lista.obtener(i).getMoneda().equals(moneda)) {
                return lista.obtener(i).getTotal();
            }
        }
        return 0.0;
    }
}
