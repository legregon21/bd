import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.*;


class ClinicaAso extends JFrame implements ActionListener {
	JButton Jabrir,Jfechar,Jsalvar,Jsalvarcomo;
	JTextArea Jarea;
	JDesktopPane formpai;
	Statement stmt;
	Connection con;

	JMenuItem menuPaciente;
	JMenuItem menuPacienteCadastro;
	JMenuItem menuPacienteOS;
	JMenuItem menuEmpresa;
	JMenuItem menuEmpresaCadastro;
    JMenuItem menuMedicos;
    JMenuItem menuMedicosCadastro;
	JMenuItem menuAtendimento;
	JMenuItem menuAtendimentoCadastro;
	JMenuItem menuAtendimentoExame;
    JMenuItem menuExames;
    JMenuItem menuExamesCadastro;
	JMenuItem menuSair;
    JMenuItem menuEvoMedicoAtendimento;
    JMenuItem menuEvoMedicoResultadosExames;


	public ClinicaAso(){
		super("Exames Medicos - ASO");

		setBounds(50,50,700,500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		formpai = new JDesktopPane();
		add (formpai);
		setJMenuBar(Menu());
		setVisible(true);
		TestaConexao();

	}

	JMenuBar Menu(){

		JMenuBar menuBarra = new JMenuBar();

        JMenu menuEmpresa = new JMenu("Empresa");
		menuBarra.add(menuEmpresa);
		menuEmpresaCadastro = new JMenuItem("Cadastro de Empresas");
		menuEmpresa.add(menuEmpresaCadastro);

		JMenu menuPaciente = new JMenu("Paciente");
		menuBarra.add(menuPaciente);
		menuPacienteCadastro = new JMenuItem("Cadastro de Paciente");
		menuPaciente.add(menuPacienteCadastro);
		menuPacienteOS = new JMenuItem("Consultar Resultado por Paciente");
		menuPaciente.add(menuPacienteOS);

        JMenu menuMedicos = new JMenu("Medicos");
		menuBarra.add(menuMedicos);
		menuMedicosCadastro = new JMenuItem("Cadastro de Medicos");
		menuMedicos.add(menuMedicosCadastro);

        JMenu menuAtendimento = new JMenu("Atendimento");
		menuBarra.add(menuAtendimento);
		menuAtendimentoCadastro = new JMenuItem("Cadastro de Atendimento");
		menuAtendimento.add(menuAtendimentoCadastro);
		menuAtendimentoExame = new JMenuItem("Cadastro de Exames por Atendimento");
		menuAtendimento.add(menuAtendimentoExame);
        
        JMenu menuExames = new JMenu("Exames");
		menuBarra.add(menuExames);
		menuExamesCadastro = new JMenuItem("Cadastro de Exames");
		menuExames.add(menuExamesCadastro);

        JMenu menuEvoMedica = new JMenu("Evolucao Medica");
		menuBarra.add(menuEvoMedica);
		menuEvoMedicoAtendimento = new JMenuItem("Evoluir Atendimento");
		menuEvoMedica.add(menuEvoMedicoAtendimento);
		menuEvoMedicoResultadosExames = new JMenuItem("Cadastrar Resultados de Exames");
		menuEvoMedica.add(menuEvoMedicoResultadosExames);

		menuSair = new JMenuItem("Sair");
		menuBarra.add(menuSair);


		menuPacienteCadastro.addActionListener(this);
		menuPacienteOS.addActionListener(this);
		menuEmpresa.addActionListener(this);
		menuEmpresaCadastro.addActionListener(this);
		menuAtendimentoCadastro.addActionListener(this);
		menuAtendimentoExame.addActionListener(this);
        menuMedicosCadastro.addActionListener(this);
        menuAtendimentoCadastro.addActionListener(this);
        menuExamesCadastro.addActionListener(this);
        menuEvoMedicoAtendimento.addActionListener(this);
        menuEvoMedicoResultadosExames.addActionListener(this);
		menuSair.addActionListener(this);


		return menuBarra;

	}

	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == menuPacienteCadastro){
			new JanelaPacienteCadastro(formpai, con);
		}
		else if (e.getSource() == menuEmpresaCadastro){
			new JanelaEmpresaCadastro(formpai, con);
		}
		else if (e.getSource() == menuMedicosCadastro){
			new JanelaMedicoCadastro(formpai, con);
		}
		else if (e.getSource() == menuAtendimentoExame){
			new JanelaOSBaixa(formpai, con);
		}
        else if (e.getSource() == menuPacienteOS){
			new JanelaResultadoPaciente(formpai, con);
		}
        else if (e.getSource() == menuAtendimentoCadastro){
			new JanelaAtendimentoCadastro(formpai, con);
		}
        else if (e.getSource() == menuExamesCadastro){
			new JanelaExamesCadastro(formpai, con);
		}
        else if (e.getSource() == menuEvoMedicoAtendimento){
			new JanelaEvolucaoAtendimento(formpai, con);
		}
        else if (e.getSource() == menuEvoMedicoResultadosExames){
			new JanelaResultadoExames(formpai, con);
		}
		else if (e.getSource() == menuSair){
			System.exit(0);
		}

	}

	public static void main(String[] args)  {
		new ClinicaAso();
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



}

class JanelaPacienteCadastro extends JInternalFrame implements ActionListener {
		JDesktopPane formfilho;
		PreparedStatement pStmt, pStmt2;
		JTextField tfNome, tfCPF, tfDataNasc, tfLogradouro, tfNumero, tfSexo, tfEmpresas_CNPJ, tfCidade,tfComplemento;
        JTextField tfTelefone, tfEndereco,tfCodigoCliente;
		JButton btIncluir, btExcluir, btAlterar, btSalvar;
		JPanel painel1 = new JPanel();
		JPanel painel2 = new JPanel();
		JPanel painel3 = new JPanel();
		Connection con;
		int situacao = 0;  // 1 - Incluir | 2 - Excluir | 3 - Alterar
        String CodPac, CPFPac;
		public JanelaPacienteCadastro(JDesktopPane f, Connection conex) {
			super("Cadastro de Pacientes", false,true,false,true);
			con = conex;

				formfilho = f;
				setLayout(new BorderLayout());
				painel3.setLayout(new FlowLayout());
				painel1.setLayout(new FlowLayout());
				painel2.setLayout(new GridLayout(20,1));
				setPreferredSize(new Dimension(500,400));
				painel1.add(new JLabel("CPF: "));
				painel1.add(tfCPF = new JTextField(30));
				painel2.add(new JLabel("Nome: ")); 
				painel2.add(tfNome = new JTextField(100));
				painel2.add(new JLabel("Data de Nascimento: "));
				painel2.add(tfDataNasc = new JTextField(10));
                painel2.add(new JLabel("Telefone: "));
				painel2.add(tfTelefone = new JTextField(10));
				painel2.add(new JLabel("Logradouro: "));
				painel2.add(tfLogradouro = new JTextField(100));
				painel2.add(new JLabel("Numero: "));
				painel2.add(tfNumero = new JTextField(10));
                painel2.add(new JLabel("Complemento: "));
				painel2.add(tfComplemento = new JTextField(20));
                painel2.add(new JLabel("Cidade"));
				painel2.add(tfCidade = new JTextField(50));
                painel2.add(new JLabel("Sexo"));
				painel2.add(tfSexo = new JTextField(1));
                painel2.add(new JLabel("CNPJ Empresa: "));
				painel2.add(tfEmpresas_CNPJ = new JTextField(30));
				add(painel1, BorderLayout.NORTH);
				add(painel2, BorderLayout.CENTER);
				painel3.add(btIncluir = new JButton("Incluir"));
				painel3.add(btAlterar = new JButton("Alterar"));
				painel3.add(btExcluir = new JButton("Excluir"));
				painel3.add(btSalvar = new JButton("Salvar"));
				add(painel3, BorderLayout.SOUTH);
				pack();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setVisible(true);
				formfilho.add(this);

                btExcluir.addActionListener(this);
                btAlterar.addActionListener(this);
				btIncluir.addActionListener(this);
                btSalvar.addActionListener(this);
				tfCodigoCliente.setEditable(false);
				btSalvar.setEnabled(false);

		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btIncluir)
                {

                    

                    btIncluir.setEnabled(false);
                    btExcluir.setEnabled(false);
                    btAlterar.setEnabled(false);
                    btSalvar.setEnabled(true);


                    
                    situacao = 1;
                }


