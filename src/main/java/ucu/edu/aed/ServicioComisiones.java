package ucu.edu.aed;

import ucu.edu.aed.tda.TDAConjunto;

public class ServicioComisiones {

    private FormulaComision formulaVigente;

    public ServicioComisiones(FormulaComision formulaVigente) {
        this.formulaVigente = formulaVigente;
    }

    public FormulaComision getFormulaVigente() {
        return formulaVigente;
    }

    public void setFormulaVigente(FormulaComision formulaVigente) {
        this.formulaVigente = formulaVigente;
    }

    /*
     * Liquida la comisión de un cliente utilizando
     * la fórmula actualmente vigente.
     */
    public double liquidarCliente(Cliente cliente, String moneda) {

        double saldo = calcularSaldo(cliente, moneda);

        double cantidadProductos =
                contarProductos(cliente, moneda);

        return formulaVigente.evaluar(
                saldo,
                cantidadProductos
        );
    }

    /*
     * Simula una fórmula nueva sobre el cliente,
     * pero NO modifica la fórmula vigente.
     */
    public double simularCliente(
            Cliente cliente,
            String moneda,
            FormulaComision formulaNueva) {

        double saldo = calcularSaldo(cliente, moneda);

        double cantidadProductos =
                contarProductos(cliente, moneda);

        return formulaNueva.evaluar(
                saldo,
                cantidadProductos
        );
    }

    /*
     * Calcula el saldo de los productos del cliente
     * correspondientes a una moneda determinada.
     */
    private double calcularSaldo(
            Cliente cliente,
            String moneda) {

        double total = 0;

        TDAConjunto<ProductoBancario> productos =
                cliente.getProductos();

        for (int i = 0; i < productos.tamanio(); i++) {

            ProductoBancario producto =
                    productos.obtener(i);

            if (producto.getMoneda().equals(moneda)) {
                total += producto.getSaldo();
            }
        }

        return total;
    }

    /*
     * Cuenta cuántos productos tiene el cliente
     * en la moneda indicada.
     */
    private int contarProductos(
            Cliente cliente,
            String moneda) {

        int cantidad = 0;

        TDAConjunto<ProductoBancario> productos =
                cliente.getProductos();

        for (int i = 0; i < productos.tamanio(); i++) {

            ProductoBancario producto =
                    productos.obtener(i);

            if (producto.getMoneda().equals(moneda)) {
                cantidad++;
            }
        }

        return cantidad;
    }
}