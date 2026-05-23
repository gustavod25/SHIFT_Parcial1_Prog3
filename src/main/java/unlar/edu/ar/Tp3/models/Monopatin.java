package unlar.edu.ar.Tp3.models;

import lombok.*;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Monopatin extends Vehiculo {
    private boolean amortiguacionReforzada;

    public Monopatin(String patente, int bateria, double tarifaBase, boolean amortiguacionReforzada) {
        super(patente, bateria, tarifaBase);
        this.amortiguacionReforzada = amortiguacionReforzada;
    }

}
