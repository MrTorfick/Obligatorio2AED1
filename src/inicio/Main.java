package inicio;

import clases.*;
import enums.Estado;
import sistemaAutogestion.*;
import tads.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Prueba p = new Prueba();
        Sistema s = new Sistema();


        Consulta consulta1 = new Consulta(85743756, 5, new Date(2023 - 1900, 5, 15), Estado.Pendiente);
        Lista<Consulta> listaConsultas = new Lista(0);
        listaConsultas.agregarInicio(consulta1);
        Medico medico1 = new Medico("Medico_1", 5, 382839132, 5, listaConsultas);
        //Medico medico2 = new Medico("Medico_2", 3, 727473857, 5);
        //Medico medico3 = new Medico("Medico_3", 44, 207584365, 5);
        //Medico medico4 = new Medico("Medico_4", 1, 764285693, 5);

        Lista<Medico> listaMedicos = new Lista(40);
        listaMedicos.agregarInicio(medico1);

        Paciente paciente1 = new Paciente("Paciente_1", 85743756, "Direccion Paciente 1");
        Paciente paciente2 = new Paciente("Paciente_2", 36574068, "Direccion Paciente 1");
        Paciente paciente3 = new Paciente("Paciente_3", 17485748, "Direccion Paciente 1");
        Paciente paciente4 = new Paciente("Paciente_4", 27593063, "Direccion Paciente 1");

        p.inicializarResultadosPrueba();
        p1_creacionSistema(p, s);
        p2_RegistrarMedico(p, s);
        p3_EliminarMedico(p, s);
        p4_RegistrarPaciente(p, s);
        p5_EliminarPaciente(p, s);
        p6_registrarDiaDeConsulta(p, s);
        p6_ReservaConsulta(p, s);
        p7_CancelarReserva(p, s);
        p8_AnunciaLlegada(p, s);
        p9_terminarConsultaMedicoPaciente(p, s);
        p10_cerrarConsulta(p, s);
        p11_ListarMedicos(p, s);
        p12_ListarPacientes(p, s);
        p13_ListarConsultas(p, s);
        p14_consultasPendientesPaciente(p, s);
        p15_historiaClínicaPaciente(p, s);
        p16_reporteDePacientesXFechaYEspecialidad(p, s);
        p.imprimirResultadosPrueba();


    }

    public static void p1_creacionSistema(Prueba p, Sistema s) {
        p.ver(s.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK, "Se crea correctamente el sistema con capacidad 10");
        p.ver(s.crearSistemaDeAutogestion(-5).resultado, Retorno.Resultado.ERROR_1, "No se crea el sistema, error1");
        p.ver(s.crearSistemaDeAutogestion(20).resultado, Retorno.Resultado.ERROR_1, "No se crea el sistema, error1");
    }

    public static void p2_RegistrarMedico(Prueba p, Sistema s) {
        p.ver(s.registrarMedico("Marcos", 1, 3893212, 5).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Pedro", 2, 9364758, 2).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Abel", 11, 7364736, 8).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Pedro", 2, 9364758, 2).resultado, Retorno.Resultado.ERROR_1, "No se registra el medico, error1");
        p.ver(s.registrarMedico("Peter", 3, 1748576, 21).resultado, Retorno.Resultado.ERROR_2, "No se registra el medico, error2");
        p.ver(s.registrarMedico("Peter", 3, 1748576, 0).resultado, Retorno.Resultado.ERROR_2, "No se registra el medico, error2");
    }

    public static void p3_EliminarMedico(Prueba p, Sistema s) {
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.OK, "Se elimino correctamente el medico");
        p.ver(s.eliminarMedico(11).resultado, Retorno.Resultado.OK, "Se elimino correctamente el medico");
        p.ver(s.eliminarMedico(10).resultado, Retorno.Resultado.ERROR_1, "No se elimina el medico, error1");
    }

    public static void p4_RegistrarPaciente(Prueba p, Sistema s) {
        p.ver(s.agregarPaciente("Pepe", 234879893, "Direccion del paciente Pepe").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Marcos", 858493751, "Direccion del paciente Marcos").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Nicolas", 758472658, "Direccion del paciente Nicolas").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Nicolas", 758472658, "Direccion del paciente Nicolas").resultado, Retorno.Resultado.ERROR_1, "No se registro el paciente, error1");
    }

    public static void p5_EliminarPaciente(Prueba p, Sistema s) {
        p.ver(s.eliminarPaciente(234879893).resultado, Retorno.Resultado.OK, "Se elimino el paciente");
        p.ver(s.eliminarPaciente(111111111).resultado, Retorno.Resultado.ERROR_1, "No se elimino el paciente, error1");
    }

    public static void p6_registrarDiaDeConsulta(Prueba p, Sistema s) {
        p.ver(s.registrarDiaDeConsulta(2, new Date(2023 - 1900, 12, 10)).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p6_ReservaConsulta(Prueba p, Sistema s) {

        p.ver(s.reservaConsulta(2, 758472658, new Date(2022 - 1900, 02, 25)).resultado, Retorno.Resultado.OK, "Se asocio al paciente");
    }

    public static void p7_CancelarReserva(Prueba p, Sistema s) {
        p.ver(s.cancelarReserva(1, 1).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p8_AnunciaLlegada(Prueba p, Sistema s) {
        p.ver(s.anunciaLlegada(1, 1).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p9_terminarConsultaMedicoPaciente(Prueba p, Sistema s) {
        p.ver(s.terminarConsultaMedicoPaciente(1, 1, "Detalles de la consulta").resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p10_cerrarConsulta(Prueba p, Sistema s) {
        p.ver(s.cerrarConsulta(1, new Date(2023 - 1900, 2, 20)).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p11_ListarMedicos(Prueba p, Sistema s) {
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se listaron los medicos");
    }

    public static void p12_ListarPacientes(Prueba p, Sistema s) {
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se listaron los pacientes");
    }

    public static void p13_ListarConsultas(Prueba p, Sistema s) {
        p.ver(s.listarConsultas(2).resultado, Retorno.Resultado.OK, "Se listan las consultas");
    }

    public static void p14_consultasPendientesPaciente(Prueba p, Sistema s) {
        p.ver(s.consultasPendientesPaciente(1).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p15_historiaClínicaPaciente(Prueba p, Sistema s) {
        p.ver(s.historiaClínicaPaciente(1).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }

    public static void p16_reporteDePacientesXFechaYEspecialidad(Prueba p, Sistema s) {
        p.ver(s.reporteDePacientesXFechaYEspecialidad(1, 1).resultado, Retorno.Resultado.OK, "Funcion no implementada");
    }


}
