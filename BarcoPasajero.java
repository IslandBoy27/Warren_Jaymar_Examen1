import javax.swing.*;
public final class BarcoPasajero extends Barco {
    private final String[] pasajeros;
    private final double precioBoleto;
    private int contadorPasajeros;

    public BarcoPasajero(String nombre, int maxPasajeros, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[maxPasajeros];
        this.precioBoleto = precioBoleto;
        this.contadorPasajeros = 0;
    }

    @Override
    public void agregarElemento() {
        if (contadorPasajeros < pasajeros.length) {
            String nombrePasajero = JOptionPane.showInputDialog(null, "Ingrese el nombre del pasajero:");
            if (nombrePasajero != null && !nombrePasajero.trim().isEmpty()) {
                pasajeros[contadorPasajeros++] = nombrePasajero.trim();
            } else {
                JOptionPane.showMessageDialog(null, "Nombre del pasajero no puede estar vacío.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay más espacio en el barco.");
        }
    }

    @Override
    public double vaciarCobrar() {
        double total = contadorPasajeros * precioBoleto;
        contadorPasajeros = 0;
        return total;
    }

    @Override
    public double precioElemento() {
        return precioBoleto;
    }

    @Override
    public String toString() {
        return super.toString() + " (Cantidad de Pasajeros que compraron boleto: " + contadorPasajeros + ")";
    }

    public void listarPasajeros() {
        String pasajerosListado = listarPasajerosRecursivo(0);
        JOptionPane.showMessageDialog(null, pasajerosListado, "Lista de Pasajeros", JOptionPane.INFORMATION_MESSAGE);
    }

    private String listarPasajerosRecursivo(int index) {
        if (index < contadorPasajeros) {
            return pasajeros[index] + "\n" + listarPasajerosRecursivo(index + 1);
        }
        return "";
    }
}
