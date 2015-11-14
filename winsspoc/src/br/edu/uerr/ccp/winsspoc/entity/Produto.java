package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the produto database table.
 * 
 */
@Dependent
@Entity
@Table(name="produto")
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Column(name="quantidade_estoque")
	private Integer quantidadeEstoque;

	@Column(name="valor_compra")
	private BigDecimal valorCompra;

	@Column(name="valor_venda")
	private BigDecimal valorVenda;

	//bi-directional many-to-one association to FornecedorProduto
	@OneToMany(mappedBy="produto")
	private List<FornecedorProduto> fornecedorProdutos;

	//bi-directional many-to-one association to GrupoProduto
	@ManyToOne
	@JoinColumn(name="id_grupo_produto")
	private GrupoProduto grupoProduto;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="produto")
	private List<Venda> vendas;

	public Produto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public BigDecimal getValorCompra() {
		return this.valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return this.valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public List<FornecedorProduto> getFornecedorProdutos() {
		return this.fornecedorProdutos;
	}

	public void setFornecedorProdutos(List<FornecedorProduto> fornecedorProdutos) {
		this.fornecedorProdutos = fornecedorProdutos;
	}

	public FornecedorProduto addFornecedorProduto(FornecedorProduto fornecedorProduto) {
		getFornecedorProdutos().add(fornecedorProduto);
		fornecedorProduto.setProduto(this);

		return fornecedorProduto;
	}

	public FornecedorProduto removeFornecedorProduto(FornecedorProduto fornecedorProduto) {
		getFornecedorProdutos().remove(fornecedorProduto);
		fornecedorProduto.setProduto(null);

		return fornecedorProduto;
	}

	public GrupoProduto getGrupoProduto() {
		return this.grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setProduto(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setProduto(null);

		return venda;
	}
	
	
}