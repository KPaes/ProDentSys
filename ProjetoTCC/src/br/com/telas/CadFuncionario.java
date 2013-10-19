package br.com.telas;
/**
 * Cadastrar e consultar Funcionários
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.TableModel.TableCellRenderer;
import br.com.bean.Funcionario;
import br.com.dao.FuncionarioDao;
import br.com.exception.DaoException;
import br.com.util.MascaraUtil;
import br.com.util.Moeda;
import br.com.util.ValidacaoUtil;
import br.com.util.CriptografiaUtil;


public class CadFuncionario extends JFrame implements KeyListener, ActionListener {
    final JPanel lista = new JPanel();
    final JPanel formulario = new JPanel();

	private FuncionarioDao funcDao = new FuncionarioDao();
	private static final long serialVersionUID = 1L;	
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTable table;
	private JTextField textField;
	private JTextField textField_2;
	private JFormattedTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_1;
	private JTextField txtDigiteONome;
	private JTextField textComissao;
	

	private JLabel lblIndisponivel;
	private JLabel lblSenhaInvalida;
	private int status_nome_usuario = 1;
	@SuppressWarnings("unused")
	private int status_senha_usuario = 1;
	private JPasswordField textSenhaOld;
	private JLabel lblSenhaAntiga;
	
	private String senhaOld = "";
	private String cripta = "";

	public CadFuncionario() throws DaoException {
		setResizable(false);
		//setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Funcion\u00E1rios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadFuncionario.class.getResource("/br/com/images/logo_transp.png")));
		int width = 800;
        int height =600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/3;
        setBounds(x,y,width,height);
        getContentPane().setLayout(null);
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(UIManager.getColor("Button.background"));
        buttonPanel.setBounds(0, 0, 150, 562);
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(null);
        
        JLabel lblPesquisar = new JLabel("Pesquisar:");
        lblPesquisar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        lblPesquisar.setBounds(35, 91, 86, 14);
        buttonPanel.add(lblPesquisar);
        
        txtDigiteONome = new JTextField();
        txtDigiteONome.setToolTipText("Digite o nome do funcion\u00E1rio");
        txtDigiteONome.setBounds(10, 109, 133, 20);
        buttonPanel.add(txtDigiteONome);
        txtDigiteONome.setColumns(10);
        
        JButton btnOk = new JButton("");
        btnOk.setIcon(new ImageIcon(CadFuncionario.class.getResource("/br/com/images/pesquisar.png")));
        btnOk.setToolTipText("Pesquisar funcion\u00E1rio");
        btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					atualizaLista(table, txtDigiteONome.getText().toString());
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
          	}
        });
        btnOk.setBounds(42, 134, 41, 33);
        buttonPanel.add(btnOk);
            


                formulario.setBounds(152, 0, 632, 562);
                getContentPane().add(formulario);
                formulario.setLayout(null);
                
                JPanel panel = new JPanel();
                panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                panel.setLayout(null);
                panel.setBounds(21, 35, 590, 288);
                formulario.add(panel);
                
                passwordField = new JPasswordField();
                passwordField.setBounds(331, 242, 94, 20);
                panel.add(passwordField);
                
                JLabel lblConfirmarASenha = new JLabel("Confirmar a senha:");
                lblConfirmarASenha.setHorizontalAlignment(SwingConstants.RIGHT);
                lblConfirmarASenha.setFont(new Font("Arial Black", Font.PLAIN, 12));
                lblConfirmarASenha.setBounds(199, 242, 127, 18);
                panel.add(lblConfirmarASenha);
                
                passwordField_1 = new JPasswordField();
                passwordField_1.setBounds(92, 242, 94, 20);
                panel.add(passwordField_1);
                
                JLabel label_2 = new JLabel("Senha:");
                label_2.setHorizontalAlignment(SwingConstants.RIGHT);
                label_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
                label_2.setBounds(12, 242, 70, 18);
                panel.add(label_2);
                
                JLabel label_3 = new JLabel("Login:");
                label_3.setHorizontalAlignment(SwingConstants.RIGHT);
                label_3.setFont(new Font("Arial Black", Font.PLAIN, 12));
                label_3.setBounds(12, 176, 70, 18);
                panel.add(label_3);
                
                textField_4 = new JTextField();
                textField_4.setName("Usuário");
                textField_4.addKeyListener(this);
                textField_4.addActionListener(this);
                textField_4.setColumns(10);
                textField_4.setBounds(92, 174, 190, 20);
                panel.add(textField_4);
                
                lblIndisponivel = new JLabel("Dispon\u00EDvel");
                lblIndisponivel.setFont(new Font("Tahoma", Font.ITALIC, 11));
        	 	 lblIndisponivel.setVisible(false);
                lblIndisponivel.setBounds(292, 179, 82, 14);
                panel.add(lblIndisponivel);                
                                 
                JLabel lblprofisso = new JLabel("*Profiss\u00E3o:");
                lblprofisso.setHorizontalAlignment(SwingConstants.RIGHT);
                lblprofisso.setFont(new Font("Arial Black", Font.PLAIN, 12));
                lblprofisso.setBounds(0, 60, 82, 18);
                panel.add(lblprofisso);
                
                JLabel lblnome = new JLabel("*Nome:");
                lblnome.setHorizontalAlignment(SwingConstants.RIGHT);
                lblnome.setFont(new Font("Arial Black", Font.PLAIN, 12));
                lblnome.setBounds(12, 31, 70, 18);
                panel.add(lblnome);
                
                JButton button = new JButton("");
                button.setToolTipText("Salvar Alt+S");
                button.setMnemonic(KeyEvent.VK_S);
                button.setIcon(new ImageIcon(CadFuncionario.class.getResource("/br/com/images/salvar.png")));
                button.setBounds(491, 242, 60, 35);
                panel.add(button);
                
                button.addActionListener(new ActionListener() {					
					@Override					
					  public void actionPerformed(ActionEvent arg0){					  
					  String cSenha = new String(passwordField_1.getPassword());				  
					  if( validarFormulário() ){
						  
						  if(status_nome_usuario == 1){
//								JOptionPane.showMessageDialog(null, "Nome de usuário inválido!");								
							}
						  
						  FuncionarioDao objDAO = new FuncionarioDao();
						  
							Funcionario obj = new Funcionario();
							obj.setTelFunc(textField_3.getText()); //tirei o hideMascara		
							obj.setSalarioFunc(Double.parseDouble(MascaraUtil.hideMascaraMoeda(textField_1)));
							//fazer replace da vírgula pro ponto
						//	obj.setSalarioFunc(Double.parseDouble(textField_1.getText()));
							obj.setComissaoFunc(Double.parseDouble(textComissao.getText()));
							obj.setLogin(textField_4.getText());	
														
							obj.setNomeFunc(textField.getText());
							obj.setProfissaoFunc(textField_2.getText());
							obj.setSenha(cSenha);
						
							int matri = 0;
							try {
								
								if(textField_5.getText().equals("")){									
									objDAO.inserirFuncionarios(obj, matri);		
									limpaFormulario();
								}else{
									Integer matr = Integer.parseInt(textField_5.getText()); 
									obj.setNumFunc(matr);
									//Verifica se a senha antiga confere com a do banco
									Funcionario auteticacao = new FuncionarioDao().procurarFuncionarioSenha(matr);
									senhaOld = auteticacao.getSenha();
									
//										String cripta;
											cripta = CriptografiaUtil.encripta(String.copyValueOf(textSenhaOld.getPassword())); 
											if(cripta.equals(senhaOld)){
												objDAO.atualizarFuncionario(obj);
												JOptionPane.showMessageDialog(formulario, "Dados atualizados com sucesso!");
											}else{
												JOptionPane.showMessageDialog(null, "Senha antiga não confere", "Alterar Senha", JOptionPane.WARNING_MESSAGE);											
											}
//										if(status_senha_usuario == 1){
//											JOptionPane.showMessageDialog(null, "Nome de usuário inválido!");								
//										}else{										
																		
									
								}	
//								}
								limpaFormulario();
								atualizaLista(table,"");
							} catch (DaoException e) {
								e.printStackTrace();
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					  }

						} 
					 
				});
                
                JButton button_1 = new JButton("");
                button_1.setIcon(new ImageIcon(CadFuncionario.class.getResource("/br/com/images/limpar.png")));
                button_1.setToolTipText("Limpar Alt+L");
                button_1.setMnemonic(KeyEvent.VK_L);
                button_1.setBounds(435, 242, 46, 35);
                panel.add(button_1);
                button_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						limpaFormulario();
						
					}
				});
                
                textField = new JTextField();
                textField.setBounds(92, 31, 413, 20);
                panel.add(textField);
                textField.setColumns(10);
                
                textField_2 = new JTextField();
                textField_2.setBounds(92, 60, 154, 20);
                panel.add(textField_2);
                textField_2.setColumns(10);
                
                try {
					textField_3 = new JFormattedTextField(MascaraUtil.setMaskTelefoneInTf(textField_3));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                textField_3.setBounds(92, 91, 127, 20);
                 panel.add(textField_3);
                 textField_3.setColumns(10);
                 
                 
                 
                 
                 textField_5 = new JTextField();
                 textField_5.setVisible(false);
                 textField_5.setText("");
                 panel.add(textField_5);
                 
                 JLabel lblTel = new JLabel("*Tel.:");
                 lblTel.setFont(new Font("Arial Black", Font.PLAIN, 12));
                 lblTel.setBounds(50, 93, 60, 14);
                 panel.add(lblTel);
                 
                 JLabel lblSalrio = new JLabel("*Sal\u00E1rio:");
                 lblSalrio.setFont(new Font("Arial Black", Font.PLAIN, 12));
                 lblSalrio.setBounds(310, 62, 70, 14);
                 panel.add(lblSalrio);
                 
                 textField_1 =  new JTextField();
                 textField_1.setDocument(new Moeda()); 
                 textField_1.setBounds(388, 60, 117, 20);
                 panel.add(textField_1);
                 textField_1.setColumns(10);
                 
                 JLabel lblcamposObrigatrios = new JLabel("*Campos obrigat\u00F3rios!");
                 lblcamposObrigatrios.setForeground(Color.RED);
                 lblcamposObrigatrios.setFont(new Font("Tahoma", Font.PLAIN, 12));
                 lblcamposObrigatrios.setBounds(307, 128, 206, 14);
                 panel.add(lblcamposObrigatrios);
                 
                 JLabel lblComisso = new JLabel("Comiss\u00E3o:");
                 lblComisso.setFont(new Font("Arial Black", Font.PLAIN, 12));
                 lblComisso.setBounds(12, 128, 82, 14);
                 panel.add(lblComisso);
                 
                 textComissao = new JTextField();
                 textComissao.setToolTipText("Em porcentagem");
                 textComissao.setBounds(92, 126, 86, 20);
                 panel.add(textComissao);
                 textComissao.setColumns(10);
                 
                 textSenhaOld = new JPasswordField();
                 textSenhaOld.setBounds(120, 211, 162, 20);
                 textSenhaOld.setVisible(false);
                 panel.add(textSenhaOld);
                 textSenhaOld.setColumns(10);
                 
                 lblSenhaAntiga = new JLabel("Senha Antiga:");
                 lblSenhaAntiga.setHorizontalAlignment(SwingConstants.RIGHT);
                 lblSenhaAntiga.setFont(new Font("Arial Black", Font.PLAIN, 12));
                 lblSenhaAntiga.setVisible(false);
                 lblSenhaAntiga.setBounds(0, 213, 110, 18);
                 panel.add(lblSenhaAntiga);
                 
                 lblSenhaInvalida = new JLabel("Certo!");
                 lblSenhaInvalida.setFont(new Font("Tahoma", Font.ITALIC, 11));
                 lblSenhaInvalida.setVisible(false);
                 lblSenhaInvalida.setBounds(292, 217, 82, 14);
                 panel.add(lblSenhaInvalida); 
                 
                 
                 JButton btnVoltar = new JButton("");
                 btnVoltar.setToolTipText("Voltar");
                 //btnVoltar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_MASK));
                 btnVoltar.setIcon(new ImageIcon(CadFuncionario.class.getResource("/br/com/images/voltar.png")));
                 btnVoltar.setBounds(21, 340, 89, 23);
                 formulario.add(btnVoltar);
                 formulario.setVisible(false);
                 
                 btnVoltar.addActionListener(new ActionListener() {
                 	public void actionPerformed(ActionEvent arg0) {
                 		lista.setVisible(true);
                 		formulario.setVisible(false); 
                 		buttonPanel.setVisible(true);
                 	}
                 });    
            

            lista.setBounds(152, 0, 632, 562);
            getContentPane().add(lista);
            lista.setLayout(null);
            
            JLabel lblFuncionriosCadastrados = new JLabel("Funcion\u00E1rios Cadastrados");
            lblFuncionriosCadastrados.setFont(new Font("Kalinga", Font.BOLD, 16));
            lblFuncionriosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
            lblFuncionriosCadastrados.setBackground(Color.WHITE);
            lblFuncionriosCadastrados.setBounds(10, 11, 612, 29);
            lista.add(lblFuncionriosCadastrados);
            

            

              
              Button Novo = new Button("Adicionar");          
              Novo.setBounds(10, 530, 70, 22);
              lista.add(Novo);                                    
              lista.setVisible(true);  
              textSenhaOld.setVisible(false);
              lblSenhaAntiga.setVisible(false);
              table = new JTable();
              table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int linha = table.getSelectedRow();
				int coluna = table.getSelectedColumn();
				String matricula = (String) table.getValueAt(linha,0);
				Integer mat = Integer.parseInt(matricula); 
				if(coluna == 4){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o funcionário de número: "+ matricula ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							funcDao.excluirFuncionarios(mat);
							atualizaLista(table,"");
						} catch (DaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
					   }
				}
				if (coluna == 3){
					Funcionario objFunc = new Funcionario();

					try {
						objFunc = funcDao.consultarFuncionarioID(mat);
						atualizaFormulario(objFunc);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				
			}
		});
              

              table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
              table.setModel(new DefaultTableModel(
        		  new Object[][] {
                      	},
                      	new String[] {
                      		"Número", "Nome", "Telefone", "Editar","Excluir"
                      	}
                      	
                      )
                      { 
					private static final long serialVersionUID = 1L;

					@Override 
                    	  public boolean isCellEditable(int row, int col) 
                    	  { 
                    	  return false; 
                    	  } 
                    	  }
                      );
              table.getColumnModel().getColumn(0).setPreferredWidth(55);
              table.getColumnModel().getColumn(0).setMinWidth(55);
              table.getColumnModel().getColumn(1).setPreferredWidth(250);
              table.getColumnModel().getColumn(1).setMinWidth(250);
              table.getColumnModel().getColumn(2).setPreferredWidth(150);
              table.getColumnModel().getColumn(2).setMinWidth(150);
              table.setBounds(39, 175, 530, 232);
              atualizaLista(table,"");
              
              
              JScrollPane scrollPane = new JScrollPane();
              scrollPane.setBounds(0, 51, 622, 473);
              lista.add(scrollPane);
              
                             scrollPane.setViewportView(table);
                             
                                     Novo.addActionListener(new ActionListener() {
                                     	public void actionPerformed(ActionEvent arg0) {
                                     		lista.setVisible(false);
                                     		formulario.setVisible(true);
                                     		buttonPanel.setVisible(false);
                                     		limpaFormulario();
                                     		try {
                                               			atualizaLista(table,"");
                                               		} catch (DaoException e) {
                                               			// TODO Auto-generated catch block
                                               			e.printStackTrace();
                                               		}
                                     	}
                                     });	
        
	}
	
	public void atualizaLista(JTable lista, String nome) throws DaoException{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        ImageIcon editar = new ImageIcon(CadFuncionario.class.getResource("/br/com/images/editar.png"));  
        ImageIcon excluir = new ImageIcon(CadFuncionario.class.getResource("/br/com/images/icon_excluir.png"));

		TableColumnModel columnModel = table.getColumnModel();
		
		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();		
		
		renderer.setValue(editar);
		renderer.setHorizontalAlignment(JLabel.CENTER);
		renderer.setToolTipText("Editar");
		columnModel.getColumn(3).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		renderer1.setToolTipText("Excluir");
		columnModel.getColumn(4).setCellRenderer(renderer1);

        dtm.setRowCount(0); 
		List<Funcionario> listaFunc  = new ArrayList<Funcionario>();
 		listaFunc = funcDao.consultarFuncionarios(nome);
 		String dados[] = new String[3]; 
		for (Funcionario obj : listaFunc) {
			dados[0] = String.valueOf(obj.getNumFunc());
			dados[1] = obj.getNomeFunc();
			dados[2] = obj.getTelFunc();
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
			//table.setDefaultRenderer(Object.class, new TableCellRenderer());
		} 
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		table.repaint();
	}

	public class JTableRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		protected void setValue(Object value) {
			if (value instanceof ImageIcon) {
				if (value != null) {
					ImageIcon d = (ImageIcon) value;
					setIcon(d);
				}
			} else {
				super.setValue(value);
			}
		}
	}	

	public void atualizaFormulario(Funcionario objFunc){
		textField_3.setText(objFunc.getTelFunc());
		textField_2.setText(objFunc.getProfissaoFunc());
		textField_4.setText(objFunc.getLogin());
		textField.setText(objFunc.getNomeFunc());
		
		lblSenhaAntiga.setVisible(true);
		textSenhaOld.setVisible(true);
//		textSenhaOld.setEnabled(true);
		
		textComissao.setText(objFunc.getComissaoFunc().toString());
		
		String aux = objFunc.getSalarioFunc().toString();
		aux = aux.replace(".","");
		textField_1.setText(aux);
		
		passwordField.setText("");
		passwordField_1.setText("");
		
		Integer matr = objFunc.getNumFunc();
		textField_5.setText(matr.toString());
		
		lista.setVisible(false);
		formulario.setVisible(true);
	}
	
	public void limpaFormulario(){
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		passwordField.setText("");
		passwordField_1.setText("");	
		textComissao.setText("");
		textSenhaOld.setText("");
	}
	
	public boolean validarFormulário(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField)){
			JOptionPane.showMessageDialog(null, "Campo Nome Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_1)){
			JOptionPane.showMessageDialog(null, "Campo Salário Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Campo Profissão Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_3)){
			JOptionPane.showMessageDialog(null, "Campo Telefone Vazio!");
			result = false;
		}
//		if(!ValidacaoUtil.textFieldVazio(textField_4)){
//			JOptionPane.showMessageDialog(null, "Campo Login Vazio!");
//			result = false;
//		}
//		if(!ValidacaoUtil.textFieldVazio(passwordField)){
//			JOptionPane.showMessageDialog(null, "Campo Senha Vazio!");
//			result = false;
//		}
//		if(!ValidacaoUtil.textFieldVazio(passwordField_1)){
//			JOptionPane.showMessageDialog(null, "Campo confirmar senha Vazio!");
//			result = false;
//		}
		if(!ValidacaoUtil.textFieldVazio(textComissao)){
//			JOptionPane.showMessageDialog(null, "Campo confirmar senha Vazio!");
			
			textComissao.setText("0");
			
			result = false;
		}
		
		if(!ValidacaoUtil.passwordsIguais(passwordField, passwordField_1)){
			JOptionPane.showMessageDialog(null, "Campos senha e confirmar senha devem ser iguais!");
			result = false;
		}		
		return result;
	}	
	
	
	private void verificaNomeUsuario(){
		
		Runnable run = new Runnable(){  
			   public void run(){  
				   
					List<String> todos = null;
					try {
						todos = new FuncionarioDao().buscarLogin();
					} catch (DaoException e) {
						e.printStackTrace();
					}
					
					for (String s : todos) {
						if (textField_4.getText().equals(s) || s.equals(textField_4.getText())) {
							lblIndisponivel.setVisible(true);
							textField_4.setForeground(Color.RED);
							lblIndisponivel.setText("Indisponível, tente outro.");
							lblIndisponivel.setForeground(Color.RED);
							status_nome_usuario = 1;
						}
						if(textField_4.getText().equals("")){
							lblIndisponivel.setVisible(false);
							status_nome_usuario = 1;
						}
						if(!textField_4.getText().equals(s) || !s.equals(textField_4.getText())){
							lblIndisponivel.setVisible(true);
							textField_4.setForeground(Color.BLACK);
							lblIndisponivel.setText("Disponível");
							lblIndisponivel.setForeground(Color.BLACK);
							status_nome_usuario = 0;
						}
					}
					
			   }  
		};  
			  
		Thread t = new Thread(run);  
		t.start();
		
	}
	
@SuppressWarnings("unused")
private void verificaSenhaUsuario(){
//	 final String senhaOld;
//	 final String cripta = "";
		Runnable run = new Runnable(){  
			   public void run(){  
				   
					try {
						Integer matr = Integer.parseInt(textField_5.getText());						
						
						Funcionario auteticacao = new FuncionarioDao().procurarFuncionarioSenha(matr);
						senhaOld = auteticacao.getSenha();
						
						try {
							cripta = CriptografiaUtil.encripta(String.copyValueOf(textSenhaOld.getPassword()));
			
					
						if (cripta.equals(senhaOld) || senhaOld.equals(cripta)) {
							lblSenhaInvalida.setVisible(true);
							lblSenhaInvalida.setText("Errado, tente novamente!");
							lblSenhaInvalida.setForeground(Color.RED);
							status_senha_usuario = 1;
						}
						if(String.copyValueOf(textSenhaOld.getPassword()).equals("")){
							lblSenhaInvalida.setVisible(false);
							status_senha_usuario = 1;
						}
						if(!cripta.equals(senhaOld) || !senhaOld.equals(cripta)){
							lblSenhaInvalida.setVisible(true);
							lblSenhaInvalida.setText("Certo!");
							lblSenhaInvalida.setForeground(Color.BLACK);
							status_senha_usuario = 0;
						}
					
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} catch (DaoException e) {
						e.printStackTrace();
					}
						}
					
			   
		};  
			  
		Thread t = new Thread(run);  
		t.start();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
    public void focusLost(FocusEvent event) {
		if(event.getSource() == textField_4){
			verificaNomeUsuario();
		}
//		if(event.getSource() == textSenhaOld){
//			verificaSenhaUsuario();
//		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getSource() == textField_4){
			verificaNomeUsuario();
		}
//		if(event.getSource() == textSenhaOld){
//			verificaSenhaUsuario();
//		}
		
	}
	@Override
	public void keyReleased(KeyEvent event) {
		
	}
}

