package tads;

public interface ICola<T> {

    /*
     *
     * Pre: El elemento no puede ser nulo
     * Post: Encola el elemento en la cola
     * */
    public void encolar(T dato);



    /*
     * Pre: No hay
     * Post: Desencola el primer elemento de la cola y lo devuelve
     * */

    public Comparable desencolar();

    /*
     * Pre: No hay
     * Post: Devuelve el primer elemento de la cola
     * */

    public T front();

    /*
     * Pre: No hay
     * Post: Devuelve true si la cola esta vacia, false en caso contrario
     * */

    public boolean isEmpty();

    /*
     * Pre: No hay
     * Post: Vacia la cola
     * */

    public void vaciar();
    /*
     * Pre: No hay
     * Post: Devuelve la cantidad de nodos de la cola
     *
     * */

    public int cantidadNodos();

    /*
     * Pre: No hay
     * Post: Muestra los elementos de la cola
     * */

    public void Mostrar();

}
