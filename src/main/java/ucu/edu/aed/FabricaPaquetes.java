package ucu.edu.aed;

import java.util.Date;

public class FabricaPaquetes {

    // Fábrica de paquetes bancarios

    public Paquete crearPaqueteOro() {
    /*--------------------CREO PAQUETE ORO ---------------------------------------------*/
        Paquete paquete = new Paquete("Paquete Oro");
    //creo primero los productos que va a tener este paquete:
        ProductoBancario cuenta = new ProductoBancario(1, "Cuenta Corriente", new Date(), "activo", 15000.0, "UYU");
        ProductoBancario cuentaAuxiliar = new ProductoBancario(6, "Cuenta Auxiliar", new Date(), "activo", 3000.0, "UYU");
        ProductoBancario cuentaCA = new ProductoBancario(2, "Caja de Ahorros", new Date(), "activo", 500.0, "USD");
        ProductoBancario tarjetaCredito = new ProductoBancario(3, "Tarjeta de Crédito", new Date(), "activo", -2000.0, "UYU");
        ProductoBancario extensionTarjeta = new ProductoBancario(4, "Extensión Tarjeta", new Date(), "activo", -500.0, "UYU");
        ProductoBancario seguro = new ProductoBancario(5, "Seguro", new Date(), "activo", 0.0, "UYU");
    // agrego los productos al árbol
    //primero genrero una raiz:
        ProductoBancario raizPaquete = new ProductoBancario(0, "PaqueteOro", new Date(), "activo");
        paquete.setRaiz(raizPaquete);
    //agrega los componentes al paquete
        paquete.agregarComponente(raizPaquete.getId(), cuenta);
        paquete.agregarComponente(cuenta.getId(), cuentaAuxiliar);
        paquete.agregarComponente(raizPaquete.getId(), cuentaCA);
        paquete.agregarComponente(raizPaquete.getId(), tarjetaCredito);
    //este componente es una extensión de la tarjeta de crédito
        paquete.agregarComponente(tarjetaCredito.getId(), extensionTarjeta);
        paquete.agregarComponente(raizPaquete.getId(), seguro);
        return paquete;
    }
    /*--------------------CREO PAQUETE PREMIUM ---------------------------------------------*/

    public Paquete crearPaquetePremium() {
        Paquete paquete = new Paquete("Paquete Premium");
        ProductoBancario raizPaquete = new ProductoBancario(0, "PaquetePremium", new Date(), "activo");
        paquete.setRaiz(raizPaquete);
        //agrega los componentes al paquete
        ProductoBancario cuenta = new ProductoBancario(1, "Cuenta Corriente", new Date(), "activo");
        ProductoBancario tarjetaDebito = new ProductoBancario(2, "Tarjeta de Débito", new Date(), "activo");
        paquete.agregarComponente(raizPaquete.getId(), cuenta);
        paquete.agregarComponente(raizPaquete.getId(), tarjetaDebito);
        return paquete;
    }

    /*--------------------CREO PAQUETE BASICO ---------------------------------------------*/
    public Paquete crearPaqueteBasico() {
        Paquete paquete = new Paquete("Paquete Básico");
        ProductoBancario raizPaquete = new ProductoBancario(0, "PaqueteBasico", new Date(), "activo");
        paquete.setRaiz(raizPaquete);
        //agrega los componentes al paquete
        ProductoBancario cuenta = new ProductoBancario(1, "Cuenta Corriente", new Date(), "activo");
        paquete.agregarComponente(raizPaquete.getId(), cuenta);
        return paquete;
    }


}



