package model.VO;

public class ApostaVO {
	private int id;
	private double valor;
	private Cliente cliente;

	public ApostaVO() {
		super();

	}

	public ApostaVO(int id, double valor, Cliente cliente) {
		super();
		this.id = id;
		this.valor = valor;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "ApostaVO [id=" + id + " ,valor=" + valor + ", cliente=" + cliente + "]";
	}

}
