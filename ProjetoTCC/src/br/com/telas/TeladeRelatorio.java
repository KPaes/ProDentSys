package br.com.telas;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumnModel;


import br.com.TableModel.TableCellRenderer;
import br.com.bean.Pedido;

import br.com.dao.RelatorioDao;
import br.com.exception.DaoException;
import br.com.util.MascaraUtil;


public class TeladeRelatorio extends JFrame {
	
	

	 final JPanel lista = new JPanel();
	    final JPanel buttonPanel = new JPanel();
	    private RelatorioDao relatorioDao = new RelatorioDao();
		private static final long serialVersionUID = 1L;				

		private JTable table;
	
		private JTextField textField_1;
		
		

		public TeladeRelatorio() throws DaoException {
			setIconImage(Toolkit.getDefaultToolkit().getImage(CadPedido.class.getResource("/br/com/images/logo_transp.png")));
			setTitle("Relatório dos Pedidos");
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
	        
	    //    textField_1 = new JFormattedTextField(MascaraUtil.setMascara("##/##/####"));
	        try {
				textField_1 = new JFormattedTextField(MascaraUtil.setMaskDateInTf((JFormattedTextField) textField_1));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        textField_1.setToolTipText("Digite a data de entrega");
	        textField_1.setBounds(10, 75, 132, 20);
	        buttonPanel.add(textField_1);
	        textField_1.setColumns(10);
	        
	        JButton btnOk_2 = new JButton("OK");
	        btnOk_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {        		
	        		try {
						atualizaLista(table, textField_1.getText()); //Data de Entrega
					} catch (DaoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        });
	        btnOk_2.setToolTipText("Pesquisar pedidos");
	        btnOk_2.setBounds(35, 102, 67, 23);
	        buttonPanel.add(btnOk_2);
	        
	                  
	                 
	                 
	                 
	                  
	        

	        lista.setBounds(152, 0, 656, 562);
	        getContentPane().add(lista);
	        lista.setLayout(null);
	        
	        JLabel lblFuncionriosCadastrados = new JLabel("Pedidos Agendados");
	        lblFuncionriosCadastrados.setFont(new Font("Kalinga", Font.BOLD, 16));
	        lblFuncionriosCadastrados.setHorizontalAlignment(SwingConstants.CENTER);
	        lblFuncionriosCadastrados.setBackground(Color.WHITE);
	        lblFuncionriosCadastrados.setBounds(10, 11, 612, 29);
	        lista.add(lblFuncionriosCadastrados);
	        

         
	        		
				         
	          
	          table = new JTable();
	          table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	          table.setModel(new DefaultTableModel(
	        		  new Object[][] {
	                  	},
	                  	new String[] {
	                  		"Nº", "Dentista", "Paciente", "Data Pedido", "Data Entrega","Total"
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
	          table.getColumnModel().getColumn(5).setPreferredWidth(100);
	          table.getColumnModel().getColumn(5).setMinWidth(100);

	          table.setBounds(39, 175, 530, 232);
	          atualizaLista(table,"");
	          
	          
	          JScrollPane scrollPane = new JScrollPane();
	          scrollPane.setBounds(10, 51, 636, 473);
	          lista.add(scrollPane);
	          
	                         scrollPane.setViewportView(table);
	                         
	                                                 
	                         	                                      
	                   	                   
	                   
		}
		
		public void atualizaLista(JTable lista, String data) throws DaoException{
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();

	        dtm.setRowCount(0); 
			List<Pedido> listaPedido  = new ArrayList<Pedido>();
	 		listaPedido = relatorioDao.consultarRelatorio(data);
	 		String dados[] = new String[6]; 
			for (Pedido obj : listaPedido) {
				dados[0] = String.valueOf(obj.getNumPed()) ;
				dados[1] = obj.getNomeCliente();
				dados[2] = obj.getNomePaciente();
				dados[3] = obj.getDataPedido();
				dados[4] = obj.getDataEntrega();
				dados[5] = String.valueOf(obj.getTotalPedido());
				((DefaultTableModel) table.getModel()).addRow(dados); 
			} 
			table.setDefaultRenderer(Object.class, new TableCellRenderer());
			table.repaint();
		}

			
		
		
}
	 


