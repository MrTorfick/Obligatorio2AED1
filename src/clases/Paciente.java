package clases;

import tads.Lista;

public class Paciente implements Comparable<Paciente> {

    private String nombre;
    private int CI;
    private String direccion;
    private Lista<Consulta> listaConsultasPendientes;

    public Paciente(String nombre, int CI, String direccion) {
        this.setNombre(nombre);
        this.setCI(CI);
        this.setDireccion(direccion);
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
}
