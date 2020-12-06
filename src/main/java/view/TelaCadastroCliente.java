package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;

import controller.ControladoraCliente;
import model.VO.Cliente;

@SuppressWarnings("serial")

public class TelaCadastroCliente extends JPanel {
	private Cliente clienteAlterado = new Cliente();
	private JTextField txtNome;
	private JButton btnLimpar;
	private JButton btnRelatorio;
	private JTable tblConsultaCliente;
	private ArrayList<Cliente> clientes;
	private JFormattedTextField fmtTelefone;
	protected int linhaSelecionada;
	private JFormattedTextField fmtCpf;
	private JTextField txtValor;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public TelaCadastroCliente() {
		setBackground(Color.CYAN);
		setBounds(0, 0, 768, 630);

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton btnExcluirCliente = new JButton("Excluir ");
		btnExcluirCliente.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icons/icons8-excluir-32.png")));
		btnExcluirCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) tblConsultaCliente.getModel();
				int valorBotao = JOptionPane.YES_NO_OPTION;
				valorBotao = JOptionPane.showConfirmDialog(null, "deseja realmente excluir este cliente?",
						"CONFIRMACAO", valorBotao);
				if (valorBotao == JOptionPane.YES_OPTION) {

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

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {

		});
		scrollPane.setViewportView(panel);

		tblConsultaCliente = new JTable();
		panel.add(tblConsultaCliente);
		tblConsultaCliente.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblCpf = new JLabel("CPF :");

		JLabel lblCadastroDeCliente = new JLabel("Cadastro De Cliente");
		lblCadastroDeCliente.setFont(new Font("Verdana", Font.ITALIC, 16));

		JLabel lblNome = new JLabel("Nome :");

		txtNome = new JTextField();
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone :");

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		final DateTimePicker dataTeste = new DateTimePicker(dateSettings, null);
		dataTeste.getDatePicker().getComponentToggleCalendarButton().setForeground(Color.BLACK);

		JButton btnSalvar = new JButton("Salva");
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

					atualizarTabelaClientes();
				} else {
					JOptionPane.showMessageDialog(null, mensagem, "Cadastrar cliente", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/icons/icons8-apagar-24.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtValor.setText("");
				fmtTelefone.setText("");
				fmtCpf.setText("");

			}
		});

		JSeparator separator = new JSeparator();

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);

		JSeparator separator_1 = new JSeparator();

		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente");
		lblConsultaDeCliente.setForeground(Color.BLACK);
		lblConsultaDeCliente.setFont(new Font("Verdana", Font.ITALIC, 16));
		construirTabelaClientes();

		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##)#####-####");
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
			fmtTelefone = new JFormattedTextField(mascaraTelefone);

			fmtCpf = new JFormattedTextField(mascaraCpf);

		} catch (ParseException e) {
		}

		JLabel lblDataNascimento = new JLabel("Data Nascimento :");

		try {

			MaskFormatter formatoDataEntrada = new MaskFormatter("##/##/####");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtValor = new JNumberFormatField();
		txtValor.setColumns(10);

		JLabel lblDeposito = new JLabel("Deposito :");
		lblDeposito.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnRelatorio = new JButton("Gerar Relatorio");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jcf = new JFileChooser();
				jcf.setDialogTitle("Salvar relatório como...");

				int resultado = jcf.showSaveDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jcf.getSelectedFile().getAbsolutePath();

					ControladoraCliente controllerCliente = new ControladoraCliente();
					controllerCliente.gerarRelatorio(clientes, caminhoEscolhido);
				}
			}

		});

		 JButton btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionadaNaTabela = tblConsultaCliente.getSelectedRow();
				Cliente clienteSelecionado = clientes.get(linhaSelecionadaNaTabela - 1);

				JOptionPane.showMessageDialog(null, "Chamar a tela de edição e passar o objeto clienteSelecionado...");
			}

	//	});
//		btnEditar.addMouseListener(new MouseAdapter() {
			//@Override
		//	public void mouseClicked(MouseEvent e) {
				//int indiceSelecionado = tblConsultaCliente.getSelectedRow();

				//if (indiceSelecionado > 0) {
				//	btnEditar.setEnabled(true);
				//} else {
				//	btnEditar.setEnabled(false);
				//}
			//}

		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(37)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addGap(63).addComponent(lblCadastroDeCliente, GroupLayout.PREFERRED_SIZE, 285,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(37)
								.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(fmtCpf, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(10)
								.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addGap(7)
								.addComponent(fmtTelefone, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(139).addComponent(
										lblConsultaDeCliente, GroupLayout.PREFERRED_SIZE, 196,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 522, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup().addGap(28)
										.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 114,
												GroupLayout.PREFERRED_SIZE)
										.addGap(62)
										.addComponent(btnExcluirCliente, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE)
										.addGap(63).addComponent(btnRelatorio, GroupLayout.PREFERRED_SIZE, 149,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(29)
								.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDeposito, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(47).addComponent(btnSalvar).addGap(7).addComponent(btnEditar,
												GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblDataNascimento,
								GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(20).addComponent(dataTeste,
								GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(148, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(17)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(lblNome,
								GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(lblCadastroDeCliente,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
				.addGap(6)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(3).addComponent(lblCpf,
								GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(fmtCpf, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(lblTelefone,
								GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(fmtTelefone, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(20).addComponent(lblDataNascimento).addGap(13)
				.addComponent(dataTeste, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE).addGap(2)
				.addComponent(lblDeposito, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE).addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtValor, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGap(28)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(separator_2,
								GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(lblConsultaDeCliente, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)
										.addGap(13).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
										.createSequentialGroup().addGap(3)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnRelatorio, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnExcluirCliente)))
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnLimpar,
														GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap()));
		setLayout(groupLayout);

	}

	protected void atualizarTabelaClientes() {
		ControladoraCliente controller = new ControladoraCliente();
		clientes = controller.consultarTodos();
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
}
