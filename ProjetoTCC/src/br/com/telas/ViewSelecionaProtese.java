package br.com.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.TableModel.ProtesesTableModel;
import br.com.TableModel.TableCellRenderer;
import br.com.bean.ClienteDent;
import br.com.bean.TabeladePreco;
import br.com.dao.ClienteDao;
import br.com.dao.TabeladePrecoDao;
import br.com.exception.DaoException;

public class ViewSelecionaProtese extends JFrame implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	final static JPanel carrinho = new JPanel();

	private TabeladePrecoDao tabDao = new TabeladePrecoDao();
    private Double valorServ;  
    private Double valorProt;  
    private Double pedidoTotal;
	private JTextField textValorProt;  
    private JTextField textValorServ;  
    private JTextField textTotal;  
    private ProtesesTableModel tableModel;  
    TabeladePreco naoSelecionado;
   // private List<Proteses> proteses;        	    
    List<TabeladePreco> listaTab  = new ArrayList<TabeladePreco>();
    private JTable tabela;  
	
    private int requisicao = 0;
	

	/**
	 * 0 = sem requisicao
	 * 1 = requisição vindo da tela folha de pagamento
	 * 2 = requisicao vindo da tela pedido
	 */
	
	public static void main(String[] args) {		
		try {
			new ViewSelecionaProtese(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 * @throws DaoException 
	 */

	public ViewSelecionaProtese(int requisicao) throws DaoException{
		
//		this.requisicao = requisicao;
		initialize();
		setVisible(true);
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() throws DaoException {
		setTitle("Selecionar as Pr\u00F3teses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 587);
		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		
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
                try {
					refreshTable();
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                	        	                
                                                                
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
                                                                                                    			if(requisicao == 1){
                                                                                                    			dispose();
                                                                                                    			Ped.pegaTotalPedido("", textTotal);
                                                                                                    			}
                                                                                                    	}
                                                                                                    });
                                                                                                    btnSalvar.setBounds(289, 522, 89, 23);
                                                          carrinho.setVisible(false);
}
	public void refreshTable() throws DaoException{      
		tabela.setDefaultRenderer(Object.class, new TableCellRenderer());
		listaTab = tabDao.consultarProteses();    
		tableModel.deleteAll();  
		tableModel.insertAll(listaTab);  
   }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	} 
}
