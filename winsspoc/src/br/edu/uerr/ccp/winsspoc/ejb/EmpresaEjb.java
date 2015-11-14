package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.Empresa;

/**
 * Session Bean implementation class EmpresaEjb
 */
@Stateless
public class EmpresaEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(Empresa entity) throws Exception {
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

    public void remover(Empresa entity){
    	try {
    		if (entity.getId() != null && entity.getId() > 0) {
    			entityManager.remove(entityManager.find(Empresa.class, entity.getId()));
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<Empresa> findAll() throws Exception {
		try {
			String sql = "select * from empresa";
			List<Empresa> listaEmpresa = executaSqlNativo(sql, Empresa.class, entityManager);
			return listaEmpresa;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


}
