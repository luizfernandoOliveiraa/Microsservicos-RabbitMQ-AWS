package com.dev.propostaapp.controller;

import com.dev.propostaapp.dto.PropostaRequestDTO;
import com.dev.propostaapp.dto.PropostaResponseDTO;
import com.dev.propostaapp.service.PropostaSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaSerivce propostaSerivce;

    public PropostaController(PropostaSerivce propostaSerivce) {
        this.propostaSerivce = propostaSerivce;
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO propostaRequestDTO) {
        PropostaResponseDTO propostaResponseDTO = propostaSerivce.criar(propostaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(propostaResponseDTO);
    }


}
