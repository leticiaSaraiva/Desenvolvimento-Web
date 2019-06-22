package com.br.ufc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.ufc.security.UserDetailsServiceImplementacao;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;
	
	
	//Definir regras da aplicacao

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/cliente/formulario").permitAll()
		.antMatchers("/cliente/salvar").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/prato/formularioPrato").hasRole("GERENTE")
		.antMatchers("/prato/excluir/*").hasRole("GERENTE")
		.antMatchers("/prato/atualizar/*").hasRole("GERENTE")
		.antMatchers("/carrinho/*").hasRole("USER")
		.antMatchers("/pedido/*").hasRole("USER")
		
		.anyRequest().authenticated()
		
		
		.and()
		.formLogin()
		.loginPage("/logar")
		.permitAll()
		.defaultSuccessUrl("/")
		
		.and()
		.logout()
		.logoutSuccessUrl("/logar?logout")
		.permitAll();
		
	}
	
	
	//método da autenticacao
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	//ignorar esses arquivos para permitir que eles aparecam
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/img/**");
	}
	
	
	
	
	

}
