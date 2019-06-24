package com.br.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.ufc.model.Cliente;
import com.br.ufc.model.Role;
import com.br.ufc.repository.ClienteRepository;
import com.br.ufc.repository.RoleRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
    private RoleRepository roleRepo;
	
	public void cadastrar(Cliente cliente) {
		
        cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));

        Role role = roleRepo.findByPapel("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        cliente.setRoles(roles);

        clienteRepo.save(cliente);
	}
	
	public Cliente buscarPorLogin(String login) {
        return clienteRepo.findByLogin(login);
    }
	
//	public List<Visitante> listarTodos(){
//		return clienteRepo.findAll();
//	}
	
}
