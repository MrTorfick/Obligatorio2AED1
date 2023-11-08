package clases;

import tads.Cola;
import tads.Lista;
import tads.Nodo;

import java.time.LocalDate;
import java.util.Date;

public class Medico implements Comparable<Medico> {

    private String nombre;
    private int codMedico;
    private int tel;
    private int especialidad;
    private Lista<Consulta> listaPacientesEnEspera;
    private Cola<Consulta> colaPacientesEsperaNumeros;
    private Lista<FechaConsulta> listaFechas;

    public Medico(String nombre, int codMedico, int tel, int especialidad, Lista<Consulta> listaPacientesEnEspera) {
        this.setNombre(nombre);
        this.setCodMedico(codMedico);
        this.setTel(tel);
        this.setEspecialidad(especialidad);
        this.setListaPacientesEnEspera(listaPacientesEnEspera);
        this.setColaPacientesEsperaNumeros(new Cola<Consulta>());
    }

    public Medico(int codMedico) {
        this.setCodMedico(codMedico);
    }

    public Lista<FechaConsulta> getListaFechas() {
        return listaFechas;
    }

    public void setListaFechas(Lista<FechaConsulta> fecha) {
        this.listaFechas = fecha;
    }

    public FechaConsulta ObtenerFechaConsulta(Date Fecha) {

        Nodo nodo = listaFechas.getInicio();
        LocalDate fechaAux = LocalDate.of(Fecha.getYear(), Fecha.getMonth(), Fecha.getDay());
        while (nodo != null) {
            FechaConsulta fecha = (FechaConsulta) nodo.getDato();
            if (fecha.getFecha().equals(fechaAux)) {
                return fecha;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
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

    public Lista<Consulta> getListaPacientesEnEspera() {
        return listaPacientesEnEspera;
    }

    public void setListaPacientesEnEspera(Lista<Consulta> listaPacientesEnEspera) {
        this.listaPacientesEnEspera = listaPacientesEnEspera;
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

    public Cola<Consulta> getColaPacientesEsperaNumeros() {
        return colaPacientesEsperaNumeros;
    }

    public void setColaPacientesEsperaNumeros(Cola<Consulta> colaPacientesEsperaNumeros) {
        this.colaPacientesEsperaNumeros = colaPacientesEsperaNumeros;
    }

}
