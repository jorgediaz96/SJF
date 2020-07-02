package Modelo;

public class Modelo {

	private SJF colaLista;
	private SJF colaBloqueada;

	public Modelo() {
		colaLista = new SJF();
		colaBloqueada = new SJF();
	}

	public SJF getQueueReady() {
		return colaLista;
	}

	public SJF getQueueLock() {
		return colaBloqueada;
	}

	public void colaFinalizada() throws Throwable {
		colaLista.finalizarCola();
		colaBloqueada.finalizarCola();
	}
}
