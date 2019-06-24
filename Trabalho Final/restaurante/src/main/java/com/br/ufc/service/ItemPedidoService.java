package com.br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.model.ItemPedido;
import com.br.ufc.model.Pedido;
import com.br.ufc.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PratoService pratoService;

    public void salvar(Iterable<ItemPedido> itens) {

        itemPedidoRepository.saveAll(itens);
    }

    public List<ItemPedido> listarItensPorPedido(Pedido pedido) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedido(pedido);
        return itens;
    }

    public ItemPedido criaItem(Long codigoP) {
        ItemPedido item = new ItemPedido();
        item.setPrato(pratoService.buscarPorId(codigoP));
        item.setQuantidade(1);
        return item;
    }
}
