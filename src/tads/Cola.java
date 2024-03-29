package tads;

public class Cola<T extends Comparable> implements ICola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int cantidad;

    public Cola() {
        primero = null;
        ultimo = null;
        cantidad = 0;

    }


    @Override
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo(dato);
        if (isEmpty()) {
            primero = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
        }
        ultimo = nuevoNodo;
        cantidad++;

    }

    @Override
    public T desencolar() {
        if (!isEmpty()) {
            T dato = primero.getDato();
            primero = primero.getSiguiente();
            if (primero == null) {
                ultimo = null;
            }
            cantidad--;
            return dato;
        }
        return null;
    }

    @Override
    public T front() {
        if (isEmpty()) {
            return null;
        }
        return primero.getDato();
    }


    @Override
    public boolean isEmpty() {
        return primero == null;
    }


    @Override
    public void vaciar() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }


    @Override
    public int cantidadNodos() {
        return cantidad;
    }


    @Override
    public void Mostrar() {
        if (!this.isEmpty()) {
            Nodo<T> aux = primero;

            while (aux != null) {
                System.out.println(aux.getDato());
                aux = aux.getSiguiente();
            }
        }
    }


    /*
     * Pre: No hay
     * Post: Muestra los elementos de la cola de forma recursiva
     * */
    public void MostrarRecursivo() {
        MostrarRecursivo(primero);
    }

    public void MostrarRecursivo(Nodo<T> aux) {
        if (aux != null) {
            System.out.println(aux.getDato());
            MostrarRecursivo(aux.getSiguiente());
        }

    }


}
