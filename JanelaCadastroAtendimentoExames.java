package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.OracleDriver;


public class JanelaCadastroAtendimentoExames extends JInternalFrame {
	private JTextField tfDataExame;
	int situacao = 0, ID = 1, ID_Exame=0;
	JButton btIncluir = new JButton("Incluir");
	JButton btExcluir = new JButton("Excluir");
	JButton btSalvar = new JButton("Salvar");
	JButton btConsultar = new JButton("Consultar");
	JButton btSelecionarM = new JButton("Selecionar...");
	JButton btSelecionarE = new JButton("Selecionar...");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac, CRM_Medico, Data_Ocorrencia;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfNomeMedico;
	private JTextField tfNomeExame;
	/**
	 * Launch the application.
	 */
/**	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaCadastroPaciente frame = new JanelaCadastroPaciente();
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
	public JanelaCadastroAtendimentoExames(Connection conex) {
		setBounds(100, 100, 515, 485);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastro de Exames x Atendimento:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(90, 33, 347, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 72, 314, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Data:");
		lblNome.setBounds(146, 14, 41, 14);
		panel.add(lblNome);
		
		tfDataExame = new JTextField();
		tfDataExame.setBounds(197, 11, 99, 20);
		panel.add(tfDataExame);
		tfDataExame.setColumns(10);
		
		JLabel lblId = new JLabel("C\u00F3digo:");
		lblId.setBounds(10, 14, 41, 14);
		panel.add(lblId);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(61, 11, 60, 20);
		panel.add(tfCodigo);
		tfCodigo.setEditable(false);
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es do Atendimento:");
		lblNewLabel_1_1_2.setBounds(21, 58, 142, 14);
		getContentPane().add(lblNewLabel_1_1_2);

		
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o Código do Atendimento:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT PESSOAS_CPF FROM ATENDIMENTO WHERE CODIGO_ATENDIMENTO = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                
                        
                        
                        
                        if (rs.next())
                        	
                            {
                        	
                        	try
                            {
                                pStmt = con.prepareStatement("SELECT NOME FROM PESSOAS WHERE CPF LIKE ?");
                                pStmt.setString(1,rs.getString(1));
                                 rs = pStmt.executeQuery();
                                 rs.next();
                                
                        	
				                        	 	tfCPF.setText(retorno);
				                        		tfNome.setText(rs.getString(1));
					               
													
													
									                btIncluir.setEnabled(false);
									                btExcluir.setEnabled(false);
									       
									                btSalvar.setEnabled(true);
									                btConsultar.setEnabled(false);
									                btSelecionarE.setEnabled(true);
									                btSelecionarM.setEnabled(true);
									                
									                tfCodigo.setText(String.valueOf(ID));
									
									
									                
									                situacao = 1;
					
													 
			                 }
						     catch (SQLException ex)
						       {
						                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
			
						       }
                        	
                            }		 
							else
			                       JOptionPane.showMessageDialog(null,"Atendimento não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			                          

			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
		});
		btIncluir.setBounds(55, 346, 89, 23);
		getContentPane().add(btIncluir);
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o ID do Exame:");
                String retorno2 = JOptionPane.showInputDialog(null,"Digite a data de sua ocorrência:");

                try
                    {
                        pStmt = con.prepareStatement(" SELECT * FROM OCORRENCIA_EXAMES WHERE EXAMES_ID_EXAME = ? AND DATAEXAME = TO_DATE(?, 'DD/MM/YYYY')");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        pStmt.setString(2, retorno2);
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                        		ID_Exame = Integer.parseInt(rs.getString(5));
                        		CRM_Medico = rs.getString(4);
                        		Data_Ocorrencia = retorno2;
                        		tfDataExame.setText(rs.getString(1));
                                try
                                {
                                    pStmt = con.prepareStatement("SELECT PESSOAS_CPF FROM ATENDIMENTO WHERE CODIGO_ATENDIMENTO = ?");
                                    pStmt.setInt(1,Integer.parseInt(rs.getString(3)));
                                    rs = pStmt.executeQuery();
                                    rs.next();
                                    CPFPac = rs.getString(1);
                                    try
                                    {
                                        pStmt = con.prepareStatement("SELECT NOME, CPF FROM PESSOAS WHERE CPF LIKE ?");
                                        pStmt.setString(1, CPFPac);
                                        rs = pStmt.executeQuery();
                                        rs.next();
                                        tfNome.setText(rs.getString(1));
                                        tfCPF.setText(rs.getString(2));
                                        try
                                        {
                                            pStmt = con.prepareStatement("SELECT NOME FROM EXAMES WHERE ID_EXAME = ?");
                                            pStmt.setInt(1, ID_Exame);
                                            rs = pStmt.executeQuery();
                                            rs.next();
                                            tfNomeExame.setText(rs.getString(1));
                                            
                                            try
                                            {
                                                pStmt = con.prepareStatement("SELECT PESSOAS_CPF FROM MEDICOS WHERE CRM LIKE ?");
                                                pStmt.setString(1, CRM_Medico);
                                                rs = pStmt.executeQuery();
                                                rs.next();
                                               
                                                try
                                                {
                                                    pStmt = con.prepareStatement("SELECT NOME FROM PESSOAS WHERE CPF LIKE ?");
                                                    pStmt.setString(1, rs.getString(1));
                                                    rs = pStmt.executeQuery();
                                                    rs.next();
                                                    tfNomeMedico.setText(rs.getString(1));
                                                    
                                                }
                                                catch (SQLException ex)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                                }
                                                
                                            }
                                            catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                            }
                                            
                                        }
                                        catch (SQLException ex)
                                        {
                                            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                        }
                                        
                                    }
                                    catch (SQLException ex)
                                    {
                                        JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                    }
                                    
                                    
                                    
                                    
                                }
                                catch (SQLException ex)
                                {
                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                }

                                situacao = 3;
                                
                               

                                btExcluir.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                btConsultar.setEnabled(false);
                                tfCodigo.setEditable(false);
                              


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Ocorrência de Exame não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                           
                                btSalvar.setEnabled(false);
                                btIncluir.setEnabled(true);
                                btConsultar.setEnabled(true);


                            }
            }
        catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

            }

    }
		});
		
		
		btExcluir.setBounds(144, 346, 89, 23);
		getContentPane().add(btExcluir);
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                ResultSet rs;
                switch (situacao)
                    {
                        case 1:
                        
                                                try
                                                    {
                                    
                                                        pStmt = con.prepareStatement("INSERT INTO OCORRENCIA_EXAMES VALUES ( TO_DATE(?, 'DD/MM/YYYY'),'Aguardando',?,?,?)");
                                                        pStmt.setString(1, tfDataExame.getText());
                                                        pStmt.setString(2, tfCodigo.getText());
                                                        pStmt.setString(3, CRM_Medico);
                                                        pStmt.setInt(4, ID_Exame);
                                            
                                                        pStmt.executeUpdate();
                                                        
                                                        btIncluir.setEnabled(true);
                                                        btExcluir.setEnabled(true);
                                                     
                                                        btSalvar.setEnabled(false);
                                                        btConsultar.setEnabled(true);
                                                        btSelecionarM.setEnabled(false);
                                                        btSelecionarE.setEnabled(false);
                                                        tfCodigo.setText("");
                                                        tfNomeMedico.setText("");
                                                        tfNomeExame.setText("");
                                                        tfDataExame.setText("");
                                                        tfNome.setText("");
                                                        tfCPF.setText("");
                                                   

                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }



                        break;       

                        case 3 :

                                        try
                                            {

                                                pStmt = con.prepareStatement("DELETE FROM OCORRENCIA_EXAMES WHERE DATAEXAME = TO_DATE(?, 'DD/MM/YYYY') AND EXAMES_ID_EXAME = ?");
                                                pStmt.setString(1,Data_Ocorrencia);
                                                pStmt.setInt(2,ID_Exame);
                                                pStmt.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Ocorrência de exame excluída com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                        
                                       
                                        
                                        btIncluir.setEnabled(true);
                                        btExcluir.setEnabled(true);
                                       
                                        btSalvar.setEnabled(false);
                                        btConsultar.setEnabled(true);
                                        tfDataExame.setText("");
                                        tfCodigo.setText("");
                                        tfCPF.setText("");
                                        tfNome.setText("");
                                        tfNomeMedico.setText("");
                                        tfNomeExame.setText("");
                                        btSalvar.setText("Salvar");

      

                        break;
                    }
            }
		});
		
		
		btSalvar.setBounds(234, 346, 89, 23);
		getContentPane().add(btSalvar);
		

		btConsultar.setBounds(323, 346, 89, 23);
		getContentPane().add(btConsultar);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Informa\u00E7\u00F5es do Paciente:");
		lblNewLabel_1_1_2_1.setBounds(21, 130, 142, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 144, 458, 47);
		getContentPane().add(panel_1);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setBounds(176, 14, 41, 14);
		panel_1.add(lblNome_1);
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setColumns(10);
		tfNome.setBounds(227, 11, 221, 20);
		panel_1.add(tfNome);
		
		JLabel lblId_1 = new JLabel("CPF:");
		lblId_1.setBounds(10, 14, 41, 14);
		panel_1.add(lblId_1);
		
		tfCPF = new JTextField();
		tfCPF.setEditable(false);
		tfCPF.setColumns(10);
		tfCPF.setBounds(35, 11, 131, 20);
		panel_1.add(tfCPF);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(20, 216, 458, 47);
		getContentPane().add(panel_1_1);
		
		JLabel lblNome_1_1 = new JLabel("Nome:");
		lblNome_1_1.setBounds(176, 14, 41, 14);
		panel_1_1.add(lblNome_1_1);
		
		tfNomeMedico = new JTextField();
		tfNomeMedico.setEditable(false);
		tfNomeMedico.setColumns(10);
		tfNomeMedico.setBounds(227, 11, 221, 20);
		panel_1_1.add(tfNomeMedico);
		
		
		btSelecionarM.setEnabled(false);
		btSelecionarM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o CRM do Médico:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM MEDICOS WHERE CRM LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                
                        
                        
                        
                        if (rs.next())
                        	
                            {
                        		CRM_Medico = rs.getString(1);
                        		
                        		  try
                                  {
                                      pStmt = con.prepareStatement("SELECT NOME FROM PESSOAS  WHERE CPF LIKE ?");
                                      pStmt.setString(1,rs.getString(2));
                                      rs = pStmt.executeQuery();
                                      rs.next();
                                      tfNomeMedico.setText(rs.getString(1));
                                      
                                  }
                        		  catch (SQLException ex)
          			            	{
          			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

          			            	}
                        		  
                              
                            }		 
							else
			                       JOptionPane.showMessageDialog(null,"Médico não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			                          

			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
		});
		btSelecionarM.setBounds(28, 10, 104, 23);
		panel_1_1.add(btSelecionarM);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Informa\u00E7\u00E3o do M\u00E9dico Realizante:");
		lblNewLabel_1_1_2_1_1.setBounds(21, 202, 232, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2.setBounds(20, 288, 458, 47);
		getContentPane().add(panel_1_2);
		
		JLabel lblNome_1_2 = new JLabel("Nome:");
		lblNome_1_2.setBounds(176, 14, 41, 14);
		panel_1_2.add(lblNome_1_2);
		
		tfNomeExame = new JTextField();
		tfNomeExame.setEditable(false);
		tfNomeExame.setColumns(10);
		tfNomeExame.setBounds(227, 11, 221, 20);
		panel_1_2.add(tfNomeExame);
		
		
		btSelecionarE.setEnabled(false);
		btSelecionarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o CRM do Médico:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EXAMES WHERE ID_EXAME = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                
                        
                        
                        
                        if (rs.next())
                        	
                            {
                        		
        
                                 tfNomeExame.setText(rs.getString(2));
                                 ID_Exame = Integer.parseInt(rs.getString(1));
                
                        		  
                              
                            }		 
							else
			                       JOptionPane.showMessageDialog(null,"Exame não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			                          

			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
		});
		btSelecionarE.setBounds(27, 10, 103, 23);
		panel_1_2.add(btSelecionarE);
		
		JLabel lblNewLabel_1_1_2_1_2 = new JLabel("Informa\u00E7\u00F5es do Exame Selecionado:");
		lblNewLabel_1_1_2_1_2.setBounds(21, 274, 190, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2);
		
	

	}
}