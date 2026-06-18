package unlar.edu.ar.models;

public class Pago {
    public static Object obtenerProcesador(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "TARJETA" -> new TarjetaCredito();
            case "BILLETERA" -> new BilleteraVirtual();
            default -> throw new IllegalArgumentException("Medio de pago no soportado: " + tipo);
        };
    }
}