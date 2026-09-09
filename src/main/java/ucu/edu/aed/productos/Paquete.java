package ucu.edu.aed.productos;

import java.util.Date;

import ucu.edu.aed.comisiones.SaldoPorMoneda;
import ucu.edu.aed.implementaciones.ArbolNario;
import ucu.edu.aed.modelo.Cliente;
import ucu.edu.aed.modelo.ProductoBancario;
import ucu.edu.aed.tda.TDAElementoNario;
import ucu.edu.aed.tda.TDALista;


public class Paquete extends ProductoBancario {
    // árbol n-ario que contiene los componentes del paquete

    private ArbolNario<ProductoBancario> componentes;

    // establece la raíz del árbol de componentes
    public void setRaiz(ProductoBancario raiz) {
        this.componentes.setRaiz(raiz);
    }
    // obtiene la raíz del árbol de componentes
    public ProductoBancario getRaiz() {
        return this.componentes.obtenerRaiz().getDato();
    }

    // constructor
    public Paquete(String tipo) {super(0, tipo, new Date(), "activo");
        this.componentes = new ArbolNario<>();
    }

//agrego un componente al paquete bajo un padre específico
public void agregarComponente(int idPadre, ProductoBancario producto) {
    Comparable<ProductoBancario> criterioPadre = p -> Integer.compare(idPadre, p.getId());
    componentes.agregarHijo(criterioPadre, producto);
}

    // obtiene los componentes del paquete
    public ArbolNario<ProductoBancario> getComponentes() {
        return componentes;
    }
    //
    public void mostrarEstructura(TDAElementoNario<ProductoBancario> nodo, int nivel) {

    // Pongo espacios dependiendo de la profundidad
    for (int i = 0; i < nivel; i++) {
        System.out.print("   ");
    }

    // Muestro el dato de este nodo
    System.out.println("- " + nodo.getDato());

    // Obtengo sus hijos
    TDALista<TDAElementoNario<ProductoBancario>> hijos = nodo.getHijos();

    // Hago lo mismo para cada hijo
    for (int i = 0; i < hijos.tamanio(); i++) {
        mostrarEstructura(hijos.obtener(i), nivel + 1);
    }
}

/*------------------------------------------------------------------------------ */
    // métodos relacionados con la contratación de productos del paquete
/*------------------------------------------------------------------------------ */

public void contratar(Cliente cliente) {

    TDAElementoNario<ProductoBancario> raiz =
        componentes.obtenerRaiz();
    if (raiz == null) {
        return;
    }
    TDALista<TDAElementoNario<ProductoBancario>> hijos =
        raiz.getHijos();
    for (int i = 0; i < hijos.tamanio(); i++) {
        contratarSubarbol(hijos.obtener(i), cliente);
    }
}
// método auxiliar recursivo para contratar todos los productos de un subárbol del paquete
private void contratarSubarbol(
        TDAElementoNario<ProductoBancario> nodo,
        Cliente cliente) {
    cliente.agregarProducto(nodo.getDato());
    TDALista<TDAElementoNario<ProductoBancario>> hijos =
        nodo.getHijos();
    for (int i = 0; i < hijos.tamanio(); i++) {
        contratarSubarbol(hijos.obtener(i), cliente);
    }
}
public boolean darBajaProducto(int idProducto) {

    Comparable<ProductoBancario> criterio =
        producto -> Integer.compare(idProducto, producto.getId());

    componentes.eliminar(criterio);
    return true;
}
public TDALista<SaldoPorMoneda> posicionConsolidada() {
    TDALista<SaldoPorMoneda> resultado = new ucu.edu.aed.implementaciones.TDAListaEnlazadaImpl<>();

    TDAElementoNario<ProductoBancario> raiz = componentes.obtenerRaiz();
    if (raiz == null) {
        return resultado;
    }

    consolidarSubarbol(raiz, resultado);
    return resultado;
}

private void consolidarSubarbol(
        TDAElementoNario<ProductoBancario> nodo,
        TDALista<SaldoPorMoneda> resultado) {

    ProductoBancario producto = nodo.getDato();
    acumular(resultado, producto.getMoneda(), producto.getSaldo());

    TDALista<TDAElementoNario<ProductoBancario>> hijos = nodo.getHijos();
    for (int i = 0; i < hijos.tamanio(); i++) {
        consolidarSubarbol(hijos.obtener(i), resultado);
    }
}

private void acumular(TDALista<SaldoPorMoneda> resultado, String moneda, double monto) {
    for (int i = 0; i < resultado.tamanio(); i++) {
        SaldoPorMoneda actual = resultado.obtener(i);
        if (actual.getMoneda().equals(moneda)) {
            actual.sumar(monto);
            return;
        }
    }
        resultado.agregar(new SaldoPorMoneda(moneda, monto));
    }
    public TDALista<ProductoBancario> obtenerSubarbol(int idProducto) {
    Comparable<ProductoBancario> criterio =
        producto -> Integer.compare(idProducto, producto.getId());
    TDAElementoNario<ProductoBancario> nodo = componentes.obtenerRaiz().buscar(criterio);
    TDALista<ProductoBancario> productosABorrar = new ucu.edu.aed.implementaciones.TDAListaEnlazadaImpl<>();
    if (nodo != null) {
        nodo.preOrden(productosABorrar::agregar);
    }
        return productosABorrar;
    }
}