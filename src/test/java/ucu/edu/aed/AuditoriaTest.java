package ucu.edu.aed;

import junit.framework.TestCase;
import java.util.Date;

public class AuditoriaTest extends TestCase {

    public void testRegistroDeInteraccionesYAuditoria() {
        Sucursal s = new Sucursal("AuditSucursal");
        Cliente c = new Cliente("ClienteAudit", 100, Prioridad.NORMAL);
        s.registrarCliente(c);

        // acciones que generan interacciones: presentar documento, alta producto, baja producto, atención
        Documento doc = new Documento(300, "CI", new Date(), new Date(System.currentTimeMillis() + 10000));
        s.presentarDocumentacion(c, doc);

        ProductoBancario prod = new ProductoBancario(400, "Cuenta", new Date(), "ACTIVO");
        s.darAltaProducto(c, prod);

        // dar baja
        boolean baja = s.darBajaProducto(c, prod);
        assertTrue(baja);

        // solicitar y atender
        s.solicitarAtencion(c);
        Cliente atendido = s.atenderSiguiente();
        assertEquals(c, atendido);

        // Auditoria: debe tener 4 interacciones registradas (presentación, alta, baja, atención)
        int total = 0;
        for (int i = 0; i < s.getClientes().tamanio(); i++) {
            // no-op, just ensure getClientes works
        }

        // cantidad por tipo
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.PRESENTACION_DOCUMENTACION));
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.BAJA_PRODUCTO));
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.CONSULTA));

        // Ultima interaccion debe ser la atención
        Interaccion last = s.ultimaInteraccionAuditada();
        assertNotNull(last);
        assertEquals(TipoInteraccion.CONSULTA, last.getTipo());

        // Historial del cliente debe reflejar en orden cronológico las interacciones que le pertenecen
        assertEquals(4, c.getHistorial().tamanio());
        assertTrue(c.getHistorial().obtener(0).getTipo() == TipoInteraccion.PRESENTACION_DOCUMENTACION);
        assertTrue(c.getHistorial().obtener(1).getTipo() == TipoInteraccion.ALTA_PRODUCTO);
        assertTrue(c.getHistorial().obtener(2).getTipo() == TipoInteraccion.BAJA_PRODUCTO);
        assertTrue(c.getHistorial().obtener(3).getTipo() == TipoInteraccion.CONSULTA);
    }
}
