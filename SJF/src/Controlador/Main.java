package Controlador;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Modelo.Modelo;
import Vista.Ventana;

public class Main {

	private final static String[] COLUMN_NAME = { "Proceso", "Prioridad", "T. Llegada", "T. Rafaga", "T. Comienzo",
			"T. Final", "T. Retorno", "T. Espera" };

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Controlador controller = new Controlador(new Modelo(), new Ventana("Algoritmo SJF"));
		controller.initController(COLUMN_NAME);
	}

}
