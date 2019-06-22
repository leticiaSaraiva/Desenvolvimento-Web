package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.ItemPedido;
import com.br.ufc.model.Pedido;
import com.br.ufc.service.ItemPedidoService;
import com.br.ufc.service.PedidoService;

@Controller
@RequestMapping("/itemPedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping("/listar/{id}")
    public ModelAndView listar(@PathVariable Long id) {

        Pedido pedido = pedidoService.buscarPorID(id);

        List<ItemPedido> listaItens = itemPedidoService.listarItensPorPedido(pedido);

        ModelAndView mv = new ModelAndView("ListarItens");
        mv.addObject("listaItens", listaItens);
        return mv;

    }

}
