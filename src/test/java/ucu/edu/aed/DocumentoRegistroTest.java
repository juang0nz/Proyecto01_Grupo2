package ucu.edu.aed;

import java.util.Date;

import junit.framework.TestCase;
import ucu.edu.aed.auditoria.Interaccion;
import ucu.edu.aed.modelo.Cliente;
import ucu.edu.aed.modelo.Documento;
import ucu.edu.aed.modelo.Prioridad;
import ucu.edu.aed.modelo.TipoInteraccion;
import ucu.edu.aed.sucursal.Sucursal;

public class DocumentoRegistroTest extends TestCase {

    public void testRegistroDocumentalGuardaFechasYVigenciaEnHistorialYAuditoria() {
        Sucursal s = new Sucursal("SucursalDocs");
        Cliente c = new Cliente("ClienteDocs", 10, Prioridad.NORMAL);
        s.registrarCliente(c);

        long now = System.currentTimeMillis();
        Date fechaPresentacion = new Date(now - 5_000); // hace 5s
        Date fechaVigencia = new Date(now + 10_000); // vence en 10s

        Documento doc = new Documento(555, "Comprobante", fechaPresentacion, fechaVigencia);

        s.presentarDocumentacion(c, doc);

        // El documento debe estar en el conjunto de documentos del cliente
        assertEquals(1, c.getDocumentos().tamanio());
        Documento almacenado = c.getDocumentos().obtener(0);
        assertEquals(doc.getId(), almacenado.getId());
        assertEquals(doc.getTipo(), almacenado.getTipo());
        assertEquals(doc.getFechaPresentacion(), almacenado.getFechaPresentacion());
        assertEquals(doc.getFechaVencimiento(), almacenado.getFechaVencimiento());

        // Debe registrarse una interaccion de PRESENTACION_DOCUMENTACION en el historial del cliente
        assertEquals(1, c.getHistorial().tamanio());
        Interaccion hist = c.getHistorial().obtener(0);
        assertEquals(TipoInteraccion.PRESENTACION_DOCUMENTACION, hist.getTipo());
        assertTrue(hist.getDescripcion().contains("Presentación de documento"));

        // Auditoría de la sucursal debe contener esa interaccion
        assertEquals(1, s.cantidadInteraccionesPorTipo(TipoInteraccion.PRESENTACION_DOCUMENTACION));
        Interaccion ultima = s.ultimaInteraccionAuditada();
        assertNotNull(ultima);
        assertEquals(TipoInteraccion.PRESENTACION_DOCUMENTACION, ultima.getTipo());

        // Verificar vigencia: con fecha de referencia antes de vencimiento -> no vencido
        assertFalse(almacenado.estaVencido(new Date(now)));
        // Con fecha de referencia después de vencimiento -> vencido
        assertTrue(almacenado.estaVencido(new Date(now + 20_000)));
    }
}
