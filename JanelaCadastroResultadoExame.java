package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.OracleDriver;



public class JanelaCadastroResultadoExame extends JInternalFrame {
	private JTextField tfDataExame;
	int situacao = 0, ID = 1, ID_Exame=0;
	JButton btSelecionarE = new JButton("Selecionar...");
	JRadioButton tfReprovado = new JRadioButton("Reprovado");
	JRadioButton tfAprovado = new JRadioButton("Aprovado");
	JRadioButton tfAguardando = new JRadioButton("Aguardando");
	JButton btConsultarExame = new JButton("Consultar");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac, CRM_Medico, Data_Ocorrencia;
	String horarioatendimento;
	private JTextField tfNomeExame;
	private JTable tfGrid;
	private JTextField tfNomePaciente;
	private JTextField tfNomeExameSel;
	
	
	public JanelaCadastroResultadoExame(Connection conex) {
		setBounds(100, 100, 515, 499);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastrar Resultado de Exame:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(111, 33, 347, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 136, 267, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
                ResultSet rs;
                switch (situacao)
                    {
                        case 1:
                        
                                                try
                                                    {
                                    
                                                		pStmt = con.prepareStatement("UPDATE EXAMES_POR_ATENDIMENTO SET LAUDO = ? WHERE DATAEXAME = TO_DATE(?, 'DD/MM/YYYY HH24:MI')");
                                                        
                                                		if (tfAguardando.isSelected())
                                                        	pStmt.setString(1, "Aguardando");
                                                        else if(tfAprovado.isSelected())
                                                        	pStmt.setString(1,"Aprovado");
                                                        else
                                                        	pStmt.setString(1,"Reprovado");
                                                		

                                                		pStmt.setString(2, horarioatendimento);
                                                        pStmt.executeUpdate();
                                                        
                                                        btConsultarExame.setEnabled(true);

                                                        btSalvar.setEnabled(false);

                                                        tfNomePaciente.setText("");
                                                        tfNomeExameSel.setText("");



                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }



                        break;       

   
                    }
                
                try
                {
            		Vector nomeColunas = new Vector();
            		Vector dados = new Vector();
            	
            		pStmt = con.prepareStatement("SELECT TO_CHAR(DATAEXAME, 'HH24:MI'), NOME_PACIENTE, NOME_EXAME, LAUDO FROM EXAMES_POR_ATENDIMENTO WHERE DATAEXAME > TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND DATAEXAME < TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND EXAMES_ID_EXAME = ?");
            		String DataMin = tfDataExame.getText() + " 00:00";
            		String DataMax = tfDataExame.getText() + " 23:59";
            		pStmt.setString(1,DataMin);
            		pStmt.setString(2,DataMax);
            		pStmt.setInt(3, ID_Exame);
                    
                    rs = pStmt.executeQuery();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int cols = metaData.getColumnCount();
                    nomeColunas.addElement("Horário");
                    nomeColunas.addElement("Nome do Paciente");
                    nomeColunas.addElement("Nome do Exame");
                    nomeColunas.addElement("Status do Laudo");
                    
                    if (rs.next())
                    {
                    	do 
                    	{
                    		Vector row = new Vector(cols);
                    		for (int i = 1; i<= cols; i++)
                    			row.addElement(rs.getObject(i));
                    			dados.addElement(row);

                    	} while (rs.next());
                    	tfGrid = new JTable(dados, nomeColunas);
                    	tfGrid.setBounds(10, 11, 438, 142);
                		
                		
                    	TableColumn column;
                        for (int i = 0; i < tfGrid.getColumnCount(); i++) {
                            column = tfGrid.getColumnModel().getColumn(i);
                            column.setMaxWidth(250);
                            
                        }
                		
                        panel_1.add(tfGrid);
                        panel_1.revalidate();
                    	
                    }
                    else
                    {
                    	 JOptionPane.showMessageDialog(null, "Sem exames para essa data. Verifique.\n", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                    
                    
                    
     
		                            
		            }
		        catch (SQLException ex)
		            {
		                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

		            }
            }
		});
		
		JLabel lblNome = new JLabel("Data:");
		lblNome.setBounds(10, 14, 41, 14);
		panel.add(lblNome);
		
		tfDataExame = new JTextField();
		tfDataExame.setBounds(43, 11, 99, 20);
		panel.add(tfDataExame);
		tfDataExame.setColumns(10);
		

		ButtonGroup group = new ButtonGroup();
		group.add(tfAguardando);
		group.add(tfAprovado);
		group.add(tfReprovado);
		
		JButton btConsultar =  new JButton("Consultar");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
									{
				
                try
                    {
                		Vector nomeColunas = new Vector();
                		Vector dados = new Vector();
                	
                		pStmt = con.prepareStatement("SELECT TO_CHAR(DATAEXAME, 'HH24:MI'), NOME_PACIENTE, NOME_EXAME, LAUDO FROM EXAMES_POR_ATENDIMENTO WHERE DATAEXAME > TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND DATAEXAME < TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND EXAMES_ID_EXAME = ?");
                		String DataMin = tfDataExame.getText() + " 00:00";
                		String DataMax = tfDataExame.getText() + " 23:59";
                		pStmt.setString(1,DataMin);
                		pStmt.setString(2,DataMax);
                		pStmt.setInt(3, ID_Exame);
                        
                        ResultSet rs = pStmt.executeQuery();
                        ResultSetMetaData metaData = rs.getMetaData();
                        int cols = metaData.getColumnCount();
                        nomeColunas.addElement("Horário");
                        nomeColunas.addElement("Nome do Paciente");
                        nomeColunas.addElement("Nome do Exame");
                        nomeColunas.addElement("Status do Laudo");
                        
                        if (rs.next())
                        {
                        	do 
                        	{
                        		Vector row = new Vector(cols);
                        		for (int i = 1; i<= cols; i++)
                        			row.addElement(rs.getObject(i));
                        			dados.addElement(row);

                        	} while (rs.next());
                        	tfGrid = new JTable(dados, nomeColunas);
                        	tfGrid.setBounds(10, 11, 438, 142);
                    		
                    		
                        	TableColumn column;
                            for (int i = 0; i < tfGrid.getColumnCount(); i++) {
                                column = tfGrid.getColumnModel().getColumn(i);
                                column.setMaxWidth(250);
                                
                            }
                    		
                            panel_1.add(tfGrid);
                            panel_1.revalidate();
                        	
                        }
                        else
                        {
                        	 JOptionPane.showMessageDialog(null, "Sem exames para essa data. Verifique.\n", "Erro", JOptionPane.ERROR_MESSAGE);

                        }
                        
                        
                        
         
			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
		});
		btConsultar.setBounds(167, 10, 89, 23);
		panel.add(btConsultar);
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es do Atendimento:");
		lblNewLabel_1_1_2.setBounds(22, 122, 174, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Exames em Aberto:");
		lblNewLabel_1_1_2_1.setBounds(21, 180, 142, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);
		
		
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 194, 458, 140);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2.setBounds(20, 72, 458, 47);
		getContentPane().add(panel_1_2);
		
		JLabel lblNome_1_2 = new JLabel("Nome:");
		lblNome_1_2.setBounds(176, 14, 41, 14);
		panel_1_2.add(lblNome_1_2);
		
		tfNomeExame = new JTextField();
		tfNomeExame.setEditable(false);
		tfNomeExame.setColumns(10);
		tfNomeExame.setBounds(227, 11, 221, 20);
		panel_1_2.add(tfNomeExame);
		btSelecionarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o ID do Exame:");

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
		lblNewLabel_1_1_2_1_2.setBounds(21, 58, 190, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2);
		
		JLabel lblNewLabel_1_1_2_1_2_1 = new JLabel("Informa\u00E7\u00F5es do Exame Selecionado:");
		lblNewLabel_1_1_2_1_2_1.setBounds(21, 345, 190, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2_1);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2_1.setBounds(21, 360, 282, 98);
		getContentPane().add(panel_1_2_1);
		
		JLabel lblNome_1_2_1 = new JLabel("Nome:");
		lblNome_1_2_1.setBounds(10, 14, 41, 14);
		panel_1_2_1.add(lblNome_1_2_1);
		
		tfNomePaciente = new JTextField();
		tfNomePaciente.setEditable(false);
		tfNomePaciente.setColumns(10);
		tfNomePaciente.setBounds(48, 11, 221, 20);
		panel_1_2_1.add(tfNomePaciente);
		

		btSalvar.setBounds(136, 64, 89, 23);
		panel_1_2_1.add(btSalvar);
		
		JLabel lblNome_1_2_1_1 = new JLabel("Exame:");
		lblNome_1_2_1_1.setBounds(10, 42, 41, 14);
		panel_1_2_1.add(lblNome_1_2_1_1);
		
		tfNomeExameSel = new JTextField();
		tfNomeExameSel.setEditable(false);
		tfNomeExameSel.setColumns(10);
		tfNomeExameSel.setBounds(48, 39, 140, 20);
		panel_1_2_1.add(tfNomeExameSel);
		

		btConsultarExame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o horário do Exame:");
				horarioatendimento = tfDataExame.getText() + " " + retorno;
                
                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EXAMES_POR_ATENDIMENTO WHERE DATAEXAME = TO_DATE(?, 'DD/MM/YYYY HH24:MI')");
                        pStmt.setString(1, horarioatendimento);
                        ResultSet rs = pStmt.executeQuery();
                        
                        if (rs.next())
                        	
                            {

                                                       	
				                        	 	tfNomePaciente.setText(rs.getString(5));
				                        	 	tfNomeExameSel.setText(rs.getString(4));
				                        	 	if (rs.getString(2).equals("Aguardando"))                                          
				                                	tfAguardando.setSelected(true); 
				                        	 	else if (rs.getString(2).equals("Aprovado"))
				                        	 		tfAprovado.setSelected(true);
				                        	 	else
				                                	tfReprovado.setSelected(true);
					               
													
													
									                btConsultarExame.setEnabled(false);

									       
									                btSalvar.setEnabled(true);
									                btConsultarExame.setEnabled(false);
									                																		
									                
									                situacao = 1;
					
                        	
                            }		 
							else
			                       JOptionPane.showMessageDialog(null,"Não há exame para o horário escolhido.", "Erro", JOptionPane.ERROR_MESSAGE);
			                          

			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
		});
		btConsultarExame.setBounds(20, 64, 89, 23);
		panel_1_2_1.add(btConsultarExame);
		
		JPanel panel_1_2_1_1 = new JPanel();
		panel_1_2_1_1.setLayout(null);
		panel_1_2_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2_1_1.setBounds(309, 360, 101, 98);
		getContentPane().add(panel_1_2_1_1);
		
		
		tfAguardando.setSelected(true);
		tfAguardando.setBounds(6, 7, 89, 23);
		panel_1_2_1_1.add(tfAguardando);
		tfAprovado.setBounds(6, 33, 73, 23);
		panel_1_2_1_1.add(tfAprovado);
		tfReprovado.setBounds(6, 59, 89, 23);
		panel_1_2_1_1.add(tfReprovado);
		
		JLabel lblNewLabel_1_1_2_1_2_1_1 = new JLabel("Resultado:");
		lblNewLabel_1_1_2_1_2_1_1.setBounds(309, 345, 190, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2_1_1);
		
	

	}
}