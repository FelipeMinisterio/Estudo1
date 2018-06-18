package br.com.farmacia.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores>comboFornecedores;

	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO pdao = new ProdutoDAO();
			itens = pdao.listar();
		} catch (Exception e) {

			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {
		produtos = new Produtos();
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		comboFornecedores = fdao.listar();
		
	}

	public void novo() {
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.salvar(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto Salvo com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
		}
	}

	public void excluir() {
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.excluir(produtos);
		if (pdao.excluir(produtos) == true) {
			itens = pdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto Excluído com sucesso!");
		} else {
			JSFUtil.adicionarMensagemErro("Não é possível excluir o produto! ");

		}
	}

	public void editar() {
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.editar(produtos);

			itens = pdao.listar();

			JSFUtil.adicionarMensagemErro("Produto Editado com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Não foi possível editar o produto!");
			e.printStackTrace();
		}
	}
	public void prepararEditar() {
		produtos = new Produtos();
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		comboFornecedores = fdao.listar();
		
	}
}
