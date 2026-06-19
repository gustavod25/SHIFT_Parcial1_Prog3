package unlar.edu.ar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unlar.edu.ar.models.Vehiculo;
import unlar.edu.ar.service.AlquilerService;

record VehiculoReporteDTO(String patente, int bateria, String estado) {
}

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final AlquilerService alquilerService;

    public VehiculoController(FlotaService flotaService, AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping("/prioridad-carga")
    public ResponseEntity<List<VehiculoReporteDTO>> getPrioridadCarga() {
        List<Vehiculo> vehiculos = alquilerService.obtainPorCargaNatural();
        List<VehiculoReporteDTO> respuesta = new ArrayList<>();

        for (Vehiculo v : vehiculos) {
            respuesta.add(new VehiculoReporteDTO(v.getPatente(), v.getBateria(), v.getEstado().getNombre()));
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/tarifa-descendente")
    public ResponseEntity<List<VehiculoReporteDTO>> getTarifaDescendente() {
        List<Vehiculo> vehiculos = alquilerService.obtenerPorTarifaDescendente();
        List<VehiculoReporteDTO> respuesta = new ArrayList<>();

        for (Vehiculo v : vehiculos) {
            respuesta.add(new VehiculoReporteDTO(v.getPatente(), v.getBateria(), v.getEstado().getNombre()));
        }
        return ResponseEntity.ok(respuesta);
    }
}
