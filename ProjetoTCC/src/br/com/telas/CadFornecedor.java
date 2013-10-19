package br.com.telas;

import java.awt.Dimension;
import java.awt.Toolkit;


import javax.swing.JButton;
import javax.swing.JDialog;
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
import br.com.bean.Fornecedor;
import br.com.dao.FornecedorDao;
import br.com.exception.DaoException;
import br.com.util.MascaraUtil;
import br.com.util.ValidacaoUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;

public class CadFornecedor extends JDialog {
    final JPanel lista = new JPanel();
    final JPanel formulario = new JPanel();
    final JPanel buttonPanel = new JPanel();
    private FornecedorDao fornecDao = new FornecedorDao();
	private static final long serialVersionUID = 1L;	
	private JTable table;
	private JTextField textField;
	private JFormattedTextField textField_2;
	private JFormattedTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_1;
	private JTextField textField_9;
	

	public CadFornecedor() throws DaoException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadFornecedor.class.getResource("/br/com/images/logo_sys.png")));
		setTitle("Cadastro de Fornecedores");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(CadFuncionario.class.getResource("/br/com/images/cadForm.jpg")));
		int width = 800;
        int height =600;
        setModal(true);
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
        lblPesquisar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        lblPesquisar.setBounds(40, 66, 75, 14);
        buttonPanel.add(lblPesquisar);
        
        textField_1 = new JTextField();
        textField_1.setToolTipText("Digite o nome do fornecedor");
        textField_1.setBounds(10, 84, 132, 20);
        buttonPanel.add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnOk = new JButton("");
        btnOk.setIcon(new ImageIcon(CadFornecedor.class.getResource("/br/com/images/pesquisar.png")));
        btnOk.setToolTipText("Pesquisar fornecedor");
        btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					atualizaLista(table,textField_1.getText().toString());
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
          	}
        });
        btnOk.setBounds(35, 111, 48, 33);
        buttonPanel.add(btnOk);                  
                                 

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
                                    lblnome.setBounds(12, 31, 70, 18);
                                    panel.add(lblnome);
                                    
                                    JLabel lbltelefone = new JLabel("*Telefone:");
                                    lbltelefone.setHorizontalAlignment(SwingConstants.RIGHT);
                                    lbltelefone.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                    lbltelefone.setBounds(12, 60, 70, 18);
                                    panel.add(lbltelefone);
                                    
                                    JLabel lblrua = new JLabel("*Rua:");
                                    lblrua.setHorizontalAlignment(SwingConstants.RIGHT);
                                    lblrua.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                    lblrua.setBounds(12, 89, 70, 18);
                                    panel.add(lblrua);
                                    
                                    JLabel lbln = new JLabel("*N\u00BA:");
                                    lbln.setHorizontalAlignment(SwingConstants.RIGHT);
                                    lbln.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                    lbln.setBounds(356, 89, 50, 18);
                                    panel.add(lbln);
                                    
                                    JLabel lblbairro = new JLabel("*Bairro:");
                                    lblbairro.setHorizontalAlignment(SwingConstants.RIGHT);
                                    lblbairro.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                    lblbairro.setBounds(12, 147, 70, 18);
                                    panel.add(lblbairro);
                                    
                                    JLabel lblcidade = new JLabel("*Cidade:");
                                    lblcidade.setHorizontalAlignment(SwingConstants.RIGHT);
                                    lblcidade.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                    lblcidade.setBounds(12, 176, 70, 18);
                                    panel.add(lblcidade);
                                    
                                    JLabel lblcep = new JLabel("*CEP:");
                                    lblcep.setHorizontalAlignment(SwingConstants.RIGHT);
                                    lblcep.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                    lblcep.setBounds(12, 205, 70, 18);
                                    panel.add(lblcep);
                                    
                                     
                                     JButton button = new JButton("");
                                     button.setToolTipText("Salvar Alt+S");
                                     button.setMnemonic(KeyEvent.VK_S);                               
                                     button.setIcon(new ImageIcon(CadFornecedor.class.getResource("/br/com/images/salvar.png")));
                                     button.setBounds(491, 242, 63, 35);
                                     panel.add(button);
                                     
                                     button.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if( validarFormulário() ){
							Fornecedor obj = new Fornecedor();
							
							obj.setTelFornec(textField_2.getText());
							obj.setRuaFornec(textField_4.getText());
							obj.setNumEndFornec(textField_6.getText());
							obj.setBairroFornec(textField_7.getText());
							obj.setCidadeFornec(textField_8.getText());							
							obj.setNomeFornec(textField.getText());
							obj.setCepFornec(textField_3.getText());
							obj.setComplFornec(textField_9.getText());
							
							FornecedorDao objDAO = new FornecedorDao();
							try {
								
								if(textField_5.getText().equals("")){
									objDAO.inserirFornecedores(obj);
									JOptionPane.showMessageDialog(formulario, "Dados salvos com sucesso!");	
									limpaFormulario();
								}else{
									Integer matr = Integer.parseInt(textField_5.getText()); 
									obj.setNumFornec(matr);
									objDAO.atualizarFornecedor(obj);
									JOptionPane.showMessageDialog(formulario, "Dados atualizados com sucesso!");
								}								
								atualizaLista(table,"");
							} catch (DaoException e) {
								e.printStackTrace();
							}

						}
					  
					}
				});
                                     
                                     JButton  button_1 = new JButton("");
                                     button_1.setIcon(new ImageIcon(CadFornecedor.class.getResource("/br/com/images/limpar.png")));
                                     button_1.setMnemonic(KeyEvent.VK_L);
                                     button_1.setToolTipText("Limpar Alt+L");                                     
                                     button_1.setBounds(418, 242, 63, 35);
                                     panel.add(button_1);
                                     button_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						limpaFormulario();
						
					}
				});
                                     
                                     textField = new JTextField();
                                     textField.setBounds(92, 31, 389, 20);
                                     panel.add(textField);
                                     textField.setColumns(10);
                                     try {
										textField_2 = new JFormattedTextField(MascaraUtil.setMaskTelefoneInTf(textField_2));
									} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
                                     textField_2.setBounds(92, 60, 127, 20);
                                     panel.add(textField_2);
                                     textField_2.setColumns(10);
                                     try {
										textField_3 = new JFormattedTextField(MascaraUtil.setMaskCepInTable(textField_3));
									} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
                                     textField_3.setBounds(92, 205, 127, 20);
                                     panel.add(textField_3);
                                     textField_3.setColumns(10);
                                     
                                     textField_4 = new JTextField();
                                     textField_4.setBounds(92, 89, 254, 20);
                                     panel.add(textField_4);
                                     textField_4.setColumns(10);
                                     
                                     textField_6 = new JTextField();
                                     textField_6.setBounds(411, 89, 70, 20);
                                     panel.add(textField_6);
                                     textField_6.setColumns(10);
                                     
                                     textField_7 = new JTextField();
                                     textField_7.setBounds(92, 147, 254, 20); 
                                     panel.add(textField_7);
                                     textField_7.setColumns(10);
                                     
                                     textField_8 = new JTextField();
                                     textField_8.setBounds(92, 176, 254, 20); 
                                     panel.add(textField_8);
                                     textField_8.setColumns(10);
                                     
                                     textField_5 = new JTextField();
                                     textField_5.setVisible(false);
                                     textField_5.setText("");
                                     panel.add(textField_5);
                                     
                                     JLabel lblTodosOsCampos = new JLabel("*Campos obrigat\u00F3rios!");
                                     lblTodosOsCampos.setForeground(new Color(255, 0, 0));
                                     lblTodosOsCampos.setFont(new Font("Tahoma", Font.PLAIN, 12));
                                     lblTodosOsCampos.setBounds(122, 257, 206, 14);
                                     panel.add(lblTodosOsCampos);
                                     
                                     JLabel lblComplemento = new JLabel("Compl.:");
                                     lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
                                     lblComplemento.setFont(new Font("Arial Black", Font.PLAIN, 12));
                                     lblComplemento.setBounds(12, 118, 70, 18);
                                     panel.add(lblComplemento);
                                     
                                     textField_9 = new JTextField();
                                     textField_9.setBounds(91, 116, 255, 20);
                                     panel.add(textField_9);
                                     textField_9.setColumns(10);
                                     
                                     JButton btnVoltar = new JButton("");
                                     btnVoltar.setIcon(new ImageIcon(CadFornecedor.class.getResource("/br/com/images/voltar.png")));
                                     btnVoltar.setToolTipText("Voltar");
                                     btnVoltar.setBounds(21, 340, 89, 23);
                                     formulario.add(btnVoltar);
                                     

                                     lista.setBounds(152, 0, 656, 562);
                                     getContentPane().add(lista);
                                     lista.setLayout(null);
                                     
                                     JLabel lblFuncionriosCadastrados = new JLabel("Fornecedores Cadastrados");
                                     lblFuncionriosCadastrados.setFont(new Font("Kalinga", Font.BOLD, 16));
                                     lblFuncionriosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
                                     lblFuncionriosCadastrados.setBackground(Color.WHITE);
                                     lblFuncionriosCadastrados.setBounds(10, 11, 612, 29);
                                     lista.add(lblFuncionriosCadastrados);
                                     


                                       
                                       Button Novo = new Button("Adicionar");          
                                       Novo.setBounds(10, 530, 70, 22);
                                       lista.add(Novo);                                    
                                       lista.setVisible(true);       
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
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o fornecedor de matricula: "+matricula ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							fornecDao.excluirFornecedores(mat);
							atualizaLista(table,"");
						} catch (DaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
					   }
				}
				if (coluna == 3){
					Fornecedor objFornec = new Fornecedor();

					try {
						objFornec = fornecDao.consultarFornecedorID(mat);
						atualizaFormulario(objFornec);
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
                    table.getColumnModel().getColumn(1).setPreferredWidth(220);
                    table.getColumnModel().getColumn(1).setMinWidth(220);
                    table.getColumnModel().getColumn(2).setPreferredWidth(80);
                    table.getColumnModel().getColumn(2).setMinWidth(80);
                    table.getColumnModel().getColumn(3).setPreferredWidth(70);
                    table.getColumnModel().getColumn(3).setMinWidth(70);
                    table.getColumnModel().getColumn(4).setPreferredWidth(60);
                    table.getColumnModel().getColumn(4).setMinWidth(60);
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
                                     formulario.setVisible(false);
                                     
                                     btnVoltar.addActionListener(new ActionListener() {
                                     	public void actionPerformed(ActionEvent arg0) {
                                     		lista.setVisible(true);
                                     		formulario.setVisible(false); 
                                     		buttonPanel.setVisible(true);
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
		List<Fornecedor> listaFornec  = new ArrayList<Fornecedor>();
		
		
		listaFornec = fornecDao.consultarFornecedores(nome);
		
		String dados[] = new String[3]; 
		for (Fornecedor obj : listaFornec) {
			dados[0] = String.valueOf(obj.getNumFornec()) ;
			dados[1] = obj.getNomeFornec();
			dados[2] = obj.getTelFornec();
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

	public void atualizaFormulario(Fornecedor objFornec){
		textField_2.setText(objFornec.getTelFornec());
		textField_4.setText(objFornec.getRuaFornec());
		textField_6.setText(objFornec.getNumEndFornec());
		textField_7.setText(objFornec.getBairroFornec());
		textField_8.setText(objFornec.getCidadeFornec());
		textField.setText(objFornec.getNomeFornec());
		textField_3.setText(objFornec.getCepFornec());
		textField_9.setText(objFornec.getComplFornec());
		
		Integer matr = objFornec.getNumFornec();
		textField_5.setText(matr.toString());
		
		lista.setVisible(false);
		formulario.setVisible(true);
		buttonPanel.setVisible(false);
	}
	
	public void limpaFormulario(){
		textField.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
				
	}
	
	public boolean validarFormulário(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField)){
			JOptionPane.showMessageDialog(null, "Campo Nome Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_4)){
			JOptionPane.showMessageDialog(null, "Campo Rua Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_3)){
			JOptionPane.showMessageDialog(null, "Campo CEP Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Campo Telefone Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_6)){
			JOptionPane.showMessageDialog(null, "Campo Nº Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_7)){
			JOptionPane.showMessageDialog(null, "Campo Bairro Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_8)){
			JOptionPane.showMessageDialog(null, "Campo Cidade Vazio!");
			result = false;
		}
		
		return result;
	}	

}

