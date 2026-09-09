package ucu.edu.aed.comisiones;

public class ElementoFormula {

    private TipoElementoFormula tipo;
    private String valor;

    public ElementoFormula(TipoElementoFormula tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public TipoElementoFormula getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }
}