package br.com.farmacia.test;

import java.util.ArrayList;

import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutoDAOTeste {

	
	public void salvar() {
		Produtos p1 = new Produtos();
		p1.setDescricao("DIPIRONA");
		p1.setPreco(2.99);
		p1.setQuantidade(12L);

		Fornecedores f = new Fornecedores();
		f.setCodigo(12L);
		p1.setFornecedores(f);

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p1);
	}
	@Test
	public void listar() {
		
		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produtos> lista = pdao.listar();
	}
}