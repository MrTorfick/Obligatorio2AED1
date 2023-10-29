package sistemaAutogestion;

import java.util.Date;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    //pre:       post: Crea el sistema con sus listas correspondientes
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);
     //pre: nombre no puede ser nulo     post: Registra al medico
    public Retorno registrarMedico(String nombre,int codMedico,int tel, int especialidad); 
     //pre:      post: Elimina al medico mediante su id pasada por parametro
    public  Retorno eliminarMedico(int codMedico); 
     //pre: nombre ni direccion puede ser nulos     post: Agrega al paciente
    public Retorno agregarPaciente(String nombre, int CI, String direccion); 
     //pre:      post:Elimina al paciente mediante su ci pasada por parametro
    public Retorno eliminarPaciente(int CI); 

     /*
    **************** GESTIÓN DE CONSULTAS **************************************
    */
      
    //pre:      post:Se genera una reserva de una consulta para un medico en especifico
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha);
    //pre:      post:Se cancela la reserva de un consulta para un paciente con un medico
    public Retorno cancelarReserva(int codMedico, int ciPaciente); 
    //pre:      post:Se anuncia la llegada del paciente para su consulta con el medico
    public Retorno anunciaLlegada(int codMedico, int CIPaciente); 
    //pre:detallesDeConsulta no puede ser nulo      post:Se termina la consulta del paciente con el medico
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);   
    //pre:      post:Se cierra la consulta para una fecha en especifico
    public Retorno cerrarConsulta(int codMédico, Date fechaConsulta);
    
 
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    //pre:      post:Se listan los medicos por orden alfabetico
    public Retorno listarMédicos(); 
    //pre:      post:Se listan los pacientes segun el orden en el que fueron registrados
    public Retorno listarPacientes();     
    //pre:      post:Se listan las consultas asignadas a ese medico agrupadas por dia
    public Retorno listarConsultas(int codMedico); 
    //pre:      post:Lista a todos los pacientes en espera, con ese medico para esa fecha
    public Retorno listarPacientesEnEspera (int codMedico, Date fecha);
    //pre:      post:Lista las consultas pendientes de un paciente
    public Retorno consultasPendientesPaciente(int CIPaciente);   
    //pre:      post:Lista todas las consultas a las que asistio o no asistio el paciente
    public Retorno historiaClínicaPaciente (int ci);    
    //pre:      post:Se muestra una matriz con la cantidad de pacientes atendidos por cada especialidad
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año);
    Retorno registrarDiaDeConsulta(int codMedico,Date fecha);
     
}