            else if (e.getSource() == btAlterar)
                {
                            String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Paciente");

                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
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


                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"Paciente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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

            else if (e.getSource() == btExcluir)
                {
                            String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Paciente");
                            CodPac = retorno;

                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    //tfNome.setEditable(false);
                                   // tfCPF.setEditable(false);
                                   // tfEndereco.setEditable(false);
                                   // tfTelefone.setEditable(false);

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
                                                            btExcluir.setEnabled(false);
                                                            btAlterar.setEnabled(false);
                                                            btSalvar.setEnabled(true);
                                                            btIncluir.setEnabled(false);
                                                            btSalvar.setText("Confirmar");
                                     

                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
			else if (e.getSource() == btSalvar)
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
                                                            pStmt.setString(7, tfSexo.getText());
                                                            pStmt.setString(8, tfEmpresas_CNPJ.getText());
                                                            pStmt.setString(9, tfComplemento.getText());

                                                            pStmt.executeUpdate();
                                                            
                                                            pStmt = con.prepareStatement("INSERT INTO TELEFONE VALUES ( ?, ?)");
                                                            pStmt.setString(1, tfCPF.getText());
                                                            pStmt.setString(2, tfTelefone.getText());
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
                                                            tfEmpresas_CNPJ.setText("");
                                                            tfComplemento.setText("");
                                                            tfTelefone.setText("");

                                                        }
                                                    catch (SQLException ex)
                                                        {
                                                            JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
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
                        }
                }
            }

        }

