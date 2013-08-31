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

import br.com.bean.TabeladePrecoNair;
import br.com.dao.TabelaNairDao;
import br.com.enviaremail.Interface;
import br.com.exception.DaoException;

import br.com.telas.Principal;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaTabelaNair extends JFrame {
	
	
	/**
	 * 
	 */
	private TabelaNairDao tabDao = new TabelaNairDao();
	private static final long serialVersionUID = 1L;
//	private static final Object F_Mascara = null;
	private JPanel contentPane;
	private JTable tabela;
	private JTextField textField;
	private JTextField textField_1;
	private JFormattedTextField textField_2;
	private JTextField textField_5;

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
	public TelaTabelaNair() throws DaoException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConTabeladePreco.class.getResource("/br/com/images/logo_transp.png")));
		setTitle("Tabela de Pre\u00E7o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 400);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10, 191, 434, 159);
		contentPane.add(scroll);

		
		//dados da tabela
		String[][] dados = new String[][] {
			};
		String[] colunas = new String[] {
				"Nº", "Nome", "Tipo", "Preço", "Enviar Email"
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
             atualizaLista(tabela);
		 
		
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
					textField.setText((String) tabela.getValueAt(linha, 1));
					textField_1.setText((String) tabela.getValueAt(linha, 2));
					textField_2.setText((String) tabela.getValueAt(linha, 3));
				}
				
			}
		});
		
		
		JLabel lblExemploDeUtilizao = new JLabel("Formul\u00E1rio de cadastro");
		lblExemploDeUtilizao.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblExemploDeUtilizao.setBounds(10, 11, 301, 26);
		contentPane.add(lblExemploDeUtilizao);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Arial", Font.BOLD, 12));
		lblNome.setBounds(20, 48, 80, 14);
		contentPane.add(lblNome);
		
		JLabel lblIdade = new JLabel("Tipo");
		lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdade.setFont(new Font("Arial", Font.BOLD, 12));
		lblIdade.setBounds(20, 73, 80, 14);
		contentPane.add(lblIdade);
		
		JLabel lblSexo = new JLabel("Pre\u00E7o");
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
		
		//textField_2 = new JFormattedTextField(Mascara("R$###,##"));
        textField_2 = new JFormattedTextField();
        textField_2.setBounds(110, 96, 100, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")){
					JOptionPane.showMessageDialog(rootPane, "Campo Vazio!");
				}else{
					//String[] novaLinha = new String[]{textField.getText(),textField_1.getText(),textField_2.getText()};
				//	DefaultTableModel model = (DefaultTableModel)tabela.getModel();
					//model.addRow(novaLinha);
					/*JOptionPane.showMessageDialog(rootPane, "OK!");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");*/
				
					TabeladePrecoNair obj = new TabeladePrecoNair();
							
							obj.setNomeProtese(textField.getText());
							obj.setTipoProtese(textField_1.getText());
							obj.setPrecoProtese(Double.parseDouble(textField_2.getText()));
							TabelaNairDao objDAO = new TabelaNairDao();
							try {
								
								if(textField_5.getText().equals("")){
								textField_5.getText();
								//.equals("")){
									objDAO.inserirTabeladePreco(obj);
									
									JOptionPane.showMessageDialog(rootPane, "Dados salvos com sucesso!");							
								}else{
									Integer matr = Integer.parseInt(textField_5.getText()); 
									obj.setNumProtese(matr);
									objDAO.atualizarTabeladePreco(obj);
									JOptionPane.showMessageDialog(rootPane, "Dados atualizados com sucesso!");
								}							
								//atualizaLista(tabela);
							} catch (DaoException e) {
								e.printStackTrace();
							}
			}
			}
		});
		btnNewButton.setBounds(110, 127, 100, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DefaultTableModel model = (DefaultTableModel)tabela.getModel();
				int numLinha  = tabela.getSelectedRow();
				String matricula = (String) tabela.getValueAt(numLinha,0);
				Integer mat = Integer.parseInt(matricula);
				if(numLinha != -1){
					//model.removeRow(numLinha);
					try {
						tabDao.excluirTabeladePreco(mat);
						atualizaLista(tabela);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										
					/*textField.setText("");
					textField_1.setText("");
					textField_2.setText("");*/
					JOptionPane.showMessageDialog(rootPane, "OK!");
				}else{
					JOptionPane.showMessageDialog(rootPane, "Selecione a linha primeiro!");
				}
			}
		});
		btnRemover.setBounds(321, 127, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int linha = tabela.getSelectedRow();
				String matricula = (String) tabela.getValueAt(linha,0);
				Integer mat = Integer.parseInt(matricula);
				//DefaultTableModel model = (DefaultTableModel)tabela.getModel();
				TabeladePrecoNair objTab = new TabeladePrecoNair();
				try {
					objTab = tabDao.consultarTabeladePrecoID(mat);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				atualizaFormulario(objTab);
				/*int linha = tabela.getSelectedRow();
				if(linha != -1){
					model.setValueAt(textField.getText(), linha, 0);
					model.setValueAt(textField_1.getText(), linha, 1);
					model.setValueAt(textField_2.getText(), linha, 2);
					JOptionPane.showMessageDialog(rootPane, "OK!");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					tabela.clearSelection();
				}else{
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha primeiro!");
				}
			}
		};*/
		
		//}
		/*	 // model.setRowCount(0); 
		List<TabeladePreco> listaTabela  = new ArrayList<TabeladePreco>();
		listaTabela = tabDao.consultarPagamento();
 		String dados[] = new String[4]; 
		for (TabeladePreco obj : listaTabela) {
			dados[0] = String.valueOf(obj.getNumProt()) ;
			dados[1] = obj.getNomeProt();
			dados[2] = obj.getTipoProt();
			dados[3] = obj.getPrecoProt();
			((DefaultTableModel) tabela.getModel()).addRow(dados); 
		} 
		table.repaint();
		}*/
			}
		});
		btnAlterar.setBounds(222, 127, 89, 23);
		contentPane.add(btnAlterar);
		
		
		
		JLabel lblUsuriosCadastrados = new JLabel("P\u00F3teses Cadastradas");
		lblUsuriosCadastrados.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblUsuriosCadastrados.setBounds(10, 161, 301, 26);
		contentPane.add(lblUsuriosCadastrados);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaFormulario();
			}
		});
		btnLimpar.setBounds(419, 127, 89, 23);
		contentPane.add(btnLimpar);
		
	
	JButton btnEnviarEmail = new JButton("E-mail");
	btnEnviarEmail.setIcon(new ImageIcon(TelaTabelaNair.class.getResource("/br/com/images/icon_email.png")));
	btnEnviarEmail.setToolTipText("Enviar E-mail");
	btnEnviarEmail.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Interface enviarEmail;
			enviarEmail = new Interface();
			enviarEmail.setVisible(true);
		}
	});
	btnEnviarEmail.setBounds(463, 266, 111, 23);
	contentPane.add(btnEnviarEmail);
	
	JLabel label = new JLabel("Todos os campos s\u00E3o obrigat\u00F3rios!");
	label.setForeground(Color.RED);
	label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	label.setBounds(368, 99, 206, 14);
	contentPane.add(label);
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
	
	public void atualizaFormulario(TabeladePrecoNair objTab){
		textField_1.setText(objTab.getTipoProtese());
		textField_2.setText(objTab.getPrecoProtese().toString());
		textField.setText(objTab.getNomeProtese());
		Integer matr = objTab.getNumProtese();
		textField_5.setText(matr.toString());
	}
	
	public void atualizaLista(JTable lista) throws DaoException{
		
		DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
		
        dtm.setRowCount(0); 
		List<TabeladePrecoNair> listaTab  = new ArrayList<TabeladePrecoNair>();
		listaTab = tabDao.consultarProteses();
 		String dados[] = new String[4]; 
		for (TabeladePrecoNair obj : listaTab) {
			dados[0] = String.valueOf(obj.getNumProtese()) ;
			dados[1] = obj.getNomeProtese();
			dados[2] = obj.getTipoProtese();
			dados[3] = String.valueOf(obj.getPrecoProtese());
			
			((DefaultTableModel) tabela.getModel()).addRow(dados); 
		} 
		tabela.repaint();
	}
	
	public void limpaFormulario(){
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
	}
}
	
//}


