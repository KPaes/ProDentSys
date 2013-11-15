package br.com.telas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Button;
import javax.swing.ListSelectionModel;

import br.com.TableModel.TableCellRenderer;
import br.com.bean.ClienteDent;
import br.com.dao.ClienteDao;
import br.com.exception.DaoException;
import br.com.util.MascaraUtil;
import br.com.util.ValidacaoUtil;
import br.com.util.ValidaCPF;
import br.com.util.ValidaEmail;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;

import org.jdesktop.swingx.JXSearchField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadCliente extends JFrame implements KeyListener{
    final JPanel lista = new JPanel();
    final JPanel formulario = new JPanel();
    final JPanel buttonPanel = new JPanel();
    private ClienteDao clienteDao = new ClienteDao();
	private static final long serialVersionUID = 1L;	

	private JTable table;
	private JTextField textField;
	private JTextField txtEmail;
	private JTextField textField_2;
	private JFormattedTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JFormattedTextField textField_7;
	private JTextField textField_8;
	private JFormattedTextField txtCPF;
	private JXSearchField txtPesq;
	
	private JLabel lblCpf;
	private JLabel lblEmail;
	
	private JTextField txtCompl;
	

	
	String email = null;
	
	public CadCliente() throws DaoException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadCliente.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Cadastro de Clientes");
		int width = 800;
        int height = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/3;
        setBounds(x,y,821,600);
        getContentPane().setLayout(null);
        
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(UIManager.getColor("Button.background"));
        buttonPanel.setBounds(0, 0, 152, 562);
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(null);
        
        JLabel lblPesquisar = new JLabel("Pesquisar");
        lblPesquisar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        lblPesquisar.setBounds(40, 64, 73, 14);
        buttonPanel.add(lblPesquisar);
        
        txtPesq = new JXSearchField();
        txtPesq.setFont(new Font("Tahoma", Font.PLAIN, 11));
        txtPesq.addKeyListener(this);
        txtPesq.setPrompt("Nome do cliente");
        txtPesq.setToolTipText("Digite o nome do cliente para pesquisar");
        txtPesq.setBounds(10, 85, 132, 20);
        buttonPanel.add(txtPesq);
        txtPesq.setColumns(10);
                   
                

                 formulario.setBounds(152, 0, 632, 562);
                 getContentPane().add(formulario);
                 formulario.setLayout(null);
                 
                 JPanel panel = new JPanel();
                 panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                 panel.setLayout(null);
                 panel.setBounds(21, 35, 590, 288);
                 formulario.add(panel);
                 
                                                   
                           JLabel lblnome = new JLabel("*Nome:");
                           lblnome.setHorizontalAlignment(SwingConstants.RIGHT);
                           lblnome.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lblnome.setBounds(-12, 31, 82, 18);
                           panel.add(lblnome);
                           
                           JLabel lblcpf = new JLabel("*CPF:");
                           lblcpf.setHorizontalAlignment(SwingConstants.RIGHT);
                           lblcpf.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lblcpf.setBounds(0, 60, 70, 18);
                           panel.add(lblcpf);
                           
                           
                           JLabel lbltelefone = new JLabel("*Telefone:");
                           lbltelefone.setHorizontalAlignment(SwingConstants.RIGHT);
                           lbltelefone.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lbltelefone.setBounds(0, 118, 70, 18);
                           panel.add(lbltelefone);
                           
                           JLabel label_3 = new JLabel("E-mail:");
                           label_3.setHorizontalAlignment(SwingConstants.RIGHT);
                           label_3.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           label_3.setBounds(0, 89, 70, 18);
                           panel.add(label_3);
                           
                           JLabel lblrua = new JLabel("*Rua:");
                           lblrua.setHorizontalAlignment(SwingConstants.RIGHT);
                           lblrua.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lblrua.setBounds(0, 147, 70, 18);
                           panel.add(lblrua);
                           
                           JLabel lbln = new JLabel("*N\u00BA:");
                           lbln.setHorizontalAlignment(SwingConstants.RIGHT);
                           lbln.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lbln.setBounds(280, 147, 56, 18);
                           panel.add(lbln);
                           
                           JLabel lblbairro = new JLabel("*Bairro:");
                           lblbairro.setHorizontalAlignment(SwingConstants.RIGHT);
                           lblbairro.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lblbairro.setBounds(266, 176, 70, 18);
                           panel.add(lblbairro);
                           
                           JLabel lblcidade = new JLabel("*Cidade:");
                           lblcidade.setHorizontalAlignment(SwingConstants.RIGHT);
                           lblcidade.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lblcidade.setBounds(0, 205, 70, 18);
                           panel.add(lblcidade);
                           
                           JLabel lblcep = new JLabel("*CEP:");
                           lblcep.setHorizontalAlignment(SwingConstants.RIGHT);
                           lblcep.setFont(new Font("Arial Black", Font.PLAIN, 12));
                           lblcep.setBounds(0, 234, 70, 18);
                           panel.add(lblcep);
                           
                            
                            JButton button = new JButton("");
                            button.setIcon(new ImageIcon(CadCliente.class.getResource("/br/com/images/salvar.png")));
                            button.setToolTipText("Salvar Alt+S");
                            button.setMnemonic(KeyEvent.VK_S);
                            button.setBounds(491, 244, 56, 33);
                            panel.add(button);
                            
                            button.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
							if( validarFormulário()){	
																							
								ClienteDent obj = new ClienteDent();
								
								obj.setTelCliente(textField_3.getText());
								obj.setEmailCliente(txtEmail.getText());
								obj.setRuaCliente(textField_2.getText());
								obj.setNumEndCliente(textField_8.getText());
								obj.setBairroCliente(textField_4.getText());
								obj.setCidadeCliente(textField_6.getText());							
								obj.setNomeCliente(textField.getText());
								obj.setCepCliente(textField_7.getText());
								obj.setComplCliente(txtCompl.getText());
															
								obj.setCpfCliente(txtCPF.getText());				
								
								ClienteDao objDAO = new ClienteDao();
								int matri = 0;
								try {
																
									if(textField_5.getText().equals("")){
										objDAO.inserirClientes(obj, matri);
										limpaFormulario();
									}
									else {
										Integer matr = Integer.parseInt(textField_5.getText()); 
										obj.setNumCliente(matr);
										objDAO.atualizarCliente(obj);
										JOptionPane.showMessageDialog(formulario, "Dados atualizados com sucesso!");
									}
									atualizaLista(table,"");
								} catch (DaoException e) {
									e.printStackTrace();
								}
								}
								
						}
						                
				});
                            
                  JButton button_1 = new JButton("");
                  button_1.setToolTipText("Limpar Alt+L");
                  button_1.setMnemonic(KeyEvent.VK_L);
                  button_1.setIcon(new ImageIcon(CadCliente.class.getResource("/br/com/images/limpar.png")));
                  button_1.setBounds(425, 244, 56, 33);
                  panel.add(button_1);
                  button_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						limpaFormulario();
						
					}
				});
                  
                 textField = new JTextField();
                 textField.setBounds(80, 31, 335, 20);
                 panel.add(textField);
                 textField.setColumns(10);
                 
                 txtEmail = new JTextField();
                 txtEmail.addFocusListener(new FocusAdapter() {
                 	@Override
                 	public void focusLost(FocusEvent e) {
                 		email = txtEmail.getText();
						if(txtEmail.getText().isEmpty()){
							email = null;
						}
                    	if(ValidaEmail.validaEmail(email) == false){
                    		lblEmail.setText("E-mail Inválido");
                    		lblEmail.requestFocus();
                    	}else{
                    		lblEmail.setText("");
                    	}
                 	}
                 });
                 txtEmail.setBounds(80, 89, 335, 20);
                 panel.add(txtEmail);
                 txtEmail.setColumns(10);
                 
                 textField_2 = new JTextField();
                 textField_2.setBounds(80, 147, 190, 20);
                 panel.add(textField_2);
                 textField_2.setColumns(10);
                 
                  try {
					textField_3 = new JFormattedTextField(MascaraUtil.setMaskTelefoneInTf(textField_3));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                  textField_3.setBounds(80, 118, 127, 20);
                  panel.add(textField_3);
                  textField_3.setColumns(10);
                  
                 textField_4 = new JTextField();
                 textField_4.setBounds(346, 176, 190, 20);
                 panel.add(textField_4);
                 textField_4.setColumns(10);
                 
                 textField_6 = new JTextField();
                 textField_6.setBounds(80, 205, 190, 20); 
                 panel.add(textField_6);
                 textField_6.setColumns(10);
                 
                 try {
					textField_7 = new JFormattedTextField(MascaraUtil.setMaskCepInTf(textField_7));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                 textField_7.setBounds(80, 234, 115, 20); 
                 panel.add(textField_7);
                 textField_7.setColumns(10);
                 
                 textField_8 = new JTextField();
                 textField_8.setBounds(346, 147, 89, 20); 
                 panel.add(textField_8);
                 textField_8.setColumns(10);
                 
                 textField_5 = new JTextField();
                 textField_5.setVisible(false);
                 textField_5.setText("");
                 panel.add(textField_5);
                 
                 
					try {
						txtCPF = new JFormattedTextField(MascaraUtil.setMaskCpfInTf(txtCPF));					
						txtCPF.addFocusListener(new FocusAdapter() {						
					@Override
					public void focusLost(FocusEvent e) {
						if(ValidaCPF.existe(txtCPF, false) == false){
							lblCpf.setText("CPF já cadastrado no sistema!");
							txtCPF.requestFocus();
						}else if(ValidaCPF.valida(txtCPF) == false){
								lblCpf.setText("CPF inválido!");
								txtCPF.requestFocus();
						}
						else{
							lblCpf.setText("");
						}						
					}				
				});
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			

				
                 txtCPF.setBounds(80, 60, 127, 20);
                 panel.add(txtCPF);
                 txtCPF.setColumns(10);
                 
                 JLabel lblTodosOsCampos = new JLabel("*Campos obrigat\u00F3rios!");
                 lblTodosOsCampos.setForeground(new Color(255, 0, 0));
                 lblTodosOsCampos.setFont(new Font("Tahoma", Font.PLAIN, 12));
                 lblTodosOsCampos.setBounds(130, 263, 206, 14);
                 panel.add(lblTodosOsCampos);
                 
                 JLabel lblCompl = new JLabel("Compl.:");
                 lblCompl.setHorizontalAlignment(SwingConstants.RIGHT);
                 lblCompl.setFont(new Font("Arial Black", Font.PLAIN, 12));
                 lblCompl.setBounds(0, 176, 70, 18);
                 panel.add(lblCompl);
                 
                 txtCompl = new JTextField();
                 txtCompl.setBounds(80, 176, 190, 20);
                 panel.add(txtCompl);
                 txtCompl.setColumns(10);
                 
                 lblCpf = new JLabel("");
                 lblCpf.setFont(new Font("Tahoma", Font.ITALIC, 11));
                 lblCpf.setBounds(217, 63, 198, 14);
                 panel.add(lblCpf);
                 
                 lblEmail = new JLabel("");
                 lblEmail.setFont(new Font("Tahoma", Font.ITALIC, 11));
                 lblEmail.setBounds(425, 92, 111, 14);
                 panel.add(lblEmail);
                 
                 JButton btnVoltar = new JButton("");
                 btnVoltar.setToolTipText("Voltar");
            //     ((JButton) btnVoltar.getComponent(1)).setIcon(new ImageIcon(CadCliente.class.getResource("/br/com/images/voltar.png")));
                 btnVoltar.setIcon(new ImageIcon(CadCliente.class.getResource("/br/com/images/voltar.png")));
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
                 

                 lista.setBounds(152, 0, 656, 562);
                 getContentPane().add(lista);
                 lista.setLayout(null);
                 
                 JLabel lblFuncionriosCadastrados = new JLabel("Clientes Cadastrados");
                 lblFuncionriosCadastrados.setFont(new Font("Kalinga", Font.BOLD, 16));
                 lblFuncionriosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
                 lblFuncionriosCadastrados.setBackground(Color.WHITE);
                 lblFuncionriosCadastrados.setBounds(10, 11, 612, 29);
                 lista.add(lblFuncionriosCadastrados);
                 


                   
                 Button Novo = new Button("Adicionar");          
                 Novo.setBounds(10, 530, 70, 22);
                 lista.add(Novo);                                    
                 lista.setVisible(true);  
                 buttonPanel.setVisible(true);
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
				if(coluna == 5){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o cliente de matricula: "+matricula ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							clienteDao.excluirClientes(mat);
							atualizaLista(table,"");
						} catch (DaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
					   }
				}
				if (coluna == 4){
					ClienteDent objCliente = new ClienteDent();
					
					try {
						objCliente = clienteDao.consultarClienteID(mat);
						atualizaFormulario(objCliente);
						buttonPanel.setVisible(false);
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
                      "Número", "Nome", "Telefone", "E-mail", "Editar","Excluir"
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
         table.getColumnModel().getColumn(1).setPreferredWidth(200);
         table.getColumnModel().getColumn(1).setMinWidth(200);
         table.getColumnModel().getColumn(2).setPreferredWidth(80);
         table.getColumnModel().getColumn(2).setMinWidth(80);
         table.getColumnModel().getColumn(3).setPreferredWidth(100);
         table.getColumnModel().getColumn(3).setMinWidth(100);
         table.setBounds(39, 175, 530, 232);
         atualizaLista(table,"");
         
         
              JScrollPane scrollPane = new JScrollPane();
              scrollPane.setBounds(10, 51, 636, 473);
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
		columnModel.getColumn(4).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		renderer1.setToolTipText("Excluir");
		columnModel.getColumn(5).setCellRenderer(renderer1);

        dtm.setRowCount(0); 
		List<ClienteDent> listaCliente  = new ArrayList<ClienteDent>();
 		listaCliente = clienteDao.consultarClientes(nome);
 		String dados[] = new String[4]; 
		for (ClienteDent obj : listaCliente) {
			dados[0] = String.valueOf(obj.getNumCliente()) ;
			dados[1] = obj.getNomeCliente();
			dados[2] = obj.getTelCliente();
			dados[3] = obj.getEmailCliente();
			((DefaultTableModel) table.getModel()).addRow(dados); 
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

	public void atualizaFormulario(ClienteDent objCliente){
		textField_2.setText(objCliente.getRuaCliente());
		txtEmail.setText(objCliente.getEmailCliente());
		textField_4.setText(objCliente.getBairroCliente());
		textField_6.setText(objCliente.getCidadeCliente());
		textField_7.setText(objCliente.getCepCliente());
		textField_8.setText(objCliente.getNumEndCliente());
		textField.setText(objCliente.getNomeCliente());
		textField_3.setText(objCliente.getTelCliente());
		txtCPF.setText(objCliente.getCpfCliente());
		txtCompl.setText(objCliente.getComplCliente());
		
		Integer matr = objCliente.getNumCliente();
		textField_5.setText(matr.toString());
		
		lista.setVisible(false);
		formulario.setVisible(true);
		buttonPanel.setVisible(false);
	}
	
	public void limpaFormulario(){
		textField.setText("");
		txtEmail.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		txtCPF.setText("");
		txtCompl.setText("");
		
	}

	public boolean validarFormulário(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField)){
			JOptionPane.showMessageDialog(null, "Campo Nome Vazio!");
			result = false;
		}
//		if(!ValidacaoUtil.textFieldVazio(txtEmail)){
//			JOptionPane.showMessageDialog(null, "Campo Email Vazio!");
//			result = false;
//		}
		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Campo Rua Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_3)){
			JOptionPane.showMessageDialog(null, "Campo Telefone Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_4)){
			JOptionPane.showMessageDialog(null, "Campo Bairro Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_7)){
			JOptionPane.showMessageDialog(null, "Campo CEP Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_6)){
			JOptionPane.showMessageDialog(null, "Campo Cidade Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_8)){
			JOptionPane.showMessageDialog(null, "Campo Nº Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(txtCPF)){
			JOptionPane.showMessageDialog(null, "Campo CPF Vazio!");
			result = false;
		}
	
		return result;
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent event) {
		if(event.getSource() == txtPesq){
			try {
				atualizaLista(table,txtPesq.getText().toString());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

