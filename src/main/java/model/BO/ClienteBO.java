package model.BO;

import java.util.ArrayList;

import model.DAO.ClienteDAO;
import model.VO.Cliente;
import model.seletor.ClienteSeletor;

public class ClienteBO {
	ClienteDAO dao = new ClienteDAO();

	public String salvar(Cliente novoCliente) {
		String mensagem = "";

		if (dao.consultarCpfCliente(novoCliente.getCpf())) {
			mensagem = "O CPF informado já pertence a outro cliente";
		} else {
			novoCliente = dao.salvar(novoCliente);

			if (novoCliente.getIdCliente() > -1) {
				mensagem = "Cliente cadastrado com sucesso";
			} else {
				mensagem = "Erro ao cadastrar cliente. Entre em contato com o administrador do sistema.";
			}
		}

		return mensagem;
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

	public ArrayList<Cliente> listaClientes(ClienteSeletor seletor) {
		return dao.listarComSeletor(seletor);
	}

	public boolean atualizarBusca(Cliente cliente) {
		return dao.atualizarBusca(cliente);
	}

}
