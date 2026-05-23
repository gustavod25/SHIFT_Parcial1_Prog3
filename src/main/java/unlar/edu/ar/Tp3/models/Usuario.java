package unlar.edu.ar.Tp3.models;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor

public abstract class Usuario {
    private String id;
    private String nombre;

    public abstract double aplicarDescuento(double monto);
}