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


public class JanelaCadastroEmpresas extends JInternalFrame {
	private JTextField tfNome;
	private JTextField tfCNPJ;
	int situacao = 0;
	JButton btIncluir = new JButton("Novo");
	JButton btAlterar = new JButton("Alterar");
	JButton btExcluir = new JButton("Excluir");
	JButton btSalvar = new JButton("Salvar");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac;
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
	public JanelaCadastroEmpresas(Connection conex) {
		setBounds(100, 100, 515, 295);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastro de Empresas:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(154, 31, 223, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 72, 477, 79);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 36, 41, 14);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(44, 33, 231, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("CNPJ:");
		lblNewLabel_1_1.setBounds(10, 8, 41, 14);
		panel.add(lblNewLabel_1_1);
		
		tfCNPJ = new JTextField();
		tfCNPJ.setColumns(10);
		tfCNPJ.setBounds(44, 5, 231, 20);
		panel.add(tfCNPJ);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es Principais");
		lblNewLabel_1_1_2.setBounds(21, 58, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Situa\u00E7\u00E3o da Empresa:");
		lblNewLabel_1_1_2_1_1_1.setBounds(21, 162, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1_1);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1_1.setBounds(20, 176, 477, 39);
		getContentPane().add(panel_1_1_1);
		
		JRadioButton tfConvSim = new JRadioButton("Conveniada");
		tfConvSim.setSelected(true);
		tfConvSim.setBounds(64, 7, 109, 23);
		panel_1_1_1.add(tfConvSim);
		
		JRadioButton tfConvNao = new JRadioButton("N\u00E3o Conveniada");
		tfConvNao.setBounds(280, 7, 119, 23);
		panel_1_1_1.add(tfConvNao);
		
		ButtonGroup group = new ButtonGroup();
		group.add(tfConvSim);
		group.add(tfConvNao);
		
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                btIncluir.setEnabled(false);
                btExcluir.setEnabled(false);
                btAlterar.setEnabled(false);
                btSalvar.setEnabled(true);


                
                situacao = 1;
			}
		});
		btIncluir.setBounds(20, 222, 89, 23);
		getContentPane().add(btIncluir);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o CNPJ da Empresa:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EMPRESAS WHERE CNPJ LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                                CodPac = rs.getString(1);
                                tfCNPJ.setText(rs.getString(1));
                                tfNome.setText(rs.getString(2));
                                if (rs.getString(3).equals("S"))                                          
                                	tfConvSim.setSelected(true); 
                                else
                                	tfConvNao.setSelected(true);

                               
                                situacao = 2;
                                
                               

                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                tfCNPJ.setEditable(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Empresa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		
		btAlterar.setBounds(145, 222, 89, 23);
		getContentPane().add(btAlterar);
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			 {
                String retorno = JOptionPane.showInputDialog(null,"Digite o CNPJ da EMPRESA:");
                CodPac = retorno;

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EMPRESAS WHERE CNPJ LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                        //tfNome.setEditable(false);
                       // tfCPF.setEditable(false);
                       // tfEndereco.setEditable(false);
                       // tfTelefone.setEditable(false);

                        if (rs.next())
                            {
                                
                                                CodPac = rs.getString(1);
                                                tfCNPJ.setText(rs.getString(1));
                                                tfNome.setText(rs.getString(2));
                                            
                                                if (rs.getString(3).equals("S"))                                          
                                                	tfConvSim.setSelected(true); 
                                                else
                                                	tfConvNao.setSelected(true);
                                               
                                                situacao = 3;
                                                btExcluir.setEnabled(false);
                                                btAlterar.setEnabled(false);
                                                btSalvar.setEnabled(true);
                                                btIncluir.setEnabled(false);
                                                btSalvar.setText("Confirmar");
                         

                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Empresa não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		
		btExcluir.setBounds(279, 222, 89, 23);
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
                                    
                                                        pStmt = con.prepareStatement("INSERT INTO EMPRESAS VALUES ( ?, ?, ?)");
                                                        pStmt.setString(1, tfCNPJ.getText());
                                                        pStmt.setString(2, tfNome.getText());
                                                        if (tfConvSim.isSelected())
                                                        	pStmt.setString(3, "S");
                                                        else
                                                        	pStmt.setString(3,"N");
                                                        pStmt.executeUpdate();
                                                        
                                                        btIncluir.setEnabled(true);
                                                        btExcluir.setEnabled(true);
                                                        btAlterar.setEnabled(true);
                                                        btSalvar.setEnabled(false);
                                                        tfCNPJ.setText("");
                                                        tfNome.setText("");


                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }



                        break;

                        case 2:

                            try
                            {
                                
                                pStmt = con.prepareStatement("UPDATE EMPRESAS SET NOME = ?, CONVENIADA = ? WHERE CNPJ = ?");
                                                            
                             pStmt.setString(3, tfCNPJ.getText());
                                pStmt.setString(1, tfNome.getText());
                               if (tfConvSim.isSelected())
                                	pStmt.setString(2, "S");
                                else
                                	pStmt.setString(2,"N"); 
                                
                                pStmt.executeUpdate();
                                btIncluir.setEnabled(true);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                tfCNPJ.setText("");
                                tfNome.setText("");
                 
                                tfCNPJ.setEditable(true);

                            }
                        catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;

                        case 3 :

                             try
                            {
                              pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE EMPRESAS_CNPJ LIKE ?");
                                 pStmt.setString(1,CodPac);
                              rs = pStmt.executeQuery();
    
                                if (rs.next())
                                    JOptionPane.showMessageDialog(null, "Empresa possui funcionários cadastrados.Nao foi possivel exclusão.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                else
                                    {
                                        try
                                            {

                                                pStmt = con.prepareStatement("DELETE FROM EMPRESAS WHERE CNPJ = ?");
                                                pStmt.setString(1, CodPac);
                                                pStmt.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Empresa excluída com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                    }
                                    tfNome.setEditable(true);
                                    tfCNPJ.setEditable(true);;
                                    btIncluir.setEnabled(true);
                                    btExcluir.setEnabled(true);
                                    btAlterar.setEnabled(true);
                                    btSalvar.setEnabled(false);
                                    tfNome.setText("");
                                    tfCNPJ.setText("");
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
		
		
		btSalvar.setBounds(408, 222, 89, 23);
		getContentPane().add(btSalvar);
		
	

	}
}