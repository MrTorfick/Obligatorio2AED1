package tads;

public class Pila<T extends Comparable> implements IPila{

    private Nodo<T> tope;
    private int cant;

    public Pila(){
        this.tope=null;
        cant=0;
    }


    @Override
    public boolean estaVacia() {
        return false;
    }

    @Override
    public void apilar(Comparable dato) {
        Nodo<T> nuevoNodo = new Nodo(dato);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        cant++;
    }


    @Override
    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        cant--;
        return dato;
    }

    @Override
    public T top() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return tope.getDato();
    }

    @Override
    public void vaciar() {
        this.tope = null;
        cant = 0;
    }

    @Override
    public int cantidadNodos() {
        return cant;
    }

    @Override
    public void mostrar() {
        if (!estaVacia()) {

            Nodo<T> actual = tope;

            while (actual != null) {
                System.out.println(actual.getDato());
                actual = actual.getSiguiente();
            }
            System.out.println("-----------------------");
        }
    }

    @Override
    public Pila CopiarPila() {
        Pila<T> aux = new Pila();
        Pila<T> pilaRet = new Pila();

        while (!this.estaVacia()) {
            aux.apilar(this.desapilar());
        }

        while (!aux.estaVacia()) {
            this.apilar(aux.top());
            pilaRet.apilar(aux.desapilar());
        }
        return pilaRet;
    }

    @Override
    public void intercambiarTope() {
        Pila<T> pilaAux = new Pila();
        pilaAux.apilar(this.desapilar());
        pilaAux.apilar(this.desapilar());
        this.apilar(pilaAux.desapilar());
        this.apilar(pilaAux.desapilar());
    }

    @Override
    public void concatenar(Pila otraPila) {

        otraPila.invertir();
        Pila<T> pilaOrigInvertida = new Pila();

        while (!this.estaVacia()) {
            pilaOrigInvertida.apilar(this.desapilar());
        }

        //Lleno pila original
        while (!pilaOrigInvertida.estaVacia()) {
            this.apilar(pilaOrigInvertida.desapilar());
        }
    }

    @Override
    public void invertir() {

        Cola<T> colaAux = new Cola();
        while (!this.estaVacia()) {
            colaAux.encolar(this.desapilar());
        }

        while (!colaAux.isEmpty()) {
            this.apilar(colaAux.desencolar());
        }

    }
}
