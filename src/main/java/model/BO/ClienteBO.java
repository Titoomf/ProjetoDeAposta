package model.BO;

import java.util.ArrayList;

import model.DAO.ClienteDAO;
import model.VO.Cliente;

public class ClienteBO {
	ClienteDAO dao = new ClienteDAO();

	public String salvar(Cliente cliente) {
		String msg = "";
		int resultado = 0;
		ClienteDAO dao = new ClienteDAO();
		if (dao.consultarCpfCliente(cliente.getCpf())) {
			msg += ("Este cpf ja foi utilizado ");
		} else {
			resultado = dao.salvar(cliente);
		}
		if (resultado > -1) {
			msg += ("Cliente cadastrado com sucesso");
		} else {

		}
		System.out.println(resultado);
		return msg;
	}

	public ArrayList<Cliente> consultarTodos() {

		return dao.consultarTodos();
	}

	public String excluir(Cliente clienteSelecionado) {
		String mensagem = "";

		if (dao.excluir(clienteSelecionado.getIdCliente())) {
			mensagem = "Cliente Excluido com sucesso";
		} else {
			mensagem = "Erro ao excluir Cliente,";

		}

		return mensagem;
	}

	public static boolean alterar(Cliente clienteselecionado) {
		// TODO Auto-generated method stub
		return  ClienteDAO.alterar(clienteselecionado);
	}

}
