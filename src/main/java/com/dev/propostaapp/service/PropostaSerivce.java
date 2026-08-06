package com.dev.propostaapp.service;

import com.dev.propostaapp.dto.PropostaRequestDTO;
import com.dev.propostaapp.dto.PropostaResponseDTO;
import com.dev.propostaapp.entity.Proposta;
import com.dev.propostaapp.repository.PropostaRepository;
import org.springframework.stereotype.Service;

@Service
public class PropostaSerivce {

    private PropostaRepository propostaRepository;

    public PropostaSerivce(PropostaRepository propostaRepository) {};

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO) {
        propostaRepository.save();
        return null;
    }
}
