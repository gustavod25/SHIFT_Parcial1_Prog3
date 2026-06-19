package unlar.edu.ar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlar.edu.ar.api.dto.*;
import unlar.edu.ar.service.AlquilerService;

record DesbloquearRequest(String idUsuario, String patente) {
}

record FinalizarRequest(String idUsuario, String patente, int minutosTranscurridos, String metodoPago) {
}

record VehiculoResponse(String patente, double costoFinalCalculado, String tiempoTranscurrido, String faseActual) {
}

@RestController
@RequestMapping("/api/alquileres")
public class alquiler {

    private final AlquilerService alquilerService;

    public alquiler(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    public alquiler(alquiler alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/desbloquear")
    public ResponseEntity<VehiculoResponse> desbloquear(@RequestBody DesbloquearRequest request) {
        VehiculoResponse response = alquilerService.procesarDesbloqueo(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/finalizar")
    public ResponseEntity<VehiculoResponse> finalizar(@RequestBody FinalizarRequest request) {
        VehiculoResponse response = alquilerService.procesarFinalizacion(request);
        return ResponseEntity.ok(response);
    }
}
