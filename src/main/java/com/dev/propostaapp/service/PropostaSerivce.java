package com.dev.propostaapp.service;

import com.dev.propostaapp.dto.PropostaRequestDTO;
import com.dev.propostaapp.dto.PropostaResponseDTO;
import com.dev.propostaapp.entity.Proposta;
import com.dev.propostaapp.mapper.PropostaMapper;
import com.dev.propostaapp.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaSerivce {

    private final PropostaRepository propostaRepository;
    private final NotificationRabbitService notificationService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(propostaRequestDTO);
        propostaRepository.save(proposta);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    private void notificarRabbitMQ(Proposta proposta ) {
        try {
            notificationService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDTO> obterProposta() {
        Iterable<Proposta> propostas = propostaRepository.findAll();
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostas);

    }
}
