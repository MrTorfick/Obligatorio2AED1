package sistemaAutogestion;

import clases.Consulta;
import clases.FechaConsulta;
import clases.Medico;
import clases.Paciente;

import java.time.LocalDate;
import java.util.Date;

import enums.Estado;
import tads.*;

public class Sistema implements IObligatorio {

    private Lista listaMedicos;
    private Lista listaPacientes;
    private int tope;

    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (maxPacientesporMedico <= 0 || maxPacientesporMedico > 15) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaMedicos = new Lista(-1);
            listaPacientes = new Lista(-1);
            tope = maxPacientesporMedico;
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(nombre, codMedico, tel, especialidad, new Lista<Consulta>(-1));
        medico.setListaFechas(new Lista<FechaConsulta>(-1));

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


        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);

        if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Nodo nodo = listaMedicos.obtenerElemento(medico);
        medico = (Medico) nodo.getDato();
        if (medico.getListaPacientesEnEspera().cantElementos() > 0) {
            r.resultado = Retorno.Resultado.ERROR_2;
        }
        Nodo nodoaux = medico.getListaFechas().getInicio();
        while (nodoaux != null) {
            FechaConsulta fecha = (FechaConsulta) nodoaux.getDato();
            if (fecha.getCantPacientes() > 0) {
                r.resultado = Retorno.Resultado.ERROR_2;
                return r;
            }
            nodoaux = nodoaux.getSiguiente();
        }

        listaMedicos.borrarElemento(medico);
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno agregarPaciente(String nombre, int CI, String direccion) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente paciente = new Paciente(nombre, CI, direccion);

        if (listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        listaPacientes.agregarFinal(paciente);
        r.resultado = Retorno.Resultado.OK;

        return r;
    }

    @Override
    public Retorno eliminarPaciente(int CI) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Paciente paciente = new Paciente(CI);
        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        paciente = (Paciente) listaPacientes.obtenerElemento(paciente).getDato();
        if (paciente.getListaConsultasPendientes().cantElementos() > 0) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        listaPacientes.borrarElemento(paciente);
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno registrarDiaDeConsulta(int codMedico, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMedico);
        LocalDate fechaAux = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDay());
        FechaConsulta fechaConsulta = new FechaConsulta(fechaAux, 0);

        if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Nodo nodo = listaMedicos.obtenerElemento(medico);
        medico = (Medico) nodo.getDato();

        if (medico.getListaFechas().existeDato(fechaConsulta)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            medico.getListaFechas().agregarInicio(fechaConsulta);
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
            LocalDate fechaAux = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDay());
            FechaConsulta fechaConsulta = new FechaConsulta(fechaAux);
            if (!medico.getListaFechas().existeDato(fechaConsulta)) {
                r.resultado = Retorno.Resultado.ERROR_4;
                return r;
            }
            if (paciente.getListaConsultasPendientes() == null || !paciente.getListaConsultasPendientes().existeDato(consultaAux)) {
                fechaConsulta = (FechaConsulta) medico.getListaFechas().obtenerElemento(fechaConsulta).getDato();
                Consulta consulta = new Consulta(paciente.getCI(), medico.getCodMedico(), fecha, Estado.Pendiente);
                if (fechaConsulta.getCantPacientes() + 1 <= tope) {
                    paciente.getListaConsultasPendientes().agregarInicio(consulta);
                    fechaConsulta.setCantPacientes(fechaConsulta.getCantPacientes() + 1);
                    fechaConsulta.getListaPacientes().agregarInicio(paciente);

                } else {
                    medico.getColaPacientesEsperaNumeros().encolar(consulta);
                }
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
        boolean tienePacieneEsperaNumeros = false;

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
        Nodo nodoAux1 = listaPacientes.obtenerElemento(paciente);
        paciente = (Paciente) nodoAux1.getDato();


        if (paciente.getListaHistoriaClinica().existeDato(consultaAux)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        medico = (Medico) listaMedicos.obtenerElemento(medico).getDato();
        if (medico.getListaPacientesEnEspera().existeDato(consultaAux)) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }


        if (!paciente.getListaConsultasPendientes().existeDato(consultaAux)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        Consulta ObtenerConsultaPendiente = (Consulta) paciente.getListaConsultasPendientes().obtenerElemento(consultaAux).getDato();

        Medico medicoConsulta = new Medico(ObtenerConsultaPendiente.getCodMedico());
        Nodo nodoAux2 = listaMedicos.obtenerElemento(medicoConsulta);
        medicoConsulta = (Medico) nodoAux2.getDato();
        Consulta consulta = medicoConsulta.getColaPacientesEsperaNumeros().front();
        if (consulta != null) {
            tienePacieneEsperaNumeros = true;
            consulta.setNumeroDeReserva(ObtenerConsultaPendiente.getNumeroDeReserva());
        }
        paciente.getListaConsultasPendientes().borrarElemento(consultaAux);//Se borra la consulta de la lista de consultas pendientes del paciente
        //FechaConsulta fechaConsultaAux = medicoConsulta.ObtenerFechaConsulta(consulta.getFecha());
        FechaConsulta fechaConsultaAux = medicoConsulta.ObtenerFechaConsulta(ObtenerConsultaPendiente.getFecha());
        fechaConsultaAux.getListaPacientes().borrarElemento(paciente);//Se borra el paciente de la fecha de consulta, correspondiente al medico
        fechaConsultaAux.setCantPacientes(fechaConsultaAux.getCantPacientes() - 1);


        if (tienePacieneEsperaNumeros) {
            Paciente pacienteAux = new Paciente(consulta.getCiPaciente());
            Nodo nodoAux = listaPacientes.obtenerElemento(pacienteAux);
            pacienteAux = (Paciente) nodoAux.getDato();
            pacienteAux.getListaConsultasPendientes().agregarInicio(consulta);//Se agrega la consulta a la lista de consultas pendientes del paciente
            fechaConsultaAux.getListaPacientes().agregarInicio(pacienteAux);//Se agrega el paciente a la fecha de consulta, correspondiente al medico
            medicoConsulta.getColaPacientesEsperaNumeros().desencolar();
        }
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
        if (paciente.getListaConsultasPendientes().esVacia() || !paciente.getListaConsultasPendientes().existeDato(consulta)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }


        consulta = (Consulta) paciente.getListaConsultasPendientes().obtenerElemento(consulta).getDato();
        if (!paciente.getListaConsultasPendientes().existeDato(consulta)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        consulta = (Consulta) paciente.getListaConsultasPendientes().obtenerElemento(consulta).getDato();
        consulta.setEstado(Estado.En_Espera);
        Medico medico = new Medico(codMedico);
        medico = (Medico) listaMedicos.obtenerElemento(medico).getDato();
        consulta.setOrdenarPorNumeroDeReserva(true);
        medico.getListaPacientesEnEspera().agregarOrdenado(consulta);
        System.out.println("Nombre del medico: " + medico.getNombre() + "\nNumero de reserva: " + consulta.getNumeroDeReserva());
        paciente.getListaConsultasPendientes().borrarElemento(consulta);
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Paciente paciente = new Paciente(CIPaciente);

        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }

        Consulta consulta = new Consulta(codMedico, CIPaciente);
        Medico medico = new Medico(codMedico);
        medico = (Medico) listaMedicos.obtenerElemento(medico).getDato();
        if (!medico.getListaPacientesEnEspera().existeDato(consulta)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        //consulta = (Consulta) paciente.getListaConsultasPendientes().obtenerElemento(consulta).getDato();
        consulta = (Consulta) medico.getListaPacientesEnEspera().obtenerElemento(consulta).getDato();
        consulta.setDetalle(detalleDeConsulta);
        consulta.setEstado(Estado.Terminada);
        paciente = (Paciente) listaPacientes.obtenerElemento(paciente).getDato();
        paciente.getListaHistoriaClinica().agregarInicio(consulta);
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno cerrarConsulta(int codMédico, Date fechaConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medico = new Medico(codMédico);

        if (!listaMedicos.existeDato(medico)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        medico = (Medico) listaMedicos.obtenerElemento(medico).getDato();
        LocalDate fechaAux = LocalDate.of(fechaConsulta.getYear(), fechaConsulta.getMonth(), fechaConsulta.getDay());
        FechaConsulta fecha = new FechaConsulta(fechaAux);
        if (!medico.getListaFechas().existeDato(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        FechaConsulta consulta = medico.ObtenerFechaConsulta(fechaConsulta);
        consulta.CerrarConsultasNoAsistio(codMédico, fechaConsulta);
        r.resultado = Retorno.Resultado.OK;
        return r;
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

    private void listarConsultasRec(Medico medico, Lista<Consulta> listaConsultas) {
        if (medico.getListaPacientesEnEspera() != null) {
            listaConsultas.agregarOrdenadoListas(medico.getListaPacientesEnEspera());
        }
        if (medico.getColaPacientesEsperaNumeros() != null) {
            listaConsultas.agregarOrdenadoCola(medico.getColaPacientesEsperaNumeros());
        }

        Lista<FechaConsulta> listaAux = medico.getListaFechas();
        Nodo nodoAux = listaAux.getInicio();
        while (nodoAux != null) {
            FechaConsulta fechaConsulta = (FechaConsulta) nodoAux.getDato();
            listarConsultasFechaRec(fechaConsulta, listaConsultas, medico.getCodMedico());
            nodoAux = nodoAux.getSiguiente();
        }
    }

    private void listarConsultasFechaRec(FechaConsulta fechaConsulta, Lista<Consulta> listaConsultas,
                                         int codMedico) {
        Lista<Paciente> listaAuxPacientes = fechaConsulta.getListaPacientes();
        Nodo nodoAuxPacientes = listaAuxPacientes.getInicio();
        while (nodoAuxPacientes != null) {
            Paciente paciente = (Paciente) nodoAuxPacientes.getDato();
            if (paciente.ObtenerConsultasHistoriaClinica(codMedico) != null) {
                listaConsultas.agregarOrdenadoListas(paciente.ObtenerConsultasHistoriaClinica(codMedico));
            }
            nodoAuxPacientes = nodoAuxPacientes.getSiguiente();
        }
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
        Lista<Consulta> listaConsultas = new Lista<>(-1);
        medico = (Medico) nodo.getDato();
        listarConsultasRec(medico, listaConsultas);
        listaConsultas.mostrarRecursivo();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno listarPacientesEnEspera(int codMédico, Date fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico medico = new Medico(codMédico);
        medico = (Medico) listaMedicos.obtenerElemento(medico).getDato();
        LocalDate fechaAux = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDay());
        FechaConsulta fechaConsulta = new FechaConsulta(fechaAux);
        if (!medico.getListaFechas().existeDato(fechaConsulta)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Lista<Consulta> listaConsultas = medico.getListaPacientesEnEspera();
        Lista<Paciente> listaPacientesEnEspera = new Lista<Paciente>(-1);
        Lista<Consulta> listaAuxConsultas = new Lista<Consulta>(-1);
        Nodo nodo = listaConsultas.getInicio();
        while (nodo != null) {
            Consulta consulta = (Consulta) nodo.getDato();
            if (consulta.getFecha().equals(fecha)) {
                Paciente paciente = new Paciente(consulta.getCiPaciente());
                paciente = (Paciente) listaPacientes.obtenerElemento(paciente).getDato();
                consulta.setOrdenarPorNumeroDeReserva(true);
                listaAuxConsultas.agregarOrdenado(consulta);
            }
            nodo = nodo.getSiguiente();
        }
        Nodo nodoAux = listaAuxConsultas.getInicio();
        while (nodoAux != null) {
            Consulta consulta = (Consulta) nodoAux.getDato();
            Paciente paciente = new Paciente(consulta.getCiPaciente());
            paciente = (Paciente) listaPacientes.obtenerElemento(paciente).getDato();
            listaPacientesEnEspera.agregarInicio(paciente);
            nodoAux = nodoAux.getSiguiente();
        }
        listaPacientesEnEspera.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente paciente = new Paciente(CIPaciente);
        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        paciente = (Paciente) listaPacientes.obtenerElemento(paciente).getDato();
        paciente.getListaConsultasPendientes().mostrarRecursivo();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente paciente = new Paciente(ci);
        if (!listaPacientes.existeDato(paciente)) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        paciente = (Paciente) listaPacientes.obtenerElemento(paciente).getDato();
        paciente.getListaHistoriaClinica().mostrarRecursivo();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (mes <= 0 || mes > 12 || año < 2020 || año > 2023) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        int matriz[][] = new int[31][21];

        Nodo nodo = listaPacientes.getInicio();
        while (nodo != null) {
            Paciente paciente = (Paciente) nodo.getDato();
            Lista<Consulta> listaConsultas = paciente.getListaHistoriaClinica();
            Nodo nodoAux = listaConsultas.getInicio();
            while (nodoAux != null) {
                Consulta consulta = (Consulta) nodoAux.getDato();
                int mess = consulta.getFecha().getMonth();
                int anio = consulta.getFecha().getYear();
                anio = anio + 1900;
                if (consulta.getFecha().getMonth() == mes && anio == año) {
                    Medico medico = new Medico(consulta.getCodMedico());
                    medico = (Medico) listaMedicos.obtenerElemento(medico).getDato();
                    matriz[consulta.getFecha().getDate()][medico.getEspecialidad()]++;
                }
                nodoAux = nodoAux.getSiguiente();
            }
            nodo = nodo.getSiguiente();
        }
        for (int i = 1; i < matriz.length; i++) {
            System.out.println("Dia: " + i);
            for (int j = 1; j < matriz[i].length; j++) {
                System.out.println("Especialidad: " + j + " Cantidad: " + matriz[i][j]);
            }
        }
        r.resultado = Retorno.Resultado.OK;
        return r;
    }
}
