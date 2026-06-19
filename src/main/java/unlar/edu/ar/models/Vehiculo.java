package unlar.edu.ar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public abstract class Vehiculo implements Comparable<Vehiculo> {

    private String patente;

    private int bateria;

    private double tarifaBase;
    private EstadoVehiculo estado;

    public Vehiculo(String patente, int bateria, double tarifaBase) {
        this.patente = patente;
        this.bateria = bateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EstadoEnEspera();
    }

    public String getPatente() {
        return patente;
    }

    public int getBateria() {
        return bateria;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Vehiculo otro) {
        return Integer.compare(this.bateria, otro.bateria);
    }

}
