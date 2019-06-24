package com.br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView salvar(@Validated Cliente visitante, RedirectAttributes atributes, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("redirect:/cliente/formulario");
		
		if(result.hasErrors()) {
			return form(visitante);
		}
		
		atributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		
		clienteService.cadastrar(visitante);
		
		return mv;
	}
	
	
}
