package Modelo;

public class Nodo<T> {

	private T dato;
	private Nodo<T> siguiente;

	public Nodo() {
		dato = null;
		siguiente = null;
	}

	public T getData() {
		return dato;
	}

	public void setData(T data) {
		this.dato = data;
	}

	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
}
