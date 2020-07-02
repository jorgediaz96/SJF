package Controlador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modelo.Modelo;
import Vista.Ventana;

public class Controlador {

	private Modelo model;
	private Ventana view;

	public Controlador(Modelo model, Ventana view) {
		this.model = model;
		this.view = view;
	}

	public void initController(String[] columnName) {
		setTableColumName(columnName);
		view.getPanelAction().getBtnInit().addActionListener(e -> IniciarAccion());
		view.getPanelAction().getBtnPoll().addActionListener(e -> pollAction());
		view.getPanelAction().getBtnAdd().addActionListener(e -> AgregarAccion());
		view.getPanelAction().getBtnLock().addActionListener(e -> MirarAccion());
		view.getPanelAction().getBtnUnLock().addActionListener(e -> desbloquearAccion());
		view.getPanelAction().getBtnRestart().addActionListener(e -> reiniciarAccion());
		view.getPanelAction().getBtnExit().addActionListener(e -> salir());
		view.setVisible(true);
	}

	private void setTableColumName(String[] columnName) {
		if (view.getPanelTable().getTableModel() == null)
			view.getPanelTable().setTableModel(new DefaultTableModel(columnName, 0));
			view.getPanelTableGantt().setTableModel(new DefaultTableModel(new Object[] { "PID" }, 0));
	}

	private void IniciarAccion() {
		if (model.getQueueReady().getId() == 0) {
			for (int i = 1; i <= 5; i++)
				view.getPanelTableReadyQueue().getTableModel().addRow(model.getQueueReady().addProceso());
		} else {
			JOptionPane.showMessageDialog(null, "¡No se puede inciar más de una vez!", "Iniciar",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void pollAction() {
		try {
			if (!model.getQueueReady().colaVacia()) {
				Object[] data = model.getQueueReady().getDataProcess(model.getQueueReady().pollProcessByPriority());
				view.getPanelTableReadyQueue().removeRow(0, String.valueOf(data[0]));
				view.getPanelTable().getTableModel().addRow(data);
				view.getPanelTableGantt().paintProcess(data);

			} else {
				JOptionPane.showMessageDialog(null, "¡No hay ningún procesos por atender!", "Atender",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e) {
			System.out.println("algo");
		}

	}

	private void AgregarAccion() {
		view.getPanelTableReadyQueue().getTableModel().addRow(model.getQueueReady().addProceso());
	}

	private void MirarAccion() {
		if (!model.getQueueReady().colaVacia()) {
			Object[] data = model.getQueueLock().adjuntarProceso((model.getQueueReady().pollProcessByPriority()));
			view.getPanelTableReadyQueue().removeRow(0, String.valueOf(data[0]));
			view.getPanelTableLockQueue().getTableModel().addRow(data);
		} else {
			JOptionPane.showMessageDialog(null, "¡No hay ningún procesos para bloquear!", "Bloquear",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void desbloquearAccion() {
		if (!model.getQueueLock().colaVacia()) {
			view.getPanelTableLockQueue().getTableModel().removeRow(0);
			view.getPanelTableReadyQueue().getTableModel()
					.addRow(model.getQueueReady().adjuntarProceso((model.getQueueLock().pollProcess())));
		} else {
			JOptionPane.showMessageDialog(null, "¡No hay ningún procesos por desbloquear!", "Desbloquear",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void reiniciarAccion() {
		int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere reiniciar del programa?", "Reiniciar",
				JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			try {
				model.colaFinalizada();
				model = new Modelo();
				view.getPanelTable().getTableModel().setNumRows(0);
				view.getPanelTableReadyQueue().getTableModel().setNumRows(0);
				view.getPanelTableLockQueue().getTableModel().setNumRows(0);
				view.getPanelTableGantt().getTableModel().setNumRows(0);
				view.getPanelTableGantt().getTableModel().setColumnCount(1);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	private void salir() {
		int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que quiere salir del programa?", "Salir",
				JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION)
			try {
				model.colaFinalizada();
				System.exit(0);
			} catch (Throwable e) {
				e.printStackTrace();
			}
	}
}
