package unlar.edu.ar.models;

import unlar.edu.ar.exception.BusinessException;

public class EstadoEnReparacion {

    @Override
    public void iniciarViaje(Vehiculo v) {
        throw new BusinessException("Vehículo en reparación. No apto para iniciar viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo v) {
        throw new BusinessException("El vehículo está en el taller.");
    }

    @Override
    public void enviarMantenimiento(Vehiculo v) {
        throw new BusinessException("El vehículo ya está siendo reparado.");
    }

    @Override
    public String getNombre() {
        return "En Reparación";
    }
}
