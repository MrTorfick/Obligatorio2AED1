package tads;

public class ListaSimple<T extends Comparable<T>> implements ILista<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;
    private int cantMax;

    public ListaSimple(int tope) {
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

    public Nodo getFin() {
        return fin;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;

        }
        cantElementos++;
    }

    @Override
    public void agregarFinal(T n) {
        if (esVacia()) {
            agregarInicio(n);
        } else {
            Nodo nuevo = new Nodo(n);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        cantElementos++;
    }

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
            cantElementos--;
        }

    }

    @Override
    public void borrarFin() {
        if (!esVacia()) {
            if (inicio == fin) {
                vaciar();
            } else {
                Nodo aux = inicio;
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(null);
                fin = aux;
            }
            cantElementos--;
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
        boolean existe = true;
        if (!esVacia()) {

            if (inicio.getDato().equals(n)) {
                return existe;
            } else if (fin.getDato().equals(n)) {
                return existe;
            }
            Nodo aux = inicio.getSiguiente();
            existe = false;
            while (aux != null && !existe) {

                if (aux.getDato().equals(n)) {
                    existe = true;
                }
                aux = aux.getSiguiente();
            }
        } else {
            existe = false;
        }

        return existe;
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
