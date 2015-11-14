package br.edu.uerr.ccp.winsspoc.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_movimento database table.
 * 
 */
@Dependent
@Entity
@Table(name="tipo_movimento")
@NamedQuery(name="TipoMovimento.findAll", query="SELECT t FROM TipoMovimento t")
public class TipoMovimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="tipoMovimento")
	private List<Parametro> parametros;

	public TipoMovimento() {
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

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setTipoMovimento(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setTipoMovimento(null);

		return parametro;
	}

}