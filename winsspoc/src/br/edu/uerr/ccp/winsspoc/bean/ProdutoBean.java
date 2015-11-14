package br.edu.uerr.ccp.winsspoc.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.jasper.tagplugins.jstl.ForEach;

import br.edu.uerr.ccp.winsspoc.ejb.GrupoProdutoEjb;
import br.edu.uerr.ccp.winsspoc.ejb.ProdutoEjb;
import br.edu.uerr.ccp.winsspoc.entity.GrupoProduto;
import br.edu.uerr.ccp.winsspoc.entity.Produto;

@Named
@SessionScoped
public class ProdutoBean extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Produto produto;
	
	@Inject
	private GrupoProduto grupoProduto;
	
	@EJB
	private GrupoProdutoEjb grupoProdutoEjb;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private ProdutoEjb produtoEjb;
	
	private List<Produto> produtoList = new ArrayList<Produto>();
	
	private List<GrupoProduto> grupoProdutoList = new ArrayList<GrupoProduto>();
		
	public ProdutoBean(){
		super();
	}
	
	public String preparaCadastroProduto() throws Exception{
		produto = new Produto();
		grupoProdutoList = new ArrayList<GrupoProduto>();
		grupoProdutoList = grupoProdutoEjb.findAll();
		produtoList = new ArrayList<Produto>();
		produtoList = produtoEjb.findAll();
		return redirect("/sistema/produto/cadastroProduto.xhtml");
	}
	
	public String preparaEditarProduto(Produto aux){
		try {
			produto = new Produto();
			produto=aux;
			grupoProdutoList = new ArrayList<GrupoProduto>();
			grupoProdutoList = grupoProdutoEjb.findAll();
			produtoList = new ArrayList<Produto>();
			produtoList = produtoEjb.findAll();			
			return redirect("/sistema/produto/cadastroProduto.xhtml");
		} catch (Exception e) {
			return null;
		}
	}
	
	public void salvar(){
		try {
			produtoEjb.salvar(produto);
			produto = new Produto();
			grupoProdutoList = new ArrayList<GrupoProduto>();
			grupoProdutoList = grupoProdutoEjb.findAll();
			produtoList = new ArrayList<Produto>();
			produtoList = produtoEjb.findAll();
			showFacesMessage("salvo com sucesso!!!", 2);
		} catch (Exception e) {
			e.printStackTrace();			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	public void excluir(Produto aux){
		try {
			produto = new Produto();
			produto = aux;
			produtoEjb.remover(produto);
			showFacesMessage("excluido com sucesso!!!", 2);
		} catch (Exception e) {
			e.printStackTrace();			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	public List<GrupoProduto> getGrupoProdutoList() {
		return grupoProdutoList;
	}

	public void setGrupoProdutoList(List<GrupoProduto> grupoProdutoList) {
		this.grupoProdutoList = grupoProdutoList;
	}
	
	
}
