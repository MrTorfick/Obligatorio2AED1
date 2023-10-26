package tads;

public class Nodo<T extends Comparable> {

    private T dato;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    public Nodo(T elDato) {
        this.setDato(elDato);
        this.setSiguiente(null);
        this.setAnterior(null);
        siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }


}
