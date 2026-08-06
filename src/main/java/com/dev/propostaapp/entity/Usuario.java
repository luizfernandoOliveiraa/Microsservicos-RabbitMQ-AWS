package com.dev.propostaapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Proposta> propostas = new ArrayList<>();
}
