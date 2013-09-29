package br.com.telas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
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
import br.com.bean.FolhaPagamento;
import br.com.bean.Pedido;
import br.com.dao.FolhadePagamentoDao;
import br.com.dao.PedidoDao;
import br.com.bean.Funcionario;
import br.com.dao.FuncionarioDao;
import br.com.exception.DaoException;

import br.com.relatorio.FolhaControle;
import br.com.util.ValidacaoUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TelaFolhadePagamento extends JFrame {
    final JPanel lista = new JPanel();
    final JPanel formulario = new JPanel();
    final JPanel buttonPanel = new JPanel();
    private FolhadePagamentoDao folhaDao = new FolhadePagamentoDao();
    private FuncionarioDao funcDao = new FuncionarioDao();
    private PedidoDao pedDao = new PedidoDao();
    private Pedido objPed = new Pedido();
	private static final long serialVersionUID = 1L;	

	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JFormattedTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_9;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_6;
	
	private Double salario, salarioTotal;
	private Double bonus = 0.0;
	private Double porCentCom = 0.0;
	private Double total = 0.0;
	private Double totalCom = 0.0;
	private JTextField textField_7;

	public TelaFolhadePagamento() throws DaoException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFolhadePagamento.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Folha de Pagamento");
		int width = 800;
        int height =600;
      //  setModal(true);
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
        
        JButton btnPesquisar = new JButton("");
        btnPesquisar.setIcon(new ImageIcon(TelaFolhadePagamento.class.getResource("/br/com/images/pesquisar.png")));
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					atualizaLista(table,textField_7.getText().toString());
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnPesquisar.setToolTipText("Pesquisar Pagamento");
        btnPesquisar.setBounds(48, 107, 50, 23);
        buttonPanel.add(btnPesquisar);
        
        textField_7 = new JTextField();
        textField_7.setBounds(10, 76, 132, 20);
        buttonPanel.add(textField_7);
        textField_7.setColumns(10);
        
        JLabel lblPesquisar = new JLabel("Pesquisar");
        lblPesquisar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        lblPesquisar.setBounds(40, 58, 79, 14);
        buttonPanel.add(lblPesquisar);
                         

                         formulario.setBounds(152, 0, 632, 562);
                         getContentPane().add(formulario);
                         formulario.setLayout(null);
                         
                         JPanel panel = new JPanel();
                         panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                         panel.setLayout(null);
                         panel.setBounds(21, 33, 590, 290);
                         formulario.add(panel);
                         
                         JLabel lblNome = new JLabel("Nome:");
                         lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
                         lblNome.setFont(new Font("Arial Black", Font.PLAIN, 12));
                         lblNome.setBounds(0, 89, 70, 18);
                         panel.add(lblNome);
                         
                         
                         JLabel lblSalrio = new JLabel("Sal\u00E1rio:");
                         lblSalrio.setHorizontalAlignment(SwingConstants.RIGHT);
                         lblSalrio.setFont(new Font("Arial Black", Font.PLAIN, 12));
                         lblSalrio.setBounds(0, 171, 70, 18);
                         panel.add(lblSalrio);
                         
                         JLabel lblProfisso = new JLabel("Profiss\u00E3o:");
                         lblProfisso.setHorizontalAlignment(SwingConstants.RIGHT);
                         lblProfisso.setFont(new Font("Arial Black", Font.PLAIN, 12));
                         lblProfisso.setBounds(0, 129, 70, 18);
                         panel.add(lblProfisso);
                         
                         JLabel label_5 = new JLabel("Nº:");
                         label_5.setHorizontalAlignment(SwingConstants.RIGHT);
                         label_5.setFont(new Font("Arial Black", Font.PLAIN, 12));
                         label_5.setBounds(10, 45, 56, 18);
                         panel.add(label_5);
                         
                          
                          JButton button = new JButton("Salvar");
                          button.setBounds(491, 254, 89, 23);
                          panel.add(button);
                          
                          button.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
					  					  
					  if(validarFormulário()){
						FolhaPagamento obj = new FolhaPagamento();
							
							//obj.setSalarioFunc(Double.parseDouble(MascaraUtil.hideMascaraMoeda(textField_3)));
							obj.setSalarioFunc(Double.parseDouble(textField_3.getText()));
							obj.setComissaoFuncTotal(Double.parseDouble(textField_2.getText()));
							//obj.setBonusFunc(Double.parseDouble(MascaraUtil.hideMascaraMoeda(textField_4)));
							obj.setBonusFunc(Double.parseDouble(textField_4.getText()));
						//	obj.setTotalFunc(Double.parseDouble(MascaraUtil.hideMascaraMoeda(textField_6))); 
							obj.setTotalFunc(Double.parseDouble(textField_6.getText()));
							obj.setNomeFunc(textField_9.getText());
							obj.setNumFunc(Integer.parseInt(textField.getText()));
							obj.setProfissaoFunc(textField_1.getText());
							
							FolhadePagamentoDao objDAO = new FolhadePagamentoDao();
							try {							
								
								if(textField_5.getText().equals("")){
									objDAO.inserirPagamento(obj);
									JOptionPane.showMessageDialog(formulario, "Dados salvos com sucesso!");	
									limpaFormulario();
								}else{
									Integer matr = Integer.parseInt(textField_5.getText()); 
									obj.setCodDep(matr);
									objDAO.atualizarPagamento(obj);
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
                          button_1.setBounds(392, 254, 89, 23);
                          panel.add(button_1);
                          button_1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						limpaFormulario();
						
					}
				});
                          
                          textField = new JTextField();
                          textField.setBounds(80, 45, 127, 20);
                          panel.add(textField);
                          textField.setColumns(10);
                          
                          textField_1 = new JTextField();
                          textField_1.setBounds(80, 129, 335, 20);
                          panel.add(textField_1);
                          textField_1.setColumns(10);
                          
                          //textField_3 = new JFormattedTextField(MascaraUtil.setMascara("R$####,##"));
                          textField_3 = new JFormattedTextField();
                        //  textField_3.setDocument(new Moeda());
                          textField_3.setBounds(80, 171, 80, 20);
                          panel.add(textField_3);
                          textField_3.setColumns(10);
                          
                          textField_5 = new JTextField();
                          textField_5.setVisible(false);
                          textField_5.setText("");
                          panel.add(textField_5);
                          
                          textField_9 = new JTextField();
                          textField_9.setBounds(80, 89, 335, 20);
                          panel.add(textField_9);
                          textField_9.setColumns(10);
                          
                          JButton btnOk = new JButton("OK");
                          btnOk.addActionListener(new ActionListener() {
                          	public void actionPerformed(ActionEvent e) {
                          		//PESQUISAR NO BANCO DE DADOS O NÚMERO DO FUNCIONÁRIO
                          		String aux1;
                          		aux1 = textField.getText();                                             		
                          	//	if(aux.contains("^[a-Z]")){ //método para verificar se contém letras
                          		if(aux1.matches("^[0-9]*$")){
                          			chamaFuncionario(Integer.parseInt(textField.getText())); 
                          		}else{
                          			JOptionPane.showMessageDialog(null, "Digite apenas número!");
                          		}
                          		                         		
                          	}
                          });
                          btnOk.setBounds(217, 44, 89, 23);
                          panel.add(btnOk);
                          
                          JLabel lblComisso = new JLabel("Comiss\u00E3o:");
                          lblComisso.setFont(new Font("Arial Black", Font.PLAIN, 12));
                          lblComisso.setBounds(170, 173, 80, 14);
                          panel.add(lblComisso);
                          
                          JLabel lblBnus = new JLabel("B\u00F4nus:");
                          lblBnus.setFont(new Font("Arial Black", Font.PLAIN, 12));
                          lblBnus.setBounds(20, 212, 50, 14);
                          panel.add(lblBnus);
                          
                          JLabel lblTotal = new JLabel("Total:");
                          lblTotal.setFont(new Font("Arial Black", Font.PLAIN, 12));
                          lblTotal.setBounds(24, 257, 46, 14);
                          panel.add(lblTotal);
                          
                          textField_2 = new JTextField();                          
                          textField_2.setToolTipText("Comiss\u00E3o calculada");
                          textField_2.setBounds(245, 171, 70, 20);
                       //   textField_2.setDocument(new Moeda());
                          panel.add(textField_2);
                          textField_2.setColumns(10);
                          
                         // textField_4 = new JFormattedTextField(MascaraUtil.setMascara("R$####,##"));
                          textField_4 = new JTextField();
                    //      textField_4.setDocument(new Moeda()); 
                          textField_4.setBounds(80, 210, 80, 20);
                          panel.add(textField_4);
                          textField_4.setColumns(10);
                          
                        //  textField_6 = new JFormattedTextField(MascaraUtil.setMascara("R$####,##"));
                          textField_6 = new JTextField();                          
                     //     textField_6.setDocument(new Moeda()); 
                          textField_6.setBounds(80, 255, 80, 20);
                          panel.add(textField_6);
                          textField_6.setColumns(10);
                          
                          JButton btnCalcular = new JButton("Calcular");
                          btnCalcular.addActionListener(new ActionListener() {
                          	public void actionPerformed(ActionEvent e) {
                          		//Calcular quanto o funcionário irá receber
                          	//	bonus = 0.0;
                          		//textField 3(salario) e 4 (bonus) 2 (comissão)
                          		if(validarFormulárioCalculo()){
                          				
                          		//		String aux = textField_4.getText().replace(",", ".").trim();
                          			//	bonus = Double.parseDouble(aux);
                          			bonus = Double.parseDouble(textField_4.getText());
                          				
                                  	//	salario = Double.parseDouble(MascaraUtil.hideMascaraMoeda(textField_3));
                                  		salario = Double.parseDouble(textField_3.getText());  
                                  		totalCom = Double.parseDouble(textField_2.getText());
                                  		
                                  		salarioTotal = totalCom + bonus + salario;
                                  		
                                  		textField_6.setText(String.valueOf(salarioTotal));
                                  		textField_6.setEditable(false);
                          		}                          	
                          		
                          		
                          	}
                          });
                          btnCalcular.setBounds(188, 209, 89, 23);
                          panel.add(btnCalcular);
                          
                          
                          JButton btnVoltar = new JButton("Voltar");
                          btnVoltar.setBounds(21, 340, 89, 23);
                          formulario.add(btnVoltar);
                          formulario.setVisible(false);
                          
                          btnVoltar.addActionListener(new ActionListener() {
                          	public void actionPerformed(ActionEvent arg0) {
                          		lista.setVisible(true);
                          		buttonPanel.setVisible(true);
                          		formulario.setVisible(false);       	
                          	}
                          });
                         

                         lista.setBounds(152, 0, 656, 562);
                         getContentPane().add(lista);
                         lista.setLayout(null);
                         
                         JLabel lblFuncionriosCadastrados = new JLabel("Pagamentos Efetuados");
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
				if(coluna == 6){
					int opcao;
					opcao = JOptionPane.showConfirmDialog(null,"Deseja excluir o registro de matricula: "+matricula ,"Cuidado!!",JOptionPane.YES_NO_OPTION);				
					   if(opcao == JOptionPane.YES_OPTION){  
						   try {
							folhaDao.excluirPagamento(mat);
							atualizaLista(table,"");
						} catch (DaoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
					   }
				}
				if (coluna == 4){
					FolhaPagamento objFolha = new FolhaPagamento();

					try {
						objFolha = folhaDao.consultarPagamentoID(mat);
						atualizaFormulario(objFolha);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//Gera a folha pra imprimir
				if (coluna == 5){
					FolhaControle controle = new FolhaControle();
					Funcionario objFunc = new Funcionario();
					
					String nome = (String) table.getValueAt(linha,1);
                     try {                    	 
                    	 objFunc = funcDao.procurarFuncionarioNome(nome);
                    	 
         				controle.gerarRelatorioFolha(objFunc.getNumFunc());
         			} catch (DaoException e1) {
         				// TODO Auto-generated catch block
         				e1.printStackTrace();
         			}
				}
				
			}
		});
                           
                           
                           
                           table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                           table.setModel(new DefaultTableModel(
        		  new Object[][] {
                                   	},
                                   	new String[] {
                                   		"Nº", "Nome", "Profissão", "Salário Total", "Editar","Relatório", "Excluir"
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
                           table.getColumnModel().getColumn(0).setPreferredWidth(50);
                           table.getColumnModel().getColumn(0).setMinWidth(50);
                           table.getColumnModel().getColumn(1).setPreferredWidth(200);
                           table.getColumnModel().getColumn(1).setMinWidth(200);
                           table.getColumnModel().getColumn(2).setPreferredWidth(80);
                           table.getColumnModel().getColumn(2).setMinWidth(80);
                           table.getColumnModel().getColumn(3).setPreferredWidth(80);
                           table.getColumnModel().getColumn(3).setMinWidth(80);
                           
                           table.getColumnModel().getColumn(4).setPreferredWidth(50);
                           table.getColumnModel().getColumn(4).setMinWidth(50);
                           table.getColumnModel().getColumn(5).setPreferredWidth(50);
                           table.getColumnModel().getColumn(5).setMinWidth(50);

                           table.getColumnModel().getColumn(6).setPreferredWidth(50);
                           table.getColumnModel().getColumn(6).setMinWidth(50);
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

        ImageIcon relatorio = new ImageIcon(CadFuncionario.class.getResource("/br/com/images/rela.png")); //mudar icon

        
		TableColumnModel columnModel = table.getColumnModel();
		
		JTableRenderer renderer = new JTableRenderer();
		JTableRenderer renderer1 = new JTableRenderer();
		JTableRenderer renderer2 = new JTableRenderer();
		
		renderer.setValue(editar);
		renderer.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(4).setCellRenderer(renderer);
		
		renderer1.setValue(excluir);
		renderer1.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(6).setCellRenderer(renderer1);
	
		renderer2.setValue(relatorio);
		renderer2.setHorizontalAlignment(JLabel.CENTER);
		columnModel.getColumn(5).setCellRenderer(renderer2);

        dtm.setRowCount(0); 
		List<FolhaPagamento> listaFolha  = new ArrayList<FolhaPagamento>();
		listaFolha = folhaDao.consultarPagamento(nome);
 		String dados[] = new String[4]; 
		for (FolhaPagamento obj : listaFolha) {
			dados[0] = String.valueOf(obj.getCodDep());
			dados[1] = obj.getNomeFunc();
			dados[2] = obj.getProfissaoFunc();
			dados[3] = String.valueOf(obj.getTotalFunc());
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

	public void atualizaFormulario(FolhaPagamento objFolha){
		textField_9.setText(objFolha.getNomeFunc());
		textField.setText(objFolha.getNumFunc().toString());
		textField_3.setText(objFolha.getSalarioFunc().toString());
		textField_1.setText(objFolha.getProfissaoFunc());
		textField_2.setText(objFolha.getComissaoFuncTotal().toString());
		textField_4.setText(objFolha.getBonusFunc().toString());
		textField_6.setText(String.valueOf(objFolha.getTotalFunc()));

		
		Integer matr = objFolha.getNumFunc();
		textField_5.setText(matr.toString());
		
		lista.setVisible(false);
		formulario.setVisible(true);
		buttonPanel.setVisible(false);
	}	
	
	public void limpaFormulario(){
		textField.setText("");
		textField_1.setText("");
		textField_3.setText("");
		textField_5.setText("");
		textField_9.setText("");
		textField_2.setText("");
		textField_4.setText("");
		textField_6.setText("");
		
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
		
		textField_9.setText(objFunc.getNomeFunc());
		textField_1.setText(objFunc.getProfissaoFunc());
		
		textField_3.setText(String.valueOf(objFunc.getSalarioFunc()));
		textField_3.getText().replace(",", ".");  
		textField_3.setEditable(false);
		
		//Gerar a comissão automaticamente
		textField_2.setText(String.valueOf(objFunc.getComissaoFunc()));
		textField_2.setEditable(false);

		try {
			objPed =	pedDao.comissaoTotalPedido(Integer.parseInt(textField.getText()));
			total = objPed.getTotalPedido();
			porCentCom = Double.parseDouble(textField_2.getText());
			totalCom = (total * porCentCom)/100 ;
			JOptionPane.showMessageDialog(null,"Comissão calculada!"+ "\nR$ " + totalCom);	
			textField_2.setText(String.valueOf(totalCom));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean validarFormulário(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField_9)){
			JOptionPane.showMessageDialog(null, "Campo Nome Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_1)){
			JOptionPane.showMessageDialog(null, "Campo Profissão Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Campo Comissão Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_3)){
			JOptionPane.showMessageDialog(null, "Campo Salário Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_4)){
			JOptionPane.showMessageDialog(null, "Campo Bônus Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_6)){
			JOptionPane.showMessageDialog(null, "Campo Total Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField)){
			JOptionPane.showMessageDialog(null, "Campo Nº Vazio!");
			result = false;
		}
				
		return result;
	}	
	
	public boolean validarFormulárioCalculo(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Campo Comissão Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_3)){
			JOptionPane.showMessageDialog(null, "Campo Salário Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_4)){
			JOptionPane.showMessageDialog(null, "Campo Bônus Vazio!");
			result = false;
		}		
				
		return result;
	}
	
	public boolean validarFormulárioGerar(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Digite a Porcentagem!");
			result = false;
		}	
				
		return result;
	}
}

