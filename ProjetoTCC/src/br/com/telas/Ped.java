package br.com.telas;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import br.com.TableModel.ProtesesTableModel;
import br.com.TableModel.TableCellRenderer;
import br.com.bean.ClienteDent;
import br.com.bean.Funcionario;
import br.com.bean.Pedido;
import br.com.bean.TabeladePreco;
import br.com.dao.ClienteDao;
import br.com.dao.FuncionarioDao;
import br.com.dao.PedidoDao;
import br.com.dao.TabeladePrecoDao;
import br.com.exception.DaoException;

import br.com.util.ValidacaoUtil;


import org.jdesktop.swingx.JXDatePicker;

import java.text.SimpleDateFormat;



public class Ped extends JFrame {
	 final JPanel lista = new JPanel();
	    final static JPanel formulario = new JPanel();
	    final static JPanel carrinho = new JPanel();
	    final JPanel buttonPanel = new JPanel();
	    private PedidoDao pedidoDao = new PedidoDao();
		private static final long serialVersionUID = 1L;	
		
		int idCliente;

		private JScrollPane scrollPane;
		private JEditorPane epDescricao;
		
		private JTable table;
		private JTextField textField;
		private JTextField textTotalPed;
		private JTextField textField_5;
		private JTextField textField_6;
		private JTextField textField_7;
		private JTextField textField_8;
		private JTextField textField_9;
		private JTextField textField_1;
		private JTextField textField_cpf;
		
		private JXDatePicker dtDataPedido;
		private JXDatePicker dtDataEntrega;
		
		private JTextField textProteses;
		private JTextField textTipos;
		
		 private TabeladePrecoDao tabDao = new TabeladePrecoDao();
		    private String pegaTotal, mensagem;
		    private Double valorServ;  
		    private Double valorProt;  
		    private Double pedidoTotal;
		private JTable tabela;  
	    private JTextField textValorProt;  
	    private JTextField textValorServ;  
	    private JTextField textTotal;  	     	    
	    private ProtesesTableModel tableModel;  
	    TabeladePreco naoSelecionado;
	   // private List<Proteses> proteses;        	    
	    List<TabeladePreco> listaTab  = new ArrayList<TabeladePreco>();
	    
       // MouseListener tableMouseListener = null;

