package br.edu.uerr.ccp.winsspoc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.uerr.ccp.winsspoc.ejb.EmpresaEjb;
import br.edu.uerr.ccp.winsspoc.ejb.GrupoProdutoEjb;
import br.edu.uerr.ccp.winsspoc.entity.Empresa;
import br.edu.uerr.ccp.winsspoc.entity.GrupoProduto;

@Named
@SessionScoped
public class GrupoBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoProduto grupoProduto;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private GrupoProdutoEjb grupoProdutoEjb;
	
	@EJB
	private EmpresaEjb empresaEjb;
	
	private List<GrupoProduto> grupoProdutosList = new ArrayList<GrupoProduto>();
	
	private List<Empresa> empresaList = new ArrayList<Empresa>();
	
	public GrupoBean(){
		super();
	}


	public String abrirCadastroGrupo() throws Exception{
		grupoProduto=new GrupoProduto();
		empresaList = new ArrayList<Empresa>();
		empresaList=empresaEjb.findAll();
		grupoProdutosList = new ArrayList<GrupoProduto>();		
		grupoProdutosList = grupoProdutoEjb.findAll();
		return redirect("/sistema/grupos/cadastroGrupo.xhtml"); 
	}
	public String preparaEditarGrupo(GrupoProduto aux){
		grupoProduto=new GrupoProduto();
		grupoProduto=grupoProdutoEjb.pegarGrupoProduto(aux.getId());
		grupoProduto.setEmpresa(sistemaBean.getEmpresaMap().get(grupoProduto.getEmpresa().getId()));
		return redirect("/sistema/grupos/cadastroGrupo.xhtml");
	}
	public void salvar(){
		try {
			grupoProdutoEjb.salvar(grupoProduto);
			showFacesMessage("salvo com sucesso!!!", 2);
		} catch (Exception e) {
			e.printStackTrace();			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	public void excluir(GrupoProduto aux){
		try {
			grupoProduto=new GrupoProduto();
			grupoProduto=aux;
			grupoProdutoEjb.remover(grupoProduto);
			showFacesMessage("excluido com sucesso!!!", 2);
			grupoProduto=new GrupoProduto();
			grupoProdutosList=new ArrayList<GrupoProduto>();
			grupoProdutosList=grupoProdutoEjb.findAll();
		} catch (Exception e) {
			e.printStackTrace();			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public List<GrupoProduto> getGrupoProdutosList() {
		return grupoProdutosList;
	}

	public void setGrupoProdutosList(List<GrupoProduto> grupoProdutosList) {
		this.grupoProdutosList = grupoProdutosList;
	}


	public List<Empresa> getEmpresaList() {
		return empresaList;
	}


	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	
}
