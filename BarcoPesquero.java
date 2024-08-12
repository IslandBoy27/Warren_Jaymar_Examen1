import javax.swing.*;

public class BarcoPesquero extends Barco {
    private int pecesCapturados;
    private final TipoPesquero tipoPesquero;

    public BarcoPesquero(String nombre, TipoPesquero tipoPesquero) {
        super(nombre);
        this.tipoPesquero = tipoPesquero;
        this.pecesCapturados = 0;
    }

    @Override
    public void agregarElemento() {
        pecesCapturados++;
    }

    @Override
    public double vaciarCobrar() {
        double total = pecesCapturados * tipoPesquero.price;
        String mensaje = String.format("Tipo de Pez: %s\nPeces Capturados: %d\nPrecio Total: $%.2f",
                tipoPesquero.name(), pecesCapturados, total);
        JOptionPane.showMessageDialog(null, mensaje, "Vaciar y Cobrar", JOptionPane.INFORMATION_MESSAGE);
        pecesCapturados = 0; // Reiniciar la cantidad de peces después de cobrar
        return total;
    }

    @Override
    public double precioElemento() {
        return tipoPesquero.price;
    }

    @Override
    public String toString() {
        return super.toString() + " - Tipo de Barco: Pesquero - Peces Capturados: " + pecesCapturados;
    }
}
