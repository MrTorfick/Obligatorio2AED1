package clases;

import java.time.LocalDate;

public class FechaConsulta implements Comparable<FechaConsulta> {

    private LocalDate fecha;
    private int cantPacientes;

    public LocalDate getFecha() {
        return fecha;
    }

    public FechaConsulta(LocalDate fecha, int cantPacientes) {
        this.fecha = fecha;
        this.cantPacientes = cantPacientes;
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
        //   = this.getFecha() == fechaConsulta.getFecha();
        return iguales;
    }

    @Override
    public int compareTo(FechaConsulta o) {
        return this.getFecha().compareTo(o.getFecha());
    }
}
