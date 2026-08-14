package com.dev.propostaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropostaResponseDTO {

    private Long id;

    private String nome;

    private String sobrenome;

    private String telefone;

    private String cpf;

    private Double renda;

    private String valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;

    private String observacao;

}
