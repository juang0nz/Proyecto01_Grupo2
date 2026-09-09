package ucu.edu.aed;

import ucu.edu.aed.implementaciones.ArbolBinario;
import ucu.edu.aed.tda.TDAElemento;

public class FormulaComision {

    private String textoOriginal;
    private ArbolBinario<ElementoFormula> arbol;

    public FormulaComision(String textoOriginal) {
        this.textoOriginal = textoOriginal;
        this.arbol = new ArbolBinario<>();
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    public ArbolBinario<ElementoFormula> getArbol() {
        return arbol;
    }

    public void setArbol(ArbolBinario<ElementoFormula> arbol) {
        this.arbol = arbol;
    }

    public String mostrarFormula() {
        return mostrarNodo(arbol.obtenerRaiz(), 0);
    }

    // Método recursivo para mostrar la fórmula con paréntesis según la prioridad de
    // los operadores.
    private String mostrarNodo(
            TDAElemento<ElementoFormula> nodo,
            int prioridadPadre) {

        ElementoFormula elemento = nodo.getDato();

        // Si es número o variable, simplemente lo devolvemos
        if (elemento.getTipo() != TipoElementoFormula.OPERADOR) {
            return elemento.getValor();
        }

        int prioridadActual = prioridad(elemento.getValor());

        String izquierda = mostrarNodo(
                nodo.getHijoIzquierdo(),
                prioridadActual);

        String derecha = mostrarNodo(
                nodo.getHijoDerecho(),
                prioridadActual);

        String expresion = izquierda + " "
                + elemento.getValor()
                + " " + derecha;

        if (prioridadActual < prioridadPadre) {
            return "(" + expresion + ")";
        }

        return expresion;
    }

    /**
     * Devuelve la prioridad de un operador.
     * Multiplicación y división tienen prioridad 2.
     * Suma y resta tienen prioridad 1.
     * Otros operadores tienen prioridad 0.
     */
    private int prioridad(String operador) {

        if (operador.equals("*") || operador.equals("/")) {
            return 2;
        }

        if (operador.equals("+") || operador.equals("-")) {
            return 1;
        }

        return 0;
    }

    public double evaluar(
            double saldo,
            double cantidadProductos) {

        return evaluarNodo(
                arbol.obtenerRaiz(),
                saldo,
                cantidadProductos);
    }

    // Método recursivo para evaluar la fórmula representada en el árbol.
    private double evaluarNodo(
            TDAElemento<ElementoFormula> nodo,
            double saldo,
            double cantidadProductos) {

        ElementoFormula elemento = nodo.getDato();

        // CASO 1: es un número
        if (elemento.getTipo() == TipoElementoFormula.NUMERO) {
            return Double.parseDouble(elemento.getValor());
        }

        // CASO 2: es una variable
        if (elemento.getTipo() == TipoElementoFormula.VARIABLE) {

            switch (elemento.getValor()) {

                case "saldo":
                    return saldo;

                case "cantidadProductos":
                    return cantidadProductos;

                default:
                    throw new IllegalArgumentException(
                            "Variable desconocida: " + elemento.getValor());
            }
        }

        // CASO 3: es un operador
        double izquierdo = evaluarNodo(
                nodo.getHijoIzquierdo(),
                saldo,
                cantidadProductos);

        double derecho = evaluarNodo(
                nodo.getHijoDerecho(),
                saldo,
                cantidadProductos);

        switch (elemento.getValor()) {

            case "+":
                return izquierdo + derecho;

            case "-":
                return izquierdo - derecho;

            case "*":
                return izquierdo * derecho;

            case "/":
                return izquierdo / derecho;

            default:
                throw new IllegalArgumentException(
                        "Operador desconocido: " + elemento.getValor());
        }

    }

}
