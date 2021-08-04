package view;

import java.awt.EventQueue;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.OracleDriver;


public class vMenuPrincipal {

	private JFrame frame;

	Statement stmt;
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vMenuPrincipal window = new vMenuPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void TestaConexao(){

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String password = "oracle2021";
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle2021");
			stmt = con.createStatement();
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, " Erro ao carregar driver. Verifique: -classpath .:hsql.jar. \n "+ex, "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro. Verifique se o servidor está online. "+ex, "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	
	

	/**
	 * Create the application.
	 */
	public vMenuPrincipal() {
		initialize();
		TestaConexao();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 624, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		
		JDesktopPane desktopPane = new JDesktopPane() ;
		
		

		
		desktopPane.setBounds(0,0,2000,2000);
		frame.getContentPane().add(desktopPane);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Empresa");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastro de Empresa");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroEmpresas JCE = new JanelaCadastroEmpresas(con);
				JCE.setVisible(true);
				JCE.setClosable(true);
				desktopPane.add(JCE);
				
				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Paciente");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cadastro de Paciente");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroPaciente JCP = new JanelaCadastroPaciente(con);
				JCP.setVisible(true);
				JCP.setClosable(true);
				desktopPane.add(JCP);
				
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consultar Resultado");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Medicos");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Cadastro de M\u00E9dico");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroMedicos JCM = new JanelaCadastroMedicos(con);
				JCM.setVisible(true);
				JCM.setClosable(true);
				desktopPane.add(JCM);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_4 = new JMenu("Atendimento");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cadastro de Atendimento");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroAtendimentos JCAT = new JanelaCadastroAtendimentos(con);
				JCAT.setVisible(true);
				JCAT.setClosable(true);
				desktopPane.add(JCAT);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Cadastro de Exame por Atendimento");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroAtendimentoExames JCATEX = new JanelaCadastroAtendimentoExames(con);
				JCATEX.setVisible(true);
				JCATEX.setClosable(true);
				desktopPane.add(JCATEX);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_5 = new JMenu("Exames");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cadastro de Exames");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroExames JCEX = new JanelaCadastroExames(con);
				JCEX.setVisible(true);
				JCEX.setClosable(true);
				desktopPane.add(JCEX);
				
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_6 = new JMenu("Evolu\u00E7\u00E3o M\u00E9dica");
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Evoluir Atendimento");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaEvolucoesMedica JCEVM = new JanelaEvolucoesMedica(con);
				JCEVM.setVisible(true);
				JCEVM.setClosable(true);
				desktopPane.add(JCEVM);
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Cadastrar Resultado por Exames");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaCadastroResultadoExame JCRE = new JanelaCadastroResultadoExame(con);
				JCRE.setVisible(true);
				JCRE.setClosable(true);
				desktopPane.add(JCRE);
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Sair");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(mntmNewMenuItem_9);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		frame.getContentPane().setLayout(null);
		

		
		
	}
}
