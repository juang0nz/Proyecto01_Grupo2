package ucu.edu.aed.comisiones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import ucu.edu.aed.implementaciones.ArbolBinario;
import ucu.edu.aed.tda.TDAElemento;
import ucu.edu.aed.tda.TDALista;

public class ParserFormulaTest extends TestCase {

    public void testTokenizarSimple() {
        ParserFormula parser = new ParserFormula();

        assertEquals(Arrays.asList("saldo", "+", "2"),
                aLista(parser.tokenizar("saldo+2")));
    }

    // ignora espacios y separa los parentesis como tokens
    public void testTokenizarConEspaciosYParentesis() {
        ParserFormula parser = new ParserFormula();

        assertEquals(Arrays.asList("(", "saldo", "+", "2", ")", "*", "3"),
                aLista(parser.tokenizar("( saldo + 2 ) * 3")));
    }

    // un decimal y una variable larga quedan como un unico token cada uno
    public void testTokenizarNumeroDecimalYVariableLarga() {
        ParserFormula parser = new ParserFormula();

        assertEquals(Arrays.asList("saldo", "*", "0.01", "+", "cantidadProductos"),
                aLista(parser.tokenizar("saldo * 0.01 + cantidadProductos")));
    }

    // '*' tiene mas prioridad que '+'
    public void testConvertirAPostfijaRespetaPrecedencia() {
        ParserFormula parser = new ParserFormula();

        assertEquals(Arrays.asList("saldo", "2", "3", "*", "+"),
                aLista(parser.convertirAPostfija(parser.tokenizar("saldo + 2 * 3"))));
    }

    // los parentesis cambian el orden de evaluacion
    public void testConvertirAPostfijaConParentesis() {
        ParserFormula parser = new ParserFormula();

        assertEquals(Arrays.asList("saldo", "2", "+", "3", "*"),
                aLista(parser.convertirAPostfija(parser.tokenizar("( saldo + 2 ) * 3"))));
    }

    // la raiz del arbol es el operador de menor prioridad
    public void testConstruirArbolRaizEsOperadorDeMenorPrioridad() {
        ParserFormula parser = new ParserFormula();
        TDALista<String> postfija =
                parser.convertirAPostfija(parser.tokenizar("saldo + 2 * 3"));

        ArbolBinario<ElementoFormula> arbol = parser.construirArbol(postfija);
        TDAElemento<ElementoFormula> raiz = arbol.obtenerRaiz();

        assertEquals(TipoElementoFormula.OPERADOR, raiz.getDato().getTipo());
        assertEquals("+", raiz.getDato().getValor());
        assertEquals("saldo", raiz.getHijoIzquierdo().getDato().getValor());
        assertEquals("*", raiz.getHijoDerecho().getDato().getValor());
    }

    // distingue numeros de variables al armar las hojas
    public void testConstruirArbolClasificaNumeroYVariable() {
        ParserFormula parser = new ParserFormula();
        TDALista<String> postfija =
                parser.convertirAPostfija(parser.tokenizar("saldo * 0.01"));

        ArbolBinario<ElementoFormula> arbol = parser.construirArbol(postfija);
        TDAElemento<ElementoFormula> raiz = arbol.obtenerRaiz();

        assertEquals("*", raiz.getDato().getValor());
        assertEquals(TipoElementoFormula.VARIABLE, raiz.getHijoIzquierdo().getDato().getTipo());
        assertEquals(TipoElementoFormula.NUMERO, raiz.getHijoDerecho().getDato().getTipo());
    }

    private List<String> aLista(TDALista<String> tokens) {
        List<String> resultado = new ArrayList<String>();
        for (int i = 0; i < tokens.tamanio(); i++) {
            resultado.add(tokens.obtener(i));
        }
        return resultado;
    }
}
