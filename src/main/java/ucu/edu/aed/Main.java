package ucu.edu.aed;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Sucursal suc = new Sucursal("Sucursal Central");

        // Registrar clientes
        Cliente c1 = new Cliente("Ana", 1, Prioridad.NORMAL);
        Cliente c2 = new Cliente("Pablo", 2, Prioridad.PRIORITARIA);
        suc.registrarCliente(c1);
        suc.registrarCliente(c2);

        // Agregar productos y documentos
        ProductoBancario p1 = new ProductoBancario(101, "Cuenta Corriente", new Date(), "ACTIVO");
        ProductoBancario p2 = new ProductoBancario(102, "Plazo Fijo", new Date(), "ACTIVO");
        suc.darAltaProducto(c1, p1);
        suc.darAltaProducto(c2, p2);

        Documento d1 = new Documento(201, "DNI", new Date(), new Date(System.currentTimeMillis() + 1_000_000));
        suc.presentarDocumentacion(c1, d1);

        // Mostrar clientes registrados y sus productos/documentos
        System.out.println("Clientes registrados en " + suc.getNombre() + ":");
        for (int i = 0; i < suc.getClientes().tamanio(); i++) {
            Cliente c = suc.getClientes().obtener(i);
            System.out.println(" - " + c);
            System.out.println("   Productos:");
            for (int j = 0; j < c.getProductos().tamanio(); j++) {
                System.out.println("     * " + c.getProductos().obtener(j));
            }
            System.out.println("   Documentos:");
            for (int j = 0; j < c.getDocumentos().tamanio(); j++) {
                System.out.println("     - " + c.getDocumentos().obtener(j));
            }
        }

        // Atención en mostrador: solicitamos atención en orden (primero NORMAL, luego PRIORITARIA)
        System.out.println("\nSolicitando atención en mostrador (primero Ana NORMAL, luego Pablo PRIORITARIA)...");
        suc.solicitarAtencion(c1);
        suc.solicitarAtencion(c2);

        // Demostración de prioridad: aunque Pablo se solicitó después, debe ser el primero por prioridad
        System.out.println("Proximo a atender (debe ser Pablo con prioridad): " + suc.proximoAAtender());
        Cliente atendido1 = suc.atenderSiguiente();
        System.out.println("Atendido (1): " + atendido1);

        System.out.println("Proximo a atender (ahora debe ser Ana): " + (suc.hayClientesEnEspera() ? suc.proximoAAtender() : "(ninguno)"));
        Cliente atendido2 = suc.hayClientesEnEspera() ? suc.atenderSiguiente() : null;
        System.out.println("Atendido (2): " + atendido2);

        // Mostrar última interacción auditada
        System.out.println("\nUltima interaccion auditada: " + suc.ultimaInteraccionAuditada().getDescripcion());

        // Mostrar historial de ambos clientes para verificar orden cronológico
        System.out.println("\nHistorial del cliente " + c1.getId() + " (Ana):");
        for (int k = 0; k < c1.getHistorial().tamanio(); k++) {
            System.out.println(" - " + c1.getHistorial().obtener(k).getDescripcion());
        }

        System.out.println("\nHistorial del cliente " + c2.getId() + " (Pablo):");
        for (int k = 0; k < c2.getHistorial().tamanio(); k++) {
            System.out.println(" - " + c2.getHistorial().obtener(k).getDescripcion());
        }

        // Contadores y consultas de auditoría (usando las APIs públicas existentes)
        System.out.println("\nCantidad de presentaciones de documento en auditoria: "
                + suc.cantidadInteraccionesPorTipo(TipoInteraccion.PRESENTACION_DOCUMENTACION));
        System.out.println("Cantidad de altas de producto en auditoria: "
                + suc.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));
        System.out.println("Cantidad de bajas de producto en auditoria: "
                + suc.cantidadInteraccionesPorTipo(TipoInteraccion.BAJA_PRODUCTO));
        System.out.println("Cantidad de atenciones/consultas en auditoria: "
                + suc.cantidadInteraccionesPorTipo(TipoInteraccion.CONSULTA));

        // Operaciones que consideramos relevantes para el funcionamiento
        // (resumen y demostración de las consultas principales)
        System.out.println("\n--- Operaciones relevantes de la sucursal ---");

        // 1) Buscar todos los productos de un tipo
        String tipoBuscado = "Cuenta Corriente";
        System.out.println("Productos del tipo '" + tipoBuscado + "':");
        for (int i = 0; i < suc.buscarProductosPorTipo(tipoBuscado).tamanio(); i++) {
            System.out.println(" - " + suc.buscarProductosPorTipo(tipoBuscado).obtener(i));
        }

        // 2) Buscar documentos vencidos respecto a hoy
        System.out.println("\nDocumentos vencidos hasta hoy:");
        for (int i = 0; i < suc.documentosVencidos(new Date()).tamanio(); i++) {
            System.out.println(" - " + suc.documentosVencidos(new Date()).obtener(i));
        }

        // 3) Obtener clientes prioritarios
        System.out.println("\nClientes prioritarios:");
        for (int i = 0; i < suc.clientesPrioritarios().tamanio(); i++) {
            System.out.println(" - " + suc.clientesPrioritarios().obtener(i));
        }

        // 4) Contar interacciones por tipo (ejemplo: ALTA_PRODUCTO)
        System.out.println("\nCantidad de altas de producto (ejemplo): "
                + suc.cantidadInteraccionesPorTipo(TipoInteraccion.ALTA_PRODUCTO));

        // 5) Empleados por sector (creamos un sector de ejemplo si existe)
        Sector ejemplo = new Sector("Prestamos");
        System.out.println("\nEmpleados del sector 'Prestamos':");
        for (int i = 0; i < suc.empleadosPorSector(ejemplo).tamanio(); i++) {
            System.out.println(" - " + suc.empleadosPorSector(ejemplo).obtener(i));
        }

        System.out.println("\nDemo finalizada.");
    }
}
