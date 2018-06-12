package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.ConnectionFactory.ConexaoFactory;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutoDAO {
	public void salvar(Produtos p) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo)  ");
		sql.append("VALUES (?,?,?,?)");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, p.getDescricao());
			comando.setDouble(2, p.getPreco());
			comando.setLong(3, p.getQuantidade());
			comando.setLong(4, p.getFornecedores().getCodigo());
			comando.executeUpdate();
			System.out.println("Sucesso ao inserir!");
		} catch (SQLException e) {
			System.out.println("Falha ao inserir!");

			e.printStackTrace();
		}

	}
	public ArrayList<Produtos> listar() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());

			ResultSet resultado = comando.executeQuery();

			ArrayList<Produtos> lista = new ArrayList<Produtos>();

			while (resultado.next()) {
				Fornecedores f = new Fornecedores();
				f.setCodigo(resultado.getLong("f.codigo"));
				f.setDescricao(resultado.getString("f.descricao"));

				Produtos p = new Produtos();
				p.setCodigo(resultado.getLong("p.codigo"));
				p.setDescricao(resultado.getString("p.descricao"));
				p.setPreco(resultado.getDouble("p.preco"));
				p.setQuantidade(resultado.getLong("p.quantidade"));
				p.setFornecedores(f);
				
				lista.add(p);
			}
			
			for (Produtos p : lista) {
				System.out.println("Resultado: \n" + p);

			}
			System.out.println("Sucesso ao listar");
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao listar");
			return null;
		}
	}

}
