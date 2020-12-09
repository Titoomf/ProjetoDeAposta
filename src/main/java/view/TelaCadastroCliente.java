package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;

import controller.ControladoraCliente;
import model.VO.Cliente;
import model.seletor.ClienteSeletor;

@SuppressWarnings("serial")

public class TelaCadastroCliente extends JPanel {

	private JTextField txtNome;
	private JButton btnLimpar;
	private DatePicker datanascimento;
	private JButton btnRelatorio;
	private JTable tblConsultaCliente;
	private ArrayList<Cliente> clientes;
	private ArrayList<Cliente> consultarCliente;
	private JFormattedTextField fmtTelefone;
	protected int linhaSelecionada;
	private JFormattedTextField fmtCpf;
	private JTextField txtValor;
	private JButton btnExcluirCliente;
	private ClienteSeletor seletor = new ClienteSeletor();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public TelaCadastroCliente() {
		setBackground(Color.CYAN);
		setBounds(0, 0, 768, 630);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		btnExcluirCliente = new JButton("Excluir ");
		btnExcluirCliente.setBounds(266, 593, 118, 41);
		btnExcluirCliente.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icons/icons8-excluir-32.png")));
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
				int valorBotao = JOptionPane.YES_NO_OPTION;
				valorBotao = JOptionPane.showConfirmDialog(null, "deseja realmente excluir este cliente?",
						"CONFIRMAÇÃO", valorBotao);
				if (valorBotao == JOptionPane.YES_NO_OPTION) {
					if (tblConsultaCliente.getSelectedRow() >= 0) {
						linhaSelecionada = tblConsultaCliente.getSelectedRow();
						Cliente clienteSelecionado = clientes.get(linhaSelecionada - 1);
						model.removeRow(tblConsultaCliente.getSelectedRow());
						ControladoraCliente controladoraCliente = new ControladoraCliente();
						String mensagem = controladoraCliente.excluir(clienteSelecionado);
						JOptionPane.showMessageDialog(null, mensagem);

					} else {

						JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Cancelado");
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 360, 522, 230);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {

		});
		scrollPane.setViewportView(panel);

		tblConsultaCliente = new JTable();
		panel.add(tblConsultaCliente);
		tblConsultaCliente.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setBounds(42, 64, 46, 14);

		JLabel lblCadastroDeCliente = new JLabel("Cadastro De Cliente");
		lblCadastroDeCliente.setBounds(330, 25, 285, 30);
		lblCadastroDeCliente.setFont(new Font("Verdana", Font.ITALIC, 16));

		JLabel lblNome = new JLabel("Nome :");
		lblNome.setBounds(42, 25, 46, 14);

		txtNome = new JTextField();
		txtNome.setBounds(88, 22, 179, 22);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setBounds(15, 112, 66, 17);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		final DateTimePicker dataTeste = new DateTimePicker(dateSettings, null);
		dataTeste.setBounds(25, 179, 183, 37);
		dataTeste.getDatePicker().getComponentToggleCalendarButton().setForeground(Color.BLACK);

		JButton btnSalvar = new JButton("Salva");
		btnSalvar.setBounds(140, 246, 155, 49);
		btnSalvar.setIcon(new ImageIcon(
				TelaCadastroCliente.class.getResource("/icons/icons8-adicionar-usu\u00E1rio-masculino.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladoraCliente controladora = new ControladoraCliente();
				Cliente novoCliente = new Cliente();

				String nomeCliente = txtNome.getText().toUpperCase();
				String telefoneCliente = fmtTelefone.getText();
				String valorCliente = txtValor.getText();
				valorCliente = valorCliente.replaceAll(",", ".");
				valorCliente = valorCliente.replace("R$ ", "");
				Double valor = Double.parseDouble(valorCliente);
				LocalDate datanascimento = dataTeste.getDatePicker().getDate();

				telefoneCliente.replaceAll("()", "");
				telefoneCliente.replaceAll("-", "");
				String cpfCliente = fmtCpf.getText();
				cpfCliente.replaceAll(".", "");
				cpfCliente.replaceAll("-", "");
				String mensagem = "";
				String msg = "";

				mensagem = controladora.validar(nomeCliente, telefoneCliente, cpfCliente, datanascimento, valorCliente);

				if (mensagem.isEmpty()) {
					Cliente cliente = new Cliente(nomeCliente, telefoneCliente, cpfCliente, datanascimento, valor);
					msg = controladora.salvar(cliente);
					JOptionPane.showMessageDialog(null, msg);
				} else
					JOptionPane.showMessageDialog(null, mensagem);
			}
			
		});

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(140, 592, 114, 42);
		btnLimpar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icons/icons8-apagar-24.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtValor.setText("");
				fmtTelefone.setText("");
				fmtCpf.setText("");

				limparTabela();

			}
		});

		JSeparator separator = new JSeparator();

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(578, 325, 20, 299);
		separator_2.setOrientation(SwingConstants.VERTICAL);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 323, 565, 10);

		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setBounds(154, 333, 196, 14);
		lblConsultaDeCliente.setForeground(Color.BLACK);
		lblConsultaDeCliente.setFont(new Font("Verdana", Font.ITALIC, 16));
		construirTabelaClientes();

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#####-####");
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			fmtTelefone = new JFormattedTextField(mascaraTelefone);
			fmtTelefone.setBounds(88, 110, 179, 20);

			fmtCpf = new JFormattedTextField(mascaraCpf);
			fmtCpf.setBounds(88, 61, 179, 20);

		} catch (ParseException e) {
		}

		JLabel lblDataNascimento = new JLabel("Data Nascimento :");
		lblDataNascimento.setBounds(15, 150, 133, 16);

		try {

			MaskFormatter formatoDataEntrada = new MaskFormatter("##/##/####");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtValor = new JNumberFormatField();
		txtValor.setBounds(15, 252, 116, 37);
		txtValor.setColumns(10);

		JLabel lblDeposito = new JLabel("Dep\u00F3sito :");
		lblDeposito.setBounds(15, 218, 133, 16);
		lblDeposito.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnRelatorio = new JButton("Gerar Relatorio");
		btnRelatorio.setBounds(400, 593, 149, 40);
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jcf = new JFileChooser();
				jcf.setDialogTitle("Salvar relatório como...");

				int resultado = jcf.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jcf.getSelectedFile().getAbsolutePath();

					ControladoraCliente controllerCliente = new ControladoraCliente();
					controllerCliente.gerarRelatorio(clientes, caminhoEscolhido);

					JOptionPane.showConfirmDialog(null, "sALVOU");
				}
			}

		});

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(
				new ImageIcon(TelaCadastroCliente.class.getResource("/icons/icons8-lista-com-marcadores.png")));
		btnConsultar.setBounds(335, 247, 149, 46);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nomeCliente = txtNome.getText().toUpperCase();
				String telefoneCliente = fmtTelefone.getText();
				String valorCliente = txtValor.getText();
				valorCliente = valorCliente.replaceAll(",", ".");
				valorCliente = valorCliente.replace("R$ ", "");
				Double valor = Double.parseDouble(valorCliente);
				LocalDate datanascimento = dataTeste.getDatePicker().getDate();

				telefoneCliente = telefoneCliente.replaceAll("\\(", "");
				telefoneCliente = telefoneCliente.replaceAll("\\)", "");
				telefoneCliente = telefoneCliente.replaceAll("-", "");
				String cpfCliente = fmtCpf.getText();
				cpfCliente = cpfCliente.replaceAll(".", "");
				cpfCliente = cpfCliente.replaceAll("-", "");

				ControladoraCliente controller = new ControladoraCliente();
				ClienteSeletor seletor = new ClienteSeletor();

				seletor.setNomeCliente(nomeCliente);
				seletor.setTelefoneCliente(telefoneCliente);
				seletor.setCpfCliente(cpfCliente);

				List<Cliente> cliente = controller.listarCliente(seletor);

				atualizarTabelaClientes(cliente);

				tblConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(100);
				tblConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(101);
				tblConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
				tblConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(102);
				tblConsultaCliente.getColumnModel().getColumn(4).setPreferredWidth(90);

			}
		});
		txtNome.setText("");
		txtValor.setText("");
		fmtTelefone.setText("");
		fmtCpf.setText("");
		setLayout(null);
		add(lblNome);
		add(txtNome);
		add(lblCadastroDeCliente);
		add(lblCpf);
		add(fmtCpf);
		add(lblTelefone);
		add(fmtTelefone);
		add(separator_1);
		add(lblConsultaDeCliente);
		add(scrollPane);
		add(btnLimpar);
		add(btnExcluirCliente);
		add(btnRelatorio);
		add(separator_2);
		add(lblDeposito);
		add(txtValor);
		add(btnSalvar);
		add(btnConsultar);
		add(lblDataNascimento);
		add(dataTeste);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnEditar.setBounds(25, 590, 105, 46);
		add(btnEditar);

	}

	protected void atualizarTabelaClientes(List<Cliente> clientes) {

		construirTabelaClientes();
		DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
		for (Cliente cliente : clientes) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String dataFormatada = cliente.getDataNascimento().format(formatter);
			String[] novaLinha = new String[5];
			novaLinha[0] = cliente.getNome().toUpperCase();
			novaLinha[1] = cliente.getTelefone().toUpperCase();
			novaLinha[2] = cliente.getCpf().toUpperCase();
			novaLinha[3] = String.valueOf(cliente.getDataNascimento().format(formatter));
			String valor = "R$" + String.valueOf(cliente.getValor());
			novaLinha[4] = valor;

			// Adiciona a nova linha na tabela
			model.addRow(novaLinha);

		}

	}

	private void construirTabelaClientes() {

		consultarCliente = clientes;
		this.limparTabela();

		tblConsultaCliente.setModel(
				new DefaultTableModel(new Object[][] { { "Nome", "telefone", "CPF ", "DataNascimento", "valor" }, },
						new String[] { "Nome", "telefone", "CPF ", "DataNascimento", "valor" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tblConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(101);
		tblConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(102);
		tblConsultaCliente.getColumnModel().getColumn(4).setPreferredWidth(90);

	}

	private void limparTabela() {
		tblConsultaCliente.setModel(
				new DefaultTableModel(new String[][] { { "Nome", "Telefone", "CPF", "DataNasciemnto", "Valor" }, },
						new String[] { "Nome", "Telefone", "CPF", "DataNasciemnto", "Valor" }));
	}

}