package clases;

import enums.Estado;

import java.util.Date;

public class Consulta implements Comparable<Consulta> {

    private int codConsulta;
    private int CiPaciente;
    private int CodMedico;
    private Date Fecha;
    private Estado estado;
    private int NumeroDeReserva;

    public Consulta(int codConsulta, int ciPaciente, int codMedico, Date fecha, Estado estado, int numeroDeReserva) {
        this.setCodConsulta(codConsulta);
        this.setCiPaciente(ciPaciente);
        this.setCodMedico(codMedico);
        this.setFecha(fecha);
        this.setEstado(estado);
        this.setNumeroDeReserva(numeroDeReserva);
    }


    public int getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(int codConsulta) {
        this.codConsulta = codConsulta;
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

    @Override
    public int compareTo(Consulta o) {
        return 0;
    }
}
