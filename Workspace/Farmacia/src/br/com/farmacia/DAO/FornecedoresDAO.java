package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;

import br.com.farmacia.ConnectionFactory.ConexaoFactory;
import br.com.farmacia.domain.Fornecedores;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, f.getDescricao());
			comando.executeUpdate();
			System.out.println("Sucesso ao inserir!");
		} catch (SQLException e) {
			System.out.println("Falha ao inserir!");

			e.printStackTrace();
		}

	}

	public void excluir(Fornecedores f) {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, f.getCodigo());
			comando.executeUpdate();
			System.out.println("Sucesso ao excluir!");
		} catch (SQLException e) {
			System.out.println("Falha ao excluir!");
			e.printStackTrace();
		}

	}

	public void editar(Fornecedores f) {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, f.getDescricao());
			comando.setLong(2, f.getCodigo());
			comando.executeUpdate();
			System.out.println("Sucesso ao editar!");
		} catch (SQLException e) {
			System.out.println("Falha ao editar!");
			e.printStackTrace();
		}

	}

	public Fornecedores buscaPorCodigo(Fornecedores f) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, f.getCodigo());

			ResultSet resultado = comando.executeQuery();
			Fornecedores retorno = null;

			if (resultado.next()) {
				retorno = new Fornecedores();
				retorno.setCodigo(resultado.getLong("codigo"));
				retorno.setDescricao(resultado.getString("descricao"));
			}

			System.out.println("Codigo:" + retorno.getCodigo());
			System.out.println("Codigo:" + retorno.getDescricao());
			System.out.println("Sucesso ao procurar por codigo!");

			return retorno;
		} catch (SQLException e) {
			System.out.println("Falha ao procurar por codigo!");
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Fornecedores> listar() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());

			ResultSet resultado = comando.executeQuery();

			ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

			while (resultado.next()) {
				Fornecedores f = new Fornecedores();
				f.setCodigo(resultado.getLong("codigo"));
				f.setDescricao(resultado.getString("descricao"));

				lista.add(f);
			}

			for (Fornecedores f : lista) {
				System.out.println("Resultado: " + f);

			}
			System.out.println("Sucesso ao listar");
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao listar");
			return null;
		}
	}

	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");

		try {
			Connection conexao = ConexaoFactory.getConnection();
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, "%" + f.getDescricao() + "%");

			ResultSet resultado = comando.executeQuery();

			ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

			while (resultado.next()) {
				Fornecedores item = new Fornecedores();
				item.setCodigo(resultado.getLong("codigo"));
				item.setDescricao(resultado.getString("descricao"));

				lista.add(item);
			}
			
			System.out.println(lista);
			System.out.println("Sucesso ao buscar por Descrição");	
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Falha ao buscar por Descrição");
			return null;
		}
	}

	public static void main(String[] args) {

		FornecedoresDAO fdao = new FornecedoresDAO();
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("labioça10");
		fdao.salvar(f1);
	}
}
