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

    @PostMapping("/desbloqueo")
    public ResponseEntity<?> desbloquearVehiculo(@RequestBody DesbloqueoRequest request) {
        try {
            AlquilerResponse response = alquilerService.procesarDesbloqueo(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/finalizar")
    public ResponseEntity<VehiculoResponse> finalizar(@RequestBody FinalizarRequest request) {
        return ResponseEntity.ok(alquilerService.procesarFinalizacion(request));
    }
}
