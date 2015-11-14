package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the vendas database table.
 * 
 */
@Dependent
@Entity
@Table(name="vendas")
@NamedQuery(name="Venda.findAll", query="SELECT v FROM Venda v")
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="data_hora")
	private Timestamp dataHora;

	private BigDecimal desconto;

	@Column(name="preco_venda")
	private BigDecimal precoVenda;

	private Integer quantidade;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to FormaPagamento
	@ManyToOne
	@JoinColumn(name="id_forma_pagamento")
	private FormaPagamento formaPagamento;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	public Venda() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public BigDecimal getDesconto() {
		return this.desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getPrecoVenda() {
		return this.precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormaPagamento getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}