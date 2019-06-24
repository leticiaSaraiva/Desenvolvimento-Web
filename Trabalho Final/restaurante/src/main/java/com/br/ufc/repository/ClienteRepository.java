package com.br.ufc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ufc.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByLogin(String username);

}
