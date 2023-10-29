package clases;

import tads.Cola;
import tads.Lista;

import java.util.Date;

public class Medico implements Comparable<Medico> {

    private String nombre;
    private int codMedico;
    private int tel;
    private int especialidad;
    private Lista<Consulta> listaPacientes;
    private Cola<Paciente> colaPacientes;
    private Lista<Date> listaFechas;

    public Medico(String nombre, int codMedico, int tel, int especialidad, Lista<Consulta> listaPacientes) {
        this.setNombre(nombre);
        this.setCodMedico(codMedico);
        this.setTel(tel);
        this.setEspecialidad(especialidad);
        this.setListaPacientes(listaPacientes);
    }

    public Medico(int codMedico) {
        this.setCodMedico(codMedico);
    }

    public Lista<Date> getListaFechas() {
        return listaFechas;
    }

    public void setListaFechas(Lista<Date> fecha) {
        this.listaFechas = fecha;
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
     * @return the codMedico
     */
    public int getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    /**
     * @return the tel
     */
    public int getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(int tel) {
        this.tel = tel;
    }

    /**
     * @return the especialidad
     */
    public int getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    public Lista<Consulta> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(Lista<Consulta> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    //Compara el nombre de un medico con otro y luego lo ordena alfabeticamente por nombre
    @Override
    public int compareTo(Medico o) {
        return this.getNombre().compareTo(o.getNombre());
    }

    public boolean equals(Object o) {
        Medico medico = (Medico) o;//Casteo
        return this.getCodMedico() == medico.getCodMedico();
    }

    public String toString() {
        return "Nombre: " + this.getNombre() + "\nCodigo de medico: " + this.getCodMedico();
    }

}