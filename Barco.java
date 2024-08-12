import java.util.Calendar;

public abstract class Barco {
    private final String nombre;
    private final Calendar fechaCirculacion;

    public Barco(String nombre) {
        this.nombre = nombre;
        this.fechaCirculacion = Calendar.getInstance();
    }

    public final String getNombre() {
        return nombre;
    }

    public final Calendar getFechaCirculacion() {
        return fechaCirculacion;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public abstract void agregarElemento();
    public abstract double vaciarCobrar();
    public abstract double precioElemento();
}
