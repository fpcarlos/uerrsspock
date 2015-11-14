package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.Parametro;

/**
 * Session Bean implementation class ClineteEjb
 */
@Stateless
public class ParametroEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(Parametro entity) throws Exception {
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

    public void remover(Parametro entity){
    	try {
    		if (entity.getId() != null && entity.getId() > 0) {
    			entityManager.remove(entityManager.find(Parametro.class,entity.getId()));
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<Parametro> findAll() throws Exception {
		try {
			String sql = "select * from parametros";
			List<Parametro> listaParametro = executaSqlNativo(sql, Parametro.class, entityManager);
			return listaParametro;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


}
