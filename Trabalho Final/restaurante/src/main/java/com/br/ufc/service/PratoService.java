package com.br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.br.ufc.model.Prato;
import com.br.ufc.repository.PratoRepository;
import com.br.ufc.util.AulaFileUtils;

@Service
public class PratoService {

	@Autowired
	private PratoRepository pratoRepo;
	
	public void cadastrar(Prato prato, @RequestParam(value="imagem") MultipartFile imagem) {
		String caminho = "images/" + prato.getNome() + ".png";
		AulaFileUtils.salvarImagem(caminho, imagem);
		
		pratoRepo.save(prato);
	}
	
	public List<Prato> listarTodos(){
		return pratoRepo.findAll();
	}
	
	public void excluir(Long codigo) {
		pratoRepo.deleteById(codigo);	
	}
	
	public Prato buscarPorId(Long codigo) {
		return pratoRepo.getOne(codigo);
	}
}
