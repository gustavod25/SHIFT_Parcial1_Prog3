package unlar.edu.ar.api.dto;

public record VehiculoResponse(
        String patente,
        double costoFinalCalculado,
        String tiempoTranscurrido,
        String faseActual) {
}