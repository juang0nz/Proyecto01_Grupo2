package ucu.edu.aed;

import java.util.Date;

import junit.framework.TestCase;
import ucu.edu.aed.modelo.Cliente;
import ucu.edu.aed.modelo.Documento;
import ucu.edu.aed.modelo.Empleado;
import ucu.edu.aed.modelo.Prioridad;
import ucu.edu.aed.modelo.ProductoBancario;
import ucu.edu.aed.modelo.Sector;
import ucu.edu.aed.modelo.TipoInteraccion;
import ucu.edu.aed.sucursal.Sucursal;
import ucu.edu.aed.tda.TDAConjunto;
import ucu.edu.aed.tda.TDALista;

public class OperacionesRelevantesTest extends TestCase {

    public void testOperacionesRelevantes() {
        Sucursal s = new Sucursal("SucursalOps");

        // empleados / sectores
        Sector prestamos = new Sector("Prestamos");
        Empleado emp = new Empleado("Lucas", 1, prestamos);
        s.registrarEmpleado(emp);

        // clientes
        Cliente c1 = new Cliente("Cliente1", 11, Prioridad.NORMAL);
        Cliente c2 = new Cliente("Cliente2", 12, Prioridad.PRIORITARIA);
        s.registrarCliente(c1);
        s.registrarCliente(c2);

        // productos
        ProductoBancario pb1 = new ProductoBancario(201, "Cuenta Corriente", new Date(), "ACTIVO");
        ProductoBancario pb2 = new ProductoBancario(202, "Cuenta Corriente", new Date(), "ACTIVO");
        ProductoBancario pb3 = new ProductoBancario(203, "Prestamo", new Date(), "ACTIVO");
        s.darAltaProducto(c1, pb1);
        s.darAltaProducto(c2, pb2);
        s.darAltaProducto(c1, pb3);

        // documentos: uno vencido, uno vigente
        long now = System.currentTimeMillis();
        Documento dVencido = new Documento(301, "Comprobante", new Date(now - 10_000), new Date(now - 5_000));
        Documento dVigente = new Documento(302, "Comprobante", new Date(now - 2_000), new Date(now + 10_000));
        s.presentarDocumentacion(c1, dVencido);
        s.presentarDocumentacion(c2, dVigente);

        // 1) Buscar todos los productos de un tipo
        TDALista<ProductoBancario> cuentas = s.buscarProductosPorTipo("Cuenta Corriente");
        assertEquals(2, cuentas.tamanio());

        // 2) Buscar documentos vencidos (hasta ahora)
        TDALista<Documento> vencidos = s.documentosVencidos(new Date(now));
        assertEquals(1, vencidos.tamanio());
        assertEquals(dVencido.getId(), vencidos.obtener(0).getId());

        // 3) Obtener clientes prioritarios
        TDAConjunto<Cliente> prioridad = s.clientesPrioritarios();
        assertEquals(1, prioridad.tamanio());
        assertEquals(c2, prioridad.obtener(0));

        // 4) Contar interacciones por tipo (ALTAS de producto -> 3)
        assertEquals(3, s.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));

        // 5) Empleados por sector
        TDALista<Empleado> empPrest = s.empleadosPorSector(prestamos);
        assertEquals(1, empPrest.tamanio());
        assertEquals(emp, empPrest.obtener(0));
    }
}
