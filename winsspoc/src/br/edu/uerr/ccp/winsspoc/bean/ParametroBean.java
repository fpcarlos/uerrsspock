package br.edu.uerr.ccp.winsspoc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.uerr.ccp.winsspoc.ejb.EmpresaEjb;
import br.edu.uerr.ccp.winsspoc.ejb.ParametroEjb;
import br.edu.uerr.ccp.winsspoc.ejb.TipoMovimentoEjb;
import br.edu.uerr.ccp.winsspoc.entity.Empresa;
import br.edu.uerr.ccp.winsspoc.entity.Parametro;
import br.edu.uerr.ccp.winsspoc.entity.TipoMovimento;

@Named
@SessionScoped
public class ParametroBean extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Parametro parametroClass;
	
	@Inject
	private Empresa empresa;
	
	@EJB
	private ParametroEjb parametroEjb;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private EmpresaEjb empresaEjb;
	
	@EJB
	private TipoMovimentoEjb tipoMovimentoEjb;

	private List<Empresa> empresaList = new ArrayList<Empresa>();
	
	private List<TipoMovimento> tipoMovimentoList = new ArrayList<TipoMovimento>();
	
	private List<Parametro> parametroList = new ArrayList<Parametro>();
	
	public ParametroBean(){
		super();
	}
	
	public String preparaCadastroParametro() throws Exception{
		parametroClass = new Parametro();
		empresaList = new ArrayList<Empresa>();
		empresaList = empresaEjb.findAll();
		tipoMovimentoList = new ArrayList<TipoMovimento>();
		tipoMovimentoList = tipoMovimentoEjb.findAll();
		parametroList=new ArrayList<Parametro>();
		parametroList=parametroEjb.findAll();
		return redirect("/sistema/parametros/cadastroParametros.xhtml");
	}
	
	public String preparaEditarParametro(Parametro aux) throws Exception{
		parametroClass = new Parametro();
		parametroClass = aux;
		tipoMovimentoList = tipoMovimentoEjb.findAll();
		return redirect("/sistema/parametros/cadastroParametros.xhtml");
	}
	
	public void salvar(){
		try {			
			parametroEjb.salvar(parametroClass);
			parametroClass = new Parametro();
			parametroList=new ArrayList<Parametro>();
			parametroList=parametroEjb.findAll();
			showFacesMessage("salvo com sucesso!!!", 2);
		} catch (Exception e) {
			e.printStackTrace();			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	public void remover(Parametro aux){
			parametroClass = new Parametro();
			parametroClass = aux;
			parametroEjb.remover(parametroClass);
		try {
			showFacesMessage("excluido com sucesso!!!", 2);
		} catch (Exception e) {
			e.printStackTrace();			
			showFacesMessage(e.getMessage(), 4);
		}
	}

	public Parametro getParametroClass() {
		return parametroClass;
	}

	public void setParametroClass(Parametro parametroClass) {
		this.parametroClass = parametroClass;
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

	public List<TipoMovimento> getTipoMovimentoList() {
		return tipoMovimentoList;
	}

	public void setTipoMovimentoList(List<TipoMovimento> tipoMovimentoList) {
		this.tipoMovimentoList = tipoMovimentoList;
	}

	public List<Parametro> getParametroList() {
		return parametroList;
	}

	public void setParametroList(List<Parametro> parametroList) {
		this.parametroList = parametroList;
	}
	
	
	
	
}
