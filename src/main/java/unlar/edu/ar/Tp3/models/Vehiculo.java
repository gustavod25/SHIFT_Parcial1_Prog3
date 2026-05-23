package unlar.edu.ar.Tp3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public abstract class Vehiculo {

    private String patente;

    private int bateria;

    private float tarifaBase;

}
