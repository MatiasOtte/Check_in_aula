package edu.cerp.checkin;

import edu.cerp.checkin.console.MainConsole;
import edu.cerp.checkin.logic.SesionService;

public class App {
    public static void main(String[] args) {
    boolean usarGui = false;
    for (String a : args) if ("--gui".equalsIgnoreCase(a)) usarGui = true;

        SesionService service = new SesionService();
        service.cargarDatosDemo(); // datos de prueba

        if (usarGui) {
            edu.cerp.checkin.ui.CheckInGUI.show(service);
        } else {
            MainConsole.run(service);
        }
    }
}