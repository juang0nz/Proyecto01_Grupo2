package ucu.edu.aed;

import java.util.Date;

public class ProductoBancario implements Comparable<ProductoBancario> {
    private int id;
    public String tipo;
    private Date fechaAlta;
    private String estado;
    public double saldo;
    public String moneda;

    // constructor viejo: se mantiene por compatibilidad, saldo y moneda quedan en 0/UYU por defecto
    public ProductoBancario(int id, String tipo, Date fechaAlta, String estado) {
        this(id, tipo, fechaAlta, estado, 0.0, "UYU");
    }

    // constructor nuevo: permite indicar saldo y moneda
    public ProductoBancario(int id, String tipo, Date fechaAlta, String estado, double saldo, String moneda) {
        this.id = id;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
        this.saldo = saldo;
        this.moneda = moneda;
    }

    // getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public int compareTo(ProductoBancario otro) {
        return Integer.compare(this.id, otro.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductoBancario)) {
            return false;
        }
        return this.id == ((ProductoBancario) obj).id;
    }

    @Override
    public String toString() {
        return tipo;
    }

}