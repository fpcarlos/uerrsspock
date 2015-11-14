package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.GrupoProduto;

/**
 * Session Bean implementation class ClineteEjb
 */
@Stateless
public class GrupoProdutoEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(GrupoProduto entity) throws Exception {
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

    public void remover(GrupoProduto entity){
    	try {
    		if (entity.getId() != null && entity.getId() > 0) {
    			entityManager.remove(entityManager.find(GrupoProduto.class, entity.getId()));
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public GrupoProduto pegarGrupoProduto(Integer id){
    	GrupoProduto aux = entityManager.find(GrupoProduto.class, id);
		return aux;
	}
    
    public List<GrupoProduto> findAll() throws Exception {
		try {
			String sql = "select * from grupo_produto";
			List<GrupoProduto> listaGrupoProduto = executaSqlNativo(sql, GrupoProduto.class, entityManager);
			return listaGrupoProduto;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


}
