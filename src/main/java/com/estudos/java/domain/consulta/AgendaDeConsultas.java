package com.estudos.java.domain.consulta;

import com.estudos.java.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import com.estudos.java.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import com.estudos.java.domain.medico.Medico;
import com.estudos.java.domain.medico.MedicoRepository;
import com.estudos.java.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente()))
            throw new RuntimeException("ID do paciente informado não existe");

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new RuntimeException("ID do médico informado não existe");

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        if (medico == null) {
            throw new RuntimeException("Não existe médico livre nesta data");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new RuntimeException("Especialidade é obrigatória quando o médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.idConsulta())) {
            throw new RuntimeException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
