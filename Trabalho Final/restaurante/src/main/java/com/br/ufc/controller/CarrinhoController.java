package com.br.ufc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.ItemPedido;
import com.br.ufc.model.Pedido;
import com.br.ufc.service.ItemPedidoService;
import com.br.ufc.service.PedidoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
    private ItemPedidoService itemPedidoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/")
	public ModelAndView listar() {
		List<Pedido> pedidos = pedidoService.listarTodos();
		
		ModelAndView mv = new ModelAndView("IndexCarrinho");
		mv.addObject("listPedidos", pedidos);
		
		return mv;
	}
	
	@RequestMapping("/adicionar/{codigo}")
    public ModelAndView adiciona(@PathVariable("codigo") long codigo, HttpSession session) {

        ModelAndView mv = new ModelAndView("redirect:/carrinho/");

        double total = 0.0;

        if(session.getAttribute("carrinho") == null) {

            List<ItemPedido> carrinho = new ArrayList<ItemPedido>();
            ItemPedido item = itemPedidoService.criaItem(codigo);

            double precoPrato = item.getPrato().getPreco();
            int qtd = item.getQuantidade();
            double subtotal = precoPrato * qtd;
            item.setValor(subtotal);

            carrinho.add(item);

            total = item.getValor();

            session.setAttribute("carrinho", carrinho);
            session.setAttribute("total", total);

        }else{

            List<ItemPedido> carrinho = (List<ItemPedido>) session.getAttribute("carrinho");

            int i = this.exists(codigo, carrinho);

            if(i == -1) {

                ItemPedido item = itemPedidoService.criaItem(codigo);

                Double precoPrato = item.getPrato().getPreco();
                int qtd = item.getQuantidade();
                double subtotal = precoPrato * qtd;
                item.setValor(subtotal);

                carrinho.add(item);

            }else {

                int quantidade = carrinho.get(i).getQuantidade() + 1;
                carrinho.get(i).setQuantidade(quantidade);

                double precoPrato = carrinho.get(i).getPrato().getPreco();
                double subtotal = precoPrato*quantidade;
                carrinho.get(i).setValor(subtotal);

            }

            for(ItemPedido item : carrinho) {
                total += item.getQuantidade() * item.getPrato().getPreco();
            }

            session.setAttribute("carrinho", carrinho);
            session.setAttribute("total", total);

        }

        return mv;

    }

    @RequestMapping("/remove/{codigo}")
    public String remove(@PathVariable("codigo") long codigo, HttpSession session) {

        double total = 0.0;

        List<ItemPedido> carrinho = (List<ItemPedido>) session.getAttribute("carrinho");
        int i = this.exists(codigo, carrinho);

        if(carrinho.get(i).getQuantidade() > 1){
            int qtd = carrinho.get(i).getQuantidade();
            carrinho.get(i).setQuantidade(qtd-1);

        }else {
            carrinho.remove(i);
        }

        for(ItemPedido item : carrinho) {
            total += item.getQuantidade() * item.getPrato().getPreco();
        }

        session.setAttribute("carrinho", carrinho);
        session.setAttribute("total", total);
        return "redirect:/carrinho/";
    }

    public int exists(long codigo, List<ItemPedido> carrinho) {
        for(int i = 0; i < carrinho.size(); i++) {
            if(carrinho.get(i).getPrato().getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }
}
