package livros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Diego Morais
 * @see Conexao
 * @see Swing
 */

public class Livros {
		private int id;
		private String nome;
		private int edicao;
		private double preco;
		
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public int getEdicao() {
			return edicao;
		}


		public void setEdicao(int edicao) {
			this.edicao = edicao;
		}


		public double getPreco() {
			return preco;
		}


		public void setPreco(double preco) {
			this.preco = preco;
		}


		public boolean cadastrarProduto(String nome, int edicao, double preco) {
			igualaID();
			int id = this.id;
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "insert into livros set id=?, nome=?, edicao=?, preco=?;";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, nome);
				ps.setInt(3, edicao); 
				ps.setDouble(4, preco); 
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0) {
					System.out.println("Não foi feito o cadastro!!");
					return false;
				}
				System.out.println("Cadastro realizado!");
				igualaID();
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao cadastrar livro: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
		
		
		public boolean consultarLivraria(int id) {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "select * from livros where id=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) { 
					return false; 
				} else {
					while (rs.next()) {
						this.id = rs.getInt("id");
						this.nome = rs.getString("nome");
						this.edicao = rs.getInt("edicao");
						this.preco = rs.getDouble("preco");
					}
					return true;
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao consultar a livros: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
		
		public boolean atualizarLivro(int id, int edicao, double preco, String nome) {
			if (!consultarLivraria(id))
				return false;
			else {
				Connection conexao = null;
				try {
					conexao = Conexao.conectaBanco();
					String sql = "update livros set nome=?, edicao=?, preco=? where livros.id=?";
					PreparedStatement ps = conexao.prepareStatement(sql);
					ps.setString(1, nome);
					ps.setInt(2, edicao);
					ps.setDouble(3, preco);
					ps.setInt(4, id);
					int totalRegistrosAfetados = ps.executeUpdate();
					if (totalRegistrosAfetados == 0)
						System.out.println("Não foi feita a atualização!");
					else
						System.out.println("Atualização realizada!");
					return true;
				} catch (SQLException erro) {
					System.out.println("Erro ao atualizar a livraria: " + erro.toString());
					return false;
				} finally {
					Conexao.fechaConexao(conexao);
				}
			}
		}
		
		public boolean deletarLivro(int id) {
			igualaID();
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "delete from livros where id=?;";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, id);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0) {
					System.out.println("Não foi feita remoção!");
					return false;
				}
				System.out.println("Remoção realizada!");
				igualaID();
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao remover livro: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
		
		public void igualaID() {
			Connection conexao = null;
			conexao = Conexao.conectaBanco();
			try {
				String sql = "select max(id) from livros";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.id= rs.getInt("max(id)") + 1;
				}
			}catch (SQLException erro) {
				System.out.println("Erro ao igualar ID: " + erro.toString());
			}	finally{
				Conexao.fechaConexao(conexao);
			}
		}
		
		public boolean checaTabela() {
			int check=0;
			Connection conexao = null;
			conexao = Conexao.conectaBanco();
			try {
				String sql = "select max(id) from livros";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					check = rs.getInt("max(id)");
				}
			if(check!=0) {
				return true;
			}
			else {
				return false;
			}
			}catch (SQLException erro) {
				System.out.println("Erro ao checar tabela: " + erro.toString());
				return false;
			}	finally{
				Conexao.fechaConexao(conexao);
			}
		}
}
