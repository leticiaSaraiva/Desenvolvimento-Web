package com.br.ufc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.Cliente;
import com.br.ufc.model.ItemPedido;
import com.br.ufc.model.Pedido;
import com.br.ufc.service.ClienteService;
import com.br.ufc.service.ItemPedidoService;
import com.br.ufc.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemPedidoService itemPedidoService;
    
    @RequestMapping("/confirmar")
    public ModelAndView confirmar(HttpSession session) {
    	
    	Iterable<ItemPedido> carrinho = (Iterable<ItemPedido>) session.getAttribute("carrinho");

        Pedido pedido = new Pedido();

        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = (UserDetails) auth;

        Cliente cliente = clienteService.buscarPorLogin(user.getUsername());

        pedido.setCliente(cliente);
        pedido.setTotal(0.0);
        pedidoService.salvar(pedido);

        double total = 0.0;
        for (ItemPedido item : carrinho) {
            item.setPedido(pedido);
            total += item.getValor() * item.getQuantidade();
        }

        itemPedidoService.salvar(carrinho);
        
        total = (double) session.getAttribute("total");
        
        pedido.setTotal(total);
        pedidoService.salvar(pedido); 

        session.removeAttribute("carrinho");
        session.removeAttribute("total");
        
        ModelAndView mv = new ModelAndView("redirect:/pedido/listar");
        return mv;
    }
    
    @RequestMapping("/listar")
    public ModelAndView listar() {

        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails user = (UserDetails) auth;

        Cliente cliente = clienteService.buscarPorLogin(user.getUsername());

        List<Pedido> listaPedidos = pedidoService.encontrarCliente(cliente);

        ModelAndView mv = new ModelAndView("HistoricoPedidos");
        mv.addObject("listaPedidos", listaPedidos);
        return mv;
    }
}
