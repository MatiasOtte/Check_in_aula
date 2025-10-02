package edu.cerp.checkin.ui;

import javax.swing.*;
import java.awt.*;
import edu.cerp.checkin.logic.SesionService;


public class CheckInGUI extends JFrame {
    private JButton regCheckInButton;
    private JLabel regStatusLabel;
    private JTextField regNombreField;
    private JTextField regDocumentoField;
    private JComboBox<String> regCursoCombo;
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
        JPanel buscar = new JPanel();
        JPanel registrar = new JPanel();
        JPanel listar = new JPanel();
        JPanel resumir = new JPanel();
        registrar.setLayout(new GridLayout(5, 2, 10, 10));
        JTabbedPane pestañas = new JTabbedPane();
        
        JLabel regNombreLabel = new JLabel("Nombre:");
        regNombreField = new JTextField();
        JLabel regDocumentoLabel = new JLabel("Documento:");
        regDocumentoField = new JTextField();
        JLabel regCursoLabel = new JLabel("Curso:");
        regCursoCombo = new JComboBox<>(new String[] {"Prog 1", "Prog 2", "Base de Datos"});
        regCheckInButton = new JButton("Check-In");
        regStatusLabel = new JLabel("");


        regCheckInButton.addActionListener(e -> realizarCheckIn());

        registrar.add(regNombreLabel);
        registrar.add(regNombreField);
        registrar.add(regDocumentoLabel);
        registrar.add(regDocumentoField);
        registrar.add(regCursoLabel);
        registrar.add(regCursoCombo);
        registrar.add(new JLabel()); // Espacio vacío
        registrar.add(regCheckInButton);
        registrar.add(new JLabel()); // Espacio vacío
        registrar.add(regStatusLabel);

        pestañas.add("Registrar", registrar);
        pestañas.add("Buscar", buscar);
        pestañas.add("Listar", listar);
        pestañas.add("Resumir", resumir);
        add(pestañas);
    }

    private void realizarCheckIn() {
        String nombre = regNombreField.getText();
        String documento = regDocumentoField.getText();
        String curso = (String) regCursoCombo.getSelectedItem();
        if (nombre.isEmpty() || documento.isEmpty()) {
            regStatusLabel.setText("Por favor, complete nombre y documento.");
            return;
        }
        sesionService.registrar(nombre, documento, curso);
        regStatusLabel.setText("Check-in realizado para: " + nombre + " (" + curso + ")");
        regNombreField.setText("");
        regDocumentoField.setText("");
      regCursoCombo.setSelectedIndex(0);
    }

    // El método main se elimina porque la GUI se lanza desde App.java
}