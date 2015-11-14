package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Dependent
@Entity
@Table(name="empresa")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cep;

	private String cnpj;

	private String nome;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="empresa")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to GrupoProduto
	@OneToMany(mappedBy="empresa")
	private List<GrupoProduto> grupoProdutos;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="empresa")
	private List<Parametro> parametros;

	public Empresa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setEmpresa(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setEmpresa(null);

		return cliente;
	}

	public List<GrupoProduto> getGrupoProdutos() {
		return this.grupoProdutos;
	}

	public void setGrupoProdutos(List<GrupoProduto> grupoProdutos) {
		this.grupoProdutos = grupoProdutos;
	}

	public GrupoProduto addGrupoProduto(GrupoProduto grupoProduto) {
		getGrupoProdutos().add(grupoProduto);
		grupoProduto.setEmpresa(this);

		return grupoProduto;
	}

	public GrupoProduto removeGrupoProduto(GrupoProduto grupoProduto) {
		getGrupoProdutos().remove(grupoProduto);
		grupoProduto.setEmpresa(null);

		return grupoProduto;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setEmpresa(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setEmpresa(null);

		return parametro;
	}

}