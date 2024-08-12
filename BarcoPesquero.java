public final class BarcoPesquero extends Barco {
    private int pecesCapturados;
    private final TipoPesquero tipoPesquero;

    public BarcoPesquero(String nombre, TipoPesquero tipoPesquero) {
        super(nombre);
        this.pecesCapturados = 0;
        this.tipoPesquero = tipoPesquero;
    }

    @Override
    public void agregarElemento() {
        pecesCapturados ++;
    }

    @Override
    public double vaciarCobrar() {
        double total = pecesCapturados * tipoPesquero.price;
        pecesCapturados = 0;
        return total;
    }

    @Override
    public double precioElemento() {
        return tipoPesquero.price;
    }

    @Override
    public String toString() {
        return super.toString() + " - Tipo: " + tipoPesquero + " - Peces Capturados: " + pecesCapturados;
    }
}
