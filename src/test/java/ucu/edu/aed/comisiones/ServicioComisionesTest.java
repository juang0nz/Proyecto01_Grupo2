package ucu.edu.aed.comisiones;

import java.util.Date;

import junit.framework.TestCase;
import ucu.edu.aed.modelo.Cliente;
import ucu.edu.aed.modelo.Prioridad;
import ucu.edu.aed.modelo.ProductoBancario;

public class ServicioComisionesTest extends TestCase {

    // saldo UYU = 1500, cantidad productos UYU = 2  ->  1500 * 0.01 + 2 = 17
    public void testLiquidarSumaSaldoYCuentaProductosDeLaMoneda() {
        ServicioComisiones servicio =
                new ServicioComisiones(crearFormula("saldo * 0.01 + cantidadProductos"));

        assertEquals(17.0, servicio.liquidarCliente(crearCliente(), "UYU"), 0.0001);
    }

    // filtra por moneda: saldo USD = 200, cantidad USD = 1  ->  200 * 0.01 + 1 = 3
    public void testLiquidarFiltraPorMoneda() {
        ServicioComisiones servicio =
                new ServicioComisiones(crearFormula("saldo * 0.01 + cantidadProductos"));

        assertEquals(3.0, servicio.liquidarCliente(crearCliente(), "USD"), 0.0001);
    }

    // sin productos en esa moneda la comision da 0
    public void testLiquidarMonedaSinProductos() {
        ServicioComisiones servicio =
                new ServicioComisiones(crearFormula("saldo * 0.01 + cantidadProductos"));

        assertEquals(0.0, servicio.liquidarCliente(crearCliente(), "EUR"), 0.0001);
    }

    // simular no debe cambiar la formula vigente
    public void testSimularNoModificaLaFormulaVigente() {
        FormulaComision vigente = crearFormula("saldo * 0.01 + cantidadProductos");
        ServicioComisiones servicio = new ServicioComisiones(vigente);
        Cliente cliente = crearCliente();

        // 1500 * 0.05 = 75
        assertEquals(75.0,
                servicio.simularCliente(cliente, "UYU", crearFormula("saldo * 0.05")), 0.0001);

        assertSame(vigente, servicio.getFormulaVigente());
        assertEquals(17.0, servicio.liquidarCliente(cliente, "UYU"), 0.0001);
    }

    private Cliente crearCliente() {
        Cliente cliente = new Cliente("Ana", 1, Prioridad.NORMAL);
        cliente.agregarProducto(new ProductoBancario(1, "CC", new Date(), "activo", 1000.0, "UYU"));
        cliente.agregarProducto(new ProductoBancario(2, "CA", new Date(), "activo", 500.0, "UYU"));
        cliente.agregarProducto(new ProductoBancario(3, "Cuenta USD", new Date(), "activo", 200.0, "USD"));
        return cliente;
    }

    private FormulaComision crearFormula(String texto) {
        ParserFormula parser = new ParserFormula();
        FormulaComision formula = new FormulaComision(texto);
        formula.setArbol(parser.construirArbol(
                parser.convertirAPostfija(parser.tokenizar(texto))));
        return formula;
    }
}
