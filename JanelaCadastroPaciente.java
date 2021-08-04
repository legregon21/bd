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


public class JanelaCadastroPaciente extends JInternalFrame {
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
	private JTextField tfEmpresas_CNPJ;
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
	public JanelaCadastroPaciente(Connection conex) {
		setBounds(100, 100, 660, 472);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastro de Pacientes");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(207, 35, 223, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 72, 599, 79);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(233, 5, 41, 14);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(301, 5, 231, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF:");
		lblNewLabel_1_1.setBounds(10, 8, 23, 14);
		panel.add(lblNewLabel_1_1);
		
		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		tfCPF.setBounds(62, 2, 134, 20);
		panel.add(tfCPF);
		
		JLabel lblNasc = new JLabel("Nasc.:");
		lblNasc.setBounds(233, 27, 41, 14);
		panel.add(lblNasc);
		
		tfDataNasc = new JTextField();
		tfDataNasc.setColumns(10);
		tfDataNasc.setBounds(301, 27, 94, 20);
		panel.add(tfDataNasc);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sexo:");
		lblNewLabel_1_1_1.setBounds(10, 33, 41, 14);
		panel.add(lblNewLabel_1_1_1);
		
		tfSexo = new JTextField();
		tfSexo.setColumns(10);
		tfSexo.setBounds(62, 24, 23, 20);
		panel.add(tfSexo);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es Pessoais");
		lblNewLabel_1_1_2.setBounds(21, 58, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 169, 599, 79);
		getContentPane().add(panel_1);
		
		JLabel lblN = new JLabel("N\u00BA:");
		lblN.setBounds(398, 11, 41, 14);
		panel_1.add(lblN);
		
		tfNumero = new JTextField();
		tfNumero.setColumns(10);
		tfNumero.setBounds(436, 8, 49, 20);
		panel_1.add(tfNumero);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Logradouro:");
		lblNewLabel_1_1_3.setBounds(10, 8, 66, 14);
		panel_1.add(lblNewLabel_1_1_3);
		
		tfLogradouro = new JTextField();
		tfLogradouro.setColumns(10);
		tfLogradouro.setBounds(111, 8, 274, 20);
		panel_1.add(tfLogradouro);
		
		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(85, 36, 94, 20);
		panel_1.add(tfCidade);
		
		JLabel lblNasc_1_1 = new JLabel("Cidade");
		lblNasc_1_1.setBounds(10, 36, 41, 14);
		panel_1.add(lblNasc_1_1);
		
		tfComplemento = new JTextField();
		tfComplemento.setColumns(10);
		tfComplemento.setBounds(272, 36, 94, 20);
		panel_1.add(tfComplemento);
		
