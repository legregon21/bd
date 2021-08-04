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



public class JanelaEvolucoesMedica extends JInternalFrame {
	int situacao = 0, ID = 1, ID_Exame=0, codigoAtendimento = 0;
	JButton btSelecionarE = new JButton("Selecionar...");
	JRadioButton tfReprovado = new JRadioButton("Inapto");
	JRadioButton tfAprovado = new JRadioButton("Apto");
	JRadioButton tfAguardando = new JRadioButton("Aguardando");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac, CRM_Medico, Data_Ocorrencia;
	String horarioatendimento;
	private JTextField tfNomePaciente;
	private JTable tfGrid;
	private JTable tbAgenda;
	private JTextField lblAgendaDia;
	
	
	public JanelaEvolucoesMedica(Connection conex) {
		setBounds(100, 100, 722, 499);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Evolu\u00E7\u00E3o de Atendimento:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(111, 33, 347, 14);
		getContentPane().add(lblNewLabel);
		JPanel panel_1 = new JPanel();
		

		ButtonGroup group = new ButtonGroup();
		group.add(tfAguardando);
		group.add(tfAprovado);
		group.add(tfReprovado);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Exames em Aberto:");
		lblNewLabel_1_1_2_1.setBounds(21, 121, 142, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);
		
		
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 135, 458, 140);
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
		
		tfNomePaciente = new JTextField();
		tfNomePaciente.setEditable(false);
		tfNomePaciente.setColumns(10);
		tfNomePaciente.setBounds(227, 11, 221, 20);
		panel_1_2.add(tfNomePaciente);
		btSelecionarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o Horário do Atendimento:");

