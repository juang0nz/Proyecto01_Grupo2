package ucu.edu.aed;

import junit.framework.TestCase;
import java.util.Date;

public class SucursalHistorialAuditTest extends TestCase {

    public void testClienteHistorialChronologico() {
        Cliente c = new Cliente("Alice", 1, Prioridad.NORMAL);
        Interaccion i1 = new Interaccion(new Date(1000), TipoInteraccion.CONSULTA, "first");
        Interaccion i2 = new Interaccion(new Date(2000), TipoInteraccion.CONSULTA, "second");
        c.registrarInteraccion(i1);
        c.registrarInteraccion(i2);

        assertEquals(2, c.getHistorial().tamanio());
        assertEquals("first", c.getHistorial().obtener(0).getDescripcion());
        assertEquals("second", c.getHistorial().obtener(1).getDescripcion());
    }

    public void testSucursalAuditoriaChronologicoAndCounts() {
        Sucursal s = new Sucursal("Central");
        Cliente c = new Cliente("Bob", 2, Prioridad.PRIORITARIA);
        boolean added = s.registrarCliente(c);
        // registro cliente debe retornar true
        assertTrue(added);

        Documento d = new Documento(10, "DNI", new Date(), new Date(System.currentTimeMillis() + 100000));
        ProductoBancario p = new ProductoBancario(20, "Cuenta", new Date(), "ACTIVO");

        s.presentarDocumentacion(c, d);
        s.darAltaProducto(c, p);

        // la última interacción auditada debe corresponder al alta de producto
        Interaccion last = s.ultimaInteraccionAuditada();
        assertNotNull(last);
        assertTrue(last.getDescripcion().contains("Alta de producto") || last.getDescripcion().contains("Alta de producto"));

        // contar por tipo
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.PRESENTACION_DOCUMENTACION));
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));

        // historial del cliente debe contener ambas interacciones en orden cronológico
        assertEquals(2, c.getHistorial().tamanio());
        assertTrue(c.getHistorial().obtener(0).getDescripcion().contains("Presentación de documento"));
        assertTrue(c.getHistorial().obtener(1).getDescripcion().contains("Alta de producto"));
    }
}
