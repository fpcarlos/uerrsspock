package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.Produto;

/**
 * Session Bean implementation class ProdutoEjb
 */
@Stateless
public class ProdutoEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(Produto entity) throws Exception {
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

    public void remover(Produto entity){
    	try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<Produto> findAll() throws Exception {
		try {
			String sql = "select a.* from produto a";
			List<Produto> listaProduto = executaSqlNativo(sql, Produto.class, entityManager);
			return listaProduto;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


	public double totalDeUmProduto(Integer id)throws Exception{
		try {
		double total = 0;
		String sql = "select a.* from produto a where id = "+ id +"";
		List<Produto> produtoList = executaSqlNativo(sql, Produto.class, entityManager);
		for(Produto x: produtoList){			
			total += (x.getValorVenda().doubleValue() * x.getQuantidadeEstoque()); 
		}		
		return total;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}

	public double totalGeralProdutos()throws Exception{
		try {
		double total = 0;
		String sql = "select a.* from produto a ";
		List<Produto> produtoList = executaSqlNativo(sql, Produto.class, entityManager);
		for(Produto x: produtoList){			
			total += (x.getValorVenda().doubleValue() * x.getQuantidadeEstoque()); 
		}		
		return total;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}
}
