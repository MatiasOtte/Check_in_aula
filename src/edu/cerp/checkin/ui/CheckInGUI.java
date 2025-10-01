package edu.cerp.checkin.ui;

import javax.swing.*;
import java.awt.*;
import edu.cerp.checkin.logic.SesionService;


public class CheckInGUI extends JFrame {
    private JButton checkInButton;
    private JLabel statusLabel;
    private JTextField nombreField;
    private JTextField documentoField;
    private JComboBox<String> cursoCombo;
    private final SesionService sesionService;

    public CheckInGUI(SesionService sesionService) {
        setTitle("Check-In de Aula");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.sesionService = sesionService;
        initComponents();
    }

    /** Muestra la GUI con el servicio proporcionado */
    public static void show(SesionService service) {
        SwingUtilities.invokeLater(() -> {
            new CheckInGUI(service).setVisible(true);
        });
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel documentoLabel = new JLabel("Documento:");
        documentoField = new JTextField();
        JLabel cursoLabel = new JLabel("Curso:");
        cursoCombo = new JComboBox<>(new String[] {"Prog 1", "Prog 2", "Base de Datos"});
        checkInButton = new JButton("Check-In");
        statusLabel = new JLabel("");

        checkInButton.addActionListener(e -> realizarCheckIn());

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(documentoLabel);
        panel.add(documentoField);
        panel.add(cursoLabel);
        panel.add(cursoCombo);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(checkInButton);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(statusLabel);

        add(panel);
    }

    private void realizarCheckIn() {
        String nombre = nombreField.getText();
        String documento = documentoField.getText();
        String curso = (String) cursoCombo.getSelectedItem();
        if (nombre.isEmpty() || documento.isEmpty()) {
            statusLabel.setText("Por favor, complete nombre y documento.");
            return;
        }
        sesionService.registrar(nombre, documento, curso);
        statusLabel.setText("Check-in realizado para: " + nombre + " (" + curso + ")");
        nombreField.setText("");
        documentoField.setText("");
        cursoCombo.setSelectedIndex(0);
    }

    // El método main se elimina porque la GUI se lanza desde App.java
}
