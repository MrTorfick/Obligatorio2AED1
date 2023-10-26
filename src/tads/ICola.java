package tads;

public interface ICola<T> {

    public void encolar(T dato);


    public Comparable desencolar();

    public T front();

    public boolean isEmpty();

    public void vaciar();

    public int cantidadNodos();

    public void Mostrar();

    public T[] datos();
}
