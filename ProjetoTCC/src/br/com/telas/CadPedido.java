package br.com.telas;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import br.com.TableModel.TableCellRenderer;
import br.com.bean.ClienteDent;
import br.com.bean.Funcionario;
import br.com.bean.Pedido;
import br.com.dao.ClienteDao;
import br.com.dao.FuncionarioDao;
import br.com.dao.PedidoDao;
import br.com.exception.DaoException;
import br.com.util.MascaraUtil;
import br.com.util.ValidacaoUtil;

import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

import br.com.telas.TabelaCarrinho;


public class CadPedido extends JFrame {
	 final JPanel lista = new JPanel();
	    final JPanel formulario = new JPanel();
	    final JPanel buttonPanel = new JPanel();
	    private PedidoDao pedidoDao = new PedidoDao();
		private static final long serialVersionUID = 1L;	
		
		int idCliente;

		private JTable table;
		private JTextField textField;
		private JFormattedTextField textField_2;
	//	private JFormattedTextField textTotalPed;
		private JTextField textTotalPed;
		private JTextField textField_5;
		private JTextField textField_6;
		private JTextField textField_7;
		private JTextField textField_8;
		private JTextField textField_9;
		private JFormattedTextField textField_10;
		private JTextField textField_1;
		private JTextField textField_cpf;
		private JTextArea textArea;
		private JTextField textField_3;
		private JTextField textTipos;
		
	

