package view;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.VO.Cliente;

public class TelaPrincipal extends JFrame {

	
	private JPanel contentPane;
	protected TelaCadastroCliente telaCadastroCliente;
	private JDesktopPane desktopPane;

	
	
			
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setFont(new Font("Verdana", Font.PLAIN, 12));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icons/icons8-foguete-80.png")));
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 508);
		
		

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Cliente");
		mnNewMenu.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/icons8-usu\u00E1rio.png")));
		menuBar.add(mnNewMenu);

		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (telaCadastroCliente == null) {
					telaCadastroCliente = new TelaCadastroCliente();
					desktopPane.add(telaCadastroCliente);

					revalidate();
					repaint();

					//telaCadastroCliente.atualizarTabelaClientes(Cliente);
				}

			}
		});
		mnNewMenu.add(mntmCadastrar);

		JMenu mnAposta = new JMenu("Aposta");
		mnAposta.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/icons8-d\u00F3lar-americano-50.png")));
		menuBar.add(mnAposta);

		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mnAposta.add(mntmCadastrar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
	}
}
