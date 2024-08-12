import javax.swing.JOptionPane;

public class BarcoPasajero extends Barco {
    private final String[] pasajeros;
    private double precioBoleto;
    private int contador;

    public BarcoPasajero(String nombre, int capacidadMaxima, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[capacidadMaxima];
        this.precioBoleto = precioBoleto;
        this.contador = 0;
    }

    @Override
    public void agregarElemento() {
        if (contador < pasajeros.length) {
            String nombrePasajero = JOptionPane.showInputDialog("Ingrese el nombre del pasajero:");
            if (nombrePasajero != null && !nombrePasajero.trim().isEmpty()) {
                pasajeros[contador++] = nombrePasajero;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay espacio disponible para más pasajeros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public double vaciarCobrar() {
        double total = contador * precioBoleto;
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Cantidad de Pasajeros que compraron boleto: ").append(contador).append("\n")
                .append("Total generado: $").append(total).append("\n\n")
                .append("Lista de Pasajeros:\n");
        listarPasajerosRecursivo(0, mensaje); // Agregar pasajeros a la lista
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Vaciar y Cobrar", JOptionPane.INFORMATION_MESSAGE);
        contador = 0; // Reiniciar contador de pasajeros después de vaciar el barco
        return total;
    }

    @Override
    public double precioElemento() {
        return precioBoleto;
    }

    @Override
    public String toString() {
        return super.toString() + " - Cantidad de Pasajeros que compraron boleto: " + contador;
    }

    public void listarPasajeros() {
        StringBuilder detalles = new StringBuilder();
        listarPasajerosRecursivo(0, detalles);
        JOptionPane.showMessageDialog(null, detalles.toString(), "Lista de Pasajeros", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarPasajerosRecursivo(int index, StringBuilder detalles) {
        if (index < contador) {
            detalles.append(pasajeros[index]).append("\n");
            listarPasajerosRecursivo(index + 1, detalles);
        }
    }
}
