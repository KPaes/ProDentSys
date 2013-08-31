package br.com.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.TableModel.TableCellRenderer;
import br.com.bean.TabeladePreco;
import br.com.dao.TabeladePrecoDao;
import br.com.enviaremail.Interface;
import br.com.exception.DaoException;

import br.com.telas.Principal;
//import br.com.util.MascaraUtil;
import br.com.util.ValidacaoUtil;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ConTabeladePreco extends JFrame {
	
	
	/**
	 * 
	 */
	private TabeladePrecoDao tabDao = new TabeladePrecoDao();
	private static final long serialVersionUID = 1L;
	//private static final Object F_Mascara = null;
	private JPanel contentPane;
	private JTable tabela;
	private JTextField textField;
	private JTextField textField_1;
	//private JFormattedTextField textField_2;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public ConTabeladePreco() throws DaoException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConTabeladePreco.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Tabela de Pre\u00E7o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10, 191, 443, 159);
		contentPane.add(scroll);

		
		//dados da tabela
		String[][] dados = new String[][] {
			};
		String[] colunas = new String[] {
				"Nº", "Nome", "Tipo", "Preço"
			};
			
		
		tabela = new JTable();
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model = new DefaultTableModel(dados,colunas);
		tabela.setModel(model);

		
		     tabela.getColumnModel().getColumn(0).setPreferredWidth(55); 
             tabela.getColumnModel().getColumn(0).setMinWidth(55);
             tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
             tabela.getColumnModel().getColumn(1).setMinWidth(200);
             tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
             tabela.getColumnModel().getColumn(2).setMinWidth(80);
             tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
             tabela.getColumnModel().getColumn(3).setMinWidth(100);
             tabela.setBounds(39, 175, 530, 232);
             atualizaLista(tabela,"");
		 
		
		scroll.setViewportView(tabela);
		
		tabela.addMouseListener(new MouseListener() {
			
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
				int linha = tabela.getSelectedRow();
				if(linha != -1 ){
				/*	String aux = (String) tabela.getValueAt(linha, 3);
					aux = aux.replace(".","");
					textField_2.setText(aux);*/
					textField.setText((String) tabela.getValueAt(linha, 1)); //nome
					textField_1.setText((String) tabela.getValueAt(linha, 2)); //tipo
					textField_2.setText((String) tabela.getValueAt(linha, 3)); //preço
				}
				
			}
		});
		
		
		JLabel lblExemploDeUtilizao = new JLabel("Formul\u00E1rio de Cadastro");
		lblExemploDeUtilizao.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 14));
		lblExemploDeUtilizao.setBounds(10, 11, 301, 26);
		contentPane.add(lblExemploDeUtilizao);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Arial", Font.BOLD, 12));
		lblNome.setBounds(20, 48, 80, 14);
		contentPane.add(lblNome);
		
		JLabel lblIdade = new JLabel("Tipo:");
		lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdade.setFont(new Font("Arial", Font.BOLD, 12));
		lblIdade.setBounds(10, 73, 90, 14);
		contentPane.add(lblIdade);
		
		JLabel lblSexo = new JLabel("Pre\u00E7o:");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setFont(new Font("Arial", Font.BOLD, 12));
		lblSexo.setBounds(20, 98, 80, 14);
		contentPane.add(lblSexo);
		
		textField = new JTextField();
		textField.setBounds(110, 46, 300, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 71, 300, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_5 = new JTextField();
        textField_5.setVisible(false);
        textField_5.setText("");
        contentPane.add(textField_5);
		
       // textField_2 = new JFormattedTextField(MascaraUtil.setMascara("R$###,##"));
        textField_2 = new JTextField();
        textField_2.setBounds(110, 96, 100, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validarFormulário()){	
					String aux1;
					aux1 = textField_2.getText();
					
					if(aux1.matches("^[0-9]*$")){
							TabeladePreco obj = new TabeladePreco();
							
							obj.setNomeProtese(textField.getText());
							obj.setTipoProtese(textField_1.getText());
						//	obj.setPrecoProtese(Double.parseDouble(MascaraUtil.hideMascaraMoeda(textField_2)));
							obj.setPrecoProtese(Double.parseDouble(textField_2.getText()));
							TabeladePrecoDao objDAO = new TabeladePrecoDao();
							try {
								
								if(textField_5.getText().equals("")){
								textField_5.getText();
									objDAO.inserirTabeladePreco(obj);
									
									JOptionPane.showMessageDialog(rootPane, "Dados salvos com sucesso!");	
									
								}else{								
									JOptionPane.showMessageDialog(rootPane, "Dados atualizados com sucesso!");
								}
								limpaFormulario();
								atualizaLista(tabela,"");
							} catch (DaoException e) {
								e.printStackTrace();
							}
					}else{
						JOptionPane.showMessageDialog(null, "Digite apenas número no campo preço!");
					}
			}
			}
		});
		btnNewButton.setBounds(110, 127, 100, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int numLinha  = tabela.getSelectedRow();
				if(numLinha != -1){
					String matricula = (String) tabela.getValueAt(numLinha,0);
					Integer mat = Integer.parseInt(matricula);					
					try {
						tabDao.excluirTabeladePreco(mat);
						atualizaLista(tabela,"");
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(rootPane, "OK!");
				}else{					
					JOptionPane.showMessageDialog(rootPane, "Clique primeiro na linha desejada!");
				}				
			}
		});
		btnRemover.setBounds(321, 127, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int linha = tabela.getSelectedRow();
				if(linha != -1){
					String matricula = (String) tabela.getValueAt(linha,0);
					Integer mat = Integer.parseInt(matricula);				
					TabeladePreco objTab = new TabeladePreco();
					try {
						objTab = tabDao.consultarTabeladePrecoID(mat);
						objTab.setNumProtese(mat);
						
						String aux1;
						aux1 = textField_2.getText();
						
						if(aux1.matches("^[0-9]*$")){
						tabDao.atualizarTabeladePreco(objTab);
						}else{
							JOptionPane.showMessageDialog(null, "Digite apenas número no campo de preço!");
						}
					} catch (DaoException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
					atualizaFormulario(objTab);
				}else{
					JOptionPane.showMessageDialog(rootPane, "Clique primeiro na linha desejada!");
				}
			}
		});
		btnAlterar.setBounds(222, 127, 89, 23);
		contentPane.add(btnAlterar);
		

		JLabel lblUsuriosCadastrados = new JLabel("P\u00F3teses Cadastradas");
		lblUsuriosCadastrados.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 14));
		lblUsuriosCadastrados.setBounds(10, 161, 301, 26);
		contentPane.add(lblUsuriosCadastrados);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimpar.setBounds(420, 127, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnEnviarEmail = new JButton("E-mail");
		btnEnviarEmail.setToolTipText("Enviar e-mail para  cliente");
		btnEnviarEmail.setIcon(new ImageIcon(ConTabeladePreco.class.getResource("/br/com/images/icon_email.png")));
		btnEnviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interface enviarEmail;
				enviarEmail = new Interface();
				enviarEmail.setVisible(true);
			}
		});
		btnEnviarEmail.setBounds(463, 327, 111, 23);
		contentPane.add(btnEnviarEmail);
		
		JLabel label = new JLabel("Todos os campos s\u00E3o obrigat\u00F3rios!");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(321, 152, 206, 14);
		contentPane.add(label);
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPesquisar.setBounds(492, 212, 82, 14);
		contentPane.add(lblPesquisar);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Digite o nome da pr\u00F3tese");
		textField_3.setBounds(463, 230, 111, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setToolTipText("Pesquisar protese");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					atualizaLista(tabela, textField_3.getText().toString());
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(492, 261, 66, 23);
		contentPane.add(btnOk);
		
		JLabel lblPreenchaAssimR = new JLabel("Preencha assim: R$010,00 ou R$100,00");
		lblPreenchaAssimR.setForeground(Color.RED);
		lblPreenchaAssimR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreenchaAssimR.setBounds(220, 99, 233, 14);
		contentPane.add(lblPreenchaAssimR);
		}
	
	
	public MaskFormatter Mascara(String Mascara){  
		  
	       MaskFormatter F_Mascara = new MaskFormatter();  
	       try{  
	           F_Mascara.setMask(Mascara); //Atribui a mascara  
	           F_Mascara.setPlaceholderCharacter(' '); //Caracter para preenchimento  
	       }  
	       catch (Exception excecao) {  
	       excecao.printStackTrace();  
	       }  
	       return F_Mascara;  
	    }  
	
	public void atualizaFormulario(TabeladePreco objTab){
		textField_1.setText(objTab.getTipoProtese());
		textField_2.setText(objTab.getPrecoProtese().toString());
		
	/*	String aux = objTab.getPrecoProtese().toString();
		aux = aux.replace(".","");
		textField_2.setText(aux);
		*/
		textField.setText(objTab.getNomeProtese());
		Integer matr = objTab.getNumProtese();
		textField_5.setText(matr.toString());
	}
	
	public void atualizaLista(JTable lista, String nome) throws DaoException{
		
		DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
		
        dtm.setRowCount(0); 
		List<TabeladePreco> listaTab  = new ArrayList<TabeladePreco>();
		listaTab = tabDao.consultarProteses(nome);
 		String dados[] = new String[4]; 
		for (TabeladePreco obj : listaTab) {
			dados[0] = String.valueOf(obj.getNumProtese()) ;
			dados[1] = obj.getNomeProtese();
			dados[2] = obj.getTipoProtese();
			dados[3] = String.valueOf(obj.getPrecoProtese());
			
			((DefaultTableModel) tabela.getModel()).addRow(dados); 
		} 
		tabela.setDefaultRenderer(Object.class, new TableCellRenderer());
		tabela.repaint();
	}
	
	public void limpaFormulario(){
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
	}
	
	public boolean validarFormulário(){
		boolean result = true;

		if(!ValidacaoUtil.textFieldVazio(textField)){
			JOptionPane.showMessageDialog(null, "Campo Nome Vazio!");
			result = false;
		}
		
		if(!ValidacaoUtil.textFieldVazio(textField_2)){
			JOptionPane.showMessageDialog(null, "Campo Preço Vazio!");
			result = false;
		}
		if(!ValidacaoUtil.textFieldVazio(textField_1)){
			JOptionPane.showMessageDialog(null, "Campo Tipo Vazio!");
			result = false;
		}
		
		return result;
	}

}



