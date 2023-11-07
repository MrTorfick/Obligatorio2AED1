package clases;

import enums.Estado;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta implements Comparable<Consulta> {

    private int NumeroDeReserva;
    private int ultimoId = 1;
    private int CiPaciente;
    private int CodMedico;
    private Date Fecha;
    private Estado estado;
    private String Detalle;


    public Consulta(int ciPaciente, int codMedico, Date fecha, Estado estado) {
        this.setCiPaciente(ciPaciente);
        this.setCodMedico(codMedico);
        this.setFecha(fecha);
        this.setEstado(estado);
        this.setNumeroDeReserva(ultimoId);
        ultimoId++;
    }


    public Consulta(int codMedico, int ciPaciente) {
        this.setNumeroDeReserva(ultimoId++);
        ultimoId++;
        this.setCodMedico(codMedico);
        this.setCiPaciente(ciPaciente);
    }
    public Consulta(int codMedico){
        this.setCodMedico(codMedico);
    }


    public int getCiPaciente() {
        return CiPaciente;
    }

    public void setCiPaciente(int ciPaciente) {
        CiPaciente = ciPaciente;
    }

    public int getCodMedico() {
        return CodMedico;
    }

    public void setCodMedico(int codMedico) {
        CodMedico = codMedico;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getNumeroDeReserva() {
        return NumeroDeReserva;
    }

    public void setNumeroDeReserva(int numeroDeReserva) {
        NumeroDeReserva = numeroDeReserva;
    }

    public boolean equals(Object o) {
        Consulta consulta = (Consulta) o;//Casteo
        return this.getCodMedico() == consulta.getCodMedico();
    }

    @Override
    public int compareTo(Consulta o) {
        return this.getFecha().compareTo(o.getFecha());
    }


    public String toString() {
        return "Cedula del Paciente " + this.getCiPaciente() + "\nCodigo de medico: " + this.getCodMedico() +
                "\nFecha: " + this.getFecha() + "\nEstado: " + this.getEstado() + "\nNumero de reserva: " + this.getNumeroDeReserva();
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }
}
