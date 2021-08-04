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


public class JanelaCadastroExames extends JInternalFrame {
	private JTextField tfNome;
	int situacao = 0, ID = 1;
	JButton btIncluir = new JButton("Novo");
	JButton btAlterar = new JButton("Alterar");
	JButton btExcluir = new JButton("Excluir");
	JButton btSalvar = new JButton("Salvar");
	JButton btConsultar = new JButton("Consultar");
	PreparedStatement pStmt;
	Connection con;
	String CodPac, CPFPac;
	JTextArea tfDescricao = new JTextArea();
	private JTextField tfIDExame;
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
	public JanelaCadastroExames(Connection conex) {
		setBounds(100, 100, 515, 295);
		getContentPane().setLayout(null);
		con = conex;
		JLabel lblNewLabel = new JLabel("Cadastro de Exames:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel.setBounds(154, 31, 223, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 72, 458, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(122, 14, 41, 14);
		panel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(160, 11, 288, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 14, 41, 14);
		panel.add(lblId);
		
		tfIDExame = new JTextField();
		tfIDExame.setColumns(10);
		tfIDExame.setBounds(35, 11, 50, 20);
		panel.add(tfIDExame);
		tfIDExame.setEditable(false);
		
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Informa\u00E7\u00F5es Principais");
		lblNewLabel_1_1_2.setBounds(21, 58, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Descri\u00E7\u00E3o do Exame:");
		lblNewLabel_1_1_2_1_1_1.setBounds(22, 127, 119, 14);
		getContentPane().add(lblNewLabel_1_1_2_1_1_1);

		
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try
                 {
                     pStmt = con.prepareStatement("SELECT MAX(ID_EXAME) FROM EXAMES ");
                     ResultSet rs = pStmt.executeQuery();
                     if (rs.next())
                    	ID = rs.getInt(1) + 1;
               
				
				
                btIncluir.setEnabled(false);
                btExcluir.setEnabled(false);
                btAlterar.setEnabled(false);
                btSalvar.setEnabled(true);
                btConsultar.setEnabled(false);
                tfIDExame.setText(String.valueOf(ID));


                
                situacao = 1;
                
                 }
			     catch (SQLException ex)
			       {
			                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

			       }
                
			}
		});
		btIncluir.setBounds(32, 222, 89, 23);
		getContentPane().add(btIncluir);
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o ID do Exame:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EXAMES WHERE ID_EXAME = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                        		tfIDExame.setText(rs.getString(1));
                                tfNome.setText(rs.getString(2));
                                tfDescricao.setText(rs.getString(3));

                                situacao = 2;
                                
                               

                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                btConsultar.setEnabled(false);
                                tfIDExame.setEditable(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Exame não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		
		btAlterar.setBounds(122, 222, 89, 23);
		getContentPane().add(btAlterar);
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o ID do Exame:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EXAMES WHERE ID_EXAME = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                        		tfIDExame.setText(rs.getString(1));
                                tfNome.setText(rs.getString(2));
                                tfDescricao.setText(rs.getString(3));

                                situacao = 3;
                                
                               

                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                btConsultar.setEnabled(false);
                                tfIDExame.setEditable(false);


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Exame não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
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
		
		
		btExcluir.setBounds(210, 222, 89, 23);
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
                                    
                                                        pStmt = con.prepareStatement("INSERT INTO EXAMES VALUES ( ?, ?, ?)");
                                                        pStmt.setInt(1, ID);
                                                        pStmt.setString(2, tfNome.getText());
                                                        pStmt.setString(3, tfDescricao.getText());
                                            
                                                        pStmt.executeUpdate();
                                                        
                                                        btIncluir.setEnabled(true);
                                                        btExcluir.setEnabled(true);
                                                        btAlterar.setEnabled(true);
                                                        btSalvar.setEnabled(false);
                                                        btConsultar.setEnabled(true);
                                                        tfIDExame.setText("");
                                                        tfNome.setText("");
                                                        tfDescricao.setText("");


                                                    }
                                                catch (SQLException ex)
                                                    {
                                                        JOptionPane.showMessageDialog(null, "Exame já cadastrado ou cadastro possui algum campo inválido. Verifique", "Erro", JOptionPane.ERROR_MESSAGE);
                                                    }



