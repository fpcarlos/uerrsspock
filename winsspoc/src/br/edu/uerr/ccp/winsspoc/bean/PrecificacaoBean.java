package br.edu.uerr.ccp.winsspoc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.uerr.ccp.winsspoc.ejb.EmpresaEjb;
import br.edu.uerr.ccp.winsspoc.ejb.ParametroEjb;
import br.edu.uerr.ccp.winsspoc.ejb.ProdutoEjb;
import br.edu.uerr.ccp.winsspoc.entity.Empresa;
import br.edu.uerr.ccp.winsspoc.entity.Parametro;
import br.edu.uerr.ccp.winsspoc.entity.Produto;

@Named
@SessionScoped
public class PrecificacaoBean extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private transient SistemaBean sistemaBean;
	
	@Inject
	private Produto produto;
	
	@EJB
	private ProdutoEjb produtoEjb;
	
	@Inject
	private Empresa empresa;
	
	@EJB
	private EmpresaEjb empresaEjb;
	
	@Inject
	private Parametro parametroClass;
	
	@EJB
	private ParametroEjb parametroEjb;
	
	private double precoVenda;
	private double lucroEsperado;
	private double tributacaoTeste;
	private double comissaoTeste;
	private double taxaMarkup;
	private double taxaMarkupMultiplicador;
	private double precoVendaMarkup;
	private double custoFixoPorVenda;
	private double margemContribuicao;
	private double quantidadeEquilibrio;
	//private double 
	
	
	private List<Produto> produtoList = new ArrayList<Produto>();
	private List<Empresa> empresaList = new ArrayList<Empresa>();
	private List<Parametro> parametroList = new ArrayList<Parametro>();
	
	public PrecificacaoBean(){
		super();
	}
	@PostConstruct
	public void init() {
		try {
			this.setPrecoVenda(100.00);
			this.setLucroEsperado(20.00);
			this.setComissaoTeste(5.00);
			this.setTributacaoTeste(28.00);
			this.setCustoFixoPorVenda((this.totalCustoFixo()/this.pegaTotalGeralProdutos())*100);
			this.setTaxaMarkup(this.getPrecoVenda()-(this.getLucroEsperado()+this.getComissaoTeste()+this.getTributacaoTeste()+this.getCustoFixoPorVenda()));
			if(produto!=null)
				this.setPrecoVendaMarkup(produto.getValorCompra().doubleValue()/this.getTaxaMarkup());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public String abrirPrecificacao() throws Exception{
		produtoList = new ArrayList<Produto>();
		produtoList = produtoEjb.findAll();
		empresaList = new ArrayList<Empresa>();
		empresaList = empresaEjb.findAll();
		return redirect("/sistema/precificacao/iniciarPrecificacao.xhtml");
	}
	public String abrirPrecificacao2() throws Exception{
		produtoList = new ArrayList<Produto>();
		produtoList = produtoEjb.findAll();
		empresaList = new ArrayList<Empresa>();
		empresaList = empresaEjb.findAll();
		return redirect("/sistema/precificacao/_markupPrecificacao.xhtml");
	}
	
	public String abrirPrecificacao3() throws Exception{
		produtoList = new ArrayList<Produto>();
		produtoList = produtoEjb.findAll();
		//empresaList = new ArrayList<Empresa>();
		//empresaList = empresaEjb.findAll();
		return redirect("/sistema/precificacao/_markupPrecificacaoLista.xhtml");
	}
	public void getSelecionaEmpresa() throws Exception{
		//empresa = new Empresa();
		//empresa = aux;
		showFacesMessage("Empresa Selecionada: " + empresa.getNome(), 2);
		produtoList = produtoEjb.findAll();
	}
	public void getSelecionaProduto() throws Exception{
		if(produto!=null){
			this.setPrecoVendaMarkup((produto.getValorCompra().doubleValue()/this.getTaxaMarkup())*100);
			this.setTaxaMarkupMultiplicador((1/this.getTaxaMarkup())*100);
			this.setMargemContribuicao(this.getPrecoVendaMarkup()-produto.getValorCompra().doubleValue());
			this.setQuantidadeEquilibrio(this.totalCustoFixo()/this.getMargemContribuicao());
		}
		/*
		showFacesMessage("Produto Selecionado: " 
				+ produto.getNome() + "Total: " + produtoEjb.totalDeUmProduto(produto.getId()), 2);
				*/
	}
	
	public double pegaTotalDeUmProduto() throws Exception{
		return produtoEjb.totalDeUmProduto(produto.getId());
	}

	public double pegaTotalGeralProdutos() throws Exception{
		return produtoEjb.totalGeralProdutos();
	}
	public double pegaPerCustoProducao() throws Exception{		
		double valorComprap=0;
		double valorVendap=0;
		//produtoList = new ArrayList<Produto>();
		//produtoList = produtoEjb.findAll();
		for(Produto x: produtoList){
			if(produto.getId()==x.getId())
		        valorComprap+=(x.getValorCompra().doubleValue() * x.getQuantidadeEstoque());
			
		   valorVendap+=(x.getValorVenda().doubleValue() * x.getQuantidadeEstoque());
		}
		//valorComprap = (valorComprap/produtoEjb.totalGeralProdutos());
		
		return (valorComprap/valorVendap);//((valorComprap*produto.getQuantidadeEstoque())/produtoEjb.totalGeralProdutos())/100;
	}
	public double pegaPerCusto(Integer id) throws Exception{		
		double valorComprap=0;
		double valorVendap=0;
		//produtoList = new ArrayList<Produto>();
		//produtoList = produtoEjb.findAll();
		for(Produto x: produtoList){
			if(x.getId()==id)
		        valorComprap+=(x.getValorCompra().doubleValue() * x.getQuantidadeEstoque());
			
		   valorVendap+=(x.getValorVenda().doubleValue() * x.getQuantidadeEstoque());
		}
		//valorComprap = (valorComprap/produtoEjb.totalGeralProdutos());
		
		return (valorComprap/valorVendap);//((valorComprap*produto.getQuantidadeEstoque())/produtoEjb.totalGeralProdutos())/100;
	}
	public double totalCustoFixo() throws Exception{
		double valorTotal = 0;
		parametroList = new ArrayList<Parametro>();
		parametroList = parametroEjb.findAll();
		for(Parametro x: parametroList){
			if(x.getTipoMovimento().getId()==3){
				valorTotal+=x.getValor().doubleValue();
			}
		}
		return valorTotal;
	}
	public double pegaPrecoDeVenda(){
		return (produto.getValorCompra().doubleValue()/this.getTaxaMarkup());
		
	}
	
	
	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public List<Parametro> getParametroList() {
		return parametroList;
	}

	public void setParametroList(List<Parametro> parametroList) {
		this.parametroList = parametroList;
	}

	public Parametro getParametroClass() {
		return parametroClass;
	}

	public void setParametroClass(Parametro parametroClass) {
		this.parametroClass = parametroClass;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public double getLucroEsperado() {
		return lucroEsperado;
	}
	public void setLucroEsperado(double lucroEsperado) {
		this.lucroEsperado = lucroEsperado;
	}
	public double getTributacaoTeste() {
		return tributacaoTeste;
	}
	public void setTributacaoTeste(double tributacaoTeste) {
		this.tributacaoTeste = tributacaoTeste;
	}
	
	public double getComissaoTeste() {
		return comissaoTeste;
	}
	public void setComissaoTeste(double comissaoTeste) {
		this.comissaoTeste = comissaoTeste;
	}
	public double getTaxaMarkup() {
		return taxaMarkup;
	}
	public void setTaxaMarkup(double taxaMarkup) {
		this.taxaMarkup = taxaMarkup;
	}
	public double getPrecoVendaMarkup() {		
		return precoVendaMarkup;
	}
	public void setPrecoVendaMarkup(double precoVendaMarkup) {
		this.precoVendaMarkup = precoVendaMarkup;
	}
	public double getCustoFixoPorVenda() {
		return custoFixoPorVenda;
	}
	public void setCustoFixoPorVenda(double custoFixoPorVenda) {
		this.custoFixoPorVenda = custoFixoPorVenda;
	}
	public double getTaxaMarkupMultiplicador() {
		return taxaMarkupMultiplicador;
	}
	public void setTaxaMarkupMultiplicador(double taxaMarkupMultiplicador) {
		this.taxaMarkupMultiplicador = taxaMarkupMultiplicador;
	}
	public double getMargemContribuicao() {
		return margemContribuicao;
	}
	public void setMargemContribuicao(double margemContribuicao) {
		this.margemContribuicao = margemContribuicao;
	}
	public double getQuantidadeEquilibrio() {
		return quantidadeEquilibrio;
	}
	public void setQuantidadeEquilibrio(double quantidadeEquilibrio) {
		this.quantidadeEquilibrio = quantidadeEquilibrio;
	}
	
	
}
