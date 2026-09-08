package ucu.edu.aed;

import java.util.Date;
import java.util.NoSuchElementException;

import ucu.edu.aed.implementaciones.AVL;
import ucu.edu.aed.implementaciones.TDAColaPrioridadEnlazadaImpl;
import ucu.edu.aed.implementaciones.TDAConjuntoImpl;
import ucu.edu.aed.implementaciones.TDAListaEnlazadaImpl;
import ucu.edu.aed.tda.TDACola;
import ucu.edu.aed.tda.TDAConjunto;
import ucu.edu.aed.tda.TDALista;


public class Sucursal {

    private final String nombre;

    // clientes y empleados de la sucursal: no admiten duplicados
    private final TDAConjunto<Cliente> clientes = new TDAConjuntoImpl<>();
    private final TDAConjunto<Empleado> empleados = new TDAConjuntoImpl<>();
    private final TDAConjunto<Sector> sectores = new TDAConjuntoImpl<>();
    private final AVL<Cliente> clientesPorDocumento = new AVL<>();
    private final AVL<ProductoBancario> productosPorCuenta = new AVL<>();

    // mostrador de atención: cola con prioridad (PRIORITARIA antes que NORMAL)
    // la prioridad se define en el enum Prioridad, y se compara con un comparador
    
    private final TDACola<Cliente> mostrador = new TDAColaPrioridadEnlazadaImpl<>((c1, c2) -> comparaPrioridad(c1.getPrioridad()) - comparaPrioridad(c2.getPrioridad()));

