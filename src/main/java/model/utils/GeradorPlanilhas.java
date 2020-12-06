package model.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.VO.Cliente;

public class GeradorPlanilhas {

	public void gerarPlanilhasClientes(List<Cliente> clientes, String caminhoEscolhido) {
		String[] colunasTabelaClientes = { "IDCLIENTE", " NOME", "TELEFONE", "CPF", "DATANASCIMENTO", "VALOR" };

		HSSFWorkbook planilha = new HSSFWorkbook();

		HSSFSheet abaPlanilha = planilha.createSheet("clientes");

		Row headerRow = abaPlanilha.createRow(0);

		for (int i = 0; i < colunasTabelaClientes.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colunasTabelaClientes[i]);
		}

		DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		int rowNum = 1;
		for (Cliente cliente : clientes) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);

			novaLinha.createCell(0).setCellValue(cliente.getIdCliente());
			novaLinha.createCell(1).setCellValue(cliente.getNome());
			novaLinha.createCell(2).setCellValue(cliente.getTelefone());
			novaLinha.createCell(3).setCellValue(cliente.getCpf());
			novaLinha.createCell(4).setCellValue(cliente.getDataNascimento().format(formatadorDeData));
			novaLinha.createCell(5).setCellValue(cliente.getValor());

		}

		for (int i = 0; i < colunasTabelaClientes.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		FileOutputStream fileOut = null;

		try {
			fileOut = new FileOutputStream(caminhoEscolhido + ".xls");
			planilha.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.close();
				planilha.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
