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


public class JanelaCadastroMedicos extends JInternalFrame {
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfDataNasc;
	private JTextField tfNumero;
	private JTextField tfLogradouro;
	private JTextField tfCidade;
	private JTextField tfComplemento;
	private JTextField tfTelefone;
	private JTextField tfSexo;
	int situacao = 0;
	JButton btIncluir = new JButton("Novo");
	JButton btAlterar = new JButton("Alterar");
	JButton btExcluir = new JButton("Excluir");
	JButton btSalvar = new JButton("Salvar");
	JButton btConsultar = new JButton("Consultar");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac;
	private JTextField tfCRM;
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
	public JanelaCadastroMedicos(Connection conex) {
		setBounds(100, 100, 643, 508);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastro de M\u00E9dicos");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(145, 24, 223, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 100, 544, 79);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(210, 8, 41, 14);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(282, 2, 231, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF:");
		lblNewLabel_1_1.setBounds(10, 8, 23, 14);
		panel.add(lblNewLabel_1_1);
		
		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		tfCPF.setBounds(61, 2, 134, 20);
		panel.add(tfCPF);
		
		JLabel lblNasc = new JLabel("Nasc.:");
		lblNasc.setBounds(210, 30, 41, 14);
		panel.add(lblNasc);
		
		tfDataNasc = new JTextField();
		tfDataNasc.setColumns(10);
		tfDataNasc.setBounds(282, 24, 94, 20);
		panel.add(tfDataNasc);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sexo:");
		lblNewLabel_1_1_1.setBounds(10, 33, 41, 14);
		panel.add(lblNewLabel_1_1_1);
		
		tfSexo = new JTextField();
		tfSexo.setColumns(10);
		tfSexo.setBounds(71, 24, 23, 20);
		panel.add(tfSexo);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es Pessoais");
		lblNewLabel_1_1_2.setBounds(21, 86, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 200, 544, 79);
		getContentPane().add(panel_1);
		
		JLabel lblN = new JLabel("N\u00BA:");
		lblN.setBounds(418, 5, 41, 14);
		panel_1.add(lblN);
		
		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		tfNumero.setBounds(469, 5, 49, 20);
		panel_1.add(tfNumero);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Logradouro:");
		lblNewLabel_1_1_3.setBounds(10, 8, 66, 14);
		panel_1.add(lblNewLabel_1_1_3);
		
		tfLogradouro = new JTextField();
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(114, 5, 274, 20);
		panel_1.add(tfLogradouro);
		
		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(114, 30, 94, 20);
		panel_1.add(tfCidade);
		
		JLabel lblNasc_1_1 = new JLabel("Cidade");
		lblNasc_1_1.setBounds(10, 36, 41, 14);
		panel_1.add(lblNasc_1_1);
		
		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(424, 33, 94, 20);
		panel_1.add(tfComplemento);
		
