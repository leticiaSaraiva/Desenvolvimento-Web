package com.br.ufc.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Cliente implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotBlank(message="Preencha o campo de nome")
	private String nome;
	
	@NotBlank(message="Preencha o campo de CPF")
	@Length(max=14, message="O CPF não pode ser maior que o máximo de caracteres")
	private String cpf;
	
	@NotBlank(message="Preencha o campo de rua")
	private String rua;
	
	@NotBlank(message="Preencha o número da casa")
	private String numero;
	
	@NotBlank(message="Preencha o campo de bairro")
	private String bairro;
	
	@NotBlank(message="Login não pode ser vazio")
	private String login;
	
	@NotBlank(message="Preencha o campo de senha")
	private String senha;
	
	@NotBlank(message="Preencha o campo de e-mail")
	private String email;
	
	@NotNull(message="Campo de data não pode ser nulo")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
	        name = "pessoas_roles", 
	        joinColumns = @JoinColumn(
	          name = "pessoa_codigo", referencedColumnName = "codigo"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_codigo", referencedColumnName = "papel")) 
	private List<Role> roles;
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
