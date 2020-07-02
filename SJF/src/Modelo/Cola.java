package Modelo;

public class Cola<T> {

	private Nodo<T> cabeza;
	private int tam;

	public Cola() {
		cabeza = null;
		tam = 0;
	}

	public void add(T data) {
		Nodo<T> nodo = new Nodo<T>();
		nodo.setData(data);
		nodo.setSiguiente(null);
		if (cabeza == null) {
			cabeza = nodo;
		} else {
			Nodo<T> aux = cabeza;
			while (aux.getSiguiente() != null) {
				aux = aux.getSiguiente();
			}
			aux.setSiguiente(nodo);
		}
		tam++;
	}

	public T poll() {
		T data;
		Nodo<T> aux = cabeza;
		cabeza = aux.getSiguiente();
		data = aux.getData();
		aux = null;
		tam--;
		return data;
	}

	public boolean remover(int pos) {
		if (tam >= pos) {
			Nodo<T> aux = cabeza;
			if (pos == 1) {
				cabeza = aux.getSiguiente();
				aux = null;
			} else {
				for (int i = 1; i < pos - 1; i++) {
					aux = aux.getSiguiente();
				}
				Nodo<T> temp = aux.getSiguiente();
				aux.setSiguiente(temp.getSiguiente());
				temp = null;
			}
			tam--;
			return true;
		}
		return false;
	}

	public T getData(int pos) {
		Nodo<T> aux = cabeza;
		int p = 1;
		if ((pos <= tam) && (pos >= 0)) {
			while ((p < pos) && (aux != null)) {
				aux = aux.getSiguiente();
				p++;
			}
			return aux.getData();
		}
		return aux.getData();
	}

	public boolean isEmpty() {
		if (cabeza == null)
			return true;
		return false;
	}

	public int getSize() {
		return tam;
	}

	@Override
	protected void finalize() throws Throwable {
		Nodo<T> aux;
		while (cabeza != null) {
			aux = cabeza;
			cabeza = aux.getSiguiente();
			aux = null;
		}
		cabeza = null;
		System.gc();
	}
}
