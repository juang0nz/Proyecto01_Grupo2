package ucu.edu.aed.comisiones;

import junit.framework.TestCase;

public class FormulaComisionTest extends TestCase {

    public void testGuardaTextoOriginal() {
        FormulaComision formula = new FormulaComision("saldo * 0.01");

        assertEquals("saldo * 0.01", formula.getTextoOriginal());
    }

    // 10000 * 0.01 + 5 = 105
    public void testEvaluarConstanteMasVariable() {
        FormulaComision formula = crearFormula("saldo * 0.01 + 5");

        assertEquals(105.0, formula.evaluar(10000.0, 0.0), 0.0001);
    }

    // usa las dos variables: 1000 / 4 = 250
    public void testEvaluarConLasDosVariables() {
        FormulaComision formula = crearFormula("saldo / cantidadProductos");

        assertEquals(250.0, formula.evaluar(1000.0, 4.0), 0.0001);
    }

    // ( 10 + 2 ) * 3 = 36
    public void testEvaluarRespetaParentesis() {
        FormulaComision formula = crearFormula("( saldo + 2 ) * 3");

        assertEquals(36.0, formula.evaluar(10.0, 0.0), 0.0001);
    }

    // una variable que no existe debe romper la evaluacion
    public void testEvaluarVariableDesconocida() {
        FormulaComision formula = crearFormula("comision + 1");
        try {
            formula.evaluar(100.0, 1.0);
            fail("se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    // si se carga "saldo + ( 2 * 3 )" debe mostrarse "saldo + 2 * 3"
    public void testMostrarFormulaSinParentesisRedundantes() {
        FormulaComision formula = crearFormula("saldo + ( 2 * 3 )");

        assertEquals("saldo + 2 * 3", formula.mostrarFormula());
    }

    // los parentesis que cambian el resultado no se pierden
    public void testMostrarFormulaConservaParentesisNecesarios() {
        FormulaComision formula = crearFormula("( saldo + 2 ) * cantidadProductos");

        assertEquals("(saldo + 2) * cantidadProductos", formula.mostrarFormula());
    }

    private FormulaComision crearFormula(String texto) {
        ParserFormula parser = new ParserFormula();
        FormulaComision formula = new FormulaComision(texto);
        formula.setArbol(parser.construirArbol(
                parser.convertirAPostfija(parser.tokenizar(texto))));
        return formula;
    }
}
