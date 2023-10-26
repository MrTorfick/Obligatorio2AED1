package tads;

public interface IPila<T extends Comparable> {

    public boolean estaVacia();

    public void apilar(T dato);

    public T desapilar();

    public T top();

    public void vaciar();

    public int cantidadNodos();

    public void mostrar();

    public Pila<T> CopiarPila();

    public void intercambiarTope();

    public void concatenar(Pila<T> otraPila);

    public void invertir();
}
