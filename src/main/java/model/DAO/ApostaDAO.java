package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.VO.ApostaVO;
import model.VO.Cliente;

public class ApostaDAO {

	DateTimeFormatter formataDate2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public int salvar(ApostaVO novaAposta) {

		Connection conn = Banco.getConnection();

		Statement stmt = Banco.getStatement(conn);

		String query = "INSERT INTO aposta (idaposta, valor, dt_aposta, idcliente) " + " VALUES ('" + novaAposta.getId()
				+ "','" + novaAposta.getValor() + "','" + novaAposta.getCliente().getIdCliente() + "')";

		int resultado = 0;
		try {

			resultado = stmt.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println("Erro ao executar a query de cadastro da aposta");
			System.out.println("Erro: " + e.getMessage());

		} finally {

			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return resultado;

	}

	public boolean excluir(int id) {

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, null);
		int resultado = 0;

		String query = "DELETE FROM APOSTA WHERE id = " + id;
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Exclusão da aposta.");
			System.out.println("Erro: " + e.getMessage());
		}

		return (resultado > 0);
	}

	public int alterar(ApostaVO aposta) {

		Connection conn = Banco.getConnection();

		Statement stmt = Banco.getStatement(conn);

		int resultado = 0;

		String query = "UPDATE  aposta SET idaposta = " + aposta.getId() + ", idcliente = "
				+ aposta.getCliente().getIdCliente() + "', valor = " + aposta.getValor() + ",'WHERE idaposta = "
				+ aposta.getId();

		try {

			resultado = stmt.executeUpdate(query);

		} catch (SQLException e) {

			System.out.println("Erro ao executar a query de atualização de aposta");
			System.out.println("Erro: " + e.getMessage());

		} finally {

			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return resultado;
	}

		

	public ArrayList<ApostaVO> consultarTodos() {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<ApostaVO> apostasVO = new ArrayList<ApostaVO>();

		String query = "SELECT idaposta,idcliente,valor, dt_aposta FROM aposta";

		try {

			resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				ApostaVO apostaVO = new ApostaVO();

				apostaVO.setId(resultado.getInt(1));
				apostaVO.setValor(resultado.getDouble(2));

				ClienteDAO cliente = new ClienteDAO();
				Cliente cli = cliente.consultarPorId(resultado.getInt("idcliente"));

				//apostaVO.setCliente(cliente);

				apostasVO.add(apostaVO);

			}

		} catch (SQLException e) {

			System.out.println("Erro ao executar a query de consulta de todas as Receitas");
			System.out.println("Erro:   " + e.getMessage());
		} finally {

			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return apostasVO;
	}

	public double atualizaValor() {

		Connection conn = Banco.getConnection();

		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;

		String query = "SELECT VALOR FROM APOSTA WHERE ID = IDAPOSTA";

		try {

			resultado = stmt.executeQuery(query);

		} catch (SQLException e) {

			System.out.println("Erro ao executar a query de atualização da aposta");
			System.out.println("Erro: " + e.getMessage());

		} finally {

			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return 0;
	}

	public boolean validarAposta(ApostaVO aposta) {

		Connection conn = Banco.getConnection();

		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;

		String query = "SELECT VALOR FROM APOSTA  WHERE VALOR = VALOR";

		try {

			resultado = stmt.executeQuery(query);

		} catch (SQLException e) {

			System.out.println("Erro ao executar a query de atualização da aposta");
			System.out.println("Erro: " + e.getMessage());

		} finally {

			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return true;
	}

}
