package com.br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.model.Cliente;
import com.br.ufc.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@RequestMapping("/formulario")
	public ModelAndView form(Cliente cliente) {
		ModelAndView mv = new ModelAndView("Formulario");
		//mv.addObject("cliente", new Cliente());
		
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Validated Cliente visitante, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("Formulario");
		
		if(result.hasErrors()) {
			return form(visitante);
		}
		
		mv.addObject("mensagem", "Cadastro realizado com sucesso!");
		
		clienteService.cadastrar(visitante);
		
		return mv;
	}
	
	
}
