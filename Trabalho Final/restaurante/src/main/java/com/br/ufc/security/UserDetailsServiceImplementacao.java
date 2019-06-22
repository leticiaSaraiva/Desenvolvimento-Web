package com.br.ufc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.br.ufc.model.Cliente;
import com.br.ufc.repository.ClienteRepository;



@Repository
public class UserDetailsServiceImplementacao implements UserDetailsService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente pessoa = clienteRepository.findByLogin(username);
		
		if(pessoa == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		
		return new User(pessoa.getUsername(),pessoa.getPassword(),true,true,true,true,
				pessoa.getAuthorities());
	}

}
