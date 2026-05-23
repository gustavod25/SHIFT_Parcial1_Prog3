package unlar.edu.ar.Tp3.models;

public class UsuarioPremium extends Usuario {
    private final double descuento = 0.15;

    public UsuarioPremium(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto * (1 - descuento);
    }
}