class JanelaMedicoCadastro extends JInternalFrame implements ActionListener {
            JDesktopPane formfilho;
            PreparedStatement pStmt, pStmt2;
            JTextField tfCRM, tfNome, tfCPF, tfDataNasc, tfLogradouro, tfNumero, tfSexo, tfEmpresas_CNPJ, tfCidade,tfComplemento, tfTelefone;
            JButton btIncluir, btExcluir, btAlterar, btSalvar;
            JPanel painel1 = new JPanel();
            JPanel painel2 = new JPanel();
            JPanel painel3 = new JPanel();
            Connection con;
            int situacao = 0;  // 1 - Incluir | 2 - Excluir | 3 - Alterar
            String CodPac, CPFPac;
            public JanelaMedicoCadastro(JDesktopPane f, Connection conex) {
                super("Cadastro de Medicos", false,true,false,true);
                con = conex;
    
                    formfilho = f;
                    setLayout(new BorderLayout());
                    painel3.setLayout(new FlowLayout());
                    painel1.setLayout(new FlowLayout());
                    painel2.setLayout(new GridLayout(22,1));
                    setPreferredSize(new Dimension(500,500));
                    painel1.add(new JLabel("CRM: "));
                    painel1.add(tfCRM = new JTextField(30));
                    painel2.add(new JLabel("CPF: "));
                    painel2.add(tfCPF = new JTextField(30));
                    painel2.add(new JLabel("Nome: ")); 
                    painel2.add(tfNome = new JTextField(100));
                    painel2.add(new JLabel("Data de Nascimento: "));
                    painel2.add(tfDataNasc = new JTextField(10));
                    painel2.add(new JLabel("Telefone: "));
                    painel2.add(tfTelefone = new JTextField(10));
                    painel2.add(new JLabel("Logradouro: "));
                    painel2.add(tfLogradouro = new JTextField(100));
                    painel2.add(new JLabel("Numero: "));
                    painel2.add(tfNumero = new JTextField(10));
                    painel2.add(new JLabel("Complemento: "));
                    painel2.add(tfComplemento = new JTextField(20));
                    painel2.add(new JLabel("Cidade"));
                    painel2.add(tfCidade = new JTextField(50));
                    painel2.add(new JLabel("Sexo"));
                    painel2.add(tfSexo = new JTextField(1));
                    painel2.add(new JLabel("CNPJ Empresa: "));
                    painel2.add(tfEmpresas_CNPJ = new JTextField(30));
                    add(painel1, BorderLayout.NORTH);
                    add(painel2, BorderLayout.CENTER);
                    painel3.add(btIncluir = new JButton("Incluir"));
                    painel3.add(btAlterar = new JButton("Alterar"));
                    painel3.add(btExcluir = new JButton("Excluir"));
                    painel3.add(btSalvar = new JButton("Salvar"));
                    add(painel3, BorderLayout.SOUTH);
                    pack();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    setVisible(true);
                    formfilho.add(this);
    
                    btExcluir.addActionListener(this);
                    btAlterar.addActionListener(this);
                    btIncluir.addActionListener(this);
                    btSalvar.addActionListener(this);
                    btSalvar.setEnabled(false);
    
            }
    
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btIncluir)
                    {
    
                        
    
                        btIncluir.setEnabled(false);
                        btExcluir.setEnabled(false);
                        btAlterar.setEnabled(false);
                        btSalvar.setEnabled(true);
    
    
                        
                        situacao = 1;
                    }
    
    
                else if (e.getSource() == btAlterar)
                    {
                                String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Medico");
    
                                try
                                    {
                                        pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
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
    
    
                                            }
                                        else
                                            {
                                                JOptionPane.showMessageDialog(null,"Medico não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
    
                else if (e.getSource() == btExcluir)
                    {
                                String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Medico");
                                CodPac = retorno;
    
                                try
                                    {
                                        pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
                                        pStmt.setString(1,retorno);
                                        ResultSet rs = pStmt.executeQuery();
                                        //tfNome.setEditable(false);
                                       // tfCPF.setEditable(false);
                                       // tfEndereco.setEditable(false);
                                       // tfTelefone.setEditable(false);
    
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
                                                                btExcluir.setEnabled(false);
                                                                btAlterar.setEnabled(false);
                                                                btSalvar.setEnabled(true);
                                                                btIncluir.setEnabled(false);
                                                                btSalvar.setText("Confirmar");
                                         
    
                                            }
                                        else
                                            {
                                                JOptionPane.showMessageDialog(null,"Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
                else if (e.getSource() == btSalvar)
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
                                                                pStmt.setString(7, tfSexo.getText());
                                                                pStmt.setString(8, tfEmpresas_CNPJ.getText());
                                                                pStmt.setString(9, tfComplemento.getText());
    
                                                                pStmt.executeUpdate();
                                                                
                                                                pStmt = con.prepareStatement("INSERT INTO TELEFONE VALUES ( ?, ?)");
                                                                pStmt.setString(1, tfCPF.getText());
                                                                pStmt.setString(2, tfTelefone.getText());
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
                                                                tfEmpresas_CNPJ.setText("");
                                                                tfComplemento.setText("");
                                                                tfTelefone.setText("");
    
                                                            }
                                                        catch (SQLException ex)
                                                            {
                                                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
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
                                            JOptionPane.showMessageDialog(null, "Medico possui atendimentos.Nao foi possivel exclusao.\n", "Erro", JOptionPane.ERROR_MESSAGE);
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
                            }
                    }
                }
    
            }

class JanelaAtendimentoCadastro extends JInternalFrame implements ActionListener {
                JDesktopPane formfilho;
                PreparedStatement pStmt;
                JTextField tfNome, tfDataAtendimento, tfCodigoAtendimento;
                JTextField tfCRM, tfCPF, tfDataNasc, tfLogradouro, tfNumero, tfSexo, tfEmpresas_CNPJ, tfCidade,tfComplemento, tfTelefone;
                JButton btIncluir, btExcluir, btAlterar, btSalvar;
                JPanel painel1 = new JPanel();
                JPanel painel2 = new JPanel();
                JPanel painel3 = new JPanel();
                Connection con;
                int situacao = 0;  // 1 - Incluir | 2 - Excluir | 3 - Alterar
                String CodPac, CPFPac;
                public JanelaAtendimentoCadastro(JDesktopPane f, Connection conex) {
                    super("Cadastro de Atendimentos", false,true,false,true);
                    con = conex;
        
                        formfilho = f;
                        setLayout(new BorderLayout());
                        painel3.setLayout(new FlowLayout());
                        painel1.setLayout(new FlowLayout());
                        painel2.setLayout(new GridLayout(17,1));
                        setPreferredSize(new Dimension(500,400));
                        painel1.add(new JLabel("Codigo do Atendimento"));
                        painel1.add(tfCodigoAtendimento = new JTextField(12));
                        painel2.add(new JLabel("Nome do Paciente: "));
                        painel2.add(tfNome = new JTextField(30));
                        painel2.add(new JLabel("Data do Atendimento ")); 
                        painel2.add(tfDataAtendimento = new JTextField(30));
                        add(painel1, BorderLayout.NORTH);
                        add(painel2, BorderLayout.CENTER);
                        painel3.add(btIncluir = new JButton("Incluir"));
                        painel3.add(btAlterar = new JButton("Alterar"));
                        painel3.add(btExcluir = new JButton("Excluir"));
                        painel3.add(btSalvar = new JButton("Salvar"));
                        add(painel3, BorderLayout.SOUTH);
                        pack();
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        setVisible(true);
                        formfilho.add(this);
        
                        btExcluir.addActionListener(this);
                        btAlterar.addActionListener(this);
                        btIncluir.addActionListener(this);
                        btSalvar.addActionListener(this);
                        btSalvar.setEnabled(false);
        
                }
        
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == btIncluir)
                        {
        
                            
        
                            btIncluir.setEnabled(false);
                            btExcluir.setEnabled(false);
                            btAlterar.setEnabled(false);
                            btSalvar.setEnabled(true);
        
        
                            
                            situacao = 1;
                        }
        
        
                    else if (e.getSource() == btAlterar)
                        {
                                    String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Atendimento");
        
                                    try
                                        {
                                            pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
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
        
                    else if (e.getSource() == btExcluir)
                        {
                                    String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Atendimento");
                                    CodPac = retorno;
        
                                    try
                                        {
                                            pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
                                            pStmt.setString(1,retorno);
                                            ResultSet rs = pStmt.executeQuery();
                                            //tfNome.setEditable(false);
                                           // tfCPF.setEditable(false);
                                           // tfEndereco.setEditable(false);
                                           // tfTelefone.setEditable(false);
        
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
                                                                    btExcluir.setEnabled(false);
                                                                    btAlterar.setEnabled(false);
                                                                    btSalvar.setEnabled(true);
                                                                    btIncluir.setEnabled(false);
                                                                    btSalvar.setText("Confirmar");
                                             
        
                                                }
                                            else
                                                {
                                                    JOptionPane.showMessageDialog(null,"Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
                    else if (e.getSource() == btSalvar)
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
                                                                    pStmt.setString(7, tfSexo.getText());
                                                                    pStmt.setString(8, tfEmpresas_CNPJ.getText());
                                                                    pStmt.setString(9, tfComplemento.getText());
        
                                                                    pStmt.executeUpdate();
                                                                    
                                                                    pStmt = con.prepareStatement("INSERT INTO TELEFONE VALUES ( ?, ?)");
                                                                    pStmt.setString(1, tfCPF.getText());
                                                                    pStmt.setString(2, tfTelefone.getText());
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
                                                                    tfEmpresas_CNPJ.setText("");
                                                                    tfComplemento.setText("");
                                                                    tfTelefone.setText("");
        
                                                                }
                                                            catch (SQLException ex)
                                                                {
                                                                    JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
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
                                                JOptionPane.showMessageDialog(null, "Atendimento possui atendimentos.Nao foi possivel exclusao.\n", "Erro", JOptionPane.ERROR_MESSAGE);
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
                                }
                        }
                    }
        
                }

class JanelaExamesCadastro extends JInternalFrame implements ActionListener {
                    JDesktopPane formfilho;
                    PreparedStatement pStmt;
                    JTextField tfIDExame, tfNome;
                    JTextArea tfDescricao;
                    JTextField tfCRM, tfCPF, tfDataNasc, tfLogradouro, tfNumero, tfSexo, tfEmpresas_CNPJ, tfCidade,tfComplemento, tfTelefone;
                    JButton btIncluir, btExcluir, btAlterar, btSalvar;
                    JPanel painel1 = new JPanel();
                    JPanel painel2 = new JPanel();
                    JPanel painel3 = new JPanel();
                    JPanel painels2 = new JPanel();
                    Connection con;
                    int situacao = 0;  // 1 - Incluir | 2 - Excluir | 3 - Alterar
                    String CodPac, CPFPac;
                    public JanelaExamesCadastro(JDesktopPane f, Connection conex) {
                        super("Cadastro de Exames", false,true,false,true);
                        con = conex;
            
                            formfilho = f;
                            setLayout(new BorderLayout());
                            painel3.setLayout(new FlowLayout());
                            painel1.setLayout(new FlowLayout());
                            painel2.setLayout(new FlowLayout());
                            painels2.setLayout(new BorderLayout());
                            painel2.setLayout(new GridLayout(10,1));
                            setPreferredSize(new Dimension(500,400));
                            painel2.add(new JLabel("=============================================="));
                            painel2.add(new JLabel("		 Dados do Exame:	      "));
                            painel2.add(new JLabel("Id do Exame: "));
                            painel2.add(tfIDExame = new JTextField(30));
                            painel2.add(new JLabel("Nome: "));
                            painel2.add(tfNome = new JTextField(30));
                            painel2.add(new JLabel("=============================================="));
                            painel2.add(new JLabel("Descricacao do Exame: "));
                            painels2.add(painel2, BorderLayout.NORTH);
                            tfDescricao= new JTextArea(10,20);
                            JScrollPane rolagem = new JScrollPane(tfDescricao);
                            painels2.add(rolagem, BorderLayout.SOUTH);
                            add(painel1, BorderLayout.NORTH);
                            add(painels2, BorderLayout.CENTER);
                            painel3.add(btIncluir = new JButton("Incluir"));
                            painel3.add(btAlterar = new JButton("Alterar"));
                            painel3.add(btExcluir = new JButton("Excluir"));
                            painel3.add(btSalvar = new JButton("Salvar"));
                            add(painel3, BorderLayout.SOUTH);
                            pack();
                            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            setVisible(true);
                            formfilho.add(this);
            
                            btExcluir.addActionListener(this);
                            btAlterar.addActionListener(this);
                            btIncluir.addActionListener(this);
                            btSalvar.addActionListener(this);
                            btSalvar.setEnabled(false);
            
                    }
            
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == btIncluir)
                            {
            
                                
            
                                btIncluir.setEnabled(false);
                                btExcluir.setEnabled(false);
                                btAlterar.setEnabled(false);
                                btSalvar.setEnabled(true);
            
            
                                
                                situacao = 1;
                            }
            
            
                        else if (e.getSource() == btAlterar)
                            {
                                        String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Exames");
            
                                        try
                                            {
                                                pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
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
            
            
                                                    }
                                                else
                                                    {
                                                        JOptionPane.showMessageDialog(null,"Exames não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
            
                        else if (e.getSource() == btExcluir)
                            {
                                        String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Exames");
                                        CodPac = retorno;
            
                                        try
                                            {
                                                pStmt = con.prepareStatement("SELECT * FROM PESSOAS WHERE CPF LIKE ?");
                                                pStmt.setString(1,retorno);
                                                ResultSet rs = pStmt.executeQuery();
                                                //tfNome.setEditable(false);
                                               // tfCPF.setEditable(false);
                                               // tfEndereco.setEditable(false);
                                               // tfTelefone.setEditable(false);
            
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
                                                                        btExcluir.setEnabled(false);
                                                                        btAlterar.setEnabled(false);
                                                                        btSalvar.setEnabled(true);
                                                                        btIncluir.setEnabled(false);
                                                                        btSalvar.setText("Confirmar");
                                                 
            
                                                    }
                                                else
                                                    {
                                                        JOptionPane.showMessageDialog(null,"Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
                        else if (e.getSource() == btSalvar)
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
                                                                        pStmt.setString(7, tfSexo.getText());
                                                                        pStmt.setString(8, tfEmpresas_CNPJ.getText());
                                                                        pStmt.setString(9, tfComplemento.getText());
            
                                                                        pStmt.executeUpdate();
                                                                        
                                                                        pStmt = con.prepareStatement("INSERT INTO TELEFONE VALUES ( ?, ?)");
                                                                        pStmt.setString(1, tfCPF.getText());
                                                                        pStmt.setString(2, tfTelefone.getText());
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
                                                                        tfEmpresas_CNPJ.setText("");
                                                                        tfComplemento.setText("");
                                                                        tfTelefone.setText("");
            
                                                                    }
                                                                catch (SQLException ex)
                                                                    {
                                                                        JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
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
                                              pStmt = con.prepareStatement("SELECT * FROM Exames WHERE PESSOAS_CPF LIKE ?");
                                                 pStmt.setString(1,CodPac);
                                              rs = pStmt.executeQuery();
                    
                                                if (rs.next())
                                                    JOptionPane.showMessageDialog(null, "Exames possui Examess.Nao foi possivel exclusao.\n", "Erro", JOptionPane.ERROR_MESSAGE);
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
                                    }
                            }
                        }
            
                    }        

class JanelaResultadoPaciente extends JInternalFrame implements ActionListener
        {

            PreparedStatement pStmt;
            Connection con;
            JDesktopPane formfilho;
            JTextField tfNomeCliente, tfCPFCliente;
            JTextArea tfServico;
            JButton btLocalizar;
            JPanel painel1 = new JPanel();
            JPanel painel2 = new JPanel();
            JPanel painel3 = new JPanel();
            JPanel painels2 = new JPanel();
            JPanel painels3 = new JPanel();
            String CodPac;
            int CodVeiculo, CodOS;
            String quantOS;

            public JanelaResultadoPaciente(JDesktopPane f, Connection conex)
                {
                    super("Ordem de Serviço: ", false,true,false,true);
                    con = conex;
                    formfilho = f;
                    setLayout(new BorderLayout());
                    painel3.setLayout(new FlowLayout());
                    painel1.setLayout(new FlowLayout());
                    painel2.setLayout(new FlowLayout());
                    painels2.setLayout(new BorderLayout());
                    painel2.setLayout(new GridLayout(10,1));
                    setPreferredSize(new Dimension(500,400));
                    painel2.add(new JLabel("=============================================="));
                    painel2.add(new JLabel("		 Dados do Paciente	      "));
                    painel2.add(new JLabel("Nome: "));
                    painel2.add(tfNomeCliente = new JTextField(30));
                    painel2.add(new JLabel("CPF: "));
                    painel2.add(tfCPFCliente = new JTextField(30));
                    painel2.add(new JLabel("=============================================="));
                    painel2.add(new JLabel("Resultado:"));
                    painels2.add(painel2, BorderLayout.NORTH);
                    tfServico = new JTextArea(10,20);
                    JScrollPane rolagem = new JScrollPane(tfServico);
                    painels2.add(rolagem, BorderLayout.SOUTH);
                    add(painel1, BorderLayout.NORTH);
                    add(painels2, BorderLayout.CENTER);
                    painel3.add(btLocalizar = new JButton("Localizar"));
                    add(painel3, BorderLayout.SOUTH);
                    pack();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    setVisible(true);
                    formfilho.add(this);
                    tfNomeCliente.setEnabled(false);
                    tfCPFCliente.setEnabled(false);
                    tfServico.setEditable(false);

                    btLocalizar.addActionListener(this);
                }

            public void actionPerformed(ActionEvent e)
                {
                    if (e.getSource() == btLocalizar)
                        {

                            tfServico.setText(" ");
                            tfCPFCliente.setText(" ");
                            tfNomeCliente.setText(" ");
                            String retorno = JOptionPane.showInputDialog(null,"Digite o CPF do Proprietário");
                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM CLIENTES WHERE CPFCLI LIKE ? ORDER BY CodPac");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    if (rs.next())
                                        {
                                            tfNomeCliente.setText(rs.getString(2));
                                            tfCPFCliente.setText(rs.getString(3));
                                            CodPac = rs.getString(1);
                                            try
                                                {

                                                    pStmt = con.prepareStatement("SELECT CODOS, VALOROS, SITUACAO FROM OS WHERE CodPac = ?");
                                                    pStmt.setString(1,CodPac);
                                                    rs = pStmt.executeQuery();
                                                    while (rs.next())
                                                        {
                                                            quantOS = "Nº: ";
                                                            quantOS += rs.getString(1);
                                                            quantOS += "  | Valor do Serviço: R$";
                                                            quantOS += rs.getString(2);
                                                            if (Objects.equals(rs.getString(3),"P"))
                                                                quantOS +="  | Situação: PENDENTE\n";
                                                            else
                                                                quantOS +="  | Situação: BAIXADA\n";

                                                            tfServico.append(quantOS);

                                                        }
                                                }
                                            catch (SQLException ex)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco. Verifique!\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                }
                                        }
                                    else
                                        JOptionPane.showMessageDialog(null, "Cliente não encontrado!\n", "Erro", JOptionPane.ERROR_MESSAGE);

                                }
                            catch (SQLException ex)
                                {
                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                }

                        }




                }

	}

class JanelaEmpresaCadastro extends JInternalFrame implements ActionListener
        {
            PreparedStatement pStmt;
            Connection con;
            JDesktopPane formfilho;
            JTextField tfCNPJ, tfPlaca, tfModelo, tfAno, tfNome;
            JCheckBox tfConveniada;
            JButton btIncluir, btExcluir, btAlterar, btSalvar;
            JPanel painel1 = new JPanel();
            JPanel painel2 = new JPanel();
            JPanel painel3 = new JPanel();
            int CodPac = 0, situacao = 0, CodVeiculo=0;

		public JanelaEmpresaCadastro(JDesktopPane f, Connection conex)
            {

                super("Cadastro de Empresas", false,true,false,true);
                con = conex;
                
                formfilho = f;
				setLayout(new BorderLayout());
				painel3.setLayout(new FlowLayout());
				painel1.setLayout(new FlowLayout());
				painel2.setLayout(new GridLayout(17,1));
				setPreferredSize(new Dimension(500,400));
				painel1.add(new JLabel("CNPJ: "));
				painel1.add(tfCNPJ = new JTextField(30));
				painel2.add(new JLabel("Nome: ")); 
				painel2.add(tfNome = new JTextField(100));
				painel2.add(new JLabel("Conveniada: "));
				painel2.add(tfConveniada = new JCheckBox());
				add(painel1, BorderLayout.NORTH);
				add(painel2, BorderLayout.CENTER);
				painel3.add(btIncluir = new JButton("Incluir"));
				painel3.add(btAlterar = new JButton("Alterar"));
				painel3.add(btExcluir = new JButton("Excluir"));
				painel3.add(btSalvar = new JButton("Salvar"));
				add(painel3, BorderLayout.SOUTH);
				pack();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setVisible(true);
				formfilho.add(this);

                btSalvar.addActionListener(this);
                btIncluir.addActionListener(this);
                btAlterar.addActionListener(this);
                btExcluir.addActionListener(this);
                btExcluir.setEnabled(true);
                btAlterar.setEnabled(true);
                btSalvar.setEnabled(false);
                btIncluir.setEnabled(true);
            }

            public void actionPerformed(ActionEvent e)
                {
                    if (e.getSource() == btIncluir)
                        {
             
                            btIncluir.setEnabled(false);
                            btExcluir.setEnabled(false);
                            btAlterar.setEnabled(false);
                            btSalvar.setEnabled(true);
                            situacao = 1;
        

                        }
                else if (e.getSource() == btAlterar)
                    {
                            String retorno = JOptionPane.showInputDialog(null,"Digite a Placa do Veículo");

                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM VEICULOS WHERE PLACAVEICULO LIKE ?");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    tfPlaca.setEditable(false);
                                    tfNome.setEditable(false);

                                    if (rs.next())
                                        {
                                            CodVeiculo = Integer.parseInt(rs.getString(1));
                                            CodPac = Integer.parseInt(rs.getString(2));
                                            tfCNPJ.setText(rs.getString(1));
                                            tfAno.setText(rs.getString(3));
                                            tfModelo.setText(rs.getString(4));
                                            tfPlaca.setText(rs.getString(5));
                                            situacao = 2;
                                            btExcluir.setEnabled(false);
                                            btAlterar.setEnabled(false);
                                            btSalvar.setEnabled(true);
                                            btIncluir.setEnabled(false);

                                            try
                                                {
                                                    pStmt = con.prepareStatement("SELECT NOMECLI FROM CLIENTES WHERE CodPac LIKE ?");
                                                    pStmt.setInt(1,CodPac);
                                                    rs = pStmt.executeQuery();
                                                    rs.next();
                                                    tfNome.setText(rs.getString(1));
                                                }
                                            catch (Exception ex)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                }


                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"Veiculo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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

            else if (e.getSource() == btExcluir)
                {
                            String retorno = JOptionPane.showInputDialog(null,"Digite a placa do Veículo ");


                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM VEICULOS WHERE PLACAVEICULO LIKE ?");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    tfNome.setEditable(false);
                                    tfPlaca.setEditable(false);
                                    tfAno.setEditable(false);
                                    tfModelo.setEditable(false);

                                    if (rs.next())
                                        {
                                            CodVeiculo = Integer.parseInt(rs.getString(1));
                                            CodPac = Integer.parseInt(rs.getString(2));
                                            tfCNPJ.setText(rs.getString(1));
                                            tfAno.setText(rs.getString(3));
                                            tfModelo.setText(rs.getString(4));
                                            tfPlaca.setText(rs.getString(5));
                                            situacao = 3;
                                            btExcluir.setEnabled(false);
                                            btAlterar.setEnabled(false);
                                            btSalvar.setEnabled(true);
                                            btIncluir.setEnabled(false);
                                            btSalvar.setText("Confirmar");

                                            try
                                                {
                                                    pStmt = con.prepareStatement("SELECT NOMECLI FROM CLIENTES WHERE CodPac LIKE ?");
                                                    pStmt.setInt(1,CodPac);
                                                    rs = pStmt.executeQuery();
                                                    rs.next();
                                                    tfNome.setText(rs.getString(1));
                                                }
                                            catch (Exception ex)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                }

                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"Veiculo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
            else if (e.getSource() == btSalvar)
                        {
                            switch (situacao)
                                {
                                    case 1:
                                        try
                                            {
                                                pStmt = con.prepareStatement("INSERT INTO VEICULOS VALUES(?, ?, ?, ?, ?)");
                                                pStmt.setInt(1, CodVeiculo);
                                                pStmt.setInt(2, CodPac);
                                                pStmt.setString(3, tfAno.getText());
                                                pStmt.setString(4, tfModelo.getText());
                                                pStmt.setString(5, tfPlaca.getText());
                                                pStmt.executeUpdate();
                                                btIncluir.setEnabled(true);
                                                btExcluir.setEnabled(true);
                                                btAlterar.setEnabled(true);
                                                btSalvar.setEnabled(false);
                                                tfAno.setText("");
                                                tfModelo.setText("");
                                                tfPlaca.setText("");
                                                tfNome.setText("");
                                                tfCNPJ.setText("");

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                    break;

                                    case 2:

                                        try
                                            {

                                                pStmt = con.prepareStatement("UPDATE VEICULOS SET MODELOVEICULO = ?, ANOVEICULO = ? WHERE CODVEICULO = ?");
                                                pStmt.setString(1, tfModelo.getText());
                                                pStmt.setString(2, tfAno.getText());
                                                pStmt.setInt(3, CodVeiculo);
                                                pStmt.executeUpdate();

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                        btIncluir.setEnabled(true);
                                        btExcluir.setEnabled(true);
                                        btAlterar.setEnabled(true);
                                        btSalvar.setEnabled(false);
                                        tfCNPJ.setText("");
                                        tfModelo.setText("");
                                        tfAno.setText("");
                                        tfPlaca.setText("");
                                        tfNome.setText("");
                                        tfNome.setEditable(true);
                                        tfPlaca.setEditable(true);

                                    break;

                                    case 3 :

                                        try
                                            {
                                                pStmt = con.prepareStatement("SELECT * FROM OS WHERE CODVEICULO = ?");
                                                pStmt.setInt(1, CodVeiculo);
                                                ResultSet rs = pStmt.executeQuery();
                                                if (rs.next())
                                                        JOptionPane.showMessageDialog(null, "Veiculo possui OS cadastrada. Não foi possível excluir.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                                else
                                                    {
                                                        try
                                                            {

                                                                pStmt = con.prepareStatement("DELETE FROM VEICULOS WHERE CODVEICULO = ?");
                                                                pStmt.setInt(1, CodVeiculo);
                                                                pStmt.executeUpdate();
                                                                JOptionPane.showMessageDialog(null, "Veiculo excluído com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                                            }
                                                        catch (SQLException ex)
                                                                {
                                                                    JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }

                                                    }

                                                tfModelo.setEditable(true);
                                                tfPlaca.setEditable(true);
                                                tfAno.setEditable(true);
                                                tfNome.setEditable(true);
                                                btIncluir.setEnabled(true);
                                                btExcluir.setEnabled(true);
                                                btAlterar.setEnabled(true);
                                                btSalvar.setEnabled(false);
                                                tfCNPJ.setText("");
                                                tfNome.setText("");
                                                tfAno.setText("");
                                                tfModelo.setText("");
                                                tfPlaca.setText("");
                                                btSalvar.setText("Salvar");

                                }
                                catch (SQLException ex)
                                {
                                  JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                }

                            break;
                                }

                        }
                }


	}

class JanelaResultadoExames extends JInternalFrame implements ActionListener
    {
        PreparedStatement pStmt;
        Connection con;
        JDesktopPane formfilho;
        JTextField tfNomePaciente, tfDataExame, tfNomeExame; 
        JTextField tfCNPJ, tfAno, tfModelo, tfPlaca, tfNome;
        JCheckBox tfLaudo; 
        JCheckBox tfConveniada;
        JButton btIncluir, btExcluir, btAlterar, btSalvar;
        JPanel painel1 = new JPanel();
        JPanel painel2 = new JPanel();
        JPanel painel3 = new JPanel();
        int CodPac = 0, situacao = 0, CodVeiculo=0;

    public JanelaResultadoExames(JDesktopPane f, Connection conex)
        {

            super("Resultado de Exames", false,true,false,true);
            con = conex;
            
            formfilho = f;
            setLayout(new BorderLayout());
            painel3.setLayout(new FlowLayout());
            painel1.setLayout(new FlowLayout());
            painel2.setLayout(new GridLayout(17,1));
            setPreferredSize(new Dimension(500,400));
            painel1.add(new JLabel("Nome do Paciente "));
            painel1.add(tfNomePaciente = new JTextField(30));
            painel2.add(new JLabel("Nome do Exame: ")); 
            painel2.add(tfNomeExame = new JTextField(100));
            painel2.add(new JLabel("Data do Exame: ")); 
            painel2.add(tfDataExame = new JTextField(100));
            painel2.add(new JLabel("Apto: "));
            painel2.add(tfLaudo = new JCheckBox());
            add(painel1, BorderLayout.NORTH);
            add(painel2, BorderLayout.CENTER);
            painel3.add(btIncluir = new JButton("Incluir"));
            painel3.add(btAlterar = new JButton("Alterar"));
            painel3.add(btExcluir = new JButton("Excluir"));
            painel3.add(btSalvar = new JButton("Salvar"));
            add(painel3, BorderLayout.SOUTH);
            pack();
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            formfilho.add(this);

            btSalvar.addActionListener(this);
            btIncluir.addActionListener(this);
            btAlterar.addActionListener(this);
            btExcluir.addActionListener(this);
            btExcluir.setEnabled(true);
            btAlterar.setEnabled(true);
            btSalvar.setEnabled(false);
            btIncluir.setEnabled(true);
        }

        public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == btIncluir)
                    {
         
                        btIncluir.setEnabled(false);
                        btExcluir.setEnabled(false);
                        btAlterar.setEnabled(false);
                        btSalvar.setEnabled(true);
                        situacao = 1;
    

                    }
            else if (e.getSource() == btAlterar)
                {
                        String retorno = JOptionPane.showInputDialog(null,"Digite a Placa do Veículo");

                        try
                            {
                                pStmt = con.prepareStatement("SELECT * FROM VEICULOS WHERE PLACAVEICULO LIKE ?");
                                pStmt.setString(1,retorno);
                                ResultSet rs = pStmt.executeQuery();
                                tfPlaca.setEditable(false);
                                tfNome.setEditable(false);

                                if (rs.next())
                                    {
                                        CodVeiculo = Integer.parseInt(rs.getString(1));
                                        CodPac = Integer.parseInt(rs.getString(2));
                                        tfCNPJ.setText(rs.getString(1));
                                        tfAno.setText(rs.getString(3));
                                        tfModelo.setText(rs.getString(4));
                                        tfPlaca.setText(rs.getString(5));
                                        situacao = 2;
                                        btExcluir.setEnabled(false);
                                        btAlterar.setEnabled(false);
                                        btSalvar.setEnabled(true);
                                        btIncluir.setEnabled(false);

                                        try
                                            {
                                                pStmt = con.prepareStatement("SELECT NOMECLI FROM CLIENTES WHERE CodPac LIKE ?");
                                                pStmt.setInt(1,CodPac);
                                                rs = pStmt.executeQuery();
                                                rs.next();
                                                tfNome.setText(rs.getString(1));
                                            }
                                        catch (Exception ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }


                                    }
                                else
                                    {
                                        JOptionPane.showMessageDialog(null,"Veiculo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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

        else if (e.getSource() == btExcluir)
            {
                        String retorno = JOptionPane.showInputDialog(null,"Digite a placa do Veículo ");


                        try
                            {
                                pStmt = con.prepareStatement("SELECT * FROM VEICULOS WHERE PLACAVEICULO LIKE ?");
                                pStmt.setString(1,retorno);
                                ResultSet rs = pStmt.executeQuery();
                                tfNome.setEditable(false);
                                tfPlaca.setEditable(false);
                                tfAno.setEditable(false);
                                tfModelo.setEditable(false);

                                if (rs.next())
                                    {
                                        CodVeiculo = Integer.parseInt(rs.getString(1));
                                        CodPac = Integer.parseInt(rs.getString(2));
                                        tfCNPJ.setText(rs.getString(1));
                                        tfAno.setText(rs.getString(3));
                                        tfModelo.setText(rs.getString(4));
                                        tfPlaca.setText(rs.getString(5));
                                        situacao = 3;
                                        btExcluir.setEnabled(false);
                                        btAlterar.setEnabled(false);
                                        btSalvar.setEnabled(true);
                                        btIncluir.setEnabled(false);
                                        btSalvar.setText("Confirmar");

                                        try
                                            {
                                                pStmt = con.prepareStatement("SELECT NOMECLI FROM CLIENTES WHERE CodPac LIKE ?");
                                                pStmt.setInt(1,CodPac);
                                                rs = pStmt.executeQuery();
                                                rs.next();
                                                tfNome.setText(rs.getString(1));
                                            }
                                        catch (Exception ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                    }
                                else
                                    {
                                        JOptionPane.showMessageDialog(null,"Veiculo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
        else if (e.getSource() == btSalvar)
                    {
                        switch (situacao)
                            {
                                case 1:
                                    try
                                        {
                                            pStmt = con.prepareStatement("INSERT INTO VEICULOS VALUES(?, ?, ?, ?, ?)");
                                            pStmt.setInt(1, CodVeiculo);
                                            pStmt.setInt(2, CodPac);
                                            pStmt.setString(3, tfAno.getText());
                                            pStmt.setString(4, tfModelo.getText());
                                            pStmt.setString(5, tfPlaca.getText());
                                            pStmt.executeUpdate();
                                            btIncluir.setEnabled(true);
                                            btExcluir.setEnabled(true);
                                            btAlterar.setEnabled(true);
                                            btSalvar.setEnabled(false);
                                            tfAno.setText("");
                                            tfModelo.setText("");
                                            tfPlaca.setText("");
                                            tfNome.setText("");
                                            tfCNPJ.setText("");

                                        }
                                    catch (SQLException ex)
                                        {
                                            JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                break;

                                case 2:

                                    try
                                        {

                                            pStmt = con.prepareStatement("UPDATE VEICULOS SET MODELOVEICULO = ?, ANOVEICULO = ? WHERE CODVEICULO = ?");
                                            pStmt.setString(1, tfModelo.getText());
                                            pStmt.setString(2, tfAno.getText());
                                            pStmt.setInt(3, CodVeiculo);
                                            pStmt.executeUpdate();

                                        }
                                    catch (SQLException ex)
                                        {
                                            JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                        }

                                    btIncluir.setEnabled(true);
                                    btExcluir.setEnabled(true);
                                    btAlterar.setEnabled(true);
                                    btSalvar.setEnabled(false);
                                    tfCNPJ.setText("");
                                    tfModelo.setText("");
                                    tfAno.setText("");
                                    tfPlaca.setText("");
                                    tfNome.setText("");
                                    tfNome.setEditable(true);
                                    tfPlaca.setEditable(true);

                                break;

                                case 3 :

                                    try
                                        {
                                            pStmt = con.prepareStatement("SELECT * FROM OS WHERE CODVEICULO = ?");
                                            pStmt.setInt(1, CodVeiculo);
                                            ResultSet rs = pStmt.executeQuery();
                                            if (rs.next())
                                                    JOptionPane.showMessageDialog(null, "Veiculo possui OS cadastrada. Não foi possível excluir.\n", "Erro", JOptionPane.ERROR_MESSAGE);
                                            else
                                                {
                                                    try
                                                        {

                                                            pStmt = con.prepareStatement("DELETE FROM VEICULOS WHERE CODVEICULO = ?");
                                                            pStmt.setInt(1, CodVeiculo);
                                                            pStmt.executeUpdate();
                                                            JOptionPane.showMessageDialog(null, "Veiculo excluído com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                                        }
                                                    catch (SQLException ex)
                                                            {
                                                                JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                            }

                                                }

                                            tfModelo.setEditable(true);
                                            tfPlaca.setEditable(true);
                                            tfAno.setEditable(true);
                                            tfNome.setEditable(true);
                                            btIncluir.setEnabled(true);
                                            btExcluir.setEnabled(true);
                                            btAlterar.setEnabled(true);
                                            btSalvar.setEnabled(false);
                                            tfCNPJ.setText("");
                                            tfNome.setText("");
                                            tfAno.setText("");
                                            tfModelo.setText("");
                                            tfPlaca.setText("");
                                            btSalvar.setText("Salvar");

                            }
                            catch (SQLException ex)
                            {
                              JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                        break;
                            }

                    }
            }


}

class JanelaOSCadastro extends JInternalFrame implements ActionListener
        {

            PreparedStatement pStmt;
            Connection con;
            JDesktopPane formfilho;
            JTextField tfCodigoOS, tfValor, tfNomeCliente, tfCPFCliente, tfModelo;
            JLabel lblSituacao;
            JTextArea tfServico;
            JButton btIncluir, btExcluir, btAlterar, btSalvar;
            JPanel painel1 = new JPanel();
            JPanel painel2 = new JPanel();
            JPanel painel3 = new JPanel();
            JPanel painels2 = new JPanel();
            JPanel painels3 = new JPanel();
            int CodPac, CodVeiculo, CodOS, situacao = 0;

            public JanelaOSCadastro(JDesktopPane f, Connection conex)
                {
                    super("Ordem de Serviço: ", false,true,false,true);
                    con = conex;
                    formfilho = f;
                    setLayout(new BorderLayout());
                    painel3.setLayout(new FlowLayout());
                    painel1.setLayout(new FlowLayout());
                    painel2.setLayout(new FlowLayout());
                    painels2.setLayout(new BorderLayout());
                    painel2.setLayout(new GridLayout(10,1));
                    setPreferredSize(new Dimension(500,400));
                    painel1.add(new JLabel("OS nº: "));
                    painel1.add(tfCodigoOS = new JTextField(5));
                    painel1.add(new JLabel("Valor: "));
                    painel1.add(tfValor = new JTextField(10));
                    painel1.add(lblSituacao = new JLabel(" "));
                    painel2.add(new JLabel("=============================================="));
                    painel2.add(new JLabel("		 Dados do Cliente	      "));
                    painel2.add(new JLabel("Nome: "));
                    painel2.add(tfNomeCliente = new JTextField(30));
                    painel2.add(new JLabel("CPF: "));
                    painel2.add(tfCPFCliente = new JTextField(30));
                    painel2.add(new JLabel("Veiculo: "));
                    painel2.add(tfModelo = new JTextField(10));
                    painel2.add(new JLabel("=============================================="));
                    painel2.add(new JLabel("Serviço a ser executado: "));
                    painels2.add(painel2, BorderLayout.NORTH);
                    tfServico = new JTextArea(5,20);
                    JScrollPane rolagem = new JScrollPane(tfServico);
                    painels2.add(rolagem, BorderLayout.SOUTH);
                    add(painel1, BorderLayout.NORTH);
                    add(painels2, BorderLayout.CENTER);
                    painel3.add(btIncluir = new JButton("Incluir"));
                    painel3.add(btAlterar = new JButton("Alterar"));
                    painel3.add(btExcluir = new JButton("Excluir"));
                    painel3.add(btSalvar = new JButton("Salvar"));
                    add(painel3, BorderLayout.SOUTH);
                    pack();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    setVisible(true);
                    formfilho.add(this);
                    tfModelo.setEnabled(false);
                    tfNomeCliente.setEnabled(false);
                    tfCPFCliente.setEnabled(false);
                    tfCodigoOS.setEnabled(false);

                    btIncluir.addActionListener(this);
                    btAlterar.addActionListener(this);
                    btExcluir.addActionListener(this);
                    btSalvar.addActionListener(this);
                }

            public void actionPerformed(ActionEvent e)
                {
                    if (e.getSource() == btIncluir)
                        {
                            String retorno = JOptionPane.showInputDialog(null,"Digite a Placa do Veículo: ");

                            try
                                {

                                    pStmt = con.prepareStatement("SELECT CODVEICULO, CodPac, MODELOVEICULO FROM VEICULOS WHERE PLACAVEICULO LIKE ?");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    if (rs.next())
                                        {
                                            CodVeiculo = Integer.parseInt(rs.getString(1));
                                            CodPac = Integer.parseInt(rs.getString(2));
                                            tfModelo.setText(rs.getString(3));
                                            btExcluir.setEnabled(false);
                                            btAlterar.setEnabled(false);
                                            btSalvar.setEnabled(true);
                                            btIncluir.setEnabled(false);

                                            try
                                                {
                                                    pStmt = con.prepareStatement("SELECT CodPac,NOMECLI,CPFCLI FROM CLIENTES WHERE CodPac LIKE ?");
                                                    pStmt.setInt(1,CodPac);
                                                    rs = pStmt.executeQuery();
                                                    rs.next();
                                                    tfNomeCliente.setText(rs.getString(2));
                                                    tfCPFCliente.setText(rs.getString(3));

                                                }
                                            catch (SQLException ex)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                }

                                            try
                                                {
                                                    pStmt = con.prepareStatement("SELECT * FROM OS");
                                                    rs = pStmt.executeQuery();
                                                    CodOS = 1;
                                                    while (rs.next())
                                                    ++CodOS;


                                                    try
                                                        {
                                                            pStmt.close();
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
                                            lblSituacao.setText("Situação: PENDENTE");
                                            tfCodigoOS.setText(Integer.toString(CodOS));
                                            situacao = 1;

                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"Veiculo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            tfNomeCliente.setText(" ");
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
                else if (e.getSource() == btAlterar)
                    {
                            String retorno = JOptionPane.showInputDialog(null,"Digite o Código da OS: ");

                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM OS WHERE CODOS = ?");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    tfModelo.setEditable(false);
                                    tfNomeCliente.setEditable(false);
                                    tfCPFCliente.setEditable(false);

                                    if (rs.next())
                                        {
                                            if (Objects.equals(rs.getString(6),"B"))
                                                JOptionPane.showMessageDialog(null, "Essa OS não pode ser alterada pois já foi baixada! \n", "Erro", JOptionPane.ERROR_MESSAGE);
                                            else
                                                    {
                                                        CodOS = Integer.parseInt(rs.getString(1));
                                                        CodPac = Integer.parseInt(rs.getString(2));
                                                        CodVeiculo = Integer.parseInt(rs.getString(3));
                                                        tfCodigoOS.setText(rs.getString(1));
                                                        tfServico.setText(rs.getString(4));
                                                        tfValor.setText(rs.getString(5));
                                                        situacao = 2;
                                                        btExcluir.setEnabled(false);
                                                        btAlterar.setEnabled(false);
                                                        btSalvar.setEnabled(true);
                                                        btIncluir.setEnabled(false);

                                                        try
                                                            {
                                                                pStmt = con.prepareStatement("SELECT * FROM CLIENTES WHERE CodPac = ?");
                                                                pStmt.setInt(1,CodPac);
                                                                rs = pStmt.executeQuery();
                                                                rs.next();
                                                                tfNomeCliente.setText(rs.getString(1));
                                                                tfCPFCliente.setText(rs.getString(2));
                                                            }
                                                        catch (Exception ex)
                                                            {
                                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados Aqui. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                            }

                                                        try
                                                            {
                                                                pStmt = con.prepareStatement("SELECT MODELOVEICULO FROM VEICULOS WHERE CODVEICULO = ?");
                                                                pStmt.setInt(1,CodVeiculo);
                                                                rs = pStmt.executeQuery();
                                                                rs.next();
                                                                tfModelo.setText(rs.getString(1));
                                                            }
                                                        catch (Exception ex)
                                                            {
                                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                            }



                                                        }
                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"OS não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
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

                else if (e.getSource() == btExcluir)
                    {
                            String retorno = JOptionPane.showInputDialog(null,"Digite o Código da OS: ");

                            try
                                {
                                    pStmt = con.prepareStatement("SELECT * FROM OS WHERE CODOS = ?");
                                    pStmt.setString(1,retorno);
                                    ResultSet rs = pStmt.executeQuery();
                                    tfModelo.setEditable(false);
                                    tfNomeCliente.setEditable(false);
                                    tfCPFCliente.setEditable(false);
                                    tfValor.setEditable(false);
                                    tfServico.setEditable(false);
                                    btSalvar.setText("Confirmar");

                                    if (rs.next())
                                        {
                                            if (Objects.equals(rs.getString(6),"B"))
                                                JOptionPane.showMessageDialog(null, "Essa OS não pode ser excluida pois já foi baixada! \n", "Erro", JOptionPane.ERROR_MESSAGE);
                                            else
                                                    {
                                                        CodOS = Integer.parseInt(rs.getString(1));
                                                        CodPac = Integer.parseInt(rs.getString(2));
                                                        CodVeiculo = Integer.parseInt(rs.getString(3));
                                                        tfCodigoOS.setText(rs.getString(1));
                                                        tfServico.setText(rs.getString(4));
                                                        tfValor.setText(rs.getString(5));
                                                        situacao = 3;
                                                        btExcluir.setEnabled(false);
                                                        btAlterar.setEnabled(false);
                                                        btSalvar.setEnabled(true);
                                                        btIncluir.setEnabled(false);

                                                        try
                                                            {
                                                                pStmt = con.prepareStatement("SELECT * FROM CLIENTES WHERE CodPac = ?");
                                                                pStmt.setInt(1,CodPac);
                                                                rs = pStmt.executeQuery();
                                                                rs.next();
                                                                tfNomeCliente.setText(rs.getString(1));
                                                                tfCPFCliente.setText(rs.getString(2));
                                                            }
                                                        catch (Exception ex)
                                                            {
                                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados Aqui. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                            }

                                                        try
                                                            {
                                                                pStmt = con.prepareStatement("SELECT MODELOVEICULO FROM VEICULOS WHERE CODVEICULO = ?");
                                                                pStmt.setInt(1,CodVeiculo);
                                                                rs = pStmt.executeQuery();
                                                                rs.next();
                                                                tfModelo.setText(rs.getString(1));
                                                            }
                                                        catch (Exception ex)
                                                            {
                                                                JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                            }



                                                        }
                                        }
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"OS não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
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
            else if (e.getSource() == btSalvar)
                {
                            switch (situacao)
                                {
                                    case 1:
                                        try
                                            {
                                                pStmt = con.prepareStatement("INSERT INTO OS VALUES(?, ?, ?, ?, ?, ?)");
                                                pStmt.setInt(1, CodOS);
                                                pStmt.setInt(2, CodPac);
                                                pStmt.setInt(3, CodVeiculo);
                                                pStmt.setString(4, tfServico.getText());
                                                pStmt.setFloat(5, Float.parseFloat(tfValor.getText()));
                                                pStmt.setString(6, "P");
                                                pStmt.executeUpdate();
                                                btIncluir.setEnabled(true);
                                                btExcluir.setEnabled(true);
                                                btAlterar.setEnabled(true);
                                                btSalvar.setEnabled(false);
                                                lblSituacao.setText(" ");
                                                tfModelo.setText("");
                                                tfCodigoOS.setText("");
                                                tfCPFCliente.setText("");
                                                tfNomeCliente.setText("");
                                                tfValor.setText("");
                                                tfServico.setText("");

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }
                                    break;

                                    case 2:

                                        try
                                            {

                                                pStmt = con.prepareStatement("UPDATE OS SET SERVICOOS = ?, VALOROS = ? WHERE CODOS = ?");
                                                pStmt.setString(1, tfServico.getText());
                                                pStmt.setFloat(2, Float.parseFloat(tfValor.getText()));
                                                pStmt.setInt(3, CodOS);
                                                pStmt.executeUpdate();

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                                        btIncluir.setEnabled(true);
                                        btExcluir.setEnabled(true);
                                        btAlterar.setEnabled(true);
                                        btSalvar.setEnabled(false);
                                        tfCodigoOS.setText("");
                                        tfNomeCliente.setText("");
                                        tfCPFCliente.setText("");
                                        tfValor.setText("");
                                        tfServico.setText("");
                                        tfModelo.setText("");
                                        tfValor.setEditable(true);
                                        tfServico.setEditable(true);

                                    break;

                                    case 3 :

                                                        try
                                                            {

                                                                pStmt = con.prepareStatement("DELETE FROM OS WHERE CODOS = ?");
                                                                pStmt.setInt(1, CodOS);
                                                                pStmt.executeUpdate();
                                                                JOptionPane.showMessageDialog(null, "Ordem de Serviço excluída com sucesso!\n", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                                            }
                                                        catch (SQLException ex)
                                                                {
                                                                    JOptionPane.showMessageDialog(null, "Erro ao Excluir. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }

                                                tfServico.setEditable(true);
                                                tfValor.setEditable(true);
                                                btIncluir.setEnabled(true);
                                                btExcluir.setEnabled(true);
                                                btAlterar.setEnabled(true);
                                                btSalvar.setEnabled(false);
                                                tfCodigoOS.setText("");
                                                tfNomeCliente.setText("");
                                                tfCPFCliente.setText("");
                                                tfServico.setText("");
                                                tfValor.setText("");
                                                tfModelo.setText("");
                                                btSalvar.setText("Salvar");


                            break;
                                }

                        }
                }

	}

class JanelaEvolucaoAtendimento extends JInternalFrame implements ActionListener
        {

            PreparedStatement pStmt;
            Connection con;
            JDesktopPane formfilho;
            JTextField tfCodigoOS, tfValor, tfNomeCliente, tfCPFCliente, tfModelo;
            JLabel lblSituacao;
            JTextArea tfServico;
            JButton btLocalizar, btBaixar, btExcluir, btAlterar;
            JPanel painel1 = new JPanel();
            JPanel painel2 = new JPanel();
            JPanel painel3 = new JPanel();
            JPanel painels2 = new JPanel();
            JPanel painels3 = new JPanel();
            int CodPac, CodVeiculo, CodOS;

            public JanelaEvolucaoAtendimento(JDesktopPane f, Connection conex)
                {
                    super("Evolução do Atendimento: ", false,true,false,true);
                    con = conex;
                    formfilho = f;
                    setLayout(new BorderLayout());
                    painel3.setLayout(new FlowLayout());
                    painel1.setLayout(new FlowLayout());
                    painel2.setLayout(new FlowLayout());
                    painels2.setLayout(new BorderLayout());
                    painel2.setLayout(new GridLayout(10,1));
                    setPreferredSize(new Dimension(500,400));
                    painel1.add(new JLabel("Codigo do Atendimento: "));
                    painel1.add(tfCodigoOS = new JTextField(5));
                    painel2.add(new JLabel("=============================================="));
                    painel2.add(new JLabel("		 Dados do Paciente	      "));
                    painel2.add(new JLabel("Nome: "));
                    painel2.add(tfNomeCliente = new JTextField(30));
                    painel2.add(new JLabel("=============================================="));
                    painel2.add(new JLabel("Descricão do Atendimento: "));
                    painels2.add(painel2, BorderLayout.NORTH);
                    tfServico = new JTextArea(5,20);
                    JScrollPane rolagem = new JScrollPane(tfServico);
                    painels2.add(rolagem, BorderLayout.SOUTH);
                    add(painel1, BorderLayout.NORTH);
                    add(painels2, BorderLayout.CENTER);
                    painel3.add(btLocalizar = new JButton("Localizar"));
                    painel3.add(btBaixar = new JButton("Baixar"));
                    add(painel3, BorderLayout.SOUTH);
                    pack();
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    setVisible(true);
                    formfilho.add(this);
                    tfModelo.setEnabled(false);
                    tfNomeCliente.setEnabled(false);
                    tfCPFCliente.setEnabled(false);
                    tfCodigoOS.setEnabled(false);
                    tfValor.setEnabled(false);
                    tfServico.setEnabled(false);

                    btLocalizar.addActionListener(this);
                    btBaixar.addActionListener(this);

                }

            public void actionPerformed(ActionEvent e)
                {
                    if (e.getSource() == btLocalizar)
                        {
                            String retorno = JOptionPane.showInputDialog(null,"Digite o código da OS: ");

                            try
                                {

                                    pStmt = con.prepareStatement("SELECT * FROM OS WHERE CODOS = ?");
                                    pStmt.setInt(1,Integer.parseInt(retorno));
                                    ResultSet rs = pStmt.executeQuery();
                                    if (rs.next())
                                        {
                                                    CodVeiculo = Integer.parseInt(rs.getString(3));
                                                    CodPac = Integer.parseInt(rs.getString(2));
                                                    CodOS = Integer.parseInt(rs.getString(1));
                                                    tfCodigoOS.setText(rs.getString(1));
                                                    tfModelo.setText(rs.getString(3));
                                                    tfServico.setText(rs.getString(4));
                                                    tfValor.setText(rs.getString(5));
                                                    if (Objects.equals(rs.getString(6),"P"))
                                                        {
                                                            btLocalizar.setEnabled(false);
                                                            btBaixar.setEnabled(true);
                                                            lblSituacao.setText("Situação: PENDENTE");
                                                        }
                                                    else
                                                        {
                                                            btLocalizar.setEnabled(true);
                                                            btBaixar.setEnabled(false);
                                                            lblSituacao.setText("Situação: BAIXADA");
                                                            JOptionPane.showMessageDialog(null, "OS já foi Baixada.\n", "Erro", JOptionPane.ERROR_MESSAGE);

                                                        }
                                                    try
                                                        {
                                                            pStmt = con.prepareStatement("SELECT CodPac,NOMECLI,CPFCLI FROM CLIENTES WHERE CodPac LIKE ?");
                                                            pStmt.setInt(1,CodPac);
                                                            rs = pStmt.executeQuery();
                                                            rs.next();
                                                            tfNomeCliente.setText(rs.getString(2));
                                                            tfCPFCliente.setText(rs.getString(3));

                                                        }
                                                    catch (SQLException ex)
                                                        {
                                                            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                                        }

                                                    try
                                                        {
                                                            pStmt = con.prepareStatement("SELECT CODVEICULO,MODELOVEICULO FROM VEICULOS WHERE CODVEICULO = ?");
                                                            pStmt.setInt(1,CodVeiculo);
                                                            rs = pStmt.executeQuery();
                                                            rs.next();
                                                            tfModelo.setText(rs.getString(2));

                                                            try
                                                                {
                                                                    pStmt.close();
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
                                    else
                                        {
                                            JOptionPane.showMessageDialog(null,"OS não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                                            tfNomeCliente.setText(" ");
                                            btLocalizar.setEnabled(true);
                                            btBaixar.setEnabled(false);


                                        }
                        }
                    catch (SQLException ex)
                        {
                            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                        }


                        }
                    else if (e.getSource() == btBaixar)
                        {
                                        try
                                            {
                                                pStmt = con.prepareStatement("UPDATE OS SET SITUACAO = ? WHERE CODOS = ?");
                                                pStmt.setString(1, "B");
                                                pStmt.setInt(2, CodOS);
                                                pStmt.executeUpdate();
                                                JOptionPane.showMessageDialog(null, "Baixa Efetuada com sucesso!.\n", "Baixa com Sucesso", JOptionPane.WARNING_MESSAGE);
                                                btBaixar.setEnabled(false);
                                                btLocalizar.setEnabled(true);
                                                lblSituacao.setText(" ");
                                                tfModelo.setText("");
                                                tfCodigoOS.setText("");
                                                tfCPFCliente.setText("");
                                                tfNomeCliente.setText("");
                                                tfValor.setText("");
                                                tfServico.setText("");

                                            }
                                        catch (SQLException ex)
                                            {
                                                JOptionPane.showMessageDialog(null, "Erro ao Salvar. Verifique.\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
                                            }

                        }
                }

	}







