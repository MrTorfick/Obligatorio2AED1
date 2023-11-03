package clases;

import java.util.Date;

public class FechaConsulta implements Comparable<FechaConsulta> {

    private Date fecha;
    private int cantPacientes;

    public Date getFecha() {
        return fecha;
    }

    public FechaConsulta(Date fecha, int cantPacientes) {
        this.fecha = fecha;
        this.cantPacientes = cantPacientes;
    }

    public FechaConsulta(Date fecha){
        this.fecha = fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantPacientes() {
        return cantPacientes;
    }

    public void setCantPacientes(int cantPacientes) {
        this.cantPacientes = cantPacientes;
    }

    @Override
    public int compareTo(FechaConsulta o) {
        return this.getFecha().compareTo(o.getFecha());
    }
}
