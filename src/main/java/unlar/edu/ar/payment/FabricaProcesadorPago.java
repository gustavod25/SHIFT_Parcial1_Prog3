package unlar.edu.ar.payment;

public class FabricaProcesadorPago {

    public static ProcesadorPago obtenerProcesador(String tipo) {
        return switch (tipo.trim().toUpperCase()) {
            case "TARJETA" -> new TarjetaCreditoProcesador();
            case "BILLETERA" -> new BilleteraVirtualProcesador();
            default -> throw new IllegalArgumentException("Medio de pago no soportado: " + tipo);
        };
    }
}
