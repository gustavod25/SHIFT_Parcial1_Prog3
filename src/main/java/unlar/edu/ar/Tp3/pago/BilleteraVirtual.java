package unlar.edu.ar.Tp3.pago;

public class BilleteraVirtual implements ProcesadorPago {
    @Override
    public void cobrar(double monto) {
        System.out.printf("Cobro exitoso de $%.2f realizado con Billetera Virtual%n", monto);
    }
}