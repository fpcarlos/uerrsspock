package br.edu.uerr.ccp.winsspoc.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.uerr.ccp.winsspoc.ejb.SistemaEjb;
import br.edu.uerr.ccp.winsspoc.entity.Empresa;
import br.edu.uerr.ccp.winsspoc.entity.GrupoProduto;


@Named
@SessionScoped
public class SistemaBean extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	SistemaEjb sistemaEjb;
	
	private List<Empresa> empresaList;
	private Map<Integer, Empresa> empresaMap = new HashMap<Integer, Empresa>();
	
	private List<GrupoProduto> grupoProdutoList;
	private Map<Integer, GrupoProduto> grupoProdutoMap = new HashMap<Integer, GrupoProduto>();

	@PostConstruct
	public void init() {
		try {
			getEmpresaList();
			getGrupoProdutoList();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public List<Empresa> getEmpresaList() {
		try {
			if(empresaList == null || empresaList.size()<1){
				empresaList = sistemaEjb.getEmpresa();
				empresaMap = new HashMap<Integer, Empresa>();
				for (Empresa x : empresaList) {
					empresaMap.put(x.getId(), x);
				}
			}
			return empresaList;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	public Map<Integer, Empresa> getEmpresaMap() {
		return empresaMap;
	}

	public void setEmpresaMap(Map<Integer, Empresa> empresaMap) {
		this.empresaMap = empresaMap;
	}

	public List<GrupoProduto> getGrupoProdutoList() {
		try {
			if(grupoProdutoList == null || grupoProdutoList.size()<1){
				grupoProdutoList = sistemaEjb.getGrupoProduto();
				grupoProdutoMap = new HashMap<Integer, GrupoProduto>();
				for (GrupoProduto x: grupoProdutoList) {
					grupoProdutoMap.put(x.getId(), x);
				}
			}
			return grupoProdutoList;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public void setGrupoProdutoList(List<GrupoProduto> grupoProdutoList) {
		this.grupoProdutoList = grupoProdutoList;
	}

	public Map<Integer, GrupoProduto> getGrupoProdutoMap() {
		return grupoProdutoMap;
	}

	public void setGrupoProdutoMap(Map<Integer, GrupoProduto> grupoProdutoMap) {
		this.grupoProdutoMap = grupoProdutoMap;
	}

	
	
}
