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

    private boolean VerificarCapacidad() {
        if (cantElementos == cantMax) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void agregarInicio(T n) {

        if (VerificarCapacidad()) {
            new Exception("La lista esta llena");
        } else {

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


       /*
        Nodo nuevo = new Nodo(n);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;

        }
        cantElementos++;

        */
        }
    }

    @Override
    public void agregarFinal(T n) {


        if (esVacia()) {
            agregarInicio(n);
        } else {
            Nodo nuevo = new Nodo(n);
            fin.setSiguiente(nuevo);
            nuevo.setAnterior(fin);
            fin = nuevo;
            cantElementos++;
        }


        /*
        if (esVacia()) {
            agregarInicio(n);
        } else {
            Nodo nuevo = new Nodo(n);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        cantElementos++;

         */
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

        /*
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
            cantElementos--;
        }

         */

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
        /*
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
        */

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

    public void mostrarRecursivoConsultas() {
        mostrarRecursivoConsultas(inicio);
    }

    private void mostrarRecursivoConsultas(Nodo aux) {
        if (aux != null) {
            System.out.println(aux.getDato());
            mostrarRecursivoConsultas(aux.getSiguiente());
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

       /*
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

        */
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

    //Crea un metodo que agrega un elemento de forma ordenada, teniendo en cuenta que es una lista doble (se puede acceder a la anterior posicion)

    public void agregarOrdenado2(T x) {
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
                nuevo.setAnterior(aux);
                aux.getSiguiente().setAnterior(nuevo);
                aux.setSiguiente(nuevo);
                cantElementos++;
            }
        }
    }

    public void agregarOrdenado3(T x) {
        // Si la lista está vacía o el elemento es menor que el primero, se agrega al inicio
        if (esVacia() || inicio.getDato().compareTo(x) >= 0) {
            this.agregarInicio(x);
            // Si el elemento es mayor que el último, se agrega al final
        } else if (fin.getDato().compareTo(x) <= 0) {
            this.agregarFinal(x);
            // Si no, se busca la posición usando búsqueda binaria
        } else {
            // Se inicializa el rango de búsqueda con los índices del primer y último nodo
            int inicio = 0;
            int fin = cantElementos - 1;
            // Se inicializa el puntero al nodo actual con el valor del primer nodo
            Nodo actual = this.inicio;
            // Se inicializa el índice del nodo actual con el valor del índice de inicio
            int actualIndice = inicio;
            // Se repite hasta encontrar la posición o reducir el rango a un solo elemento
            while (inicio < fin) {
                // Se calcula el índice del nodo medio con la mitad del rango
                int medioIndice = (inicio + fin) / 2;
                // Se mueve el puntero al nodo actual hasta el nodo medio, avanzando o retrocediendo según sea necesario
                while (actualIndice < medioIndice) {
                    actual = actual.getSiguiente();
                    actualIndice++;
                }
                while (actualIndice > medioIndice) {
                    actual = actual.getAnterior();
                    actualIndice--;
                }
                // Se compara el elemento con el dato del nodo actual
                int comparacion = actual.getDato().compareTo(x);
                // Si son iguales, se ha encontrado la posición
                if (comparacion == 0) {
                    break;
                    // Si el elemento es menor que el dato del nodo actual, se reduce el rango a la mitad izquierda
                } else if (comparacion > 0) {
                    fin = medioIndice - 1;
                    // Si el elemento es mayor que el dato del nodo actual, se reduce el rango a la mitad derecha
                } else {
                    inicio = medioIndice + 1;
                }
            }
            // Se crea un nuevo nodo con el elemento
            Nodo nuevo = new Nodo(x);
            // Se inserta el nuevo nodo entre el nodo actual y el siguiente
            nuevo.setSiguiente(actual.getSiguiente());
            nuevo.setAnterior(actual);
            actual.getSiguiente().setAnterior(nuevo);
            actual.setSiguiente(nuevo);
            // Se incrementa el contador de elementos
            cantElementos++;
        }
    }


}
