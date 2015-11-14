package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Dependent
@Entity
@Table(name="fornecedor")
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cnpj;

	private String email;

	private String endereco;

	private String nome;

	private String telefone;

	//bi-directional many-to-one association to FornecedorProduto
	@OneToMany(mappedBy="fornecedor")
	private List<FornecedorProduto> fornecedorProdutos;

	public Fornecedor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<FornecedorProduto> getFornecedorProdutos() {
		return this.fornecedorProdutos;
	}

	public void setFornecedorProdutos(List<FornecedorProduto> fornecedorProdutos) {
		this.fornecedorProdutos = fornecedorProdutos;
	}

	public FornecedorProduto addFornecedorProduto(FornecedorProduto fornecedorProduto) {
		getFornecedorProdutos().add(fornecedorProduto);
		fornecedorProduto.setFornecedor(this);

		return fornecedorProduto;
	}

	public FornecedorProduto removeFornecedorProduto(FornecedorProduto fornecedorProduto) {
		getFornecedorProdutos().remove(fornecedorProduto);
		fornecedorProduto.setFornecedor(null);

		return fornecedorProduto;
	}

}