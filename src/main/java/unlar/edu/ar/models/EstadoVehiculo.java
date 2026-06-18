package unlar.edu.ar.models;

public interface EstadoVehiculo {
    void iniciarViaje(Vehiculo v);

    void finalizarViaje(Vehiculo v);

    void enviarMantenimiento(Vehiculo v);

    String getNombre();
}
