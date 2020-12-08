package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import exeption.CpfInvalidoException;
import exeption.DataVaziaException;
import exeption.NomeInvalidoException;
import model.BO.ClienteBO;
import model.DAO.ClienteDAO;
import model.VO.Cliente;
import model.seletor.ClienteSeletor;
import model.utils.GeradorPlanilhas;

public class ControladoraCliente {
	ClienteBO bo = new ClienteBO();
	ClienteDAO dao = new ClienteDAO();

	public String validar(String nomeCliente, String telefoneCliente, String cpfCliente, LocalDate datanascimento,
			String valorCliente) {
		String mensagem = "";

		if ((nomeCliente == null) || (nomeCliente.trim().length() < 3)) {
			mensagem += "Cliente deve ter 3 letras ";
		} else

		if ((telefoneCliente == null) || (telefoneCliente.trim().length() < 8)) {
			mensagem += "Telefone deve ter no 9 digitos ";
		} else

		if ((cpfCliente == null) || (cpfCliente.trim().length() < 12)) {
			mensagem += "Cliente deve ter  11 digitos";

		} else

		if (datanascimento == null) {
			mensagem += "Por favor informe uma data";
		}

		return mensagem;
	}

	public String atualizarBusca(Cliente cliente) {

		String mensagem = "";

		boolean valido = bo.atualizarBusca(cliente);

		try {
			this.validarCPF(cliente.getCpf());
		} catch (CpfInvalidoException excecao) {
			mensagem = excecao.getMessage();
			valido = false;
		}

		try {
			this.validarNome(cliente.getNome());
		} catch (NomeInvalidoException excecao) {
			mensagem = excecao.getMessage();
			valido = false;
		}

		try {
			this.validarData(cliente.getDataNascimento());
		} catch (DataVaziaException excecao) {
			mensagem = excecao.getMessage();
			valido = false;
		}

		if (valido) {
			if (bo.atualizarBusca(cliente)) {
				mensagem = "Atualizado com sucesso!";
			} else {
				mensagem = "Problema ao atualizar";
			}
		}
		return mensagem;
	}

	private void validarData(LocalDate dataNascimento) throws DataVaziaException {
		if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
			throw new DataVaziaException("Data inválida");
		}
	}

	private void validarNome(String nome) throws NomeInvalidoException {
		if (nome == null || nome.isEmpty() || nome.length() < 3) {
			throw new NomeInvalidoException("Nome deve possuir ao menos 3 caracteres");
		}


	}

	private void validarCPF(String cpf) throws CpfInvalidoException {
		if (cpf == null || cpf.isEmpty() || cpf.length() != 11) {
			throw new CpfInvalidoException("CPF deve possuir 11 caracteres");
		}
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

	public ArrayList<Cliente> listarCliente(ClienteSeletor seletor) {
		return bo.listaClientes(seletor);
	}

}
