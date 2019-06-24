package com.br.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.ufc.model.Prato;
import com.br.ufc.model.Cliente;
import com.br.ufc.service.PratoService;

@Controller
@RequestMapping("/prato")
public class PratoController {
	
	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("/formularioPrato")
	public ModelAndView form(Prato prato) {
		ModelAndView mv = new ModelAndView("FormularioPrato");
		//mv.addObject("prato", new Prato());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Validated Prato prato, BindingResult result, RedirectAttributes atributes , @RequestParam(value="imagem") MultipartFile imagem) {
		
		ModelAndView mv = new ModelAndView("redirect:/prato/formularioPrato");
		
		if(result.hasErrors() || imagem == null) {
			return form(prato);
		}
		
		atributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
		
		pratoService.cadastrar(prato, imagem);
		
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		//Devolve todos os pratos do banco
		List<Prato> pratos = pratoService.listarTodos();
		
		ModelAndView mv = new ModelAndView("Index");
		mv.addObject("listPratos", pratos);
		
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public ModelAndView excluir(@PathVariable Long codigo) {
		pratoService.excluir(codigo);
		ModelAndView mv = new ModelAndView("redirect:/prato/listar");
		return mv;
		
		
	}
	
	@RequestMapping("/atualizar/{codigo}")
	public ModelAndView atualizar(@PathVariable Long codigo) {
		Prato prato = pratoService.buscarPorId(codigo);
		
		ModelAndView mv = new ModelAndView("FormularioPrato");
		mv.addObject("prato", prato);
		return mv;
	}
}
