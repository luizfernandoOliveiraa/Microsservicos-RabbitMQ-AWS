package com.dev.propostaapp.controller;

import com.dev.propostaapp.dto.PropostaRequestDTO;
import com.dev.propostaapp.dto.PropostaResponseDTO;
import com.dev.propostaapp.service.PropostaSerivce;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private PropostaSerivce propostaSerivce;

    public PropostaController(PropostaSerivce propostaSerivce) {
        this.propostaSerivce = propostaSerivce;
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO propostaRequestDTO) {
        PropostaResponseDTO response = propostaSerivce.criar(propostaRequestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(response.getId())
                .toUri())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDTO>> obterProposta() {
        return ResponseEntity.ok(propostaSerivce.obterProposta());
    }
}
