import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class Muelle {
    private final ArrayList<Barco> barcos;

    public Muelle() {
        barcos = new ArrayList<>();
    }

    public void agregarBarco() {
        JTextField nombreField = new JTextField();
        JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"PESQUERO", "PASAJERO"});

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nombre del barco:"));
        panel.add(nombreField);
        panel.add(new JLabel("Tipo de barco:"));
        panel.add(tipoComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Barco", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String tipo = (String) tipoComboBox.getSelectedItem();

            for (Barco barco : barcos) {
                if (barco.getNombre().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(null, "El nombre del barco ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (tipo.equals("PESQUERO")) {
                String[] opciones = {"PEZ", "CAMARON", "LANGOSTA"};
                String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de pesquero:",
                        "Tipo de Pesquero", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                TipoPesquero tipoPesquero = TipoPesquero.valueOf(seleccion);
                BarcoPesquero barcoPesquero = new BarcoPesquero(nombre, tipoPesquero);
                barcos.add(barcoPesquero);
                JOptionPane.showMessageDialog(null, "Barco Pesquero agregado exitosamente.");
            } else if (tipo.equals("PASAJERO")) {
                int maxPasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad máxima de pasajeros:"));
                double precioBoleto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del boleto:"));
                BarcoPasajero barcoPasajero = new BarcoPasajero(nombre, maxPasajeros, precioBoleto);
                barcos.add(barcoPasajero);
                JOptionPane.showMessageDialog(null, "Barco Pasajero agregado exitosamente.");
            }
        }
    }

    public void agregarElemento(String nombre) {
        for (Barco barco : barcos) {
            if (barco.getNombre().equalsIgnoreCase(nombre)) {
                barco.agregarElemento();
                JOptionPane.showMessageDialog(null, "Elemento agregado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un barco con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public double vaciarBarco(String nombre) {
        for (Barco barco : barcos) {
            if (barco.getNombre().equalsIgnoreCase(nombre)) {
                double total = barco.vaciarCobrar();
                String detalleBarco = barco.toString();
                if (barco instanceof BarcoPasajero) {
                    BarcoPasajero barcoPasajero = (BarcoPasajero) barco;
                    barcoPasajero.listarPasajeros();
                }
                JOptionPane.showMessageDialog(null, "Detalles del barco:\n" + detalleBarco + "\nTotal generado: $" + total);
                return total;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un barco con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        return 0.0;
    }

    public void barcosDesde(int year) {
        StringBuilder detalles = new StringBuilder();
        barcosDesdeRecursivo(year, 0, detalles);
        if (detalles.length() > 0) {
            JOptionPane.showMessageDialog(null, detalles.toString(), "Barcos desde " + year, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron barcos desde el año " + year, "Barcos desde " + year, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //Funcion Recursiva
    private void barcosDesdeRecursivo(int year, int index, StringBuilder detalles) {
        if (index < barcos.size()) {
            if (barcos.get(index).getFechaCirculacion().get(Calendar.YEAR) >= year) {
                detalles.append(barcos.get(index).getNombre()).append(" - ").append(barcos.get(index).getFechaCirculacion().getTime()).append("\n");
            }
            barcosDesdeRecursivo(year, index + 1, detalles);
        }
    }

    public static void main(String[] args) {
        Muelle muelle = new Muelle();

        JFrame frame = new JFrame("Gestión de Muelle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton agregarBarcoButton = new JButton("Agregar Barco");
        agregarBarcoButton.addActionListener(e -> muelle.agregarBarco());
        panel.add(agregarBarcoButton);

        JButton agregarElementoButton = new JButton("Agregar Elemento");
        agregarElementoButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del barco:");
            muelle.agregarElemento(nombre);
        });
        panel.add(agregarElementoButton);

        JButton vaciarBarcoButton = new JButton("Vaciar Barco");
        vaciarBarcoButton.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del barco:");
            muelle.vaciarBarco(nombre);
        });
        panel.add(vaciarBarcoButton);

        JButton barcosDesdeButton = new JButton("Barcos Desde Año");
        barcosDesdeButton.addActionListener(e -> {
            int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año:"));
            muelle.barcosDesde(year);
        });
        panel.add(barcosDesdeButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
