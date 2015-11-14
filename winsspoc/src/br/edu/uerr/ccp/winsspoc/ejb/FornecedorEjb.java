package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.Fornecedor;

/**
 * Session Bean implementation class FornecedorEjb
 */
@Stateless
public class FornecedorEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(Fornecedor entity) throws Exception {
		try {
			if (entity.getId() != null && entity.getId() > 0) {
				entityManager.merge(entity);
			} else {
				entityManager.persist(entity);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

    public void remover(Fornecedor entity){
    	try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<Fornecedor> findAll() throws Exception {
		try {
			String sql = "select * from fornecedor";
			List<Fornecedor> listaFornecedor = executaSqlNativo(sql, Fornecedor.class, entityManager);
			return listaFornecedor;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


}
