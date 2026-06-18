package unlar.edu.ar.strategy;

public class TarifaTemporalClimatico implements EstrategiaTarifa {

    @Override
    public double calcularCosto(double tarifaBase, int minutos) {
        return (tarifaBase * minutos) + 150.0;
    }
}
