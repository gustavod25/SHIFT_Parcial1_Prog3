package unlar.edu.ar.service;

import unlar.edu.ar.api.dto.DesbloqueoRequest;
import unlar.edu.ar.controller.TarifaDescendenteComparator;
import unlar.edu.ar.api.dto.AlquilerResponse;

import unlar.edu.ar.models.*;
import unlar.edu.ar.payment.FabricaProcesadorPago;
import unlar.edu.ar.strategy.EstrategiaTarifa;
import unlar.edu.ar.strategy.TarifaEstandar;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AlquilerService {
    private final Map<String, Usuario> usuariosDb = new HashMap<>();
    private final EstacionAnclaje estacionSimulada = new EstacionAnclaje("Estacion Central");
    private final Map<String, Vehiculo> vehiculosDb = new HashMap<>();

    private EstrategiaTarifa estrategiaTarifaActiva = new TarifaEstandar();

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

    public void setEstrategiaTarifa(EstrategiaTarifa nuevaEstrategia) {
        this.estrategiaTarifaActiva = nuevaEstrategia;
    }

    public VehiculoResponse procesarDesbloqueo(DesbloquearRequest req) {
        Usuario usuario = usuariosDb.get(req.idUsuario());
        if (usuario == null)
            throw new BusinessException("Usuario no registrado.");

        Vehiculo v = vehiculosDb.get(req.patente());
        if (v == null)
            throw new BusinessException("Vehículo No Encontrado");

        if (v.getBateria() < 15)
            throw new BusinessException("Batería Insuficiente");

        v.getEstado().prepararParaViaje(v);
        v.getEstado().iniciarViaje(v);

        return new VehiculoResponse(v.getPatente(), 0.0, "0 min", v.getEstado().getNombre());
    }

    public VehiculoResponse procesarFinalizacion(FinalizarRequest req) {
        Vehiculo v = vehiculosDb.get(req.patente());
        if (v == null)
            throw new BusinessException("Vehículo No Encontrado");

        Usuario usuario = usuariosDb.get(req.idUsuario());
        if (usuario == null)
            throw new BusinessException("Usuario Inválido");

        v.getEstado().finalizarViaje(v);

        double costoCalculado = estrategiaTarifaActiva.calcularCosto(v.getTarifaBase(), req.minutosTranscurridos());
        double costoFinal = usuario.aplicarDescuento(costoCalculado);

        ProcesadorPago procesador = FabricaProcesadorPago.obtenerProcesador(req.metodoPago());
        procesador.cobrar(costoFinal);

        return new VehiculoResponse(v.getPatente(), costoFinal, req.minutosTranscurridos() + " min",
                v.getEstado().getNombre());
    }

    public List<String> deduplicarAlertasGPS(List<String> reportesSucios) {
        List<String> unicos = new ArrayList<>();
        Set<String> registroAuxiliar = new HashSet<>();

        for (String reporte : reportesSucios) {
            if (registroAuxiliar.add(reporte)) {
                unicos.add(reporte);
            }
        }
        return unicos;
    }

    public List<Vehiculo> obtenerPorCargaNatural() {
        List<Vehiculo> copiaConcurrente = new ArrayList<>(vehiculosDb.values());
        Collections.sort(copiaConcurrente);
        return copiaConcurrente;
    }

    public List<Vehiculo> obtenerPorTarifaDescendente() {
        List<Vehiculo> copiaConcurrente = new ArrayList<>(vehiculosDb.values());
        Collections.sort(copiaConcurrente, new TarifaDescendenteComparator());
        return copiaConcurrente;
    }
}