                try
                    {
                		
                		pStmt = con.prepareStatement("SELECT DISTINCT NOME_PACIENTE, APROVADO, CODIGO_ATENDIMENTO FROM ATENDIMENTO_APROVACAO WHERE DATAATENDIMENTO = TO_DATE(?, 'DD/MM/YYYY HH24:MI')");
                		String DataMin = lblAgendaDia.getText() +" "+ retorno;
                		pStmt.setString(1,DataMin);
                        ResultSet rs = pStmt.executeQuery();
                        
                        if (rs.next())
                        {
                        		tfNomePaciente.setText(rs.getString(1));
                        		codigoAtendimento = rs.getInt(3);
                        		if (rs.getString(2).equals("A"))                                          
                                	tfAguardando.setSelected(true); 
                        	 	else if (rs.getString(2).equals("S"))
                        	 		tfAprovado.setSelected(true);
                        	 	else
                                	tfReprovado.setSelected(true);
                     
                        	try
                            {
	                        		Vector nomeColunas = new Vector();
	                        		Vector dados = new Vector();
	                        		pStmt = con.prepareStatement("SELECT DISTINCT NOME_EXAME, LAUDO FROM ATENDIMENTO_APROVACAO WHERE DATAATENDIMENTO = TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND NOME_PACIENTE LIKE ?");
	                        		DataMin = lblAgendaDia.getText() + retorno;
	                        		pStmt.setString(1,DataMin);
	                        		pStmt.setString(2, tfNomePaciente.getText());
	                                rs = pStmt.executeQuery();
                        			
			                        ResultSetMetaData metaData = rs.getMetaData();
			                        int cols = metaData.getColumnCount();
			                        nomeColunas.addElement("Nome do Exame");
			                        nomeColunas.addElement("Laudo");
			        
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
                        else
                        {
                        	 JOptionPane.showMessageDialog(null, "Agendamento não encontrado. Verifique.\n", "Erro", JOptionPane.ERROR_MESSAGE);

                        }
						                            
						            }
						        catch (SQLException ex)
						            {
						                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
			
						            }
                
                      }
		});
		btSelecionarE.setBounds(27, 10, 103, 23);
		panel_1_2.add(btSelecionarE);
		
		JLabel lblNewLabel_1_1_2_1_2 = new JLabel("Informa\u00E7\u00F5es do Atendimento:");
		lblNewLabel_1_1_2_1_2.setBounds(21, 58, 190, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2);
		
		JLabel lblNewLabel_1_1_2_1_2_1 = new JLabel("Descri\u00E7\u00E3o do Atendimento:");
		lblNewLabel_1_1_2_1_2_1.setBounds(21, 286, 190, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2_1);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2_1.setBounds(21, 299, 343, 110);
		getContentPane().add(panel_1_2_1);
		
		JTextPane tfDescricao = new JTextPane();
		tfDescricao.setBounds(10, 11, 323, 87);
		panel_1_2_1.add(tfDescricao);
		
		JPanel panel_1_2_1_1 = new JPanel();
		panel_1_2_1_1.setLayout(null);
		panel_1_2_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_2_1_1.setBounds(374, 301, 101, 108);
		getContentPane().add(panel_1_2_1_1);
		
		
		tfAguardando.setSelected(true);
		tfAguardando.setBounds(6, 7, 89, 23);
		panel_1_2_1_1.add(tfAguardando);
		tfAprovado.setBounds(6, 33, 73, 23);
		panel_1_2_1_1.add(tfAprovado);
		tfReprovado.setBounds(6, 59, 89, 23);
		panel_1_2_1_1.add(tfReprovado);
		
		JLabel lblNewLabel_1_1_2_1_2_1_1 = new JLabel("Resultado:");
		lblNewLabel_1_1_2_1_2_1_1.setBounds(374, 286, 101, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_2_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(488, 72, 203, 341);
		getContentPane().add(panel_1_1);
		

		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Agenda do dia:");
		lblNewLabel_1_1_2_1_1.setBounds(492, 36, 88, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1);
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
                {

            		pStmt = con.prepareStatement("UPDATE ATENDIMENTO SET DESCRICAO = ?, APROVADO = ? WHERE CODIGO_ATENDIMENTO = ?");
                    
            		if (tfAguardando.isSelected())
                    	pStmt.setString(2, "A");
                    else if(tfAprovado.isSelected())
                    	pStmt.setString(2,"S");
                    else
                    	pStmt.setString(2,"N");
            		
            		pStmt.setString(1, tfDescricao.getText());
            		pStmt.setInt(3, codigoAtendimento);
                    pStmt.executeUpdate();
                   
                    


                    tfNomePaciente.setText("");
                    tfDescricao.setText("");
                    panel_1.remove(tfGrid);
                    panel_1.repaint();



                }
            catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                }

				
			}
		});
		btSalvar.setBounds(21, 420, 89, 23);
		getContentPane().add(btSalvar);
		
		JButton btnAbrirAgenda = new JButton("Abrir Agenda");
		btnAbrirAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String retorno = JOptionPane.showInputDialog(null,"Digite a data da Agenda:");

                try
                    {
                		Vector nomeColunas = new Vector();
                		Vector dados = new Vector();
                		pStmt = con.prepareStatement("SELECT DISTINCT TO_CHAR(DATAATENDIMENTO, 'HH24:MI'), NOME_PACIENTE FROM ATENDIMENTO_APROVACAO WHERE DATAATENDIMENTO >= TO_DATE(?, 'DD/MM/YYYY HH24:MI') AND DATAATENDIMENTO <= TO_DATE(?, 'DD/MM/YYYY HH24:MI')");
                		String DataMin = retorno + " 00:00";
                		String DataMax = retorno + " 23:59";
                		pStmt.setString(1,DataMin);
                		pStmt.setString(2,DataMax);
                        ResultSet rs = pStmt.executeQuery();
                        
                        
                        ResultSetMetaData metaData = rs.getMetaData();
                        int cols = metaData.getColumnCount();
                        nomeColunas.addElement("Horário");
                        nomeColunas.addElement("Nome do Paciente");
        
                       if (rs.next())
                        {
                    	   lblAgendaDia.setText(retorno);
                        	do 
                        	{
                        		Vector row = new Vector(cols);
                        		for (int i = 1; i<= cols; i++)
                        			row.addElement(rs.getObject(i));
                        			dados.addElement(row);

                        	} while (rs.next());
                        	tbAgenda = new JTable(dados, nomeColunas);
                        	tbAgenda.setBounds(10, 11, 183, 319);
                    		
                    		
                        	TableColumn column;
                            for (int i = 0; i < tbAgenda.getColumnCount(); i++) {
                                column = tbAgenda.getColumnModel().getColumn(i);
                                column.setMaxWidth(250);
                                
                            }
                    		
                            panel_1_1.add(tbAgenda);
                            panel_1_1.revalidate();
                        	
                        }
                        
                       

			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
			
		});
		btnAbrirAgenda.setBounds(508, 424, 142, 23);
		getContentPane().add(btnAbrirAgenda);
		
		lblAgendaDia = new JTextField();
		lblAgendaDia.setEditable(false);
		lblAgendaDia.setColumns(10);
		lblAgendaDia.setBounds(590, 33, 101, 20);
		getContentPane().add(lblAgendaDia);
		
	

	}
}