package inicio;

import clases.*;
import enums.Estado;
import sistemaAutogestion.*;
import tads.*;

import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Prueba p = new Prueba();
        Sistema s = new Sistema();



        p.inicializarResultadosPrueba();
        p1_creacionSistema(p, s);
        p2_RegistrarMedico(p, s);
        p6_registrarDiaDeConsulta(p, s);

        p4_RegistrarPaciente(p, s);
        p6_ReservaConsulta(p, s);
        p5_EliminarPaciente(p, s);

        p3_EliminarMedico(p, s);
        p8_AnunciaLlegada(p, s);
        p9_terminarConsultaMedicoPaciente(p, s);
        p10_cerrarConsulta(p, s);
        p7_CancelarReserva(p, s);

        p11_ListarMedicos(p, s);
        p12_ListarPacientes(p, s);
        p13_ListarConsultas(p, s);
        p14_consultasPendientesPaciente(p, s);
        p15_historiaClínicaPaciente(p, s);
        p16_reporteDePacientesXFechaYEspecialidad(p, s);
        p17_listarPacientesEnEspera(p, s);
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
        p.ver(s.registrarMedico("Abel", 99, 7364736, 8).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Martin", 22, 8746587, 12).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Matias", 9, 8475647, 19).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Matias", 11, 8475647, 9).resultado, Retorno.Resultado.OK, "Se registra correctamente el medico");
        p.ver(s.registrarMedico("Pedro", 2, 9364758, 2).resultado, Retorno.Resultado.ERROR_1, "No se registra el medico, error1");
        p.ver(s.registrarMedico("Peter", 3, 1748576, 21).resultado, Retorno.Resultado.ERROR_2, "No se registra el medico, error2");
        p.ver(s.registrarMedico("Peter", 3, 1748576, 0).resultado, Retorno.Resultado.ERROR_2, "No se registra el medico, error2");
    }

    public static void p3_EliminarMedico(Prueba p, Sistema s) {
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.OK, "Se elimino correctamente el medico");
        p.ver(s.eliminarMedico(10).resultado, Retorno.Resultado.ERROR_1, "No se elimina el medico, error1");
        p.ver(s.eliminarMedico(9).resultado, Retorno.Resultado.ERROR_2, "No se elimina el medico, error2");
    }

    public static void p4_RegistrarPaciente(Prueba p, Sistema s) {
        p.ver(s.agregarPaciente("Pepe", 234879893, "Direccion del paciente Pepe").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Marcos", 858493751, "Direccion del paciente Marcos").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Nicolas", 758472658, "Direccion del paciente Nicolas").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Matias", 111111111, "Direccion del paciente Matias").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Micaela", 826475867, "Direccion de la paciente Micaela").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Pedro", 222222222, "Direccion del paciente Pedro").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Miguel", 333333333, "Direccion del paciente Miguel").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Luana", 444444444, "Direccion de la  paciente Luana").resultado, Retorno.Resultado.OK, "Se registro correctamente el paciente");
        p.ver(s.agregarPaciente("Nicolas", 758472658, "Direccion del paciente Nicolas").resultado, Retorno.Resultado.ERROR_1, "No se registro el paciente, error1");
    }

    public static void p5_EliminarPaciente(Prueba p, Sistema s) {
        p.ver(s.eliminarPaciente(234879893).resultado, Retorno.Resultado.OK, "Se elimino el paciente");
        p.ver(s.eliminarPaciente(777777777).resultado, Retorno.Resultado.ERROR_1, "No se elimino el paciente, error1");
        p.ver(s.eliminarPaciente(111111111).resultado, Retorno.Resultado.ERROR_2, "No se elimino el paciente, error2");
    }

    public static void p6_registrarDiaDeConsulta(Prueba p, Sistema s) {
        p.ver(s.registrarDiaDeConsulta(2, new Date(2023 - 1900, Calendar.OCTOBER, 10)).resultado, Retorno.Resultado.OK, "Se registra el dia de consulta");
        p.ver(s.registrarDiaDeConsulta(9, new Date(2023 - 1900, Calendar.MARCH, 15)).resultado, Retorno.Resultado.OK, "Se registra el dia de consulta");
        p.ver(s.registrarDiaDeConsulta(22, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.OK, "Se registra el dia de consulta");
        p.ver(s.registrarDiaDeConsulta(99, new Date(2022 - 1900, Calendar.DECEMBER, 24)).resultado, Retorno.Resultado.OK, "Se registra el dia de consulta");
        p.ver(s.registrarDiaDeConsulta(11, new Date(2022 - 1900, Calendar.JULY, 5)).resultado, Retorno.Resultado.OK, "Se registra el dia de consulta");
        p.ver(s.registrarDiaDeConsulta(9, new Date(2023 - 1900, Calendar.MARCH, 15)).resultado, Retorno.Resultado.ERROR_2, "No se registra el dia de consulta, error2");
        p.ver(s.registrarDiaDeConsulta(000, new Date(2021 - 1900, Calendar.APRIL, 1)).resultado, Retorno.Resultado.ERROR_1, "No se registra el dia de consulta, error1");
    }

    public static void p6_ReservaConsulta(Prueba p, Sistema s) {

        p.ver(s.reservaConsulta(22, 758472658, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.OK, "Se reserva la consulta");
        p.ver(s.reservaConsulta(22, 826475867, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.OK, "Se reserva la consulta");
        p.ver(s.reservaConsulta(22, 858493751, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.OK, "Se reserva la consulta");
        p.ver(s.reservaConsulta(99, 111111111, new Date(2022 - 1900, Calendar.DECEMBER, 24)).resultado, Retorno.Resultado.OK, "Se reserva la consulta");
        p.ver(s.reservaConsulta(9, 222222222, new Date(2023 - 1900, Calendar.MARCH, 15)).resultado, Retorno.Resultado.OK, "Se reserva la consulta");
        p.ver(s.reservaConsulta(11, 333333333, new Date(2022 - 1900, Calendar.JULY, 5)).resultado, Retorno.Resultado.OK, "Se reserva la consulta");
        p.ver(s.reservaConsulta(9, 123456789, new Date(2023 - 1900, Calendar.MARCH, 15)).resultado, Retorno.Resultado.ERROR_1, "No se reserva la consulta, error1");
        p.ver(s.reservaConsulta(7918, 111111111, new Date(2023 - 1900, Calendar.MARCH, 15)).resultado, Retorno.Resultado.ERROR_2, "No se reserva la consulta, error2");
        p.ver(s.reservaConsulta(9, 222222222, new Date(2023 - 1900, Calendar.MARCH, 15)).resultado, Retorno.Resultado.ERROR_3, "No se reserva la consulta, error3");
    }


    public static void p7_CancelarReserva(Prueba p, Sistema s) {
        p.ver(s.cancelarReserva(22, 123456789).resultado, Retorno.Resultado.ERROR_1, "No se cancela la reserva, error1");
        p.ver(s.cancelarReserva(123456789, 758472658).resultado, Retorno.Resultado.ERROR_2, "No se cancela la reserva, error2");
        p.ver(s.cancelarReserva(99, 758472658).resultado, Retorno.Resultado.ERROR_3, "No se cancela la reserva, error3");
        p.ver(s.cancelarReserva(11, 333333333).resultado, Retorno.Resultado.OK, "Se cancela la reserva");
    }

    public static void p8_AnunciaLlegada(Prueba p, Sistema s) {

        p.ver(s.anunciaLlegada(22, 826475867).resultado, Retorno.Resultado.OK, "Se anuncia la llegada");
        p.ver(s.anunciaLlegada(22, 858493751).resultado, Retorno.Resultado.OK, "Se anuncia la llegada");
        p.ver(s.anunciaLlegada(22, 000000000).resultado, Retorno.Resultado.ERROR_1, "No se anuncia la llegada, error1");
        p.ver(s.anunciaLlegada(22, 222222222).resultado, Retorno.Resultado.ERROR_2, "No se anuncia la llegada, error2");
        p.ver(s.anunciaLlegada(22, 444444444).resultado, Retorno.Resultado.ERROR_2, "No se anuncia la llegada, error2");
    }

    public static void p9_terminarConsultaMedicoPaciente(Prueba p, Sistema s) {
        p.ver(s.terminarConsultaMedicoPaciente(826475867, 22, "Detalles de la consulta 1").resultado, Retorno.Resultado.OK, "Se termino la consulta");
        p.ver(s.terminarConsultaMedicoPaciente(758472658, 2, "Detalles de la consulta 2").resultado, Retorno.Resultado.ERROR_2, "No es posible terminar la consulta, error2");
        p.ver(s.terminarConsultaMedicoPaciente(195707564, 2, "Detalles de la consulta 3").resultado, Retorno.Resultado.ERROR_1, "No es posible terminar la consulta, error1");
    }

    public static void p10_cerrarConsulta(Prueba p, Sistema s) {
        p.ver(s.cerrarConsulta(480932890, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.ERROR_1, "No se cierra la consulta, error1");
        p.ver(s.cerrarConsulta(22, new Date(2023 - 1900, Calendar.MAY, 15)).resultado, Retorno.Resultado.ERROR_2, "No se cierra la consulta, error2");
        p.ver(s.cerrarConsulta(22, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.OK, "Se cierra la consulta");
    }

    public static void p11_ListarMedicos(Prueba p, Sistema s) {
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se listaron los medicos");
    }

    public static void p12_ListarPacientes(Prueba p, Sistema s) {
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se listaron los pacientes");
    }

    public static void p13_ListarConsultas(Prueba p, Sistema s) {
        p.ver(s.listarConsultas(22).resultado, Retorno.Resultado.OK, "Se listan las consultas");
        p.ver(s.listarConsultas(342809).resultado, Retorno.Resultado.ERROR_1, "No se listan las consultas, error1");
    }

    public static void p14_consultasPendientesPaciente(Prueba p, Sistema s) {
        p.ver(s.consultasPendientesPaciente(111111111).resultado, Retorno.Resultado.OK, "Se listan las consultas pendientes de un paciente determinado");
        p.ver(s.consultasPendientesPaciente(755555463).resultado, Retorno.Resultado.ERROR_1, "No se listan las consultas pendientes de un paciente determinado, error1");
    }

    public static void p15_historiaClínicaPaciente(Prueba p, Sistema s) {
        p.ver(s.historiaClínicaPaciente(826475867).resultado, Retorno.Resultado.OK, "Se lista la historia clinica de un paciente determinado");
        p.ver(s.historiaClínicaPaciente(755555463).resultado, Retorno.Resultado.ERROR_1, "No se lista la historia clinica de un paciente determinado, error1");
    }

    public static void p16_reporteDePacientesXFechaYEspecialidad(Prueba p, Sistema s) {
        p.ver(s.reporteDePacientesXFechaYEspecialidad(7, 2023).resultado, Retorno.Resultado.OK, "Se muestra la matriz correspondiente");
        p.ver(s.reporteDePacientesXFechaYEspecialidad(-5, 3000).resultado, Retorno.Resultado.ERROR_1, "No se muestra la matriz correspondiente, error1");
    }

    public static void p17_listarPacientesEnEspera(Prueba p, Sistema s) {
        p.ver(s.listarPacientesEnEspera(22, new Date(2023 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.OK, "Se listan los pacientes en espera");
        p.ver(s.listarPacientesEnEspera(22, new Date(2022 - 1900, Calendar.AUGUST, 22)).resultado, Retorno.Resultado.ERROR_1, "No se listan los pacientes en espera, error1");
    }


}