    // auditoría general de la sucursal: lista cronológica de interacciones
    private final TDALista<Interaccion> auditoria = new TDAListaEnlazadaImpl<>();

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }
    

    private static int comparaPrioridad(Prioridad prioridad) {
        // menor valor = más prioridad en la cola
        return prioridad == Prioridad.PRIORITARIA ? 0 : 1;
    }

    public String getNombre() {
        return nombre;
    }

    
    // Altas de clientes / empleados / sectores
    

    public boolean registrarCliente(Cliente cliente) {
        if (clientes.contiene(cliente)) {
            return false;
        }
        clientes.agregar(cliente);
        clientesPorDocumento.insertar(cliente);
        return true;
}
//registrar un empleado y agregarlo a la lista de empleados y al conjunto de sectores
    public boolean registrarEmpleado(Empleado empleado) {
        if (empleados.contiene(empleado)) {
            return false;
        }
        empleados.agregar(empleado);
        sectores.agregar(empleado.getSector());
        return true;
    }
    public Cliente buscarClientePorDocumento(int documento) {
        Comparable<Cliente> criterio = c -> Integer.compare(documento, c.getId());
        return clientesPorDocumento.buscar(criterio);
    }

    public ProductoBancario buscarProductoPorCuenta(int idCuenta) {
    Comparable<ProductoBancario> criterio = p -> Integer.compare(idCuenta, p.getId());
    return productosPorCuenta.buscar(criterio);
    }

    
    // Productos bancarios
    

    public void darAltaProducto(Cliente cliente, ProductoBancario producto) {
        cliente.getProductos().agregar(producto);
        productosPorCuenta.insertar(producto);   // ← nueva
        registrar(cliente, TipoInteraccion.ALTA_PRODUCTO,
        "Alta de producto " + producto.getTipo() + " (id=" + producto.getId() + ")");
    }

    public boolean darBajaProducto(Cliente cliente, ProductoBancario producto) {
        boolean removido = cliente.getProductos().remover(producto);
        if (removido) {
            registrar(cliente, TipoInteraccion.BAJA_PRODUCTO,
                    "Baja de producto " + producto.getTipo() + " (id=" + producto.getId() + ")");
        }
        return removido;
    }

    
    // Documentación
    

    public void presentarDocumentacion(Cliente cliente, Documento documento) {
        cliente.getDocumentos().agregar(documento);
        registrar(cliente, TipoInteraccion.PRESENTACION_DOCUMENTACION,
                "Presentación de documento " + documento.getTipo() + " (id=" + documento.getId() + ")");
    }

    
    // Atención en mostrador (cola con prioridad)


    public void solicitarAtencion(Cliente cliente) {
        mostrador.poneEnCola(cliente);
    }

    public Cliente proximoAAtender() {
        return mostrador.frente();
    }

    public Cliente atenderSiguiente() {
        Cliente atendido = mostrador.quitaDeCola();
        registrar(atendido, TipoInteraccion.CONSULTA, "Atención en mostrador");
        return atendido;
    }

    public boolean hayClientesEnEspera() {
        return !mostrador.esVacio();
    }

    
    // Auditoría
    

    private void registrar(Cliente cliente, TipoInteraccion tipo, String detalle) {
        Interaccion interaccion = new Interaccion(new Date(), tipo,
                "Cliente " + cliente.getId() + ": " + detalle);
        cliente.registrarInteraccion(interaccion);
        auditoria.agregar(interaccion);
    }

    public Interaccion ultimaInteraccionAuditada() {
        if (auditoria.tamanio() == 0) {
            throw new NoSuchElementException("No hay interacciones auditadas");
        }
        return auditoria.obtener(auditoria.tamanio() - 1);
    }

    
    // Consultas (recorren y procesan las estructuras)
    

    /**
     * Devuelve, sin alterar el conjunto de clientes, aquellos con
     * {Prioridad#PRIORITARIA}.
     */
    public TDAConjunto<Cliente> clientesPrioritarios() {
        TDAConjunto<Cliente> resultado = new TDAConjuntoImpl<>();
        for (int i = 0; i < clientes.tamanio(); i++) {
            Cliente c = clientes.obtener(i);
            if (c.getPrioridad() == Prioridad.PRIORITARIA) {
                resultado.agregar(c);
            }
        }
        return resultado;
    }

    /**
     * Recorre todos los clientes y sus conjuntos de productos, devolviendo
     * aquellos productos cuyo tipo coincide con el buscado.
     */
    public TDALista<ProductoBancario> buscarProductosPorTipo(String tipo) {
        TDALista<ProductoBancario> resultado = new TDAListaEnlazadaImpl<>();
        for (int i = 0; i < clientes.tamanio(); i++) {
            TDAConjunto<ProductoBancario> productosCliente = clientes.obtener(i).getProductos();
            for (int j = 0; j < productosCliente.tamanio(); j++) {
                ProductoBancario producto = productosCliente.obtener(j);
                if (producto.getTipo().equalsIgnoreCase(tipo)) {
                    resultado.agregar(producto);
                }
            }
        }
        return resultado;
    }

    /**
     * Recorre los documentos de todos los clientes y devuelve los que ya
     * vencieron respecto a la fecha de referencia.
     */
    public TDALista<Documento> documentosVencidos(Date fechaReferencia) {
        TDALista<Documento> resultado = new TDAListaEnlazadaImpl<>();
        for (int i = 0; i < clientes.tamanio(); i++) {
            TDAConjunto<Documento> documentosCliente = clientes.obtener(i).getDocumentos();
            for (int j = 0; j < documentosCliente.tamanio(); j++) {
                Documento documento = documentosCliente.obtener(j);
                if (documento.estaVencido(fechaReferencia)) {
                    resultado.agregar(documento);
                }
            }
        }
        return resultado;
    }

    /**
     * Recorre la lista de auditoría general y cuenta cuántas interacciones
     * corresponden al tipo indicado. No modifica la lista: solo la recorre
     * en orden cronológico.
     */
    public int cantidadInteraccionesPorTipo(TipoInteraccion tipo) {
        int contador = 0;
        for (int i = 0; i < auditoria.tamanio(); i++) {
            Interaccion actual = auditoria.obtener(i);
            if (actual.getTipo() == tipo) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Recorre el conjunto de empleados y devuelve aquellos asignados al
     * sector indicado.
     */
    public TDALista<Empleado> empleadosPorSector(Sector sector) {
        TDALista<Empleado> resultado = new TDAListaEnlazadaImpl<>();
        for (int i = 0; i < empleados.tamanio(); i++) {
            Empleado empleado = empleados.obtener(i);
            if (empleado.getSector().equals(sector)) {
                resultado.agregar(empleado);
            }
        }
        return resultado;
    }

    public TDAConjunto<Cliente> getClientes() {
        return clientes;
    }

    public TDAConjunto<Empleado> getEmpleados() {
        return empleados;
    }

    public TDAConjunto<Sector> getSectores() {
        return sectores;
    }
}
