package unlar.edu.ar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unlar.edu.ar.api.dto.*;
import unlar.edu.ar.service.AlquilerService;

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
}
