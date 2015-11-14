package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.TipoMovimento;

/**
 * Session Bean implementation class ClineteEjb
 */
@Stateless
public class TipoMovimentoEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(TipoMovimento entity) throws Exception {
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

    public void remover(TipoMovimento entity){
    	try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<TipoMovimento> findAll() throws Exception {
		try {
			String sql = "select * from tipo_movimento";
			List<TipoMovimento> listaTipoMovimento = executaSqlNativo(sql, TipoMovimento.class, entityManager);
			return listaTipoMovimento;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


}
