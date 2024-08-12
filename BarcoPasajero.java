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
            String nombrePasajero = JOptionPane.showInputDialog("Ingrese el nombre del pasajero:");
            pasajeros[contadorPasajeros] = nombrePasajero;
            contadorPasajeros++;
        } else {
            JOptionPane.showMessageDialog(null, "El barco está lleno. No se pueden agregar más pasajeros.");
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
        return super.toString() + " - Cantidad de Pasajeros que compraron boleto: " + contadorPasajeros;
    }

    public void listarPasajeros() {
        listarPasajerosRecursivo(0);
    }

    private void listarPasajerosRecursivo(int index) {
        if (index < contadorPasajeros) {
            System.out.println(pasajeros[index]);
            listarPasajerosRecursivo(index + 1);
        }
    }
}
