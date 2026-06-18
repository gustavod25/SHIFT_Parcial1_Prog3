package unlar.edu.ar.models;

import java.util.ArrayList;
import java.util.List;

public class EstacionAnclaje {
    @SuppressWarnings("unused")
    private String nombre;
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public EstacionAnclaje(String nombre) {
        this.nombre = nombre;
    }

    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}