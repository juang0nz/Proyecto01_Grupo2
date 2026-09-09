package ucu.edu.aed;

import junit.framework.TestCase;
import ucu.edu.aed.modelo.Cliente;
import ucu.edu.aed.modelo.Prioridad;
import ucu.edu.aed.sucursal.Sucursal;

public class AtencionMostradorTest extends TestCase {

    public void testGestionPrioridadEnMostrador() {
        Sucursal s = new Sucursal("TestSucursal");

        Cliente c1 = new Cliente("Cliente1", 1, Prioridad.NORMAL);
        Cliente c2 = new Cliente("Cliente2", 2, Prioridad.PRIORITARIA);
        Cliente c3 = new Cliente("Cliente3", 3, Prioridad.PRIORITARIA);
        Cliente c4 = new Cliente("Cliente4", 4, Prioridad.NORMAL);

        // Registrar opcionalmente
        s.registrarCliente(c1);
        s.registrarCliente(c2);
        s.registrarCliente(c3);
        s.registrarCliente(c4);

        // Llegan a mostrador en este orden
        s.solicitarAtencion(c1);
        s.solicitarAtencion(c2);
        s.solicitarAtencion(c3);
        s.solicitarAtencion(c4);

        // Esperamos que los prioritarios se atiendan primero, en orden de llegada
        assertTrue(s.hayClientesEnEspera());

        // 1er atendido: c2
        assertEquals(c2, s.proximoAAtender());
        Cliente atendido1 = s.atenderSiguiente();
        assertEquals(c2, atendido1);

        // 2do atendido: c3
        assertEquals(c3, s.proximoAAtender());
        Cliente atendido2 = s.atenderSiguiente();
        assertEquals(c3, atendido2);

        // 3er atendido: c1 (primer normal)
        assertEquals(c1, s.proximoAAtender());
        Cliente atendido3 = s.atenderSiguiente();
        assertEquals(c1, atendido3);

        // 4to atendido: c4 (segundo normal)
        assertEquals(c4, s.proximoAAtender());
        Cliente atendido4 = s.atenderSiguiente();
        assertEquals(c4, atendido4);

        // ahora no hay más
        assertFalse(s.hayClientesEnEspera());
    }
}
