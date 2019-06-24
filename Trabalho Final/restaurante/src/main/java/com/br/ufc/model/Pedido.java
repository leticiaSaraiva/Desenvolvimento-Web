package com.br.ufc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dataPedido = new java.sql.Date(System.currentTimeMillis());

    private double total;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ItemPedido> itens;

    @ManyToOne
    private Cliente cliente;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
