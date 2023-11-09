package tads;


public class Lista<T extends Comparable<T>> implements ILista<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;
    private int cantMax;

    public Lista(int tope) {
        inicio = null;
        fin = null;
        cantElementos = 0;
        cantMax = tope;
    }

    @Override
    public boolean esVacia() {
        return inicio == null || fin == null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public int getTope() {
        return cantMax;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }


    /*
     * Pre: No hay
     *
     * Post: Devuelve true si la lista esta llena, false si no lo esta
     *
     * */
    private boolean VerificarCapacidad() {
        if (cantElementos == cantMax) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void agregarInicio(T n) {

        if (!VerificarCapacidad()) {
            Nodo nuevo = new Nodo(n);
            if (esVacia()) {
                inicio = nuevo;
                fin = nuevo;
            } else {
                nuevo.setSiguiente(inicio);
                inicio.setAnterior(nuevo);
                inicio = nuevo;
            }
            cantElementos++;
        }
    }


    @Override
    public void agregarFinal(T n) {

        if (esVacia()) {
            agregarInicio(n);
        } else {
            if (!VerificarCapacidad()) {
                Nodo nuevo = new Nodo(n);
                fin.setSiguiente(nuevo);
                nuevo.setAnterior(fin);
                fin = nuevo;
                cantElementos++;
            }
        }
    }


    @Override
    public void borrarInicio() {

        if (!esVacia()) {
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo borrar = inicio;
                inicio = inicio.getSiguiente();
                borrar.setSiguiente(null);
                inicio.setAnterior(null);
                cantElementos--;
            }

        }

    }


    @Override
    public void borrarFin() {

        if (!esVacia()) {
            if (cantElementos == 1) {
                this.vaciar();
            } else {
                Nodo borrar = fin;
                fin = fin.getAnterior();
                borrar.setAnterior(null);
                fin.setSiguiente(null);
                cantElementos--;
            }
        }

    }



    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }



    @Override
    public void mostrar() {
        Nodo aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    /*
     * Pre: No hay
     * Post: Se muestra la lista recursivamente
     * */
    public void mostrarRecursivo() {
        mostrarRecursivo(inicio);
    }

    private void mostrarRecursivo(Nodo aux) {
        if (aux != null) {
            System.out.println(aux.getDato());
            System.out.println(" ");
            mostrarRecursivo(aux.getSiguiente());
        }
    }



    @Override
    public void borrarElemento(T n) {
        if (!esVacia()) {
            if (inicio.getDato().equals(n)) {
                borrarInicio();
                cantElementos--;
            } else if (fin.getDato().equals(n)) {
                borrarFin();
                cantElementos--;
            } else {
                Nodo aux = inicio;
                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)) {
                    aux = aux.getSiguiente();
                }
                if (aux.getSiguiente() != null) {
                    Nodo aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    cantElementos--;
                }
            }
        }

    }



    @Override
    public int cantElementos() {
        return cantElementos;
    }


    @Override
    public Nodo obtenerElemento(T n) {

        Nodo aux = inicio;
        Nodo ret = null;
        boolean encontre = false;

        while (aux != null && !encontre) {

            if (aux.getDato().equals(n)) {
                ret = aux;
                encontre = true;
            }
            aux = aux.getSiguiente();
        }
        return ret;

    }


    @Override
    public boolean existeDato(T n) {

        boolean existe = false;

        Nodo aux = inicio;
        if (esVacia()) {
            return false;
        } else {
            if (inicio.getDato().equals(n) || fin.getDato().equals(n)) {
                return true;
            } else {
                while (aux != null && !existe) {
                    if (aux.getDato().equals(n)) {
                        existe = true;
                    }
                    aux = aux.getSiguiente();
                }
            }
        }
        return existe;

    }

    /*
     * Pre: El elemento no puede ser nulo
     * El elemento debe ser una cola
     * Post: Agrega los elementos de la cola a la lista de forma ordenada
     * */
    public void agregarOrdenadoCola(Cola<T> cola) {
        Nodo aux = (Nodo) cola.front();
        while (aux != null) {
            this.agregarOrdenado((T) aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    /*
     * Pre: El elemento no puede ser nulo
     * El elemento debe ser una lista
     * Post: Agrega los elementos de la lista a la lista de forma ordeanda
     * */

    public void agregarOrdenadoListas(Lista<T> lista) {
        Nodo aux = lista.getInicio();
        while (aux != null) {
            this.agregarOrdenado((T) aux.getDato());
            aux = aux.getSiguiente();
        }
    }




    @Override
    public void agregarOrdenado(T x) {
        if (esVacia() || inicio.getDato().compareTo(x) >= 0) {
            this.agregarInicio(x);
        } else {
            Nodo aux = inicio;
            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(x) < 0) {
                aux = aux.getSiguiente();
            }
            if (aux.getSiguiente() == null) {
                this.agregarFinal(x);
            } else {
                Nodo nuevo = new Nodo(x);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                cantElementos++;
            }
        }
    }
}
