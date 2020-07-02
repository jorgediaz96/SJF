package Modelo;

public class SJF {

	private Cola<Proceso> cola;
	private int horaFinalizacion;
	private int id;

	public SJF() {
		cola = new Cola<Proceso>();
		horaFinalizacion = 0;
		id = 0;
	}

	private int random(int min, int max) {
		return (int) (Math.random() * max + min);
	}

	private void tiempoCalculado(Proceso proceso) {
		proceso.setStartTime(horaFinalizacion);
		proceso.setFinalTime(proceso.getBurstTime() + proceso.getStartTime());
		proceso.setReturnTime(proceso.getFinalTime() - proceso.getArrivalTime());
		proceso.setWaitTime(proceso.getReturnTime() - proceso.getBurstTime());
		horaFinalizacion = proceso.getFinalTime();
	}

	public Object[] getDataProcess(Proceso process) {
		tiempoCalculado(process);
		return process.resume();
	}

	public Object[] addProceso() {
		Proceso proceso = new Proceso(id + 1, id, random(1, 4), random(1, 4));
		cola.add(proceso);
		id++;
		return proceso.resume();
	}

	public Object[] adjuntarProceso(Proceso process) {
		cola.add(process);
		return process.resume();
	}

	public Proceso pollProcess() {
		return cola.poll();
	}

	public Proceso pollProcessByPriority() {
		Proceso data = null;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= cola.getSize(); j++) {
				data = cola.getData(j);
				if (data.getPriority() == i) {
					if (data.getArrivalTime() <= horaFinalizacion) {
						cola.remover(j);
						return data;
					}
				}
			}
		}
		return data;
	}

	public int getId() {
		return id;
	}

	public int getHoraFinalizacion() {
		return horaFinalizacion;
	}

	public boolean colaVacia() {
		return cola.isEmpty();
	}

	public void finalizarCola() throws Throwable {
		cola.finalize();
	}
}