		JLabel lblCompl = new JLabel("Compl:");
		lblCompl.setBounds(361, 33, 41, 14);
		panel_1.add(lblCompl);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Endere\u00E7o:");
		lblNewLabel_1_1_2_1.setBounds(21, 186, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Contato:");
		lblNewLabel_1_1_2_1_1.setBounds(21, 287, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(20, 301, 217, 39);
		getContentPane().add(panel_1_1);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Telefone: ");
		lblNewLabel_1_1_3_1.setBounds(10, 8, 66, 14);
		panel_1_1.add(lblNewLabel_1_1_3_1);
		
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(86, 5, 121, 20);
		panel_1_1.add(tfTelefone);
		
		
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                btIncluir.setEnabled(false);
                btExcluir.setEnabled(false);
                btAlterar.setEnabled(false);
                btConsultar.setEnabled(false);
                btSalvar.setEnabled(true);


                
                situacao = 1;
			}
		});
		btIncluir.setBounds(70, 351, 89, 23);
		getContentPane().add(btIncluir);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 String retorno = JOptionPane.showInputDialog(null,"Digite o CRM do Médico:");
	                CodPac = retorno;

	                try
	                    {
	                        pStmt = con.prepareStatement("SELECT * FROM MEDICOS WHERE CRM LIKE ?");
	                        pStmt.setString(1,retorno);
	                        ResultSet rs = pStmt.executeQuery();
	                        //tfNome.setEditable(false);
	                       // tfCPF.setEditable(false);
	                       // tfEndereco.setEditable(false);
	                       // tfTelefone.setEditable(false);

	                        if (rs.next())
	                            {
	                        						
	                        						tfCRM.setText(rs.getString(1));
	                        						
						                        	try
						                            {
						                                pStmt = con.prepareStatement("SELECT CPF, NOME, TO_CHAR(DATANASCIMENTO, 'DD/MM/YYYY'), CIDADE, LOGRADOURO, NUMERO, SEXO,EMPRESAS_CNPJ,COMPLEMENTO FROM PESSOAS WHERE CPF LIKE ?");
						                                pStmt.setString(1,rs.getString(2));
						                                rs = pStmt.executeQuery();
						                                rs.next();
						                                tfCPF.setEditable(false);
						                                tfCRM.setEditable(false);
		                                                CodPac = rs.getString(1);
		                                                tfCPF.setText(rs.getString(1));
		                                                tfNome.setText(rs.getString(2));
		                                                tfDataNasc.setText(rs.getString(3));
		                                                tfCidade.setText(rs.getString(4));
		                                                tfLogradouro.setText(rs.getString(5));
		                                                tfNumero.setText(rs.getString(6));
		                                                tfTelefone.setText(rs.getString(7));
		                                                tfComplemento.setText(rs.getString(9));
		                                                situacao = 2;
		                                                btExcluir.setEnabled(false);
		                                                btAlterar.setEnabled(false);
		                                                btSalvar.setEnabled(true);
		                                                btIncluir.setEnabled(false);
		                                                btConsultar.setEnabled(false);
		                                                
		                                           
						                            }
						                        	
						                        	catch (SQLException ex)
						                            {
						                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

						                            }
		                                                
	                                                
	                         

	                            }
	                        else
	                            {
	                                JOptionPane.showMessageDialog(null,"Médico não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		
		btAlterar.setBounds(160, 351, 89, 23);
		getContentPane().add(btAlterar);
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			 {
                String retorno = JOptionPane.showInputDialog(null,"Digite o CRM do Médico:");
                CodPac = retorno;

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM MEDICOS WHERE CRM LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                        //tfNome.setEditable(false);
                       // tfCPF.setEditable(false);
                       // tfEndereco.setEditable(false);
                       // tfTelefone.setEditable(false);

                        if (rs.next())
                            {
                        						
                        						tfCRM.setText(rs.getString(1));
                        						
					                        	try
					                            {
					                                pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
					                                pStmt.setString(1,rs.getString(2));
					                                rs = pStmt.executeQuery();
					                                rs.next();
					                                
	                                                CodPac = rs.getString(1);
	                                                tfCPF.setText(rs.getString(1));
	                                                tfNome.setText(rs.getString(2));
	                                                tfDataNasc.setText(rs.getString(3));
	                                                tfCidade.setText(rs.getString(4));
	                                                tfLogradouro.setText(rs.getString(5));
	                                                tfNumero.setText(rs.getString(6));
	                                                tfTelefone.setText(rs.getString(7));
	                                                tfComplemento.setText(rs.getString(9));
	                                                situacao = 3;
	                                                btExcluir.setEnabled(false);
	                                                btAlterar.setEnabled(false);
	                                                btSalvar.setEnabled(true);
	                                                btIncluir.setEnabled(false);
	                                                tfCRM.setEditable(false);
	                                                tfCPF.setEditable(false);
	                                                btSalvar.setText("Confirmar");
	                                           
					                            }
					                        	
					                        	catch (SQLException ex)
					                            {
					                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

					                            }
	                                                
                                                
                         

                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Médico não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		
		btExcluir.setBounds(250, 351, 89, 23);
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
                                    
                                                        pStmt = con.prepareStatement("INSERT INTO PESSOAS VALUES ( ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?, ?, ?)");
                                                        pStmt.setString(1, tfCPF.getText());
                                                        pStmt.setString(2, tfNome.getText());
                                                        pStmt.setString(3, tfDataNasc.getText());
                                                        pStmt.setString(4, tfCidade.getText());
                                                        pStmt.setString(5, tfLogradouro.getText());
                                                        pStmt.setString(6, tfNumero.getText());
                                                        pStmt.setString(8, tfSexo.getText());
                                                        pStmt.setString(9, "1");
                                                        pStmt.setString(7, tfComplemento.getText());

                                                        pStmt.executeUpdate();
                                                        
                                                        pStmt = con.prepareStatement("INSERT INTO MEDICOS VALUES ( ?, ?)");
                                                        pStmt.setString(1, tfCRM.getText());
                                                        pStmt.setString(2, tfCPF.getText());
                                                        pStmt.executeUpdate();


                                                        btIncluir.setEnabled(true);
                                                        btExcluir.setEnabled(true);
                                                        btAlterar.setEnabled(true);
                                                        btConsultar.setEnabled(true);
                                                        btSalvar.setEnabled(false);
                                                        tfCPF.setText("");
                                                        tfNome.setText("");
                                                        tfDataNasc.setText("");
                                                        tfCidade.setText("");
                                                        tfLogradouro.setText("");
                                                        tfNumero.setText("");
                                                        tfSexo.setText("");
                                                        tfCRM.setText("");
                                                        tfComplemento.setText("");
                                                        tfTelefone.setText("");

                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }
                                          


                        break;

                        case 2:

                            try
                            {
                                
                                pStmt = con.prepareStatement("UPDATE PESSOAS SET NOME = ?, DATANASCIMENTO = TO_DATE(?, 'DD/MM/YYYY'), CIDADE = ?, LOGRADOURO = ?," +
                                                            " NUMERO = ?, SEXO = ?, COMPLEMENTO = ? WHERE CPF = ?");
                                                            
                                pStmt.setString(8, tfCPF.getText());
                                pStmt.setString(1, tfNome.getText());
                                pStmt.setString(2, tfDataNasc.getText());
                                pStmt.setString(3, tfCidade.getText());
                                pStmt.setString(4, tfLogradouro.getText());
                                pStmt.setString(5, tfNumero.getText());
                                pStmt.setString(6, tfSexo.getText());
                                pStmt.setString(7, tfComplemento.getText());
                                
                                pStmt.executeUpdate();
                                btIncluir.setEnabled(true);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                tfCPF.setText("");
                                                        tfNome.setText("");
                                                        tfDataNasc.setText("");
                                                        tfCidade.setText("");
                                                        tfLogradouro.setText("");
                                                        tfNumero.setText("");
                                                        tfSexo.setText("");
                                                        tfCRM.setText("");
                                                        tfComplemento.setText("");
                                                        tfTelefone.setText("");
                                tfCPF.setEditable(true);
                                tfCRM.setEditable(true);

                            }
                        catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;

                        case 3 :

                             try
                            {
                              pStmt = con.prepareStatement("SELECT * FROM OCORRENCIA_EXAMES WHERE MEDICOS_CRM LIKE ?");
                                 pStmt.setString(1,tfCRM.getText());
                              rs = pStmt.executeQuery();
    
                                if (rs.next())
                                    JOptionPane.showMessageDialog(null, "Médico é vinculado a exames.Nao foi possivel exclusao.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                else
                                    {
                                        try
                                            {
	                                        		
	                                        	pStmt = con.prepareStatement("DELETE FROM MEDICOS WHERE CRM = ?");
	                                            pStmt.setString(1, tfCRM.getText());
	                                            pStmt.executeUpdate();
                                                pStmt = con.prepareStatement("DELETE FROM PESSOAS WHERE CPF = ?");
                                                pStmt.setString(1, tfCPF.getText());
                                                pStmt.executeUpdate();
                                                
                                                
                                                
                                                JOptionPane.showMessageDialog(null, "Médico excluído com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                    }
                                    tfNome.setEditable(true);
                                    tfCPF.setEditable(true);;
                                     tfDataNasc.setEditable(true);
                                     tfCidade.setEditable(true);
                                    tfLogradouro.setEditable(true);
                                       tfNumero.setEditable(true);
                                     tfSexo.setEditable(true);
                                    tfCRM.setEditable(true);
                                    tfComplemento.setEditable(true);
                                    tfTelefone.setEditable(true);
                                    btIncluir.setEnabled(true);
                                    btExcluir.setEnabled(true);
                                    btAlterar.setEnabled(true);
                                    btConsultar.setEnabled(true);
                                    btSalvar.setEnabled(false);
                                    tfNome.setText("");
                                    tfCPF.setText("");
                                     tfDataNasc.setText("");
                                   tfCidade.setText("");
                                    tfLogradouro.setText("");
                                    tfNumero.setText("");
                                   tfSexo.setText("");
                                    tfCRM.setText("");
                                    tfComplemento.setText("");
                                    tfTelefone.setText("");
                                    btSalvar.setText("Salvar");

                            }
                            catch (SQLException ex)
                            {
                              JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;
                        
                        case 4:
                        	
                        	tfNome.setEditable(true);
                            tfCPF.setEditable(true);;
                             tfDataNasc.setEditable(true);
                             tfCidade.setEditable(true);
                            tfLogradouro.setEditable(true);
                               tfNumero.setEditable(true);
                             tfSexo.setEditable(true);
                            tfCRM.setEditable(true);
                            tfComplemento.setEditable(true);
                            tfTelefone.setEditable(true);
                            btIncluir.setEnabled(true);
                            btExcluir.setEnabled(true);
                            btAlterar.setEnabled(true);
                            btConsultar.setEnabled(true);
                            btSalvar.setEnabled(false);
                            tfNome.setText("");
                            tfCPF.setText("");
                             tfDataNasc.setText("");
                           tfCidade.setText("");
                            tfLogradouro.setText("");
                            tfNumero.setText("");
                           tfSexo.setText("");
                            tfCRM.setText("");
                            tfComplemento.setText("");
                            tfTelefone.setText("");
                            btSalvar.setText("Salvar");
                        	
                        break;
                        
                    }
            }
		});
		
		
		btSalvar.setBounds(340, 351, 89, 23);
		getContentPane().add(btSalvar);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1_1_1.setBounds(234, 61, 330, 39);
		getContentPane().add(panel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_3_1_2_1 = new JLabel("CRM:");
		lblNewLabel_1_1_3_1_2_1.setBounds(10, 8, 66, 14);
		panel_1_1_1_1.add(lblNewLabel_1_1_3_1_2_1);
		
		tfCRM = new JTextField();
		tfCRM.setColumns(10);
		tfCRM.setBounds(70, 5, 152, 20);
		panel_1_1_1_1.add(tfCRM);
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String retorno = JOptionPane.showInputDialog(null,"Digite o CRM do Médico:");
	                CodPac = retorno;

	                try
	                    {
	                        pStmt = con.prepareStatement("SELECT * FROM MEDICOS WHERE CRM LIKE ?");
	                        pStmt.setString(1,retorno);
	                        ResultSet rs = pStmt.executeQuery();
	                        //tfNome.setEditable(false);
	                       // tfCPF.setEditable(false);
	                       // tfEndereco.setEditable(false);
	                       // tfTelefone.setEditable(false);

	                        if (rs.next())
	                            {
	                        						
	                        						tfCRM.setText(rs.getString(1));
	                        						
						                        	try
						                            {
						                                pStmt = con.prepareStatement("SELECT CPF, NOME, TO_CHAR(DATANASCIMENTO, 'DD/MM/YYYY'), CIDADE, LOGRADOURO, NUMERO, SEXO,EMPRESAS_CNPJ,COMPLEMENTO FROM PESSOAS WHERE CPF LIKE ?");
						                                pStmt.setString(1,rs.getString(2));
						                                rs = pStmt.executeQuery();
						                                rs.next();
						                                tfCPF.setEditable(false);
						                                tfCRM.setEditable(false);
		                                                CodPac = rs.getString(1);
		                                                tfCPF.setText(rs.getString(1));
		                                                tfNome.setText(rs.getString(2));
		                                                tfDataNasc.setText(rs.getString(3));
		                                                tfCidade.setText(rs.getString(4));
		                                                tfLogradouro.setText(rs.getString(5));
		                                                tfNumero.setText(rs.getString(6));
		                                                tfTelefone.setText(rs.getString(7));
		                                                tfComplemento.setText(rs.getString(9));
		                                                situacao = 4;
		                                                btExcluir.setEnabled(false);
		                                                btAlterar.setEnabled(false);
		                                                btSalvar.setEnabled(true);
		                                                btIncluir.setEnabled(false);
		                                                btConsultar.setEnabled(false);
		                                                btSalvar.setText("OK");
		                                                
		                                           
						                            }
						                        	
						                        	catch (SQLException ex)
						                            {
						                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

						                            }
		                                                
	                                                
	                         

	                            }
	                        else
	                            {
	                                JOptionPane.showMessageDialog(null,"Médico não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
	                                btExcluir.setEnabled(true);
	                                btAlterar.setEnabled(true);
	                                btSalvar.setEnabled(false);
	                                btIncluir.setEnabled(true);
	                                btSalvar.setText("Salvar");

	                            }
	            }
	        catch (SQLException ex)
	            {
	                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

	            }

 }
			
		});
		

		btConsultar.setBounds(429, 351, 89, 23);
		getContentPane().add(btConsultar);
		
	

	}
}