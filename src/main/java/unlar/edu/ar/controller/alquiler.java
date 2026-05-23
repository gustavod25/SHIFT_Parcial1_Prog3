package unlar.edu.ar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// DTOs agrupados aquí o en sus propios archivos record
record DesbloqueoRequest(String idUsuario, String patente, String metodoPago) {
}

record AlquilerResponse(String mensaje, String patente, String tipoVehiculo, double montoCobrado) {
}

record ErrorResponse(String error) {
}

@RestController
@RequestMapping("/api/alquileres")
public class Alquiler {

    private final Alquiler alquilerService;

    public Service(Service alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/desbloqueo")
    public ResponseEntity<?> desbloquearVehiculo(@RequestBody DesbloqueoRequest request) {
        try {
            AlquilerResponse response = alquilerService.procesarDesbloqueo(request);
            return ResponseEntity.ok(response);
        } catch (BusinessException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }
}