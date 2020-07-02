package Modelo;

public class Proceso {

	private int id;
	private int TiempoLlegada;
	private int TiempoRafaga;
	private int TiempoInicio;
	private int TiempoFinalizacion;
	private int TiempoRetorno;
	private int TiempoEspera;
	private int Prioridad;

	public Proceso(int id, int TiempoLlegada, int TiempoRafaga, int Prioridad) {
		this.id = id;
		this.TiempoLlegada = TiempoLlegada;
		this.TiempoRafaga = TiempoRafaga;
		this.Prioridad = Prioridad;
		TiempoInicio = 0;
		TiempoFinalizacion = 0;
		TiempoRetorno = 0;
		TiempoEspera = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArrivalTime() {
		return TiempoLlegada;
	}

	public void setArrivalTime(int arrivalTime) {
		this.TiempoLlegada = arrivalTime;
	}

	public int getBurstTime() {
		return TiempoRafaga;
	}

	public void setBurstTime(int burstTime) {
		this.TiempoRafaga = burstTime;
	}

	public int getStartTime() {
		return TiempoInicio;
	}

	public void setStartTime(int startTime) {
		this.TiempoInicio = startTime;
	}

	public int getFinalTime() {
		return TiempoFinalizacion;
	}

	public void setFinalTime(int finalTime) {
		this.TiempoFinalizacion = finalTime;
	}

	public int getReturnTime() {
		return TiempoRetorno;
	}

	public void setReturnTime(int returnTime) {
		this.TiempoRetorno = returnTime;
	}

	public int getWaitTime() {
		return TiempoEspera;
	}

	public void setWaitTime(int waitTime) {
		this.TiempoEspera = waitTime;
	}

	public Object[] resume() {
		return new Object[] { "P" + id, Prioridad, TiempoLlegada, TiempoRafaga, TiempoInicio, TiempoFinalizacion, TiempoRetorno, TiempoEspera };
	}

	public int getPriority() {
		return Prioridad;
	}

	public void setPriority(int priority) {
		this.Prioridad = priority;
	}
}
