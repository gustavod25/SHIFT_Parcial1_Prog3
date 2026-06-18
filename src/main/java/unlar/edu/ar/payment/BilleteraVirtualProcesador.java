package unlar.edu.ar.payment;

public class BilleteraVirtualProcesador implements ProcesadorPago {

    @Override
    public void cobrar(double monto) {
        System.out.printf("Cobro exitoso de $%.2f realizado con Billetera Virtual%n", monto);
    }

}
