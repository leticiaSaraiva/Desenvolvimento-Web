package com.br.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ufc.model.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>{

}
