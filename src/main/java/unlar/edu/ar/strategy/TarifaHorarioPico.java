package unlar.edu.ar.strategy;

public class TarifaHorarioPico implements EstrategiaTarifa {

    @Override
    public double calcularCosto(double tarifaBase, int minutos) {
        return (tarifaBase * minutos) * 1.40;
    }
}
