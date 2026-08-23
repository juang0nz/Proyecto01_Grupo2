package ucu.edu.aed;

import junit.framework.TestCase;
import java.util.Date;

public class RegistrarClienteTest extends TestCase {

    public void testRegistrarClienteYObtener() {
        Sucursal s = new Sucursal("SucursalTest");
        Cliente c = new Cliente("Ana", 1, Prioridad.NORMAL);
        assertTrue(s.registrarCliente(c));
        assertTrue(s.getClientes().contiene(c));
        assertEquals(1, s.getClientes().tamanio());
    }

    public void testRegistrarClienteDuplicadoNoSeRegistra() {
        Sucursal s = new Sucursal("SucursalTest");
        Cliente c = new Cliente("Ana", 1, Prioridad.NORMAL);
        assertTrue(s.registrarCliente(c));
        assertFalse(s.registrarCliente(c));
        assertEquals(1, s.getClientes().tamanio());
    }

    public void testClienteProductosInicialesVacios() {
        Sucursal s = new Sucursal("SucursalTest");
        Cliente c = new Cliente("Ana", 1, Prioridad.NORMAL);
        s.registrarCliente(c);
        assertEquals(0, c.getProductos().tamanio());
    }

    public void testAgregarProductoAlCliente() {
        Sucursal s = new Sucursal("SucursalTest");
        Cliente c = new Cliente("Ana", 1, Prioridad.NORMAL);
        s.registrarCliente(c);
        ProductoBancario p = new ProductoBancario(10, "Caja Ahorro", new Date(), "ACTIVO");
        s.darAltaProducto(c, p);
        assertEquals(1, c.getProductos().tamanio());
        assertTrue(c.getProductos().contiene(p));
    }

    public void testRegistrarVariosClientes() {
        Sucursal s = new Sucursal("SucursalTest");
        Cliente c1 = new Cliente("A", 1, Prioridad.NORMAL);
        Cliente c2 = new Cliente("B", 2, Prioridad.PRIORITARIA);
        assertTrue(s.registrarCliente(c1));
        assertTrue(s.registrarCliente(c2));
        assertEquals(2, s.getClientes().tamanio());
    }
}
