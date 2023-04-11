package com.estudos.java.domain.consulta.validacoes.agendamento;

import com.estudos.java.domain.consulta.DadosAgendamentoConsulta;
import com.estudos.java.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new RuntimeException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
