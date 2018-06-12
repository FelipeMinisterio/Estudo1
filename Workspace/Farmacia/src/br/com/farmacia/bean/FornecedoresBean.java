package br.com.farmacia.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensfiltrados;

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedores> getItensfiltrados() {
		return itensfiltrados;
	}

	public void setItensfiltrados(ArrayList<Fornecedores> itensfiltrados) {
		this.itensfiltrados = itensfiltrados;
	}

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listar();

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();

	}

	public void novo() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor Salvo com sucesso !!");

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
		}
	}

	public void Excluir() {
		FornecedoresDAO fdao = new FornecedoresDAO();
		fdao.excluir(fornecedores);
		if (fdao.excluir(fornecedores) == true) {
			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor excluído com sucesso!");
		} else {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um Fornecedor que tenha um produto associado!");
		}
	}

	public void Editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Não é possível editar funcionário");
			e.printStackTrace();
		}
	}

}
