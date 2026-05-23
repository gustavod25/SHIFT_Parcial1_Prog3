package unlar.edu.ar.Tp3.models;

public class UsuarioRegular extends Usuario {
    public UsuarioRegular(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto;
    }
}