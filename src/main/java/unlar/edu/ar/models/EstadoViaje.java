package unlar.edu.ar.models;

import unlar.edu.ar.models.EstadoEspera.EstadoEnEspera;

public class EstadoViaje {

    @Override
    public void iniciarViaje(Vehiculo v) {
        throw new BusinessException("El vehículo ya se encuentra ocupado en un viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo v) {
        v.setEstado(new EstadoEnEspera());
    }

    @Override
    public void enviarMantenimiento(Vehiculo v) {
        throw new BusinessException("No se puede enviar a mantenimiento en medio de un viaje.");
    }

    @Override
    public String getNombre() {
        return "En Viaje";
    }
}
