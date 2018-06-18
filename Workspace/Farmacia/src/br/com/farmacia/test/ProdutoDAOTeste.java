package br.com.farmacia.test;

import java.util.ArrayList;

import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutoDAOTeste {
	@Test
	public void salvar() {
		Produtos p1 = new Produtos();
		p1.setDescricao("PRODUTO X");
		p1.setPreco(25.00);
		p1.setQuantidade(8L);

		Fornecedores f = new Fornecedores();
		f.setCodigo(20L);
		p1.setFornecedores(f);

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p1);
	}

	public void listar() {

		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produtos> lista = pdao.listar();

		for (Produtos p : lista) {
			System.out.println("Código do Produto: " + p.getCodigo());
			System.out.println("Descrição do Produto: " + p.getDescricao());
			System.out.println("Valor do Produto: " + p.getPreco());
			System.out.println("Quantidade do Produto: " + p.getQuantidade());
			System.out.println("Código do Fornecedor: " + p.getFornecedores().getCodigo());
			System.out.println("Descrição do Fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println(" ");
		}
	}

	
	public void excluir() {
			Produtos p = new Produtos();
			p.setCodigo(15L);
			
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.excluir(p);
	}
	
	public void teste() {
		Produtos p = new Produtos();
		p.setCodigo(1L);
		p.setDescricao("PRODUTO W");
		p.setQuantidade(35L);
		p.setPreco(66.00);
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(4L);
		p.setFornecedores(f1);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.editar(p);
		
		
	}
}