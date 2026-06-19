package unlar.edu.ar.api.dto;

public record FinalizarRequest(
        String idUsuario,
        String patente,
        int minutosTranscurridos,
        String metodoPago) {
}
