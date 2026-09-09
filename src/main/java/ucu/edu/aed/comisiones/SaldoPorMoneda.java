package ucu.edu.aed.comisiones;

public class SaldoPorMoneda {

    private String moneda;
    private double total;

    public SaldoPorMoneda(String moneda, double total) {
        this.moneda = moneda;
        this.total = total;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getTotal() {
        return total;
    }

    public void sumar(double monto) {
        this.total += monto;
    }

    @Override
    public String toString() {
        return moneda + ": " + total;
    }
}