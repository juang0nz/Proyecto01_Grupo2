package ucu.edu.aed;

import java.util.Date;
    
import ucu.edu.aed.tda.TDALista;

public class Main {
    public static void main(String[] args) {

        /*
         * Sucursal suc = new Sucursal("Sucursal Central");
         * 
         * // Registrar clientes
         * Cliente c1 = new Cliente("Ana", 1, Prioridad.NORMAL);
         * Cliente c2 = new Cliente("Pablo", 2, Prioridad.PRIORITARIA);
         * suc.registrarCliente(c1);
         * suc.registrarCliente(c2);
         * 
         * // Agregar productos y documentos
         * ProductoBancario p1 = new ProductoBancario(101, "Cuenta Corriente", new
         * Date(), "ACTIVO");
         * ProductoBancario p2 = new ProductoBancario(102, "Plazo Fijo", new Date(),
         * "ACTIVO");
         * suc.darAltaProducto(c1, p1);
         * suc.darAltaProducto(c2, p2);
         * 
         * Documento d1 = new Documento(201, "DNI", new Date(), new
         * Date(System.currentTimeMillis() + 1_000_000));
         * suc.presentarDocumentacion(c1, d1);
         * 
         * // Mostrar clientes registrados y sus productos/documentos
         * System.out.println("Clientes registrados en " + suc.getNombre() + ":");
         * for (int i = 0; i < suc.getClientes().tamanio(); i++) {
         * Cliente c = suc.getClientes().obtener(i);
         * System.out.println(" - " + c);
         * System.out.println("   Productos:");
         * for (int j = 0; j < c.getProductos().tamanio(); j++) {
         * System.out.println("     * " + c.getProductos().obtener(j));
         * }
         * System.out.println("   Documentos:");
         * for (int j = 0; j < c.getDocumentos().tamanio(); j++) {
         * System.out.println("     - " + c.getDocumentos().obtener(j));
         * }
         * }
         * 
         * // Atención en mostrador: solicitamos atención en orden (primero NORMAL,
         * luego PRIORITARIA)
         * System.out.
         * println("\nSolicitando atención en mostrador (primero Ana NORMAL, luego Pablo PRIORITARIA)..."
         * );
         * suc.solicitarAtencion(c1);
         * suc.solicitarAtencion(c2);
         * 
         * // Demostración de prioridad: aunque Pablo se solicitó después, debe ser el
         * primero por prioridad
         * System.out.println("Proximo a atender (debe ser Pablo con prioridad): " +
         * suc.proximoAAtender());
         * Cliente atendido1 = suc.atenderSiguiente();
         * System.out.println("Atendido (1): " + atendido1);
         * 
         * System.out.println("Proximo a atender (ahora debe ser Ana): " +
         * (suc.hayClientesEnEspera() ? suc.proximoAAtender() : "(ninguno)"));
         * Cliente atendido2 = suc.hayClientesEnEspera() ? suc.atenderSiguiente() :
         * null;
         * System.out.println("Atendido (2): " + atendido2);
         * 
         * // Mostrar última interacción auditada
         * System.out.println("\nUltima interaccion auditada: " +
         * suc.ultimaInteraccionAuditada().getDescripcion());
         * 
         * // Mostrar historial de ambos clientes para verificar orden cronológico
         * System.out.println("\nHistorial del cliente " + c1.getId() + " (Ana):");
         * for (int k = 0; k < c1.getHistorial().tamanio(); k++) {
         * System.out.println(" - " + c1.getHistorial().obtener(k).getDescripcion());
         * }
         * 
         * System.out.println("\nHistorial del cliente " + c2.getId() + " (Pablo):");
         * for (int k = 0; k < c2.getHistorial().tamanio(); k++) {
         * System.out.println(" - " + c2.getHistorial().obtener(k).getDescripcion());
         * }
         * 
         * // Contadores y consultas de auditoría (usando las APIs públicas existentes)
         * System.out.println("\nCantidad de presentaciones de documento en auditoria: "
         * +
         * suc.cantidadInteraccionesPorTipo(TipoInteraccion.PRESENTACION_DOCUMENTACION))
         * ;
         * System.out.println("Cantidad de altas de producto en auditoria: "
         * + suc.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));
         * System.out.println("Cantidad de bajas de producto en auditoria: "
         * + suc.cantidadInteraccionesPorTipo(TipoInteraccion.BAJA_PRODUCTO));
         * System.out.println("Cantidad de atenciones/consultas en auditoria: "
         * + suc.cantidadInteraccionesPorTipo(TipoInteraccion.CONSULTA));
         * 
         * // Operaciones que consideramos relevantes para el funcionamiento
         * // (resumen y demostración de las consultas principales)
         * System.out.println("\n--- Operaciones relevantes de la sucursal ---");
         * 
         * // 1) Buscar todos los productos de un tipo
         * String tipoBuscado = "Cuenta Corriente";
         * System.out.println("Productos del tipo '" + tipoBuscado + "':");
         * for (int i = 0; i < suc.buscarProductosPorTipo(tipoBuscado).tamanio(); i++) {
         * System.out.println(" - " +
         * suc.buscarProductosPorTipo(tipoBuscado).obtener(i));
         * }
         * 
         * // 2) Buscar documentos vencidos respecto a hoy
         * System.out.println("\nDocumentos vencidos hasta hoy:");
         * for (int i = 0; i < suc.documentosVencidos(new Date()).tamanio(); i++) {
         * System.out.println(" - " + suc.documentosVencidos(new Date()).obtener(i));
         * }
         * 
         * // 3) Obtener clientes prioritarios
         * System.out.println("\nClientes prioritarios:");
         * for (int i = 0; i < suc.clientesPrioritarios().tamanio(); i++) {
         * System.out.println(" - " + suc.clientesPrioritarios().obtener(i));
         * }
         * 
         * // 4) Contar interacciones por tipo (ejemplo: ALTA_PRODUCTO)
         * System.out.println("\nCantidad de altas de producto (ejemplo): "
         * + suc.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));
         * 
         * // 5) Empleados por sector (creamos un sector de ejemplo si existe)
         * Sector ejemplo = new Sector("Prestamos");
         * System.out.println("\nEmpleados del sector 'Prestamos':");
         * for (int i = 0; i < suc.empleadosPorSector(ejemplo).tamanio(); i++) {
         * System.out.println(" - " + suc.empleadosPorSector(ejemplo).obtener(i));
         * }
         * 
         * System.out.println("\nDemo finalizada.");
         * 
         * /*-----------------------------------------------------SEGUNDO HITO
         * -----------------------------------------------------
         */
        /*
         * FabricaPaquetes fabrica = new FabricaPaquetes();
         * Paquete paqueteOro = fabrica.crearPaqueteOro();
         * 
         * System.out.println("\nEstructura del paquete Oro:");
         * paqueteOro.mostrarEstructura(paqueteOro.getComponentes().obtenerRaiz(), 0);
         * Paquete paquetePremium = fabrica.crearPaquetePremium();
         * System.out.println("\nEstructura del paquete Premium:");
         * paquetePremium.mostrarEstructura(paquetePremium.getComponentes().obtenerRaiz(
         * ), 0);
         * 
         * Paquete paqueteBasico = fabrica.crearPaqueteBasico();
         * System.out.println("\nEstructura del paquete Básico:");
         * paqueteBasico.mostrarEstructura(paqueteBasico.getComponentes().obtenerRaiz(),
         * 0);
         */

        // 1. Creo un cliente
        Cliente cliente = new Cliente("Juan", 1, Prioridad.NORMAL);

        // 2. Creo la fábrica
        FabricaPaquetes fabrica = new FabricaPaquetes();

        // 3. Creo el paquete Oro
        Paquete paqueteOro = fabrica.crearPaqueteOro();

        // 4. Muestro la estructura antes de contratar
        System.out.println("Estructura del paquete Oro:");
        paqueteOro.mostrarEstructura(paqueteOro.getComponentes().obtenerRaiz(), 0);

        // 5. El cliente contrata el paquete
        paqueteOro.contratar(cliente);

        // 6. Muestro los productos que ahora tiene el cliente
        System.out.println("\nProductos asociados al cliente:");

        for (int i = 0; i < cliente.getProductos().tamanio(); i++) {
            System.out.println("- " + cliente.getProductos().obtener(i));
        }

        // ==========================
        // DOY DE BAJA TARJETA
        // ==========================

        System.out.println("\nDando de baja Tarjeta de Crédito...");

        paqueteOro.darBajaProducto(3);

        // ==========================
        // MUESTRO EL PAQUETE NUEVAMENTE
        // ==========================

        System.out.println("\nEstructura después de la baja:");

        paqueteOro.getComponentes().mostrarEstructura();
        // ==========================
        // PROBANDO BÚSQUEDA POR AVL
        // ==========================

        Sucursal sucursalTest = new Sucursal("Sucursal Test");
        sucursalTest.registrarCliente(cliente);

        System.out.println("\nBuscando cliente con documento 1...");
        Cliente encontrado = sucursalTest.buscarClientePorDocumento(1);
        System.out.println("Encontrado: " + encontrado);

        System.out.println("Buscando cliente con documento 999 (no existe)...");
        Cliente noExiste = sucursalTest.buscarClientePorDocumento(999);
        System.out.println("Encontrado: " + noExiste);

        // ==========================
        // PROBANDO BÚSQUEDA DE PRODUCTO POR CUENTA
        // ==========================

        ProductoBancario productoTest = new ProductoBancario(500, "Caja de Ahorros", new Date(), "ACTIVO");
        sucursalTest.darAltaProducto(cliente, productoTest);

        System.out.println("\nBuscando producto con cuenta 500...");
        ProductoBancario encontradoProducto = sucursalTest.buscarProductoPorCuenta(500);
        System.out.println("Encontrado: " + encontradoProducto);

        System.out.println("Buscando producto con cuenta 9999 (no existe)...");
        ProductoBancario noExisteProducto = sucursalTest.buscarProductoPorCuenta(9999);
        System.out.println("Encontrado: " + noExisteProducto);
        // ==========================
        // PROBANDO POSICIÓN CONSOLIDADA POR MONEDA
        // ==========================

       /*  System.out.println("\nPosición consolidada del paquete Oro:");
        TDALista<SaldoPorMoneda> posicion = paqueteOro.posicionConsolidada();
        for (int i = 0; i < posicion.tamanio(); i++) {
            System.out.println(" - " + posicion.obtener(i));
        }

        System.out.println("\nCliente con documento más alto:");
        System.out.println(sucursalTest.clienteConDocumentoMasAlto());

        System.out.println("\nClientes sin productos:");
        TDALista<Cliente> sinProductos = sucursalTest.clientesSinProductos();
        for (int i = 0; i < sinProductos.tamanio(); i++) {
            System.out.println(" - " + sinProductos.obtener(i));
        }*/
        // 1. Creamos un cliente
        Cliente cliente1 = new Cliente(
                "Juan",
                1,
                Prioridad.NORMAL);

        // 2. Creamos productos
        ProductoBancario producto1 = new ProductoBancario(
                1,
                "Caja de ahorro",
                new Date(),
                "ACTIVO",
                1000,
                "UYU");

        ProductoBancario producto2 = new ProductoBancario(
                2,
                "Cuenta corriente",
                new Date(),
                "ACTIVO",
                500,
                "UYU");

        ProductoBancario producto3 = new ProductoBancario(
                3,
                "Caja de ahorro USD",
                new Date(),
                "ACTIVO",
                200,
                "USD");

        // 3. Se los agregamos al cliente
        cliente1.agregarProducto(producto1);
        cliente1.agregarProducto(producto2);
        cliente1.agregarProducto(producto3);

        // 4. Creamos la fórmula vigente
        FormulaComision formulaVigente = new FormulaComision(
                "saldo*0.02+cantidadProductos*10");

        ParserFormula parser = new ParserFormula();

        TDALista<String> tokens = parser.tokenizar(
                formulaVigente.getTextoOriginal());

        TDALista<String> postfija = parser.convertirAPostfija(tokens);

        formulaVigente.setArbol(
                parser.construirArbol(postfija));

        // 5. Creamos el servicio
        ServicioComisiones servicio = new ServicioComisiones(formulaVigente);

        // 6. LIQUIDAMOS EN UYU
        double liquidacion = servicio.liquidarCliente(
                cliente1,
                "UYU");

        System.out.println(
                "Liquidación: " + liquidacion);

        // 7. Creamos una fórmula NUEVA para simular
        FormulaComision formulaNueva = new FormulaComision(
                "saldo*0.03+cantidadProductos*5");

        tokens = parser.tokenizar(
                formulaNueva.getTextoOriginal());

        postfija = parser.convertirAPostfija(tokens);

        formulaNueva.setArbol(
                parser.construirArbol(postfija));

        // 8. SIMULAMOS
        double simulacion = servicio.simularCliente(
                cliente1,
                "UYU",
                formulaNueva);

        System.out.println(
                "Simulación: " + simulacion);

        // 9. Comprobamos que la fórmula vigente NO cambió
        System.out.println(
                "Fórmula vigente: "
                        + servicio.getFormulaVigente()
                                .mostrarFormula());

        // 10. Mostramos la fórmula nueva para verificar
        System.out.println(
                "Fórmula nueva: "
                        + formulaNueva.mostrarFormula());
                        // ==========================
// PROBANDO AUDITORÍA DE PAQUETES
// ==========================

Sucursal sucursalAuditoria = new Sucursal("Sucursal Auditoria");
Cliente clienteAuditoria = new Cliente("Maria", 10, Prioridad.NORMAL);
sucursalAuditoria.registrarCliente(clienteAuditoria);

Paquete paqueteParaAuditar = fabrica.crearPaqueteOro();
sucursalAuditoria.contratarPaquete(clienteAuditoria, paqueteParaAuditar);

System.out.println("\nAltas de producto auditadas: " + sucursalAuditoria.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));

sucursalAuditoria.darBajaComponentePaquete(clienteAuditoria, paqueteParaAuditar, 3);
System.out.println("Bajas de producto auditadas: " + sucursalAuditoria.cantidadInteraccionesPorTipo(TipoInteraccion.BAJA_PRODUCTO));
// ==========================
// PROBANDO AUDITORÍA DE CAMBIO DE FÓRMULA
// ==========================

    Sucursal sucursalFormula = new Sucursal("Sucursal Formula");
    System.out.println("\nInteracciones de MODIFICACION_PRODUCTO antes del cambio: "
            + sucursalFormula.cantidadInteraccionesPorTipo(TipoInteraccion.MODIFICACION_PRODUCTO));

    sucursalFormula.registrarCambioFormula("Cambio de fórmula de comisión a: " + formulaNueva.mostrarFormula());
    servicio.setFormulaVigente(formulaNueva);

    System.out.println("Interacciones de MODIFICACION_PRODUCTO después del cambio: "
            + sucursalFormula.cantidadInteraccionesPorTipo(TipoInteraccion.MODIFICACION_PRODUCTO));

    System.out.println("Fórmula vigente ahora: " + servicio.getFormulaVigente().mostrarFormula());
    }

}