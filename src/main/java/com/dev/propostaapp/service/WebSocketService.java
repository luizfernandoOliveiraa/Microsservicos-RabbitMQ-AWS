package com.dev.propostaapp.service;

import com.dev.propostaapp.dto.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void notificar(PropostaResponseDTO propostaResponseDTO) {
        simpMessagingTemplate.convertAndSend("/propostas", propostaResponseDTO);
    }
}
