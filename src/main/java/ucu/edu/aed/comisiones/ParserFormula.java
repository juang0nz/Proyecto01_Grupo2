package ucu.edu.aed.comisiones;

import ucu.edu.aed.implementaciones.ArbolBinario;
import ucu.edu.aed.implementaciones.ElementoAB;
import ucu.edu.aed.implementaciones.TDAListaConArregloImpl;
import ucu.edu.aed.implementaciones.TDAPilaImpl;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;
import ucu.edu.aed.tda.TDAPila;




public class ParserFormula {
    
//este metodo lo que hace es tokenizar una formula en una lista de strings, separando operadores, paréntesis y operandos.
//para de esta manera, se facilita el análisis posterior de la fórmula.
    public TDALista<String> tokenizar(String formula) {

        TDALista<String> tokens = new TDAListaConArregloImpl<>();
        String actual = "";

        for (int i = 0; i < formula.length(); i++) {

            char caracter = formula.charAt(i);

            if (caracter == ' ') {
                continue;
            }

            if (esOperador(caracter) || caracter == '(' || caracter == ')') {

                if (!actual.isEmpty()) {
                    tokens.agregar(actual);
                    actual = "";
                }

                tokens.agregar(String.valueOf(caracter));

            } else {

                actual += caracter;
            }
        }

        if (!actual.isEmpty()) {
            tokens.agregar(actual);
        }

        return tokens;
    }
//este metodo verifica si un caracter es un operador aritmético básico (+, -, *, /).
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

//este metodo devuelve la prioridad de un operador aritmético, donde '*' y '/' tienen mayor prioridad que '+' y '-'.

    private int prioridad(String operador) {

    if (operador.equals("*") || operador.equals("/")) {
        return 2;
    }

    if (operador.equals("+") || operador.equals("-")) {
        return 1;
    }

    return 0;
}

public TDALista<String> convertirAPostfija(TDALista<String> tokens) {

    TDALista<String> salida = new TDAListaConArregloImpl<>();

    // Acá usamos TU implementación concreta de TDAPila
    TDAPila<String> operadores = new TDAPilaImpl<>();
    for (int i = 0; i < tokens.tamanio(); i++) {

        String token = tokens.obtener(i);

        // Si no es operador ni paréntesis,
        // entonces es un número o una variable
        if (!esOperador(token.charAt(0))
                && !token.equals("(")
                && !token.equals(")")) {

            salida.agregar(token);
        }

        // Paréntesis izquierdo
        else if (token.equals("(")) {

            operadores.mete(token);
        }

        // Paréntesis derecho
        else if (token.equals(")")) {

            while (!operadores.esVacio()
                    && !operadores.tope().equals("(")) {

                salida.agregar(operadores.saca());
            }

            // Sacamos el "(" pero NO lo agregamos a la salida
            if (!operadores.esVacio()) {
                operadores.saca();
            }
        }

        // Si llegamos acá, es un operador
        else {

            while (!operadores.esVacio()
                    && !operadores.tope().equals("(")
                    && prioridad(operadores.tope()) >= prioridad(token)) {

                salida.agregar(operadores.saca());
            }

            operadores.mete(token);
        }
    }

    // Cuando terminamos, vaciamos los operadores restantes
    while (!operadores.esVacio()) {
        salida.agregar(operadores.saca());
    }

    return salida;
}

//este metodo va a pasar a construir un árbol binario a partir de una expresión en notación postfija
//es lo que necesitamos para evaluar la expresión posteriormente
public ArbolBinario<ElementoFormula> construirArbol(
        TDALista<String> postfija) {

    TDAPila<TDAElemento<ElementoFormula>> pila =
            new TDAPilaImpl<>();

    for (int i = 0; i < postfija.tamanio(); i++) {

        String token = postfija.obtener(i);

        if (!esOperador(token.charAt(0))) {

            ElementoFormula dato;

            if (esNumero(token)) {
                dato = new ElementoFormula(
                        TipoElementoFormula.NUMERO,
                        token
                );
            } else {
                dato = new ElementoFormula(
                        TipoElementoFormula.VARIABLE,
                        token
                );
            }

            ElementoAB<ElementoFormula> nodo =
                    new ElementoAB<>(dato);

            pila.mete(nodo);

        } else {

            TDAElemento<ElementoFormula> derecho =
                    pila.saca();

            TDAElemento<ElementoFormula> izquierdo =
                    pila.saca();

            ElementoFormula operador =
                    new ElementoFormula(
                            TipoElementoFormula.OPERADOR,
                            token
                    );

            ElementoAB<ElementoFormula> nodoOperador =
                    new ElementoAB<>(operador);

            nodoOperador.setHijoIzquierdo(izquierdo);
            nodoOperador.setHijoDerecho(derecho);

            pila.mete(nodoOperador);
        }
    }

    ArbolBinario<ElementoFormula> arbol =
            new ArbolBinario<>();

    arbol.setRaiz(pila.saca());

    return arbol;
}

private boolean esNumero(String token) {

    try {
        Double.parseDouble(token);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}



}
