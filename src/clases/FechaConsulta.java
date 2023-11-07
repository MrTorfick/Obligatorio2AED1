package clases;

import tads.Lista;
import tads.Nodo;

import java.time.LocalDate;
import java.util.Date;

public class FechaConsulta implements Comparable<FechaConsulta> {

    private LocalDate fecha;
    private int cantPacientes;
    private Lista<Paciente> listaPacientes;

    public LocalDate getFecha() {
        return fecha;
    }

    public FechaConsulta(LocalDate fecha, int cantPacientes) {
        this.fecha = fecha;
        this.cantPacientes = cantPacientes;
        this.listaPacientes = new Lista<Paciente>(-1);
    }

    public void CerrarConsultasNoAsistio(int codMedico, Date Fecha) {

        Nodo nodo = listaPacientes.getInicio();
        while (nodo != null) {
            Paciente paciente = (Paciente) nodo.getDato();
            paciente.CerrarConsultasNoAsistio(codMedico, Fecha);
            nodo = nodo.getSiguiente();
        }

    }

    public FechaConsulta(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantPacientes() {
        return cantPacientes;
    }

    public void setCantPacientes(int cantPacientes) {
        this.cantPacientes = cantPacientes;
    }

    public boolean equals(Object o) {
        FechaConsulta fechaConsulta = (FechaConsulta) o;//Casteo
        boolean iguales = this.getFecha().getDayOfYear() == fechaConsulta.getFecha().getDayOfYear() &&
                this.getFecha().getMonthValue() == fechaConsulta.getFecha().getMonthValue() &&
                this.getFecha().getDayOfMonth() == fechaConsulta.getFecha().getDayOfMonth();
        return iguales;
    }

    @Override
    public int compareTo(FechaConsulta o) {
        return this.getFecha().compareTo(o.getFecha());
    }

    public Lista<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(Lista<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }
}
