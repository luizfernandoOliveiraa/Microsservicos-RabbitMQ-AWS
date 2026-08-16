package com.dev.propostaapp.listener;

import com.dev.propostaapp.dto.PropostaResponseDTO;
import com.dev.propostaapp.entity.Proposta;
import com.dev.propostaapp.mapper.PropostaMapper;
import com.dev.propostaapp.repository.PropostaRepository;
import com.dev.propostaapp.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropstaConcluidaListener {

    private final PropostaRepository propostaRepository;

    private final WebSocketService webSocketService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
        PropostaResponseDTO responseDTO = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        webSocketService.notificar(responseDTO);
    }
}
