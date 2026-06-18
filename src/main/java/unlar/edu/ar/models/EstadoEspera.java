package unlar.edu.ar.models;

public class EstadoEnEspera implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo v) {
        v.setEstado(new EstadoEnViaje());
    }

    @Override
    public void finalizarViaje(Vehiculo v) {
        throw new BusinessException("El vehículo no está en viaje.");
    }

    @Override
    public void enviarMantenimiento(Vehiculo v) {
        v.setEstado(new EstadoEnReparacion());
    }

    @Override
    public String getNombre() {
        return "En Espera";
    }
}
