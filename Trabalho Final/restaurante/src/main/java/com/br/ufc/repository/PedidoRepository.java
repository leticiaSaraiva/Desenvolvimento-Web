package com.br.ufc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ufc.model.Cliente;
import com.br.ufc.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByCliente(Cliente cliente);

	
}
