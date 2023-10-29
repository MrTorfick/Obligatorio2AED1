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
        medico.setListaFechas(new Lista<Date>(0));

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
    public Retorno registrarDiaDeConsulta(int codMedico, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);

        if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Nodo nodo = listaMedicos.obtenerElemento(medico);
        medico = (Medico) nodo.getDato();

        if (medico.getListaFechas().existeDato(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            medico.getListaFechas().agregarFinal(fecha);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);
        Paciente paciente = new Paciente(ciPaciente);
        Consulta consultaAux = new Consulta(codMedico, ciPaciente);
        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        } else if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            Nodo nodoAux1 = listaMedicos.obtenerElemento(medico);
            medico = (Medico) nodoAux1.getDato();
            Nodo nodoAux2 = listaPacientes.obtenerElemento(paciente);
            paciente = (Paciente) nodoAux2.getDato();
            if (!medico.getListaFechas().existeDato(fecha)) {
                r.resultado = Retorno.Resultado.ERROR_4;
                return r;
            }
            if (paciente.getListaConsultasPendientes() == null || !paciente.getListaConsultasPendientes().existeDato(consultaAux)) {
                /*
                consulta.setCiPaciente(paciente.getCI());
                consulta.setCodMedico(medico.getCodMedico());
                consulta.setFecha(fecha);
                consulta.setEstado(Estado.Pendiente);
                */

                Consulta consulta = new Consulta(paciente.getCI(), medico.getCodMedico(), fecha, Estado.Pendiente);
                paciente.getListaConsultasPendientes().agregarFinal(consulta);
                r.resultado = Retorno.Resultado.OK;
            } else {
                r.resultado = Retorno.Resultado.ERROR_3;
            }
        }
        return r;
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);
        Paciente paciente = new Paciente(ciPaciente);

        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        Consulta consultaAux = new Consulta(codMedico, ciPaciente);
        if (paciente.getListaConsultasPendientes() == null || !paciente.getListaConsultasPendientes().existeDato(consultaAux)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        Consulta ObtenerConsultaPendiente = (Consulta) paciente.getListaConsultasPendientes().obtenerElemento(consultaAux).getDato();
        if (ObtenerConsultaPendiente.getEstado() == Estado.Cerrada) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        if (ObtenerConsultaPendiente.getEstado() != Estado.Pendiente) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }
        Medico medicoConsulta = new Medico(ObtenerConsultaPendiente.getCodMedico());
        Nodo nodoAux1 = listaMedicos.obtenerElemento(medicoConsulta);
        medicoConsulta = (Medico) nodoAux1.getDato();
        Consulta consulta = medicoConsulta.getColaPacientesEsperaNumeros().front();
        consulta.setNumeroDeReserva(ObtenerConsultaPendiente.getNumeroDeReserva());
        paciente.getListaConsultasPendientes().borrarElemento(consultaAux);
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Paciente pacienteaux = new Paciente(CIPaciente);

        if (!listaPacientes.existeDato(pacienteaux)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }

        Paciente paciente = (Paciente) listaPacientes.obtenerElemento(pacienteaux).getDato();

        Consulta consulta = new Consulta(codMedico, CIPaciente);

        if (!paciente.getListaConsultasPendientes().existeDato(consulta)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        consulta = (Consulta) paciente.getListaConsultasPendientes().obtenerElemento(consulta).getDato();
        consulta.setEstado(Estado.En_Espera);
        return r;

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
        medicoAux.getListaPacientesEnEspera().mostrar();
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
