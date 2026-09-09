package ucu.edu.aed.modelo;

import ucu.edu.aed.auditoria.Interaccion;
import ucu.edu.aed.implementaciones.TDAConjuntoImpl;
import ucu.edu.aed.implementaciones.TDAListaEnlazadaImpl;
import ucu.edu.aed.tda.TDAConjunto;
import ucu.edu.aed.tda.TDALista;
public class Cliente implements Comparable<Cliente> {

    private String nombre;
    public int id;
    public Prioridad prioridad;

    // productos y documentos son conjuntos: no admiten duplicados
    private final TDAConjunto<ProductoBancario> productos = new TDAConjuntoImpl<>();
    private final TDAConjunto<Documento> documentos = new TDAConjuntoImpl<>();

    // historial personal de interacciones del cliente (lista cronológica)
    private final TDALista<Interaccion> historial = new TDAListaEnlazadaImpl<>();

    //constructor
    public Cliente(String nombre, int id, Prioridad prioridad) {
        this.nombre = nombre;
        this.id = id;
        this.prioridad = prioridad;

        //getters y setters
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public TDAConjunto<ProductoBancario> getProductos() {
        return productos;
    }

    public TDAConjunto<Documento> getDocumentos() {
        return documentos;
    }

    public TDALista<Interaccion> getHistorial() {
        return historial;
    }

    // registra una interaccion en el historial del cliente (se agrega al final, cronológico)
    public void registrarInteraccion(Interaccion interaccion) {
        historial.agregar(interaccion);
    }

        @Override
    public int compareTo(Cliente otro) {
        return Integer.compare(this.id, otro.id);
    }

    //Uso equals() para determinar si dos clientes son iguales comparando sus IDs.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cliente)) {
            return false;
        }
        return this.id == ((Cliente) obj).id;
    }

// como queremos que se muestre el cliente
    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', prioridad=" + prioridad + "}";
    }
    // agrega un producto bancario al conjunto de productos del cliente
    public void agregarProducto(ProductoBancario producto) {
    productos.agregar(producto);
}
}