                        break;

                        case 2:

                            try
                            {
                                
                                pStmt = con.prepareStatement("UPDATE EXAMES SET NOME = ?, DESCRICAO = ? WHERE ID_EXAME = ?");
                                                            
                             
                             pStmt.setInt(3, Integer.parseInt(tfIDExame.getText()));
                             pStmt.setString(1, tfNome.getText());
                             pStmt.setString(2, tfDescricao.getText());
                        
                                
                                pStmt.executeUpdate();
                                btIncluir.setEnabled(true);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                btConsultar.setEnabled(false);
                                tfIDExame.setText("");
                                tfNome.setText("");
                                tfDescricao.setText("");
                 
                                

                            }
                        catch (SQLException ex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;

                        case 3 :

                             try
                            {
                              pStmt = con.prepareStatement("SELECT * FROM OCORRENCIA_EXAMES WHERE EXAMES_ID_EXAME =  ?");
                                 pStmt.setInt(1,Integer.parseInt(tfIDExame.getText()));
                              rs = pStmt.executeQuery();
    
                                if (rs.next())
                                    JOptionPane.showMessageDialog(null, "Exame já está cadastrado para ser realizado. Impossível Exclusão.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                else
                                    {
                                        try
                                            {

                                                pStmt = con.prepareStatement("DELETE FROM EXAMES WHERE ID_Exame = ?");
                                                pStmt.setInt(1,Integer.parseInt(tfIDExame.getText()));
                                                pStmt.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Exame excluído com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                    }
                                    tfNome.setEditable(true);
                                   
                                    btIncluir.setEnabled(true);
                                    btExcluir.setEnabled(true);
                                    btAlterar.setEnabled(true);
                                    btSalvar.setEnabled(false);
                                    btConsultar.setEnabled(true);
                                    tfNome.setText("");
                                    tfIDExame.setText("");
                                    tfDescricao.setText("");
                                    btSalvar.setText("Salvar");

                            }
                            catch (SQLException ex)
                            {
                              JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;
                        case 4:
                        	
                        	tfNome.setEditable(true);
                            
                            btIncluir.setEnabled(true);
                            btExcluir.setEnabled(true);
                            btAlterar.setEnabled(true);
                            btSalvar.setEnabled(false);
                            btConsultar.setEnabled(true);
                            tfNome.setText("");
                            tfIDExame.setText("");
                            tfDescricao.setText("");
                            btSalvar.setText("Salvar");
                        	
                        break;
                        
                    }
            }
		});
		
		
		btSalvar.setBounds(300, 222, 89, 23);
		getContentPane().add(btSalvar);
		
		tfDescricao.setBounds(20, 142, 458, 50);
		getContentPane().add(tfDescricao);
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
                String retorno = JOptionPane.showInputDialog(null,"Digite o ID do Exame:");

                try
                    {
                        pStmt = con.prepareStatement("SELECT * FROM EXAMES WHERE ID_EXAME = ?");
                        pStmt.setInt(1,Integer.parseInt(retorno));
                        ResultSet rs = pStmt.executeQuery();
                        if (rs.next())
                            {
                        		tfIDExame.setText(rs.getString(1));
                                tfNome.setText(rs.getString(2));
                                tfDescricao.setText(rs.getString(3));

                                situacao = 4;
                                
                               

                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
                                btIncluir.setEnabled(false);
                                btConsultar.setEnabled(false);
                                tfIDExame.setEditable(false);
                                btSalvar.setText("OK");


                            }
                        else
                            {
                                JOptionPane.showMessageDialog(null,"Exame não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                btExcluir.setEnabled(true);
                                btAlterar.setEnabled(true);
                                btSalvar.setEnabled(false);
                                btIncluir.setEnabled(true);
                                btConsultar.setEnabled(true);
                                btSalvar.setText("Salvar");


                            }
            }
        catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);

            }

    }
		});
		

		btConsultar.setBounds(389, 222, 89, 23);
		getContentPane().add(btConsultar);
		
	

	}
}