		public CadPedido() throws DaoException {
			setIconImage(Toolkit.getDefaultToolkit().getImage(CadPedido.class.getResource("/br/com/images/logo_transp.png")));
			setTitle("Cadastro de Pedidos");
			//setIconImage(Toolkit.getDefaultToolkit().getImage(CadFuncionario.class.getResource("/br/com/images/cadForm.jpg")));
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
	        
	        JButton btnOk_2 = new JButton("OK");
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
	        panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
	                  
	                   
	                   JButton button = new JButton("Salvar");
	                   button.setBounds(491, 370, 89, 23);
	                   panel.add(button);
	                   
	                   button.addActionListener(new ActionListener() {					
						@Override
						public void actionPerformed(ActionEvent arg0) {
							JTextField textTotal=null;
							String pega =	TabelaCarrinho.pegaTotalPedido("", textTotal);
							textTotalPed.setText(pega);
						
							if( validarFormulário() ){
							Pedido obj = new Pedido();
						//JTextField textTotal=null;
						//String pega =	TabelaCarrinho.pegaTotalPedido("", textTotal);
								obj.setDataPedido(textField_2.getText());
								obj.setDataEntrega(textField_10.getText());
							//	obj.setTotalPedido(textTotalPed.getText(TabelaCarrinho.pegaTotalPedido()));
							//	obj.setTotalPedido(textTotalPed.setText(pega)).toString();
								obj.setTotalPedido(Double.parseDouble(textTotalPed.getText()));
								obj.setNomeFunc(textField_6.getText());
								obj.setNomeCliente(textField.getText());
								obj.setNomePaciente(textField_9.getText());
								obj.setCpfCliente(textField_cpf.getText());
								obj.setObservacoesPed(textArea.getText());
								//falta o de observações
								
								PedidoDao objDAO = new PedidoDao();
								try {
									
									if(textField_5.getText().equals("")){
										objDAO.inserirPedidos(obj);
										JOptionPane.showMessageDialog(formulario, "Dados salvos com sucesso!");									
									}else{
										Integer matr = Integer.parseInt(textField_5.getText()); 
										obj.setNumCliente(matr);
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
	                   
	                   JButton button_1 = new JButton("Limpar");
	                   button_1.setBounds(398, 370, 89, 23);
	                   panel.add(button_1);
	                   button_1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							limpaFormulario();
							
						}
					});
	                   
	                   textField = new JTextField();
	                   textField.setBounds(326, 31, 254, 20);
	                   panel.add(textField);
	                   textField.setColumns(10);
	                   
	                   textField_2 = new JFormattedTextField(MascaraUtil.setMascara("##/##/####"));
	                   textField_2.setBounds(128, 133, 95, 20);
	                   panel.add(textField_2);
	                   textField_2.setColumns(10);
	                   
	               //    textTotalPed = new JFormattedTextField(MascaraUtil.setMascara("R$###.##"));
	                   textTotalPed = new JTextField();
	                   textTotalPed.setBounds(128, 162, 95, 20);
	                   panel.add(textTotalPed);
	                 //  textTotalPed.getText(TabelaCarrinho.pegaTotalPedido()); //pega da outra tela
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
	                   
	                   textField_10 = new JFormattedTextField(MascaraUtil.setMascara("##/##/####"));
	                   textField_10.setColumns(10);
	                   textField_10.setBounds(361, 133, 95, 20);
	                   panel.add(textField_10);
	                   
	                   JButton btnOk = new JButton("OK");
	                   btnOk.addActionListener(new ActionListener() {
	                   	public void actionPerformed(ActionEvent arg0) {
	                   		//chamar o método para pesquisar atraves do número do cliente	                   		 
	                   		chamaCliente(Integer.parseInt(textField_8.getText()));
	                   	}
	                   });
	                   btnOk.setBounds(208, 30, 56, 23);
	                   panel.add(btnOk);
	                   
	                   JButton btnOk_1 = new JButton("OK");
	                   btnOk_1.addActionListener(new ActionListener() {
	                   	public void actionPerformed(ActionEvent e) {
	                   		chamaFuncionario(Integer.parseInt(textField_7.getText()));
	                   	}
	                   });
	                   btnOk_1.setBounds(208, 268, 56, 23);
	                   panel.add(btnOk_1);
	                   
	                   JLabel lblNomeProttico = new JLabel("Nome Prot\u00E9tico:");
	                   lblNomeProttico.setBounds(0, 305, 118, 18);
	                   panel.add(lblNomeProttico);
	                   lblNomeProttico.setHorizontalAlignment(SwingConstants.RIGHT);
	                   lblNomeProttico.setFont(new Font("Arial Black", Font.PLAIN, 12));
	                   
	                   textField_6 = new JTextField();
	                   textField_6.setBounds(130, 305, 287, 20);
	                   panel.add(textField_6);
	                   textField_6.setColumns(10);
	                   
	                   Button button_2 = new Button("Selecionar Pr\u00F3teses");
	                   button_2.addActionListener(new ActionListener() {
	                   	public void actionPerformed(ActionEvent e) {
	                   		TabelaCarrinho carrinho;
								carrinho = new TabelaCarrinho();
								carrinho.setVisible(true);
	                   		
	                   	}
	                   });
	                   button_2.setBounds(233, 98, 121, 22);
	                   panel.add(button_2);
	                   
	                   JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
	                   lblObservaes.setFont(new Font("Arial Black", Font.PLAIN, 12));
	                   lblObservaes.setBounds(34, 353, 108, 14);
	                   panel.add(lblObservaes);
	                   
	                   JTextArea textArea_1 = new JTextArea();
	                   textArea_1.setRows(5);
	                   textArea_1.setToolTipText("Coloque as pr\u00F3teses que ser\u00E3o usadas aqui");
	                   textArea_1.setBounds(128, 336, 260, 57);
	                   panel.add(textArea_1);
	                   
	                   JLabel lblPrtese = new JLabel("Pr\u00F3tese(s):");
	                   lblPrtese.setFont(new Font("Arial Black", Font.PLAIN, 12));
	                   lblPrtese.setBounds(46, 195, 89, 14);
	                   panel.add(lblPrtese);
	                   
	                   textField_3 = new JTextField();
	                   textField_3.setBounds(128, 193, 420, 20);
	                   panel.add(textField_3);
	                   textField_3.setColumns(10);
	                   
	                   JLabel lblTipos = new JLabel("Tipo(s):");
	                   lblTipos.setFont(new Font("Arial Black", Font.PLAIN, 12));
	                   lblTipos.setBounds(66, 220, 56, 20);
	                   panel.add(lblTipos);
	                   
	                   textTipos = new JTextField();
	                   textTipos.setBounds(128, 220, 420, 20);
	                   panel.add(textTipos);
	                   textTipos.setColumns(10);
	                   
	                   JButton btnVoltar = new JButton("Voltar");
	                   btnVoltar.setBounds(25, 450, 89, 23);
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
	        
	        JLabel lblFuncionriosCadastrados = new JLabel("Pedidos Agendados");
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
					if(coluna == 5){
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
					if (coluna == 4){
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
	                  		"Matr\u00EDcula", "Dentista", "Paciente", "Data Entrega", "Editar","Excluir"
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
	          buttonPanel.setVisible(true);
	                              
	                   	                   
	                   
		}
		
		public void atualizaLista(JTable lista, String nome) throws DaoException{
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();

	        ImageIcon editar = new ImageIcon(CadFuncionario.class.getResource("/br/com/images/editar.gif"));  
	        ImageIcon excluir = new ImageIcon(CadFuncionario.class.getResource("/br/com/images/icon_excluir.png"));

			TableColumnModel columnModel = table.getColumnModel();
			
			JTableRenderer renderer = new JTableRenderer();
			JTableRenderer renderer1 = new JTableRenderer();		
			
			renderer.setValue(editar);
			renderer.setHorizontalAlignment(JLabel.CENTER);
			columnModel.getColumn(4).setCellRenderer(renderer);
			
			renderer1.setValue(excluir);
			renderer1.setHorizontalAlignment(JLabel.CENTER);
			columnModel.getColumn(5).setCellRenderer(renderer1);

	        dtm.setRowCount(0); 
			List<Pedido> listaPedido  = new ArrayList<Pedido>();
	 		listaPedido = pedidoDao.consultarPedidos(nome);
	 		String dados[] = new String[4]; 
			for (Pedido obj : listaPedido) {
				dados[0] = String.valueOf(obj.getNumPed()) ;
				dados[1] = obj.getNomeCliente();
				dados[2] = obj.getNomePaciente();
				dados[3] = obj.getDataEntrega(); //mais uma coluna para mais informações
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
			JTextField textTotalOutro =null;
			String pega =	TabelaCarrinho.pegaTotalPedido("", textTotalOutro);
			textTotalPed.setText(pega);
			
			textField_2.setText(objPedido.getDataPedido());
			//textField_1.setText(objPedido.getTipoProt()); //objTabela
			//textField_4.setText(objPedido.getPreco()); //valor serviço
			textField_6.setText(objPedido.getNomeFunc()); //CLASSE PESSOA
			//textField_7.setText(objPedido.getNumFunc());
			//textField_8.setText(objPedido.getNumCliente());
			textField.setText(objPedido.getNomeCliente());
			textField.setText(objPedido.getCpfCliente());
			//textField_3.setText(objPedido.getNomeProt()); //objTabela
			textField_9.setText(objPedido.getNomePaciente());
			textField_10.setText(objPedido.getDataEntrega());
			//falta do observações
			
			Integer matr = objPedido.getNumPed();
			textField_5.setText(matr.toString());
			
			lista.setVisible(false);
			formulario.setVisible(true);
		}
		
		public void limpaFormulario(){
			textField.setText("");
			textField_2.setText("");
			textTotalPed.setText("");
			textField_5.setText("");
			textField_6.setText("");
			textField_7.setText("");
			textField_8.setText("");
			textField_9.setText("");
			textField_10.setText("");
			textField_cpf.setText("");
		//	textArea.setText("");
			
		}
		public boolean validarFormulário(){
			boolean result = true;

			if(!ValidacaoUtil.textFieldVazio(textField_9)){
				JOptionPane.showMessageDialog(null, "Campo Nome Paciente Vazio!");
				result = false;
			}
			if(!ValidacaoUtil.textFieldVazio(textField_2)){
				JOptionPane.showMessageDialog(null, "Campo Data Pedido Vazio!");
				result = false;
			}
			if(!ValidacaoUtil.textFieldVazio(textField_10)){
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
}