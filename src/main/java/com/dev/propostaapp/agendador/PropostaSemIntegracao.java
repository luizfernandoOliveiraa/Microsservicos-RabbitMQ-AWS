package com.dev.propostaapp.agendador;

import com.dev.propostaapp.entity.Proposta;
import com.dev.propostaapp.repository.PropostaRepository;
import com.dev.propostaapp.service.NotificationRabbitService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class PropostaSemIntegracao {

    @Value("${rabbitmq.propostapendente.exchange}")
    String exchange;

    private final PropostaRepository propostaRepository;
    private final NotificationRabbitService notificationRabbitService;
    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao(){
        propostaRepository.findAllByIntegradaIsFalse()
                .forEach(proposta -> {
                    try {
                        notificationRabbitService.notificar(proposta, exchange);
                        atualizarProposta(proposta);
                    } catch (RuntimeException e) {
                        logger.error(e.getMessage());
                    }
                });
    }

    private void atualizarProposta(Proposta proposta) {
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}
