package unlar.edu.ar.service;

import unlar.edu.ar.Tp3.models.*;
import unlar.edu.ar.Tp3.pago.*;
import unlar.edu.ar.controller.*;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AlquilerService {
    private final Map<String, Usuario> usuariosDb = new HashMap<>();
    private final EstacionAnclaje estacionSimulada = new EstacionAnclaje("Estacion Central");

    public AlquilerService() {
        usuariosDb.put("U1", new UsuarioRegular("U1", "Juan Pérez"));
        usuariosDb.put("U2", new UsuarioPremium("U2", "Ana Martínez"));
        estacionSimulada.agregarVehiculo(new Monopatin("MONO123", 80, 150.0, true));
        estacionSimulada.agregarVehiculo(new BicicletaElectrica("BICI456", 12, 200.0, 500));
    }

    public AlquilerResponse procesarDesbloqueo(DesbloqueoRequest request) {
        Usuario usuario = usuariosDb.get(request.idUsuario());
        if (usuario == null)
            throw new IllegalArgumentException("Usuario no registrado.");

        Vehiculo vehiculoSeleccionado = null;
        for (Vehiculo v : estacionSimulada.getVehiculos()) {
            if (v.getPatente().equalsIgnoreCase(request.patente())) {
                vehiculoSeleccionado = v;
                break;
            }
        }

        if (vehiculoSeleccionado == null)
            throw new IllegalArgumentException("Vehículo No Encontrado");
        if (vehiculoSeleccionado.getBateria() < 15)
            throw new IllegalArgumentException("Batería Insuficiente");

        double importeFinal = usuario.aplicarDescuento(vehiculoSeleccionado.getTarifaBase());

        ProcesadorPago procesador;
        String metodo = request.metodoPago();
        if ("BILLETERA".equalsIgnoreCase(metodo)) {
            procesador = new BilleteraVirtual();
        } else if ("TARJETA".equalsIgnoreCase(metodo) || "TARJETA_CREDITO".equalsIgnoreCase(metodo)) {
            procesador = new TarjetaCredito();
        } else {
            throw new IllegalArgumentException("Método de pago no soportado");
        }

        procesador.cobrar(importeFinal);

        return new AlquilerResponse("Desbloqueo exitoso.", vehiculoSeleccionado.getPatente(),
                vehiculoSeleccionado.getClass().getSimpleName(), importeFinal);
    }
}