package tads;

public interface ILista<T extends Comparable<T>> {

    /*
    pre:
   
    post: Devuelve true si la lista es vacia
     */
    public boolean esVacia();

    /*
    pre:
   
    post: Agrega un elemento al inicio de la lista
     */

    public void agregarInicio(T n);

    /*
    pre: El elemento no puede ser null
   
    post:Agrega un elemento al final de la lista
     */

    public void agregarFinal(T n);

    /*
    pre: El elemento no puede ser null
   
    post: Borra el elemento que se encuentra al inicio
     */

    public void borrarInicio();

    /*
    pre:
   
    post: Borra el elemento que se encuentra al final
     */

    public void borrarFin();

    /*
    pre:
   
    post: Vacia la lista
     */

    public void vaciar();

    /*
    pre:
   
    post: Muestra la lista
     */

    public void mostrar();

    /*
    pre: El elemento no puede ser null
   
    post: Borra un elemento pasado por parametro
     */

    public void borrarElemento(T n);

    /*
    pre:
   
    post: Devuelve la cantidad de elementos de la lista
     */

    public int cantElementos();

    /*
    pre: El elemento no puede ser null
   
    post: Devuelve el elemento en caso de que se encuentre en la lista
     */

    public Nodo obtenerElemento(T n);

    /*
    pre:
   
    post: Devuelve true si el elemento se encuentra en la lista
     */

    public boolean existeDato(T n);

    /*
    pre: El elemento no puede ser null
   
    post: Agrega el elemento ordenado, segun criterios establecidos en el compareTo
     */

    public void agregarOrdenado(T x);

}
