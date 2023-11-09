package tads;

public interface ILista<T extends Comparable<T>> {

    /*
    pre:
   
    post: Devuelve true si la lista es vacia
     */
    public boolean esVacia();

    /*
     * Pre: El elemento no puede ser nulo
     * Post: Agrega un elemento al inicio de la lista
     * */
    public void agregarInicio(T n);

    /*
     * Pre: El elemento no puede ser nulo
     * Post: Agrega un elemento al final de la lista
     * */

    public void agregarFinal(T n);

    /*
     * Pre: No hay
     * Post: Borra el primer elemento de la lista
     * */

    public void borrarInicio();

    /*
     * Pre: No hay
     * Post: Borra el ultimo elemento de la lista
     * */

    public void borrarFin();

    /*
     * Pre: No hay
     * Post: Vacia la lista
     * */

    public void vaciar();

    /*
     * Pre: No hay
     * Post: Muestra la lista
     * */

    public void mostrar();

    /*
     * Pre: El elemento no puede ser nulo
     * Post: Borra el elemento dado de la lista
     * */

    public void borrarElemento(T n);

    /*
     * Pre: No hay
     * Post: Retorna la cantidad de elementos de la lista
     * */

    public int cantElementos();

    /*
     * Pre: El elemento no puede ser nulo
     * Post: Devuelve el elemento buscado
     * */

    public Nodo obtenerElemento(T n);

    /*
     * Pre: El elemento no puede ser nulo
     * Post: Devuelve si existe o no el elemento
     * */

    public boolean existeDato(T n);

    /*
     * Pre: El elemento no puede ser nulo
     * Post: Agrega el elemento a la lista de forma ordenada
     * */

    public void agregarOrdenado(T x);

}
