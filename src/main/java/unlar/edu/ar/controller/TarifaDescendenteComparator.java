package unlar.edu.ar.controller;

import java.util.Comparator;

import unlar.edu.ar.models.Vehiculo;

public class TarifaDescendenteComparator implements Comparator<Vehiculo> {

    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        // De Mayor a Menor precio base
        return Double.compare(v2.getTarifaBase(), v1.getTarifaBase());
    }
}
