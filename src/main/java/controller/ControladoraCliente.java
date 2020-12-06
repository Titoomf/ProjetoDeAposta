package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.BO.ClienteBO;
import model.DAO.ClienteDAO;
import model.VO.Cliente;
import model.utils.GeradorPlanilhas;

public class ControladoraCliente {
	ClienteBO bo = new ClienteBO();
	ClienteDAO dao = new ClienteDAO();

	public String validar(String nomeCliente, String telefoneCliente, String cpfCliente, LocalDate datanascimento,
			String valorCliente) {
		String mensagem = "";

		if ((nomeCliente == null) || (nomeCliente.trim().length() < 3)) {
			mensagem += "Cliente deve ter no 3 letras ";
		} else

		if ((telefoneCliente == null) || (telefoneCliente.trim().length() < 8)) {
			mensagem += "Telefone deve ter no 9 digitos ";
		} else

		if ((cpfCliente == null) || (cpfCliente.trim().length() < 12)) {
			mensagem += "Cliente deve ter  11 digitos";

		}

		return mensagem;
	}

	public String salvar(Cliente cliente) {

		return bo.salvar(cliente);
	}

	public ArrayList<Cliente> consultarTodos() {
		// TODO Auto-generated method stub
		return bo.consultarTodos();
	}

	public String excluir(Cliente clienteSelecionado) {

		return bo.excluir(clienteSelecionado);

	}

	public void gerarRelatorio(ArrayList<Cliente> clientes, String caminhoEscolhido) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		gerador.gerarPlanilhasClientes(clientes, caminhoEscolhido);
	}

}
