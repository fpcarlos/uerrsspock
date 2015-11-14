package br.edu.uerr.ccp.winsspoc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.uerr.ccp.winsspoc.ejb.EmpresaEjb;
import br.edu.uerr.ccp.winsspoc.entity.Empresa;

@Named
@SessionScoped
public class EmpresaBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresa empresa;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private EmpresaEjb empresaEjb;

	private List<Empresa> empresaList = new ArrayList<Empresa>();
	
	public EmpresaBean() {
		super();
	}
	
	public String abrirCadastroEmpresa() throws Exception{
		empresa = new Empresa();
		empresaList = new ArrayList<Empresa>();
		empresaList = empresaEjb.findAll();
		return redirect("/sistema/empresa/cadastroEmpresa.xhtml"); 
	}
	
	public String abrirEditarEmpresa(Empresa aux) throws Exception{
		empresa = new Empresa();
		empresa = aux;
		empresaList = new ArrayList<Empresa>();
		empresaList = empresaEjb.findAll();
		return redirect("/sistema/empresa/cadastroEmpresa.xhtml");
	}
	
	public void salvar(){
		try {
			empresaEjb.salvar(empresa);
			
			showFacesMessage("salvo com sucesso!!!", 2);
			empresa = new Empresa();
			empresaList = new ArrayList<Empresa>();
			empresaList = empresaEjb.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			showFacesMessage(e.getMessage(), 4);
		}
	}

	public void excluir(Empresa aux){
		try {
			empresa = new Empresa();
			empresa = aux;
			empresaEjb.remover(empresa);
			
			showFacesMessage("excluido com sucesso!!!", 2);
			empresa = new Empresa();
			empresaList = new ArrayList<Empresa>();
			empresaList = empresaEjb.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresaList() {
		return empresaList;
	}

	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}
	
	

}
