package br.edu.uerr.ccp.winsspoc.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.uerr.ccp.winsspoc.entity.FormaPagamento;

/**
 * Session Bean implementation class ClineteEjb
 */
@Stateless
public class FormaPagamentoEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
	private EntityManager entityManager;
	
    public void salvar(FormaPagamento entity) throws Exception {
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

    public void remover(FormaPagamento entity){
    	try {
			entityManager.remove(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<FormaPagamento> findAll() throws Exception {
		try {
			String sql = "select * from forma_pagamento";
			List<FormaPagamento> listaFormaPagamento = executaSqlNativo(sql, FormaPagamento.class, entityManager);
			return listaFormaPagamento;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}


}
