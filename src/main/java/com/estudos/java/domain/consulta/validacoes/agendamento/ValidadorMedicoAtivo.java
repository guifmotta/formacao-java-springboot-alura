package com.estudos.java.domain.consulta.validacoes.agendamento;

import com.estudos.java.domain.consulta.DadosAgendamentoConsulta;
import com.estudos.java.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        //escolha do medico opcional
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new RuntimeException("Consulta não pode ser agendada com médico excluído");
        }
    }

}
