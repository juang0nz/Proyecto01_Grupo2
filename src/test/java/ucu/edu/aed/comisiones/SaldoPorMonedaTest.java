package ucu.edu.aed.comisiones;

import junit.framework.TestCase;

public class SaldoPorMonedaTest extends TestCase {

    public void testConstructorYGetters() {
        SaldoPorMoneda saldo = new SaldoPorMoneda("UYU", 100.0);

        assertEquals("UYU", saldo.getMoneda());
        assertEquals(100.0, saldo.getTotal(), 0.0001);
    }

    // sumar acumula sobre el total
    public void testSumar() {
        SaldoPorMoneda saldo = new SaldoPorMoneda("USD", 0.0);
        saldo.sumar(50.0);
        saldo.sumar(25.5);

        assertEquals(75.5, saldo.getTotal(), 0.0001);
    }

    // sumar tambien acepta montos negativos
    public void testSumarMontoNegativo() {
        SaldoPorMoneda saldo = new SaldoPorMoneda("UYU", 1000.0);
        saldo.sumar(-400.0);

        assertEquals(600.0, saldo.getTotal(), 0.0001);
    }

    public void testToString() {
        SaldoPorMoneda saldo = new SaldoPorMoneda("UYU", 200.0);

        assertEquals("UYU: 200.0", saldo.toString());
    }
}
