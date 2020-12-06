package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.VO.Cliente;

public class ClienteDAO {
	public int salvar(Cliente cliente) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO CLIENTE (NOME,TELEFONE,CPF ,DATANASCIMENTO,VALOR) VALUES (?,?,?,?,?)";
		PreparedStatement Prepstmt = Banco.getPreparedStatement(conn, sql);
		int resultado = 0;
		try {
			Prepstmt.setString(1, cliente.getNome());
			Prepstmt.setString(2, cliente.getTelefone());
			Prepstmt.setString(3, cliente.getCpf());
			Prepstmt.setDate(4, Date.valueOf(cliente.DataNascimento));
			Prepstmt.setDouble(5, cliente.getValor());

			Prepstmt.execute();
			ResultSet rs = Prepstmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				cliente.setIdCliente(idGerado);
			}
		} catch (SQLException e) {
		} finally {
			Banco.closePreparedStatement(Prepstmt);
			Banco.closeConnection(conn);

		}

		return resultado;
	}

	public static boolean alterar(Cliente cliente) {
		String sql = " UPDATE CLIENTE " + " SET NOME=?,TELEFONE=?,CPF=?,DATANASCIMENTO=?, VALOR=? "
				+ " WHERE IDCLIENTE=? ";

		boolean alterou = false;

		try (Connection conexao = Banco.getConnection();
				PreparedStatement query = Banco.getPreparedStatement(conexao, sql);) {
			query.setString(1, cliente.getNome());
			query.setString(2, cliente.getTelefone());
			query.setString(3, cliente.getCpf());
			query.setDate(4, Date.valueOf(cliente.DataNascimento));
			query.setDouble(5, cliente.getValor());
			query.setInt(6, cliente.getIdCliente());

			System.out.println(sql);

			int codigoRetorno = query.executeUpdate();
			alterou = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);
		} catch (SQLException e) {
			System.out.println("Erro ao alterar cliente.\nCausa: " + e.getMessage());
		}

		return alterou;
	}

	public ArrayList<Cliente> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT * FROM CLIENTE ORDER BY NOME ASC";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			result = stmt.executeQuery(sql);

			while (result.next()) {
				Cliente cliente = construirDoResultSet(result);
				clientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao Consultar Cliente");
			System.out.println("Erro " + e);
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return clientes;

	}

	private Cliente construirDoResultSet(ResultSet result) {
		Cliente novoCliente = new Cliente();
		try {
			novoCliente.setIdCliente(result.getInt("IDCLIENTE"));
			novoCliente.setNome(result.getString("NOME"));
			novoCliente.setTelefone(result.getString("TELEFONE"));
			novoCliente.setCpf(result.getString("CPF"));
			novoCliente.setDataNascimento(result.getDate("DataNascimento").toLocalDate());
			novoCliente.setValor(result.getDouble("valor"));
		} catch (SQLException e) {
			System.out.println("Erro ao construir a partir Do ResultSet. Causa: " + e.getMessage());
		}
		return novoCliente;
	}

	public Cliente consultarPorId(int idCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE WHERE IDCLIENTE=?";
		ResultSet resultadoDaConsulta = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		Cliente clienteSelecionado = null;
		try {
			stmt.setInt(1, idCliente);
			resultadoDaConsulta = stmt.executeQuery();

			if (resultadoDaConsulta.next()) {
				clienteSelecionado = construirDoResultSet(resultadoDaConsulta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar profissional por id: " + idCliente);
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return clienteSelecionado;

	}

	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		Statement statement = Banco.getStatement(conexao);
		String sql = " DELETE FROM CLIENTE WHERE IDCLIENTE = " + id;

		int quantidadeRegistrosExcluidos = 0;
		try {
			quantidadeRegistrosExcluidos = statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir Cliente. ");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(statement);
			Banco.closeConnection(conexao);
		}

		return quantidadeRegistrosExcluidos > 0;
	}

	public boolean consultarCpfCliente(String cpfdigitado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		String sql = "SELECT CPF FROM CLIENTE WHERE CPF = " + "'" + cpfdigitado + "'";
		try {
			result = stmt.executeQuery(sql);
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar Cpf");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(result);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}
		System.out.println(sql);

		return false;
	}


}