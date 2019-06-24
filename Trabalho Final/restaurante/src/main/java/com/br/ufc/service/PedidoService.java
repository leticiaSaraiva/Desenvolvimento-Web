package com.br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.model.Cliente;
import com.br.ufc.model.Pedido;
import com.br.ufc.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
    private PedidoRepository pedidoRepository;

    public void salvar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }
    

    public List<Pedido> encontrarCliente(Cliente cliente) {
    	return pedidoRepository.findByCliente(cliente);    
    	
    }

    public Pedido buscarPorID(Long codigo) {
        return pedidoRepository.getOne(codigo);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }



}
