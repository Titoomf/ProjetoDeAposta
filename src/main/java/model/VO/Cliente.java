package model.VO;

import java.time.LocalDate;

public class Cliente {
	
	private int idCliente;
	private String nome;
	private String telefone;	
	private String cpf;
	public LocalDate DataNascimento;
	private Double valor;


	public Cliente(int idCliente, String nome, String telefone,  String cpf,  LocalDate datanasciemento,Double valor) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.telefone = telefone;		
		this.cpf = cpf;
		this.DataNascimento = datanasciemento;
		this.valor = valor;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nomeCliente, String telefoneCliente,  String cpfCliente,
			LocalDate datanasciemento,Double valorCliente) {
		this.nome = nomeCliente;
		this.telefone = telefoneCliente;		
		this.cpf = cpfCliente;
		this.DataNascimento = datanasciemento;
		this.valor = valorCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		DataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "ClienteVO [id=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + ", dt_nascimento=" + DataNascimento
				+ ", valor_depositado=" + valor + "]";
	}

}
