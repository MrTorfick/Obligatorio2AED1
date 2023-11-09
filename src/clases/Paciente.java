package clases;

import enums.Estado;
import tads.Lista;
import tads.Nodo;

import java.util.Date;

public class Paciente implements Comparable<Paciente> {

    private String nombre;
    private int CI;
    private String direccion;
    private Lista<Consulta> listaConsultasPendientes;
    private Lista<Consulta> listaHistoriaClinica;

    public Paciente(String nombre, int CI, String direccion) {
        this.setNombre(nombre);
        this.setCI(CI);
        this.setDireccion(direccion);
        this.setListaConsultasPendientes(new Lista<Consulta>(-1));
        this.setListaHistoriaClinica(new Lista<Consulta>(-1));
    }


    public Lista<Consulta> ObtenerConsultasHistoriaClinica(int codMedico) {
        Lista<Consulta> lista = new Lista<Consulta>(-1);
        Nodo nodo = listaHistoriaClinica.getInicio();
        while (nodo != null) {
            Consulta consulta = (Consulta) nodo.getDato();
            if (consulta.getCodMedico() == codMedico) {
                lista.agregarInicio(consulta);
            }
            nodo = nodo.getSiguiente();
        }
        if (lista.cantElementos() > 0) {
            return lista;
        }
        return null;
    }

    public void CerrarConsultasNoAsistio(int codMedico, Date Fecha) {
        Nodo nodo = listaConsultasPendientes.getInicio();
        while (nodo != null) {
            Consulta consulta = (Consulta) nodo.getDato();
            if (consulta.getCodMedico() == codMedico && consulta.getFecha().equals(Fecha) && consulta.getEstado() == Estado.Pendiente) {
                consulta.setEstado(Estado.No_Asistio);
                listaHistoriaClinica.agregarInicio(consulta);
                listaConsultasPendientes.borrarElemento(consulta);
            }
            nodo = nodo.getSiguiente();
        }
    }

    public Paciente(int CI) {
        this.CI = CI;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the CI
     */
    public int getCI() {
        return CI;
    }

    /**
     * @param CI the CI to set
     */
    public void setCI(int CI) {
        this.CI = CI;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean equals(Object o) {
        Paciente paciente = (Paciente) o;//Casteo
        return this.getCI() == paciente.getCI();
    }

    public String toString() {
        return "Nombre: " + this.getNombre() + "\nCI: " + this.getCI();
    }



    /*
    *
    * public int compareTo(Medico o) {
        return this.getNombre().compareTo(o.getNombre());
    }
    * */
    @Override
    public int compareTo(Paciente o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Lista<Consulta> getListaConsultasPendientes() {
        return listaConsultasPendientes;
    }

    public void setListaConsultasPendientes(Lista<Consulta> listaConsultasPendientes) {
        this.listaConsultasPendientes = listaConsultasPendientes;
    }

    public Lista<Consulta> getListaHistoriaClinica() {
        return listaHistoriaClinica;
    }

    public void setListaHistoriaClinica(Lista<Consulta> listaHistoriaClinica) {
        this.listaHistoriaClinica = listaHistoriaClinica;
    }
}
