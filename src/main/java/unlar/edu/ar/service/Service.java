package unlar.edu.ar.service;

import unlar.edu.ar.model.*;
import unlar.edu.ar.pago.*;
import unlar.edu.ar.exception.*;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class Service {
    private final Map<String, Usuario> usuariosDb = new HashMap<>();
    private final EstacionAnclaje estacionSimulada = new EstacionAnclaje("Estacion Central");

    public Service() {
        // aqui metemos los datos usuario estacion
    }

    public AlquilerResponse procesarDesbloqueo(DesbloqueoRequest request) {
        Usuario usuario = usuariosDb.get(request.idUsuario());
        if (usuario == null)
            throw new Exception("Usuario no registrado.");

        Vehiculo vehiculoSeleccionado = null;
        for (Vehiculo v : estacionSimulada.getVehiculos()) {
            if (v.getPatente().equalsIgnoreCase(request.patente())) {
                vehiculoSeleccionado = v;
                break;
            }
        }

        if (vehiculoSeleccionado == null)
            throw new Exception("Vehículo No Encontrado");
        if (vehiculoSeleccionado.getBateria() < 15)
            throw new Exception("Batería Insuficiente");

        double importeFinal = usuario.aplicarDescuento(vehiculoSeleccionado.getTarifaBase());
        ProcesadorPago procesador = ProcesadorPago.obtenerProcesador(request.metodoPago());
        procesador.cobrar(importeFinal);

        return new AlquilerResponse("Desbloqueo exitoso.", vehiculoSeleccionado.getPatente(),
                vehiculoSeleccionado.getClass().getSimpleName(), importeFinal);
    }
}