package model.BO;

import java.util.ArrayList;

import model.DAO.ApostaDAO;
import model.VO.ApostaVO;

public class ApostaBO {

	public void cadastrarApostaBO(ApostaVO apostaVO) {

	}

	public boolean excluirApostaBO(ApostaVO apostaVO) {

		ApostaDAO apostaDAO = new ApostaDAO();
		return apostaDAO.excluir(apostaVO.getId());
	}

	public void atualizarApostaBO(ApostaVO apostaVO) {

	}

	public ApostaVO consultarApostasBO(ApostaVO apostaVO) {

		//ApostaDAO apostaDAO = new ApostaDAO();
		//apostaVO = apostaDAO.consultarPorId(apostaVO.getId());
		
		return apostaVO;
	}

	public java.util.ArrayList<ApostaVO> consultarApostaBO() {


		ApostaDAO petDAO = new ApostaDAO();

		ArrayList<ApostaVO> apostaVO = petDAO.consultarTodos();

		if (apostaVO.isEmpty()) {
			System.out.println("\n Lista de apostas está vazia.");
		}

		return apostaVO;
	}

	public int salvar(ApostaVO novaAposta) {

		ApostaDAO dao = new ApostaDAO();
		return dao.salvar(novaAposta);
	}

	public double atualizarValor() {
		ApostaDAO dao = new ApostaDAO();

		return dao.atualizaValor();
	}
	


}
