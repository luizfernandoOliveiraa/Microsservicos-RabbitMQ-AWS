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
    private final NotificationService notificationService;

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(propostaRequestDTO);
        propostaRepository.save(proposta);

        PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        notificationService.notificar(response, exchange);

        return response;
    }

    public List<PropostaResponseDTO> obterProposta() {
        Iterable<Proposta> propostas = propostaRepository.findAll();
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostas);

    }
}
