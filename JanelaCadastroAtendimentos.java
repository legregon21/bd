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


public class JanelaCadastroAtendimentos extends JInternalFrame {
	private JTextField tfDataAtendimento;
	int situacao = 0, ID = 1;
	JButton btIncluir = new JButton("Novo");
	JButton btAlterar = new JButton("Alterar");
	JButton btExcluir = new JButton("Excluir");
	JButton btSalvar = new JButton("Salvar");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfHora;
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
	public JanelaCadastroAtendimentos(Connection conex) {
		setBounds(100, 100, 515, 295);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastro de Atendimento M\u00E9dico:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(90, 33, 347, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 72, 437, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Data:");
		lblNome.setBounds(146, 14, 41, 14);
		panel.add(lblNome);
		
		tfDataAtendimento = new JTextField();
		tfDataAtendimento.setBounds(197, 11, 99, 20);
		panel.add(tfDataAtendimento);
		tfDataAtendimento.setColumns(10);
		
		JLabel lblId = new JLabel("C\u00F3digo:");
		lblId.setBounds(10, 14, 41, 14);
		panel.add(lblId);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(61, 11, 60, 20);
		panel.add(tfCodigo);
		tfCodigo.setEditable(false);
		
		JLabel lblHorrio = new JLabel("Hor\u00E1rio:");
		lblHorrio.setBounds(306, 14, 41, 14);
		panel.add(lblHorrio);
		
		tfHora = new JTextField();
		tfHora.setColumns(10);
		tfHora.setBounds(357, 11, 48, 20);
		panel.add(tfHora);
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es Principais");
		lblNewLabel_1_1_2.setBounds(21, 58, 142, 14);
		getContentPane().add(lblNewLabel_1_1_2);

		
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Paciente:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT NOME FROM PESSOAS WHERE CPF LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                
                        
                        
                        
                        if (rs.next())
                            {
                        	 	tfCPF.setText(retorno);
                        		tfNome.setText(rs.getString(1));
									 try
					                 {
					                     pStmt = con.prepareStatement("SELECT MAX(CODIGO_ATENDIMENTO) FROM ATENDIMENTO");
					                     rs = pStmt.executeQuery();
					                     if (rs.next())
					                    	ID = rs.getInt(1) + 1;
					                     else
					                    	ID = 1;
					               
									
									
					                btIncluir.setEnabled(false);
					                btExcluir.setEnabled(false);
					                btAlterar.setEnabled(false);
					                btSalvar.setEnabled(true);
					             
					                tfCodigo.setText(String.valueOf(ID));
					
					
					                
					                situacao = 1;
					                
					                 }
								     catch (SQLException ex)
								       {
								                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
					
								       }
                            }		 
							else
			                       JOptionPane.showMessageDialog(null,"Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			                          

			                            
			            }
			        catch (SQLException ex)
			            {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			            }
                
			}
		});
		btIncluir.setBounds(68, 202, 89, 23);
		getContentPane().add(btIncluir);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o Código do Atendimento:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT CODIGO_ATENDIMENTO, TO_CHAR(DATAATENDIMENTO, 'DD/MM/YYYY'), PESSOAS_CPF FROM ATENDIMENTO WHERE CODIGO_ATENDIMENTO = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                        		tfCodigo.setText(rs.getString(1));
                                tfDataAtendimento.setText(rs.getString(2));
                                tfCPF.setText(rs.getString(3));
                                
                                try
                                {
                                    pStmt = con.prepareStatement("SELECT NOME FROM PESSOAS WHERE CPF LIKE ?");
                                    pStmt.setString(1,rs.getString(3));
                                    rs = pStmt.executeQuery();
                                    rs.next();
                                    tfNome.setText(rs.getString(1));
                                }
                                catch (SQLException ex)
                                {
                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                }

                                situacao = 2;
                                
                               

                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                
                                tfCodigo.setEditable(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Atendimento não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                btIncluir.setEnabled(true);
                               


                            }
            }
        catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

            }

    }
		});
		
		
		btAlterar.setBounds(158, 202, 89, 23);
		getContentPane().add(btAlterar);
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o Código do Atendimento:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT CODIGO_ATENDIMENTO, TO_DATE(DATAATENDIMENTO, 'DD/MM/YYYY'), PESSOAS_CPF FROM ATENDIMENTO WHERE CODIGO_ATENDIMENTO = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                        		tfCodigo.setText(rs.getString(1));
                                tfDataAtendimento.setText(rs.getString(2));
                                tfCPF.setText(rs.getString(3));
                                
                                try
                                {
                                    pStmt = con.prepareStatement("SELECT NOME FROM PESSOAS WHERE CPF LIKE ?");
                                    pStmt.setString(1,rs.getString(3));
                                    rs = pStmt.executeQuery();
                                    rs.next();
                                    tfNome.setText(rs.getString(1));
                                }
                                catch (SQLException ex)
                                {
                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

                                }

                                situacao = 3;
                                
                               

                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                           
                                tfCodigo.setEditable(false);
                              


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Atendimento não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                btIncluir.setEnabled(true);
                              


                            }
            }
        catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

            }

    }
		});
		
		
		btExcluir.setBounds(246, 202, 89, 23);
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
                                    
                                                        pStmt = con.prepareStatement("INSERT INTO ATENDIMENTO VALUES ( ?,'Aguardando','A',?, TO_DATE(?, 'DD/MM/YYYY HH24:MI'))");
                                                        pStmt.setInt(1, ID);
                                                        pStmt.setString(2, tfCPF.getText());
                                                        pStmt.setString(3, tfDataAtendimento.getText() + " " + tfHora.getText());
                                            
                                                        pStmt.executeUpdate();
                                                        
                                                        btIncluir.setEnabled(true);
                                                        btExcluir.setEnabled(true);
                                                        btAlterar.setEnabled(true);
                                                        btSalvar.setEnabled(false);
                                                       
                                                        tfCodigo.setText("");
                                                        tfDataAtendimento.setText("");
                                                        tfNome.setText("");
                                                        tfCPF.setText("");
                                                        tfHora.setText("");
                                                   

                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }



                        break;

                        case 2:

                            try
                            {
                                
                                pStmt = con.prepareStatement("UPDATE ATENDIMENTO SET DATAATENDIMENTO = TO_DATE(?, 'DD/MM/YYYY') WHERE CODIGO_ATENDIMENTO = ?");
                                                            
                             
                             pStmt.setInt(2, Integer.parseInt(tfCodigo.getText()));
                             pStmt.setString(1, tfDataAtendimento.getText());
     
                        
                                
                                pStmt.executeUpdate();
                                btIncluir.setEnabled(true);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                            
                                tfCodigo.setText("");
                                tfDataAtendimento.setText("");
                                tfNome.setText("");
                                tfCPF.setText("");
                                tfHora.setText("");
                 
                                

                            }
                        catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;

                        case 3 :

                             try
                            {
                              pStmt = con.prepareStatement("SELECT * FROM OCORRENCIA_EXAMES WHERE ATENDIMENTO_CODIGOATENDIMENTO =  ?");
                                 pStmt.setInt(1,Integer.parseInt(tfCodigo.getText()));
                              rs = pStmt.executeQuery();
    
                                if (rs.next())
                                    JOptionPane.showMessageDialog(null, "Atendimento já possui exames para ser realizado. Impossível Exclusão.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                else
                                    {
                                        try
                                            {

                                                pStmt = con.prepareStatement("DELETE FROM ATENDIMENTO WHERE CODIGO_ATENDIMENTO = ?");
                                                pStmt.setInt(1,Integer.parseInt(tfCodigo.getText()));
                                                pStmt.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Atendimento excluído com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                    }
                                    tfDataAtendimento.setEditable(true);
                                   
                                    btIncluir.setEnabled(true);
                                    btExcluir.setEnabled(true);
                                    btAlterar.setEnabled(true);
                                    btSalvar.setEnabled(false);
                              
                                    tfDataAtendimento.setText("");
                                    tfCodigo.setText("");
                                    tfCPF.setText("");
                                    tfNome.setText("");
                                    btSalvar.setText("Salvar");

                            }
                            catch (SQLException ex)
                            {
                              JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;
                    }
            }
		});
		
		
		btSalvar.setBounds(336, 202, 89, 23);
		getContentPane().add(btSalvar);
		
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
		
	

	}
}