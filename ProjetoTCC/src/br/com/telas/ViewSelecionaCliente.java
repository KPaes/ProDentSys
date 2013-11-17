package br.com.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTitledPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import org.jdesktop.swingx.JXSearchField;

import br.com.TableModel.TableCellRenderer;
import br.com.bean.ClienteDent;
import br.com.dao.ClienteDao;
import br.com.enviaremail.Interface;
import br.com.exception.DaoException;

import javax.swing.JScrollPane;

public class ViewSelecionaCliente extends JDialog implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	ClienteDao clienteDao = new ClienteDao();
	private int requisicao = 0;
	private JTable table;
	private JXSearchField txtPesq;
	private JButton btnOk;
	private int num;
	
	/**
	 * 0 = sem requisicao
	 * 1 = requisição vindo da tela folha de pagamento
	 * 2 = requisicao vindo da tela pedido
	 */
	
	public static void main(String[] args) {
//		try {
//			ViewSelecionaFuncionario dialog = new ViewSelecionaFuncionario();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			new ViewSelecionaCliente(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 * @throws DaoException 
	 */

	public ViewSelecionaCliente(int requisicao){
		this.requisicao = requisicao;
		try {
			initialize();
//			buscar();
			setVisible(true);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initialize() throws DaoException {
		setTitle("Selecione um Cliente");
		setBounds(100, 100, 491, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JXTitledPanel titledPanel = new JXTitledPanel();
		titledPanel.getContentContainer().setLayout(null);
		
		txtPesq = new JXSearchField();
		txtPesq.addKeyListener(this);
		txtPesq.setPrompt("Digite o nome do cliente");
		txtPesq.setBounds(119, 22, 349, 22);
		titledPanel.getContentContainer().add(txtPesq);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 458, 204);
		titledPanel.getContentContainer().add(scrollPane);
		titledPanel.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		titledPanel.setTitle("Selecione um Cliente");
		titledPanel.setBounds(0, 0, 482, 300);
		contentPanel.add(titledPanel);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Número", "Nome", "CPF"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(1).setPreferredWidth(189);
			scrollPane.setViewportView(table);
			atualizaTable(table,"");
			
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("OK");
				btnOk.setActionCommand("OK");
				btnOk.addActionListener(this);
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	public void atualizaTable(JTable lista, String nome) throws DaoException{
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				
        dtm.setRowCount(0); 
		List<ClienteDent> listaCliente  = new ArrayList<ClienteDent>();
 		listaCliente = clienteDao.consultarClientes(nome);
 		String dados[] = new String[3]; 
		for (ClienteDent obj : listaCliente) {
			dados[0] = String.valueOf(obj.getNumCliente());
			dados[1] = obj.getNomeCliente();
			dados[2] = obj.getCpfCliente();
			
			((DefaultTableModel) table.getModel()).addRow(dados); 
		} 
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		table.repaint();
	}
	
	public void retornarRequisicao(){
		int row = table.getSelectedRow();
		try{
			if(row != -1){
				ClienteDent func = new ClienteDao().consultarClienteID(Integer.parseInt((String) table.getValueAt(row, 0)));
				if(func != null){
					if(this.requisicao == 1){
						dispose();
						num = func.getNumCliente();
						Ped.chamaCliente(num);
					}
					else if(this.requisicao == 2){
						dispose();
						num = func.getNumCliente();
						Interface.chamaCliente(num);
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "Nenhum registro selecionado!");
			}
		}catch(DaoException e){
			e.printStackTrace();
		}
}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent event) {
		if(event.getSource() == txtPesq){
			try {
				atualizaTable(table, txtPesq.getText().toString());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnOk){
			retornarRequisicao();
		}
		
	}
}
