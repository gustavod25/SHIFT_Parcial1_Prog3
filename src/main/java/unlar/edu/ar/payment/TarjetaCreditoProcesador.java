package unlar.edu.ar.payment;

public class TarjetaCreditoProcesador implements ProcesadorPago {

    @Override
    public void cobrar(double monto) {
        System.out.printf("Cobro exitoso de $%.2f realizado con Tarjeta de Crédito%n", monto);
    }

}
