package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fornecedor_produto database table.
 * 
 */
@Dependent
@Entity
@Table(name="fornecedor_produto")
@NamedQuery(name="FornecedorProduto.findAll", query="SELECT f FROM FornecedorProduto f")
public class FornecedorProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;


	@Temporal(TemporalType.DATE)
	@Column(name="data_comprar")
	private Date dataComprar;

	@Column(name="nota_fical")
	private String notaFical;

	//bi-directional many-to-one association to Fornecedor
	@ManyToOne
	@JoinColumn(name="id_fornecedor")
	private Fornecedor fornecedor;

	//bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	public FornecedorProduto() {
	}


	public Integer getId() {
		return this.id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDataComprar() {
		return this.dataComprar;
	}

	public void setDataComprar(Date dataComprar) {
		this.dataComprar = dataComprar;
	}

	public String getNotaFical() {
		return this.notaFical;
	}

	public void setNotaFical(String notaFical) {
		this.notaFical = notaFical;
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}