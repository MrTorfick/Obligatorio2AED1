package sistemaAutogestion;

import clases.Consulta;
import clases.Medico;
import clases.Paciente;

import java.util.Date;

import enums.Estado;
import tads.*;

public class Sistema implements IObligatorio {

    private Lista listaMedicos;
    private Lista listaPacientes;

    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (maxPacientesporMedico <= 0 || maxPacientesporMedico > 15) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaMedicos = new Lista(maxPacientesporMedico);
            listaPacientes = new Lista(0);
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(nombre, codMedico, tel, especialidad, new Lista<Consulta>(0));

        if (listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        } else {
            if (especialidad < 1 || especialidad > 20) {
                r.resultado = Retorno.Resultado.ERROR_2;
                return r;
            } else {
                listaMedicos.agregarOrdenado(medico);
                r.resultado = Retorno.Resultado.OK;
            }
        }
        return r;
    }

    @Override
    public Retorno eliminarMedico(int codMedico) {
        /*
        El caso de Error2, no esta implementado debido a que para la primera entrega
        no se solicita implementar el realizar una reserva de una consulta de un medico
         */

        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);
        if (listaMedicos.existeDato(medico)) {
            listaMedicos.borrarElemento(medico);
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    @Override
    public Retorno agregarPaciente(String nombre, int CI, String direccion) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente paciente = new Paciente(nombre, CI, direccion);

        if (listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        } else {

            listaPacientes.agregarFinal(paciente);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    @Override
    public Retorno eliminarPaciente(int CI) {
        /*
        El caso de Error2, no esta implementado debio a que para la primera entrega
        no se solicita implementar el realizar una reserva de una consulta de un medico
         */
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Paciente paciente = new Paciente(CI);
        if (listaPacientes.existeDato(paciente)) {
            listaPacientes.borrarElemento(paciente);
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;

    }

    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);
        Paciente paciente = new Paciente(ciPaciente);
        Consulta consulta = new Consulta(1);

        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_2;
        } else {
            Nodo nodo = listaMedicos.obtenerElemento(medico);
            medico = (Medico) nodo.getDato();
            if (medico.getListaPacientes() == null || !medico.getListaPacientes().existeDato(consulta)) {
                consulta.setCiPaciente(paciente.getCI());
                consulta.setCodMedico(medico.getCodMedico());
                consulta.setFecha(fecha);
                consulta.setEstado(Estado.Pendiente);
                medico.getListaPacientes().agregarFinal(consulta);
                r.resultado = Retorno.Resultado.OK;
            } else {
                r.resultado = Retorno.Resultado.ERROR_4;
            }
        }
        return r;
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno cerrarConsulta(int codMédico, Date fechaConsulta) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarMédicos() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        listaMedicos.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno listarPacientes() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        listaPacientes.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno listarConsultas(int codMédico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico medico = new Medico(codMédico);
        Nodo nodo = listaMedicos.obtenerElemento(medico);
        if (nodo == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Medico medicoAux = (Medico) nodo.getDato();
        medicoAux.getListaPacientes().mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno listarPacientesEnEspera(int codMédico, Date fecha) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

}
