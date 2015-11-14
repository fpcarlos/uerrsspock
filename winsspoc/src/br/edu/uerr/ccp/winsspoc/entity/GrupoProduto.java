package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupo_produto database table.
 * 
 */
@Dependent
@Entity
@Table(name="grupo_produto")
@NamedQuery(name="GrupoProduto.findAll", query="SELECT g FROM GrupoProduto g")
public class GrupoProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="grupoProduto")
	private List<Produto> produtos;

	public GrupoProduto() {
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

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto addProduto(Produto produto) {
		getProdutos().add(produto);
		produto.setGrupoProduto(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getProdutos().remove(produto);
		produto.setGrupoProduto(null);

		return produto;
	}

}