package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.Empresa;
import br.edu.uerr.ccp.winsspoc.entity.GrupoProduto;
import br.edu.uerr.ccp.winsspoc.entity.Produto;

/**
 * Session Bean implementation class ClineteEjb
 */
@Stateless
public class SistemaEjb extends AbstractEjb implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	// lista empresas
	public List<Empresa> getEmpresa() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome", Empresa.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}

	// lista grupo de produtos
	public List<GrupoProduto> getGrupoProduto() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome", GrupoProduto.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Produto> getProduto() throws Exception{
		try {
			return buscarComSqlNativoOrdenado("nome", Produto.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}

}