		public Ped() throws DaoException {
			setResizable(false);
			setIconImage(Toolkit.getDefaultToolkit().getImage(Ped.class.getResource("/br/com/images/logo_transp.png")));
			setTitle("Cadastro de Pedidos");
			int width = 800;
	        int height = 600;
	       // setModal(true);
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
	        lblPesquisar.setBounds(41, 59, 88, 14);
	        buttonPanel.add(lblPesquisar);
	        
	        textField_1 = new JTextField();
	        textField_1.setToolTipText("Digite o nome do dentista");
	        textField_1.setBounds(10, 75, 132, 20);
	        buttonPanel.add(textField_1);
	        textField_1.setColumns(10);
	        
	        JButton btnOk_2 = new JButton("");
	        btnOk_2.setIcon(new ImageIcon(Ped.class.getResource("/br/com/images/pesquisar.png")));
	        btnOk_2.setToolTipText("Pesquisar dentista!");
	        btnOk_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {        		
	        		try {
						atualizaLista(table,textField_1.getText().toString());
					} catch (DaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        });
	        btnOk_2.setToolTipText("Pesquisar pedido");
	        btnOk_2.setBounds(35, 102, 67, 23);
	        buttonPanel.add(btnOk_2);
	        	        
	        	        	        formulario.setBounds(152, 0, 632, 562);
	        	        	        getContentPane().add(formulario);
	        	        	        formulario.setLayout(null);
	        	        	        
	        	        	        JPanel panel = new JPanel();
	        	        	        panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Dados Sobre o Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	        	        	        panel.setLayout(null);
	        	        	        panel.setBounds(21, 35, 590, 404);
	        	        	        formulario.add(panel);
	        	        	        
	        	        	                  
	        	        	                  

	        	        	           
	        	        	                  JLabel lblNmeroCliente = new JLabel("N\u00FAmero Cliente:");
	        	        	                  lblNmeroCliente.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblNmeroCliente.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblNmeroCliente.setBounds(-14, 31, 132, 18);
	        	        	                  panel.add(lblNmeroCliente);
	        	        	                  
	        	        	                  JLabel lblPaciente = new JLabel("Nome Paciente:");
	        	        	                  lblPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblPaciente.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblPaciente.setBounds(10, 60, 108, 18);
	        	        	                  panel.add(lblPaciente);
	        	        	                  
	        	        	                  JLabel lblDataPedido = new JLabel("Data Pedido:");
	        	        	                  lblDataPedido.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblDataPedido.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblDataPedido.setBounds(0, 133, 118, 18);
	        	        	                  panel.add(lblDataPedido);
	        	        	                  
	        	        	                  JLabel lblNome = new JLabel("Nome:");
	        	        	                  lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblNome.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblNome.setBounds(260, 31, 56, 18);
	        	        	                  panel.add(lblNome);
	        	        	                  
	        	        	                  JLabel lblPreo = new JLabel("Total Pedido:");
	        	        	                  lblPreo.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblPreo.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblPreo.setBounds(10, 162, 108, 18);
	        	        	                  panel.add(lblPreo);
	        	        	                  
	        	        	                  JLabel lblNProttico = new JLabel("N\u00BA Prot\u00E9tico:");
	        	        	                  lblNProttico.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblNProttico.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblNProttico.setBounds(10, 269, 108, 18);
	        	        	                  panel.add(lblNProttico);
	        	        	                  
	        	        	                  JLabel lblDataEntraga = new JLabel("Data Entrega:");
	        	        	                  lblDataEntraga.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                  lblDataEntraga.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                  lblDataEntraga.setBounds(233, 133, 118, 18);
	        	        	                  panel.add(lblDataEntraga);
	        	        	                  
	        	        	                   
	        	        	                  JButton button = new JButton("");
	                                          button.setToolTipText("Salvar Alt+S");
	                                          button.setMnemonic(KeyEvent.VK_S);
	                                          button.setIcon(new ImageIcon(Ped.class.getResource("/br/com/images/salvar.png")));
	                                          button.setBounds(491, 370, 70, 23);
	        	        	                  panel.add(button);
	        	        	                   
	        	        	                   button.addActionListener(new ActionListener() {					
						
						public void actionPerformed(ActionEvent arg0) {
							if( validarFormulário() ){
							Pedido obj = new Pedido();
							
							SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							
								
								try {
									obj.setDataPedido(df.parse(dtDataPedido.getEditor().getText())); //_2
									obj.setDataEntrega(df.parse(dtDataEntrega.getEditor().getText())); //10
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
								
								obj.setTotalPedido(Double.parseDouble(textTotalPed.getText()));
								obj.setNomeProtese(textProteses.getText());
								obj.setTipoProtese(textTipos.getText());
								obj.setNomeFunc(textField_6.getText());
								obj.setNumFunc(Integer.parseInt(textField_7.getText()));
								obj.setNomeCliente(textField.getText());
								obj.setNumCliente(Integer.parseInt(textField_8.getText()));
								obj.setNomePaciente(textField_9.getText());
								obj.setCpfCliente(textField_cpf.getText());
								obj.setObservacoesPed(epDescricao.getText());
								//falta o de observações
								
								PedidoDao objDAO = new PedidoDao();
								try {
									
									if(textField_5.getText().equals("")){
										objDAO.inserirPedidos(obj);
										JOptionPane.showMessageDialog(formulario, "Dados salvos com sucesso!");									
									}else{
										Integer matr = Integer.parseInt(textField_5.getText()); 
										obj.setNumPed(matr);
										objDAO.atualizarPedido(obj);
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
	        	        	                   button_1.setIcon(new ImageIcon(Ped.class.getResource("/br/com/images/limpar.png")));
	                                           button_1.setToolTipText("Limpar Alt+L");
	                                           button_1.setMnemonic(KeyEvent.VK_L);
	        	        	                   button_1.setBounds(398, 370, 70, 23);
	        	        	                   panel.add(button_1);
	        	        	                   button_1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							limpaFormulario();
							
						}
					});
	        	        	                   
	        	        	                   textField = new JTextField();
	        	        	                   textField.setBounds(326, 31, 222, 20);
	        	        	                   panel.add(textField);
	        	        	                   textField.setColumns(10);
	        	        	                   
//	        	        	                   try {
//												textField_2 = new JFormattedTextField(MascaraUtil.setMaskDateInTf(textField_2));
//											} catch (ParseException e2) {
//												// TODO Auto-generated catch block
//												e2.printStackTrace();
//											}
//	        	        	                   textField_2.setBounds(128, 133, 95, 20);
//	        	        	                   panel.add(textField_2);
//	        	        	                   textField_2.setColumns(10);
	        	        	                   
	        	        	                //   textTotalPed = new JFormattedTextField(MascaraUtil.setMascara("R$###.##"));
	        	        	                   
	        	        	                   textTotalPed = new JTextField(pegaTotal);
	        	        	                   textTotalPed.setBounds(128, 162, 95, 20);
	        	        	                   panel.add(textTotalPed);
	        	        	                   textTotalPed.setText(pegaTotal);
	        	        	                   textTotalPed.setColumns(10);
	        	        	                     
	        	        	                     textField_7 = new JTextField();
	        	        	                     textField_7.setBounds(128, 269, 70, 20); 
	        	        	                     panel.add(textField_7);
	        	        	                     textField_7.setColumns(10);
	        	        	                     
	        	        	                     textField_8 = new JTextField();
	        	        	                     textField_8.setBounds(128, 31, 70, 20); 
	        	        	                     panel.add(textField_8);
	        	        	                     textField_8.setColumns(10);
	        	        	                     
	        	        	                     textField_5 = new JTextField();
	        	        	                     textField_5.setVisible(false);
	        	        	                     textField_5.setText("");
	        	        	                     panel.add(textField_5);
	        	        	                     
	        	        	                     textField_cpf = new JTextField();
	        	        	                     textField_cpf.setVisible(false);
	        	        	                     textField_cpf.setText("");
	        	        	                     panel.add(textField_cpf);
	        	        	                     
	        	        	                     textField_9 = new JTextField();
	        	        	                     textField_9.setBounds(128, 60, 420, 20);
	        	        	                     panel.add(textField_9);	                   
	        	        	                     textField_9.setColumns(10);
	        	        	                     
	        	        	                     
	        	        	                     dtDataPedido = new JXDatePicker();
	        	        	                     dtDataPedido.getEditor().setToolTipText("Data do pedido!");
//	        	        	                     dtDataPedido.setName("Data de pedido. Campo ObrigatÃ³rio.");
	        	        	                     dtDataPedido.getEditor();
	        	        	                     dtDataPedido.setFormats(new String[] {"dd/MM/yyyy"});
	        	        	                     dtDataPedido.setBounds(128, 133, 95, 20);
	        	        	             		 panel.add(dtDataPedido);
	        	        	             		 
	        	        	             		 
	        	        	             		 dtDataEntrega = new JXDatePicker();
	        	        	             	     dtDataEntrega.getEditor().setToolTipText("Data prevista para entrega!");
//	        	        	             		 dtDataEntrega.setName("Data de nascimento. Campo ObrigatÃ³rio.");
	        	        	             		 dtDataEntrega.getEditor();
	        	        	             		 dtDataEntrega.setFormats(new String[] {"dd/MM/yyyy"});
	        	        	             		 dtDataEntrega.setBounds(361, 133, 95, 20);
	        	        	             		 panel.add(dtDataEntrega);
	        	        	                     
	        	        	                     JButton btnOk = new JButton("OK");
	        	        	                     btnOk.addActionListener(new ActionListener() {
	        	        	                     	public void actionPerformed(ActionEvent arg0) {
	        	        	                     		String aux = textField_8.getText();
	        	        	                     		if(textField_8.getText().equals("")){
	        	        	            					JOptionPane.showMessageDialog(null, "Digite um número!");
	        	        	            				}else if(aux.matches("^[0-9]*$")){
	        	        	                     			//chamar o método para pesquisar atraves do número do cliente	                   		 
		        	        	                     		chamaCliente(Integer.parseInt(textField_8.getText()));
	        	                                  		}else{
	        	                                  			JOptionPane.showMessageDialog(null, "Digite apenas número!");
	        	                                  		}
	        	        	                     		
	        	        	                     	}
	        	        	                     });
	        	        	                     btnOk.setBounds(208, 30, 56, 23);
	        	        	                     panel.add(btnOk);
	        	        	                     
	        	        	                     JButton btnOk_1 = new JButton("OK");
	        	        	                     btnOk_1.addActionListener(new ActionListener() {
	        	        	                     	public void actionPerformed(ActionEvent e) {
	        	        	                     		String aux = textField_7.getText();
	        	        	                     		if(textField_7.getText().equals("")){
	        	        	            					JOptionPane.showMessageDialog(null, "Digite um número!");
	        	        	            				}else if(aux.matches("^[0-9]*$")){
	        	        	                     			//chamar o método para pesquisar atraves do número do funcionário	                   		 
	        	        	                     			chamaFuncionario(Integer.parseInt(textField_7.getText()));
	        	                                  		}else{
	        	                                  			JOptionPane.showMessageDialog(null, "Digite apenas número!");
	        	                                  		}
	        	        	                     		
	        	        	                     	}
	        	        	                     });
	        	        	                     btnOk_1.setBounds(208, 268, 56, 23);
	        	        	                     panel.add(btnOk_1);
	        	        	                     
	        	        	                     JLabel lblNomeProttico = new JLabel("Nome Prot\u00E9tico:");
	        	        	                     lblNomeProttico.setBounds(274, 269, 118, 18);
	        	        	                     panel.add(lblNomeProttico);
	        	        	                     lblNomeProttico.setHorizontalAlignment(SwingConstants.RIGHT);
	        	        	                     lblNomeProttico.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                     
	        	        	                     textField_6 = new JTextField();
	        	        	                     textField_6.setBounds(400, 269, 148, 20);
	        	        	                     panel.add(textField_6);
	        	        	                     textField_6.setColumns(10);
	        	        	                     
	        	        	                     Button button_2 = new Button("Selecionar Pr\u00F3teses");
	        	        	                     button_2.addActionListener(new ActionListener() {
	        	        	                     	public void actionPerformed(ActionEvent e) {	        	        	                     		
	        	        	                     		carrinho.setVisible(true);
	        	        	                     		formulario.setVisible(false);
	        	        	                     		limpaFormularioPedido();
	        	        	                     		
	        	        	                     	}
	        	        	                     });
	        	        	                     button_2.setBounds(233, 98, 121, 22);
	        	        	                     panel.add(button_2);
	        	        	                     
	        	        	                     JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
	        	        	                     lblObservaes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        	                     lblObservaes.setBounds(32, 321, 108, 14); 
	        	        	                     panel.add(lblObservaes);
	        	        	                     textTotalPed.setText(mensagem);
	        	        	                     
	        	        	                       JLabel lblPrtese = new JLabel("Pr\u00F3tese(s):");
	        	        		                   lblPrtese.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        		                   lblPrtese.setBounds(46, 195, 89, 14);
	        	        		                   panel.add(lblPrtese);
	        	        		                   
	        	        		                   textProteses = new JTextField();
	        	        		                   textProteses.setBounds(128, 193, 420, 20);
	        	        		                   panel.add(textProteses);
	        	        		                   textProteses.setColumns(10);
	        	        		                   
	        	        		                   JLabel lblTipos = new JLabel("Tipo(s):");
	        	        		                   lblTipos.setFont(new Font("Arial Black", Font.PLAIN, 12));
	        	        		                   lblTipos.setBounds(66, 220, 56, 20);
	        	        		                   panel.add(lblTipos);
	        	        		                   
	        	        		                   textTipos = new JTextField();
	        	        		                   textTipos.setBounds(128, 220, 420, 20);
	        	        		                   panel.add(textTipos);
	        	        		                   textTipos.setColumns(10);
	        	        		                   
	        	        		                   scrollPane = new JScrollPane();
	        	        		                   scrollPane.setBounds(128, 300, 420, 59);
	        	        		           			panel.add(scrollPane);
	        	        		           		
	        	        		           			epDescricao = new JEditorPane();
	        	        		           			epDescricao.setToolTipText("Observações sobre o pedido.");
	        	        		           			epDescricao.setName("Observações");
	        	        		           			scrollPane.setViewportView(epDescricao);
	        	        		                   
	        	        		                   JLabel lblTodosOsCampos = new JLabel("Todos os campos s\u00E3o obrigat\u00F3rios!");
	        	        		                   lblTodosOsCampos.setForeground(Color.RED);
	        	        		                   lblTodosOsCampos.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        	        		                   lblTodosOsCampos.setBounds(130, 379, 242, 14);
	        	        		                   panel.add(lblTodosOsCampos);
	        	        	                     
	        	        	                     JButton btnVoltar = new JButton("");
	        	        	                     btnVoltar.setIcon(new ImageIcon(Ped.class.getResource("/br/com/images/voltar.png")));
	                                             btnVoltar.setToolTipText("Voltar");
	        	        	                     btnVoltar.setBounds(25, 450, 89, 23);
	        	        	                     formulario.add(btnVoltar);
	        	        	                     formulario.setVisible(false);	        	        	                
	        	        	                     
	        	        	                     btnVoltar.addActionListener(new ActionListener() {
	        	        	                     	public void actionPerformed(ActionEvent arg0) {
	        	        	                     		lista.setVisible(true);
	        	        	                     		formulario.setVisible(false); 
	        	        	                     		buttonPanel.setVisible(true);
	        	        	                     		carrinho.setVisible(false);
	        	        	                     	}
	        	        	                     });
	        	        

	        	        lista.setBounds(152, 0, 656, 562);
	        	        getContentPane().add(lista);
	        	        lista.setLayout(null);
	        	        
	        	        JLabel lblFuncionriosCadastrados1 = new JLabel("Pedidos Agendados");
	        	        lblFuncionriosCadastrados1.setFont(new Font("Kalinga", Font.BOLD, 16));
	        	        lblFuncionriosCadastrados1.setHorizontalAlignment(SwingConstants.CENTER);
	        	        lblFuncionriosCadastrados1.setBackground(Color.WHITE);
	        	        lblFuncionriosCadastrados1.setBounds(10, 11, 612, 29);
	        	        lista.add(lblFuncionriosCadastrados1);
	        	        


	        	          
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
					if(coluna == 6){
						int opcao;
						opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+ matricula ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
						   if(opcao == JOptionPane.YES_OPTION){  
							   try {
								pedidoDao.excluirPedidos(mat);
								atualizaLista(table,"");
							} catch (DaoException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
								JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
						   }
					}
					if (coluna == 5){
						Pedido objPedido = new Pedido();

						try {
							objPedido = pedidoDao.consultarPedidoID(mat);
							atualizaFormulario(objPedido);
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
	        	                  		"Número", "Dentista", "Paciente", "Data Entrega", "Observações", "Editar","Excluir"
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
	        	          table.getColumnModel().getColumn(4).setPreferredWidth(100);
	        	          table.getColumnModel().getColumn(4).setMinWidth(100);	
	        	          table.setBounds(39, 175, 530, 232);
	        	          atualizaLista(table,"");
	        	          
	        	          
	        	          JScrollPane scrollPane1 = new JScrollPane();
	        	          scrollPane1.setBounds(10, 51, 636, 473);
	        	          lista.add(scrollPane1);
	        	          
	        	                         scrollPane1.setViewportView(table);
	        	                         
	        	                                                  
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
	        	        

	        	                         	       
	        	                         	    	    	        	                         	    	 

	        	        carrinho.setBounds(152, 0, 656, 562);
	        	        getContentPane().add(carrinho);
	        	        carrinho.setLayout(null);
	        	        JLabel lblFuncionriosCadastrados = new JLabel("Selecione as Próteses");  
	        	        lblFuncionriosCadastrados.setFont(new Font("Kalinga", Font.BOLD, 16));  
	        	        lblFuncionriosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);  
	        	        lblFuncionriosCadastrados.setBackground(Color.WHITE);  
	        	        lblFuncionriosCadastrados.setBounds(10, 11, 612, 29);  
	        	        carrinho.add(lblFuncionriosCadastrados);  
	        	        carrinho.setVisible(true);    
	        	        
	        	        MouseListener tableMouseListener = new MouseListener() {
              	    	  //      tableMouseListener = new MouseListener() {
              	    	        @Override  
              	    	        public void mouseReleased(MouseEvent arg0) {}  
              	    	        @Override  
              	    	        public void mousePressed(MouseEvent arg0) {}  
              	    	        @Override  
              	    	        public void mouseExited(MouseEvent arg0) {}  
              	    	        @Override  
              	    	        public void mouseEntered(MouseEvent arg0) {}  
              	    	        @Override 
              	    	        public void mouseClicked(MouseEvent arg0) {
              	    	        	Double precoProtese = 0.0 ;    
              	    	            List<TabeladePreco> protesesTable = tableModel.getProteses();  
              	    	              
              	    	            //for each  
              	    	            for(TabeladePreco protesesIn : protesesTable){  
              	    	                  
              	    	                //está selecionado  
              	    	                if(protesesIn.isSelecione() == true)  
              	    	                precoProtese+=protesesIn.getPrecoProtese();  
              	    	            }  

              	    	        textValorProt.setText(String.valueOf(precoProtese));
              	    	        textValorProt.setEditable(false);
              	    	   //     protesesTable.isSelecione = false;
              	    	     /* for(TabeladePreco protesesIn : protesesTable){ 
              	    	        if(protesesIn.isSelecione() == true)  {
              	    	        	for(int i = 0; i < tabela.getRowCount(); i++){
              	    	        		tableModel.setValueAt(false, i, 4);
              	    	        }
        	    	                precoProtese+=protesesIn.getPrecoProtese();  
        	    	            }  
              	    	      
              	    	      }*/
              	    	        }
              	    	    };
              	    	    
	        	        tabela = new JTable(tableModel); 
	        	        listaTab = new ArrayList<TabeladePreco>();            
	        	        tableModel = new ProtesesTableModel(listaTab); 
	        	        tabela.setModel(tableModel);
	        	         try {
	        	 			refreshTable();
	        	 		} catch (DaoException e) {
	        	 			// TODO Auto-generated catch block
	        	 			e.printStackTrace();
	        	 		}	        	             	        	         	        	   
                	    	    
	        	                tabela.addMouseListener(tableMouseListener);  
	        	                tabela.setPreferredScrollableViewportSize(tabela.getPreferredSize());  
	        	                JScrollPane scrollPane = new JScrollPane(tabela);  
	        	                scrollPane.setBounds(0, 50, 636, 423);  
	        	                carrinho.add(scrollPane);  
	        	                tabela.getColumnModel().getColumn(0).setPreferredWidth(55);  
	        	                tabela.getColumnModel().getColumn(0).setMinWidth(55);  
	        	                tabela.getColumnModel().getColumn(1).setPreferredWidth(200);  
	        	                tabela.getColumnModel().getColumn(1).setMinWidth(200);  
	        	                tabela.getColumnModel().getColumn(2).setPreferredWidth(80);  
	        	                tabela.getColumnModel().getColumn(2).setMinWidth(80);  
	        	                tabela.getColumnModel().getColumn(3).setPreferredWidth(100);  
	        	                tabela.getColumnModel().getColumn(3).setMinWidth(100);  
	        	                tabela.getColumnModel().getColumn(4).setPreferredWidth(100);  
	        	                tabela.getColumnModel().getColumn(4).setMinWidth(100);  
	        	                
	        	                tabela.setBounds(39, 175, 530, 232); 	        	               
	        	                scrollPane.setViewportView(tabela);  
	        	                	        	                
	        	                                                                
	        	                                                                      textValorProt = new JTextField();  
	        	                                                                      carrinho.add(textValorProt);
	        	                                                                      textValorProt.setBounds(210, 485, 86, 20);
	        	                                                                      textValorProt.setColumns(10);  
	        	                                                                      
	        	                                                                            textValorServ = new JTextField();  
	        	                                                                            carrinho.add(textValorServ);
	        	                                                                            textValorServ.setBounds(358, 484, 86, 20);
	        	                                                                            textValorServ.setColumns(10);  
	        	                                                                            
	        	                                                                                  JLabel lblSoma = new JLabel("Valor Pr\u00F3tese(s):");  
	        	                                                                                  carrinho.add(lblSoma);
	        	                                                                                  lblSoma.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        	                                                                                  lblSoma.setBounds(109, 487, 106, 14);
	        	                                                                                  
	        	                                                                                        JLabel lblService = new JLabel("Servi\u00E7o:");  
	        	                                                                                        carrinho.add(lblService);
	        	                                                                                        lblService.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        	                                                                                        lblService.setBounds(306, 487, 72, 14);
	        	                                                                                        
	        	                                                                                        
	        	                                                                                        ActionListener somarActionListener = new ActionListener() {  
	        	                                                                                        	 //   somarActionListener = new ActionListener() {         
	        	                                                                                        	        public void actionPerformed(ActionEvent arg0) {  
	        	                                                                                        	            try{  
	        	                                                                                        	                if(textValorServ.getText().trim().length() > 0 ){
	        	                                                                                        	                	String teste;
	        	                                                                                        	                	teste = textValorServ.getText();
	        	                                                                                        	                	if(teste.matches("^[0-9]*$")){
	        	                                                                                        	                		valorServ = Double.parseDouble(textValorServ.getText());
	        	                                                                                        	                	}else{
	        	                                                                                        	                		JOptionPane.showMessageDialog(null, "Digite apenas número!");
	        	                                                                                        	                	}
	        	                                                                                        	                }	        	                                                                                        	                    
	        	                                                                                        	                else  
	        	                                                                                        	                    valorServ=0D;  
	        	                                                                                        	  
	        	                                                                                        	                if(textValorProt.getText().trim().length() > 0)  
	        	                                                                                        	                    valorProt = Double.parseDouble(textValorProt.getText());  
	        	                                                                                        	                else  
	        	                                                                                        	                    valorProt=0D;  
	        	                                                                                        	  
	        	                                                                                        	                pedidoTotal = valorServ + valorProt;  
	        	                                                                                        	            }catch (Exception e) {  
	        	                                                                                        	                e.printStackTrace();  
	        	                                                                                        	            }  
	        	                                                                                        	            textTotal.setText(String.valueOf(pedidoTotal));  
	        	                                                                                        	            textTotal.setEditable(false);
	        	                                                                                        	        }  
	        	                                                                                        	    };
	        	                                                                                              JButton btnSomar = new JButton("Somar");  
	        	                                                                                              carrinho.add(btnSomar);
	        	                                                                                              btnSomar.addActionListener(somarActionListener);  
	        	                                                                                              btnSomar.setBounds(454, 484, 89, 23);
	        	                                                                                              
	        	                                                                                              
	        	                                                                                              JLabel lblTotal = new JLabel("Total:");
	        	                                                                                              carrinho.add(lblTotal);
	        	                                                                                              lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        	                                                                                              lblTotal.setBounds(109, 522, 55, 14);
	        	                                                                                              
	        	                                                                                                    textTotal = new JTextField();  
	        	                                                                                                    carrinho.add(textTotal);
	        	                                                                                                    textTotal.setBounds(150, 520, 86, 20);
	        	                                                                                                    textTotal.setColumns(10);  
	        	                                                                                                   
	        	                                                                                                    JButton btnVoltar_1 = new JButton("Voltar");
	        	                          	        	                                                          btnVoltar_1.setBounds(424, 522, 89, 23);
	        	                          	        	         	        	        	                     carrinho.add(btnVoltar_1);
	        	                          	        	         	        	        	                     carrinho.setVisible(false);
	        	                          	        	         	        	        	                     
	        	                          	        	         	        	        	                    btnVoltar_1.addActionListener(new ActionListener() {
	        	                          	        	         	        	        	                     	public void actionPerformed(ActionEvent arg0) {
	        	                          	        	         	        	        	                     		lista.setVisible(false);
	        	                          	        	         	        	        	                     		formulario.setVisible(true); 
	        	                          	        	         	        	        	                     		buttonPanel.setVisible(true);
	        	                          	        	         	        	        	                     		carrinho.setVisible(false);
	        	                          	        	         	        	        	                     	}
	        	                          	        	         	        	        	                     });
	        	                          	        	         	        	        	                    
	        	                                                                                                    JButton btnSalvar = new JButton("Salvar");
	        	                                                                                                    carrinho.add(btnSalvar);
	        	                                                                                                    btnSalvar.addActionListener(new ActionListener() {
	        	                                                                                                    	public void actionPerformed(ActionEvent arg0) {
	        	                                                                                                    		if(textTotal.getText().equals("")){
	        	                                                                                                    			JOptionPane.showMessageDialog(null, "Por gentileza, clicar no botão Somar!");
	        	                                                                                                    		}else
	        	                                                                                                    		pegaTotalPedido("", textTotal);
	        	                                                                                                    		        		
	        	                                                                                                    	}
	        	                                                                                                    });
	        	                                                                                                    btnSalvar.setBounds(289, 522, 89, 23);
	        	                                                          carrinho.setVisible(false);
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
			columnModel.getColumn(5).setCellRenderer(renderer);
			
			renderer1.setValue(excluir);
			renderer1.setHorizontalAlignment(JLabel.CENTER);
			columnModel.getColumn(6).setCellRenderer(renderer1);

	        dtm.setRowCount(0); 
			List<Pedido> listaPedido  = new ArrayList<Pedido>();
	 		listaPedido = pedidoDao.consultarPedidos(nome);
	 		String dados[] = new String[5]; 
			for (Pedido obj : listaPedido) {
				dados[0] = String.valueOf(obj.getNumPed()) ;
				dados[1] = obj.getNomeCliente();
				dados[2] = obj.getNomePaciente();
				dados[3] = String.valueOf(obj.getDataEntrega()); //mais uma coluna para mais informações
				dados[4] = obj.getObservacoesPed();
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

		public void atualizaFormulario(Pedido objPedido){
								
			textField_6.setText(objPedido.getNomeFunc()); 
			textField_7.setText(String.valueOf(objPedido.getNumFunc()));
			
			textField_8.setText(String.valueOf(objPedido.getNumCliente()));
			textField.setText(objPedido.getNomeCliente());
			textField_cpf.setText(objPedido.getCpfCliente());
						
			textField_9.setText(objPedido.getNomePaciente());
			
			dtDataPedido.setDate(objPedido.getDataPedido());
			dtDataEntrega.setDate(objPedido.getDataEntrega());			
			
			epDescricao.setText(objPedido.getObservacoesPed());
			textTotalPed.setText(String.valueOf(objPedido.getTotalPedido()));
			textProteses.setText(objPedido.getNomeProtese());
			textTipos.setText(objPedido.getTipoProtese());
			
			Integer matr = objPedido.getNumPed();
			textField_5.setText(matr.toString());
			
			lista.setVisible(false);
			formulario.setVisible(true);
		}
		
		public void limpaFormulario(){
			textField.setText("");
			dtDataPedido.getEditor().setText("");
			textTotalPed.setText("");
			textField_5.setText("");
			textField_6.setText("");
			textField_7.setText("");
			textField_8.setText("");
			textField_9.setText("");
			dtDataEntrega.getEditor().setText("");
			textField_cpf.setText("");
			textProteses.setText("");
			textTipos.setText("");
			epDescricao.setText("");
			
		}
		public boolean validarFormulário(){
			boolean result = true;

			if(!ValidacaoUtil.textFieldVazio(textField_9)){
				JOptionPane.showMessageDialog(null, "Campo Nome Paciente Vazio!");
				result = false;
			}
			if(!ValidacaoUtil.textFieldVazio(dtDataPedido)){
				JOptionPane.showMessageDialog(null, "Campo Data Pedido Vazio!");
				result = false;
			}
			if(!ValidacaoUtil.textFieldVazio(dtDataEntrega)){
				JOptionPane.showMessageDialog(null, "Campo Data Entrega Vazio!");
				result = false;
			}

		
			return result;
		}	
		/**
		 * Faz a pesquisa do cliente para inserir o nome dele no formulário do pedido
		 * @param numero
		 */
		private void chamaCliente(int idCliente) {
			ClienteDent objCliente = new ClienteDent();
	
			ClienteDao objDao = new ClienteDao();				
			
			try {
				objCliente = objDao.procurarClienteID(idCliente);
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
			textField.setText(objCliente.getNomeCliente());
			textField_cpf.setText(objCliente.getCpfCliente());

		}
		private void chamaFuncionario(int idFunc) {
			Funcionario objFunc = new Funcionario();
	
			FuncionarioDao objDao = new FuncionarioDao();				
			
			try {
				objFunc = objDao.procurarFuncionarioID(idFunc);
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
			textField_6.setText(objFunc.getNomeFunc());

		}
		
		public void pegaTotalPedido(String pegaTotal, JTextField textTotal){
			pegaTotal = textTotal.getText();
			carrinho.setVisible(false);
			
			formulario.setVisible(true);
			atualizaFormularioProt(pegaTotal, "");
			       			
		//	return pegaTotal;
		}
		public void atualizaFormularioProt(String pegaTotal, String mensagem){
			
			textTotalPed.setText(pegaTotal);
			textTotalPed.setEditable(false);
			
			String mensagemNome = "",  mensagemTipo = ""; //Isso é para colocar em Observações
			
			Double precoProtese = 0.0 ;    
            List<TabeladePreco> protesesTable = tableModel.getProteses();  
              
            //for each  
            for(TabeladePreco protesesIn : protesesTable){  
                  
                //está selecionado  
                if(protesesIn.isSelecione() == true)  {                	
                	mensagemNome += " " + protesesIn.getNomeProtese() + " / "; 
                	mensagemTipo += " " +  protesesIn.getTipoProtese() + " / ";
                }
                
    			textProteses.setText(mensagemNome); 
    			textTipos.setText(mensagemTipo);
            }  

        textValorProt.setText(String.valueOf(precoProtese)); 
			
			lista.setVisible(false);
			formulario.setVisible(true);
		}
		public void refreshTable() throws DaoException{      
			tabela.setDefaultRenderer(Object.class, new TableCellRenderer());
			listaTab = tabDao.consultarProteses();    
			tableModel.deleteAll();  
			tableModel.insertAll(listaTab);  
	   } 
		public void limpaFormularioPedido(){
			textValorServ.setText("");
			textValorProt.setText("");
			textTotal.setText("");
			for(int i = 0; i < tabela.getRowCount(); i++){
				tableModel.setValueAt(false, i, 4);
			}
			
			
		//	naoSelecionado.removeAll();
			
					
//			naoSelecionado.setSelecione(false); //colocar falso nos selecionáveis
		}
}