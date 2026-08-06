package com.dev.propostaapp.repository;

import com.dev.propostaapp.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    
}
