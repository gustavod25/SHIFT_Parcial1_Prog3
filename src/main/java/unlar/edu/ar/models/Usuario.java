package unlar.edu.ar.models;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor

public abstract class Usuario {
    private String id;
    private String nombre;

    public abstract double aplicarDescuento(double monto);
}