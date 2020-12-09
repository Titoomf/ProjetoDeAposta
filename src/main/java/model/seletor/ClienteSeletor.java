package model.seletor;

import java.time.LocalDate;

public class ClienteSeletor {

	// Atributos que servirao de filtros
	private int idCliente;
	private String nomeCliente;
	private String telefoneCliente;
	private String cpfCliente;
	private String valorCliente;

	private String dataNascimento;

	public boolean temFiltro() {
		boolean temFiltroPreenchido = false;

		temFiltroPreenchido = (nomeCliente != null && nomeCliente.trim().length() > 0)
				|| (telefoneCliente != null && telefoneCliente.trim().length() > 0) || (cpfCliente != null && cpfCliente.trim().length() > 0)
				|| valorCliente != null || valorCliente != null;

		return temFiltroPreenchido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getidCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNascimento() {
		return dataNascimento;
	}

	public void setNascimento(String nascimento) {
		this.dataNascimento = nascimento;
	}

	public String gettelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getValorCliente() {
		return valorCliente;
	}

	public void setValorCliente(String valorCliente) {
		this.valorCliente = valorCliente;
	}
}