		JLabel lblCompl = new JLabel("Compl:");
		lblCompl.setBounds(204, 42, 41, 14);
		panel_1.add(lblCompl);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Endere\u00E7o:");
		lblNewLabel_1_1_2_1.setBounds(21, 155, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Contato:");
		lblNewLabel_1_1_2_1_1.setBounds(21, 256, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(20, 270, 246, 39);
		getContentPane().add(panel_1_1);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Telefone 1: ");
		lblNewLabel_1_1_3_1.setBounds(10, 8, 66, 14);
		panel_1_1.add(lblNewLabel_1_1_3_1);
		
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(93, 8, 121, 20);
		panel_1_1.add(tfTelefone);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Dados da Empresa Conveniada:");
		lblNewLabel_1_1_2_1_1_1.setBounds(21, 313, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1_1);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1_1.setBounds(20, 327, 259, 39);
		getContentPane().add(panel_1_1_1);
		
		JLabel lblNewLabel_1_1_3_1_2 = new JLabel("CNPJ:");
		lblNewLabel_1_1_3_1_2.setBounds(10, 8, 66, 14);
		panel_1_1_1.add(lblNewLabel_1_1_3_1_2);
		
		tfEmpresas_CNPJ = new JTextField();
		tfEmpresas_CNPJ.setColumns(10);
		tfEmpresas_CNPJ.setBounds(91, 5, 152, 20);
		panel_1_1_1.add(tfEmpresas_CNPJ);
		
		
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                btIncluir.setEnabled(false);
                btExcluir.setEnabled(false);
                btAlterar.setEnabled(false);
                btSalvar.setEnabled(true);
              
                btConsultar.setEnabled(false);


                
                situacao = 1;
			}
		});
		btIncluir.setBounds(20, 373, 89, 23);
		getContentPane().add(btIncluir);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Paciente");

                try
                    {
                        pStmt = con.prepareStatement("SELECT CPF, NOME,TO_CHAR(DATANASCIMENTO, 'DD/MM/YYYY'), CIDADE, LOGRADOURO, NUMERO, SEXO, EMPRESAS_CNPJ, COMPLEMENTO FROM PESSOAS WHERE CPF LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                                CodPac = rs.getString(1);
                                tfCPF.setText(rs.getString(1));
                                tfNome.setText(rs.getString(2));
                                tfDataNasc.setText(rs.getString(3));
                                tfCidade.setText(rs.getString(4));
                                tfLogradouro.setText(rs.getString(5));
                                tfNumero.setText(rs.getString(6));
                                tfSexo.setText(rs.getString(7));
                                tfEmpresas_CNPJ.setText(rs.getString(8));
                                tfComplemento.setText(rs.getString(9));
                                situacao = 2;
                                
                                
                              try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM TELEFONE WHERE PESSOAS_CPF LIKE ?");
                                    pStmt.setString(1,retorno);
                                    rs = pStmt.executeQuery();
                                }
                                catch (SQLException ex)
                                {
                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
        
                                } 
                               rs.next();
                                tfTelefone.setText(rs.getString(2));


                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                tfCPF.setEditable(false);
                                tfEmpresas_CNPJ.setEditable(false);
                                
                                btConsultar.setEnabled(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
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
		
		
		btAlterar.setBounds(145, 373, 89, 23);
		getContentPane().add(btAlterar);
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Paciente");

                try
                    {
                        pStmt = con.prepareStatement("SELECT CPF, NOME,TO_CHAR(DATANASCIMENTO, 'DD/MM/YYYY'), CIDADE, LOGRADOURO, NUMERO, SEXO, EMPRESAS_CNPJ, COMPLEMENTO FROM PESSOAS WHERE CPF LIKE ?");
                        pStmt.setString(1,retorno);
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                                CodPac = rs.getString(1);
                                tfCPF.setText(rs.getString(1));
                                tfNome.setText(rs.getString(2));
                                tfDataNasc.setText(rs.getString(3));
                                tfCidade.setText(rs.getString(4));
                                tfLogradouro.setText(rs.getString(5));
                                tfNumero.setText(rs.getString(6));
                                tfSexo.setText(rs.getString(7));
                                tfEmpresas_CNPJ.setText(rs.getString(8));
                                tfComplemento.setText(rs.getString(9));
                                situacao = 3;
                                
                                
                              try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM TELEFONE WHERE PESSOAS_CPF LIKE ?");
                                    pStmt.setString(1,retorno);
                                    rs = pStmt.executeQuery();
                                }
                                catch (SQLException ex)
                                {
                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
        
                                } 
                               rs.next();
                                tfTelefone.setText(rs.getString(2));


                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                tfCPF.setEditable(false);
                                tfEmpresas_CNPJ.setEditable(false);
                                
                                btConsultar.setEnabled(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
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
		
		
		btExcluir.setBounds(279, 373, 89, 23);
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
                            
                                pStmt = con.prepareStatement("SELECT * FROM EMPRESAS WHERE CNPJ = ?");
                                pStmt.setString(1, tfEmpresas_CNPJ.getText());
                                rs = pStmt.executeQuery();
                                    if (!rs.next())
                                       
                                        JOptionPane.showMessageDialog(null, "Empresa nao conveniada. Verifique!\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                    else
                                        {
                                            
                                            if (!rs.getString(3).equals("S"))
                                       
                                                JOptionPane.showMessageDialog(null, " Empresa com covenio inativo. Verifique!\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                            else
                                             {
                                        

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
                                                        pStmt.setString(9, tfEmpresas_CNPJ.getText());
                                                        pStmt.setString(7, tfComplemento.getText());

                                                        pStmt.executeUpdate();
                                                        
                                                        pStmt = con.prepareStatement("INSERT INTO TELEFONE VALUES ( ?, ?)");
                                                        pStmt.setString(1, tfCPF.getText());
                                                        pStmt.setString(2, tfTelefone.getText());
                                                        pStmt.executeUpdate();


                                                        btIncluir.setEnabled(true);
                                                        btExcluir.setEnabled(true);
                                                        btAlterar.setEnabled(true);
                                                        btSalvar.setEnabled(false);
                                                    
                                                        btConsultar.setEnabled(true);
                                                        tfCPF.setText("");
                                                        tfNome.setText("");
                                                        tfDataNasc.setText("");
                                                        tfCidade.setText("");
                                                        tfLogradouro.setText("");
                                                        tfNumero.setText("");
                                                        tfSexo.setText("");
                                                        tfEmpresas_CNPJ.setText("");
                                                        tfComplemento.setText("");
                                                        tfTelefone.setText("");

                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Paciente já cadastrado ou cadastro possui algum campo inválido. Verifique.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }
                                            }
                                        }
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
                                                            
                                pStmt.setString(8, CodPac);
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
                           
                                btConsultar.setEnabled(true);
                                tfCPF.setText("");
                                                        tfNome.setText("");
                                                        tfDataNasc.setText("");
                                                        tfCidade.setText("");
                                                        tfLogradouro.setText("");
                                                        tfNumero.setText("");
                                                        tfSexo.setText("");
                                                        tfEmpresas_CNPJ.setText("");
                                                        tfComplemento.setText("");
                                                        tfTelefone.setText("");
                                tfCPF.setEditable(true);
                                tfEmpresas_CNPJ.setEditable(true);

                            }
                        catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;

                        case 3 :

                             try
                            {
                              pStmt = con.prepareStatement("SELECT * FROM ATENDIMENTO WHERE PESSOAS_CPF LIKE ?");
                                 pStmt.setString(1,CodPac);
                              rs = pStmt.executeQuery();
    
                                if (rs.next())
                                    JOptionPane.showMessageDialog(null, "Paciente possui atendimentos.Nao foi possivel exclusao.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                else
                                    {
                                        try
                                            {

                                                pStmt = con.prepareStatement("DELETE FROM PESSOAS WHERE CPF = ?");
                                                pStmt.setString(1, CodPac);
                                                pStmt.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

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
                                    tfEmpresas_CNPJ.setEditable(true);
                                    tfComplemento.setEditable(true);
                                    tfTelefone.setEditable(true);
                                    btIncluir.setEnabled(true);
                                    btExcluir.setEnabled(true);
                                    btAlterar.setEnabled(true);
                                    btSalvar.setEnabled(false);
                                
                                    btConsultar.setEnabled(true);
                                    tfNome.setText("");
                                    tfCPF.setText("");
                                     tfDataNasc.setText("");
                                   tfCidade.setText("");
                                    tfLogradouro.setText("");
                                    tfNumero.setText("");
                                   tfSexo.setText("");
                                    tfEmpresas_CNPJ.setText("");
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
                        	
                        	btIncluir.setEnabled(true);
                            btExcluir.setEnabled(true);
                            btAlterar.setEnabled(true);
                            btSalvar.setEnabled(false);
                           
                            btConsultar.setEnabled(true);
                            tfNome.setText("");
                            tfCPF.setText("");
                             tfDataNasc.setText("");
                           tfCidade.setText("");
                            tfLogradouro.setText("");
                            tfNumero.setText("");
                           tfSexo.setText("");
                            tfEmpresas_CNPJ.setText("");
                            tfComplemento.setText("");
                            tfTelefone.setText("");
                            btSalvar.setText("Salvar");
                        
                        	
                        break;
                        
                    }
            }
		});
		
		
		btSalvar.setBounds(408, 373, 89, 23);
		getContentPane().add(btSalvar);
		
		
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Paciente");

                try
                {
                    pStmt = con.prepareStatement("SELECT CPF, NOME,TO_CHAR(DATANASCIMENTO, 'DD/MM/YYYY'), CIDADE, LOGRADOURO, NUMERO, SEXO, EMPRESAS_CNPJ, COMPLEMENTO FROM PESSOAS WHERE CPF LIKE ?");
                    pStmt.setString(1,retorno);
                    ResultSet rs = pStmt.executeQuery();
                    if (rs.next())
                        {
                            CodPac = rs.getString(1);
                            tfCPF.setText(rs.getString(1));
                            tfNome.setText(rs.getString(2));
                            tfDataNasc.setText(rs.getString(3));
                            tfCidade.setText(rs.getString(4));
                            tfLogradouro.setText(rs.getString(5));
                            tfNumero.setText(rs.getString(6));
                            tfSexo.setText(rs.getString(7));
                            tfEmpresas_CNPJ.setText(rs.getString(8));
                            tfComplemento.setText(rs.getString(9));
                            situacao = 4;
                            
                            
                          try
                            {
                                pStmt = con.prepareStatement("SELECT * FROM TELEFONE WHERE PESSOAS_CPF LIKE ?");
                                pStmt.setString(1,retorno);
                                rs = pStmt.executeQuery();
                            }
                            catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
    
                            } 
                           rs.next();
                            tfTelefone.setText(rs.getString(2));


                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btSalvar.setText("OK");
                                btIncluir.setEnabled(false);
                                tfCPF.setEditable(false);
                                tfEmpresas_CNPJ.setEditable(false);
                             
                                btConsultar.setEnabled(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                btSalvar.setText("OK");
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
		btConsultar.setBounds(530, 373, 89, 23);
		getContentPane().add(btConsultar);
		
	

	}
}
